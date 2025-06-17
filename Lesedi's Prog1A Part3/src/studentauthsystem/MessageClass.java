/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentauthsystem;

import java.util.Random;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Lesedi
 */
public class MessageClass {
    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private int numberOfMessages;
    
    // Constructor for testing and convenience

    
    // using the set method for the message ID
    public void setMessageID(String messageID){
        this.messageID= messageID;
    }
    // using the get method for message ID
    public String getMessageID(){
        return messageID;
    }
    // using the set method for the recipient cell
    public void setRecipient(String recipient){
        this.recipient= recipient;
    }
    // using the get method for the recipient cell
    public String getRecipient(){
        return recipient;
    }
    // using the set method for the message
    public void setMessage(String message){
        this.message= message;
    }
    // using the get method for the message
    public String getMessage(){
        return message;
    }
    // using the set method for the message hash
    public void setMessageHash(String messageHash){
        this.messageHash= messageHash;
    }
    // using the get method for the message hash
    public String getMessageHash(){
        return messageHash;
    }
    // using the set method for the number of messages sent
    public void setNumberofMessages(int numberofMessages){
        this.numberOfMessages= numberofMessages;
    }
    // using the get method for the number of messages sent
    public int getNumberofMessage(){
        return numberOfMessages;
    }
    // a method to check the recipient's cell number
    public boolean checkRecipientCell(){
        if(recipient==null){
            return false;
        }
        String clean = recipient.trim().replaceAll("\\s+","");
        
        JOptionPane.showMessageDialog(null, "Checking recipient:\n" + clean + "\nLength: " + clean.length());
        
        if(!clean.startsWith("+27")){
            return false;
        }
        return clean.length()<=13;
    }
    // a method to check if message is 250 charecters or not
    public boolean checkMessageLenght(){
        if(message==null){
            return false;
        }
        return message.length() <= 250;
    }
    // a method that tracks the random 10 digit number for retrieving/deleting messages
    public void createMessageID(){
        Random myRandom= new Random();
        messageID= ""; // an empty string
         for (int i = 0; i < 10; i++) {
        int digit = myRandom.nextInt(10); 
        messageID += digit;
        }
     }
    // a method that creates the message hash
     public void createMessageHash(){
         // splitting the message into words
         String[] words= message.split(" ");
         // the first word in the message
         String firstWord = words[0];
         // the last word in the message
         String lastWord = words[words.length -1];
         // using the first two numbers of the message, the first & last name in capital letters
          messageHash = messageID.substring(0, 2) + ":" + numberOfMessages + ":" 
                  + firstWord.toUpperCase() + lastWord.toUpperCase();
     }
      // a method that allows the user to send/store and discard messages 
     public String sendMessage(String option) {
        return switch (option.toLowerCase()) {
            case "send" -> "Message successfully sent.";
            case "store" -> { saveToJSON();           //ChatGpt: added a JSON FILE to store the messages 
               yield  "Message successfully stored.";
            }
            case "discard" -> " The message is deleted.";
            default -> "Invalid option.";
        };
    }
       // a method that returns the messages the user sends while the program runs
     public String printMessages(){
         return" messageID: "+ messageID+ "\nMessage Hash" + messageHash+ "\nRecipient"+ recipient;
     }
     //saves the messages the user sends in a json file
     // ChatGPT
     public void saveToJSON() {
         try {
        JSONObject json = new JSONObject();
        json.put("messageID", messageID);
        json.put("recipient", recipient);
        json.put("message", message);
        json.put("messageHash", messageHash);

        
        try (FileWriter writer = new FileWriter("messages.json", true)) {
            writer.write(json.toString() + System.lineSeparator());  // one message per line
        }

        JOptionPane.showMessageDialog(null, "Message stored in messages.json");

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, " Error saving message: " + e.getMessage());
    }
     }
     
    @Override
    public String toString() {
    return "ID: " + messageID +
           "\nHash: " + messageHash +
           "\nRecipient: " + recipient +
           "\nMessage: " + message;
}

    
}



   

    






      
