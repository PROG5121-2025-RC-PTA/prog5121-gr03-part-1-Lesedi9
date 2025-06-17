/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentauthsystem;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;


/**
 *
 * @author Lesedi
 */
public class QuickChatApp {

     
    
    public static void start(String username) {
    JOptionPane.showMessageDialog(null, "Welcome to QuickChat");

    int messageSent=0;  // counts the messages the user sends
    // part 3
    QuickChatMessages manager= new QuickChatMessages();   // an instance of the quickchat message 
    
    try {
        // asks the user how many messages they want to send
        int total = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of messages you want to send:"));

        for (int i = 0; i < total; i++) {
            MessageClass message = new MessageClass();
          //checks and validates the recipient's cell number
            boolean validRecipient = false;
            while (!validRecipient) {
                String recipient = JOptionPane.showInputDialog("Enter the recipient number (+27...):");
                if (recipient == null) return;
                message.setRecipient(recipient);
                validRecipient = message.checkRecipientCell();
                if (!validRecipient) {
                    JOptionPane.showMessageDialog(null, "Recipient must start with +27 and be no more than 13 characters.");
                }
            }
             //checking and validating the messages
            boolean validMessage = false;
            while (!validMessage) {
                String text = JOptionPane.showInputDialog("Enter your message (≤ 250 characters):");
                if (text == null) return;
                message.setMessage(text);
                validMessage = message.checkMessageLenght(); 
                if (!validMessage) {
                    JOptionPane.showMessageDialog(null, "Message too long. Max is 250 characters.");
                }
            }
              // creating the message hash and messageID
            message.setNumberofMessages(i+1);
            message.createMessageID();
            message.createMessageHash();
                 //shows the message summary
            String summary = "Message ID: " + message.getMessageID() +
                             "\nHash: " + message.getMessageHash() +
                             "\nRecipient: " + message.getRecipient() +
                             "\nMessage: " + message.getMessage();

            JOptionPane.showMessageDialog(null, summary);
            
            // promts the user to select which action to choose after sending the messages
            String action = JOptionPane.showInputDialog("Action: send / store / discard");
            if (action == null) return;
            action = action.trim().toLowerCase();
            JOptionPane.showMessageDialog(null, message.sendMessage(action));
            
            
            if (action.equals("send")) {
                manager.addSentMessages(message);
                messageSent++;    
            } else if (action.equals("store")) {
                manager.addStoredMessages(message);
                message.saveToJSON();       //chatGPT
            }
            else if(action.equals("discard")){
                manager.addDisregardedMessages(message);
            }
            else{
                JOptionPane.showMessageDialog(null,"an incorrect action.Messageignored");
            } 
        }
            // Show number of sent messages
            JOptionPane.showMessageDialog(null, "The total messages sent: " + messageSent);

            // Part 3
          
            boolean menu = true;
            while (menu) {
                String[] options = {
                        "a. Show the sent messages",
                        "b. Show the longest message",
                        "c. Search using the message ID",
                        "d. Search the message using the recipient number",
                        "e. Delete the message using the message hash",
                        "f. Show the full report",
                        "g.  Show the stored messages ",
                        "h. Exit",
                };

                String choice = (String) JOptionPane.showInputDialog(null, "Choose an option:", "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                   // using if statements for the choice the user wants to choose
                if (choice == null || choice.startsWith("g")) {
                    menu = false; // Exiting the menu
                } else if (choice.startsWith("a")) {
                    manager.showSentMessage();
                } else if (choice.startsWith("b")) {
                    manager.showtheLongestMessage();
                } else if (choice.startsWith("c")) {
                    String id = JOptionPane.showInputDialog("Enter Message ID:");
                    manager.searchtheMessageID(id);
                } else if (choice.startsWith("d")) {
                    String recipient = JOptionPane.showInputDialog("Enter recipient number:");
                    manager.searchtheRecipient(recipient);
                } else if (choice.startsWith("e")) {
                    String hash = JOptionPane.showInputDialog("Enter message hash to delete:");
                    manager.deleteMessagewithMessageHash(hash); 
                } else if (choice.startsWith("f")) {
                    manager.showFullReport();
                }
                else if(choice.startsWith("g")){
                    manager.showStoredMessages();
                    readStoredMessagesFromFile();
                }
            }
            //displays the number of messages sent
              JOptionPane.showMessageDialog(null, "The total messages sent:"+ messageSent);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid number of messages entered.");
    }
    }
  
    // ChatGPT: to assist in reading the files stored in the JSON File 
    public static void readStoredMessagesFromFile(){
        File file = new File("stored_messages.json");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "No stored messages found.");
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder formatted = new StringBuilder("Stored Messages:\n\n");

            while ((line = reader.readLine()) != null) {
                JSONObject json = new JSONObject(line);
                formatted.append("ID: ").append(json.getString("messageID")).append("\n");
                formatted.append("Hash: ").append(json.getString("messageHash")).append("\n");
                formatted.append("Recipient: ").append(json.getString("recipient")).append("\n");
                formatted.append("Message: ").append(json.getString("message")).append("n\n");
            }

            reader.close();
            JOptionPane.showMessageDialog(null, formatted.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading stored messages: " + e.getMessage());
        } catch (org.json.JSONException je) {
            JOptionPane.showMessageDialog(null, "Error parsing JSON: " + je.getMessage());
        }
           
        }
    }


            
    

