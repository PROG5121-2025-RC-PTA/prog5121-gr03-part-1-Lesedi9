/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentauthsystem;
import javax.swing.*;
/**
 *
 * @author Lesedi
 */
public class QuickChatApp {

     
    
    public static void start(String username) {
    JOptionPane.showMessageDialog(null, "Welcome to QuickChat");

    int messageSent=0;  // counts the messages the user sends
    
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
                messageSent++;    
            } else if (action.equals("store")) {
                message.saveToJSON();             //chatGPT
            }
            
        }
            //displays the number of messages sent
              JOptionPane.showMessageDialog(null, "The total messages sent:"+ messageSent);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid number of messages entered.");
    }
}  
    
}
