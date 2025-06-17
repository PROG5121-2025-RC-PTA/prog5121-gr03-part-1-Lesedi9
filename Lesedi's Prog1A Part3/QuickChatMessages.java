/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentauthsystem;
import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * @author Lesedi
 */
public class QuickChatMessages {
    //using arraylists of the message class
    ArrayList<MessageClass> sentMessage= new ArrayList<>();
    ArrayList<MessageClass>disregardedMessages= new ArrayList<>();
    ArrayList<MessageClass>storedMessages= new ArrayList<>();
    
    
    // using the add method for the sent messages to the list
    public void addSentMessages(MessageClass message){
        // storing the message that is sent by the user
        sentMessage.add(message);   
    }
      // using the add method for the disregarded messages in the list
      public void addDisregardedMessages(MessageClass message){
         disregardedMessages.add(message);    
    }
      // using the add method for the stored messages in the list
      public void addStoredMessages(MessageClass message){
          storedMessages.add(message);
    }
      
      // the method that shows the messages sent by the sender and the user
      public void showSentMessage(){
          // checks if the user has not sent any messages
          if(sentMessage.isEmpty()){
              JOptionPane.showMessageDialog(null,"you did not send any message");
              return;
          }
           String showmessage= "Sent Message:\n";   // showing the messages 
           // using a for loop for the sent messages
           for(int i=0; i<sentMessage.size(); i++){
               MessageClass message= sentMessage.get(i);  // using the get method for the message at the specified index
               //using the concatenation method
               showmessage= showmessage.concat("ID:").concat(message.getMessageID()).concat("\n");
               showmessage= showmessage.concat("Hash:").concat(message.getMessageHash()).concat("\n");
               showmessage= showmessage.concat("Recipient:").concat(message.getRecipient()).concat("\n");
               showmessage= showmessage.concat("Message:").concat(message.getMessage()).concat("\n");
           }
           JOptionPane.showMessageDialog(null, showmessage);
      }
      
      // the method that will display the longest message
      public void showtheLongestMessage(){
          if(sentMessage.isEmpty()){
              JOptionPane.showMessageDialog(null, "There are no messages to check.");
            return;
        }

        MessageClass longest = sentMessage.get(0);  // the get method will retrieve the message at index 0
        for (int i = 1; i < sentMessage.size(); i++) {
           
            if (sentMessage.get(i).getMessage().length() > longest.getMessage().length()) {
                longest = sentMessage.get(i);
            }
        }
        JOptionPane.showMessageDialog(null, "Longest Message:\n" + longest.toString());
     }
      
      // the method that searches the message for a message ID
    public void searchtheMessageID(String id){
        for(int i=0; i< sentMessage.size(); i++){
            if(sentMessage.get(i).getMessageID().equals(id)){
                JOptionPane.showMessageDialog(null,sentMessage.get(i).toString() + "The message has been found:\n");
                return;
        }
        }
             JOptionPane.showMessageDialog(null,"The message ID has not been found");
    }
      
     // the method that shows the stored messages
    public void showStoredMessages() {
        if (storedMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no stored messages");
            return;
        }
        
        String showmessage = "Stored Messages:\n";
         // using a for loop for the stored messages
        for (int i = 0; i < storedMessages.size(); i++) {
            MessageClass message = storedMessages.get(i);
            //using the concatenation method
               showmessage= showmessage.concat("ID:").concat(message.getMessageID()).concat("\n");
               showmessage= showmessage.concat("Hash:").concat(message.getMessageHash()).concat("\n");
               showmessage= showmessage.concat("Recipient:").concat(message.getRecipient()).concat("\n");
               showmessage= showmessage.concat("Message:").concat(message.getMessage()).concat("n\n"); 
        }
           JOptionPane.showMessageDialog(null, showmessage);
       }    
  
    // the method that searches for the recpient's messages
    public void searchtheRecipient(String recipient){
        String results= ""; //an empty string
        for(int i=0; i<sentMessage.size(); i++){
            if(sentMessage.get(i).getRecipient().equals(recipient)){
                JOptionPane.showMessageDialog(null, sentMessage.get(i).toString().concat("\n"));
            }
        }
        if(results.isEmpty()){
            JOptionPane.showMessageDialog(null,"There are no messages found from the recipient");
            }
        else{
            JOptionPane.showMessageDialog(null, results);
        }
    }
    // the method that deletes a message using the message hash
    public void deleteMessagewithMessageHash(String hash){
        for(int i=0; i<sentMessage.size(); i++){
            if(sentMessage.get(i).getMessageHash().equals(hash)){
                MessageClass removed= sentMessage.remove(i); // using the remove method to delete the message
                JOptionPane.showMessageDialog(null,"The message deleted:\n" + removed.toString()); 
                return;
        }
}
      JOptionPane.showMessageDialog(null,"The message hash is not found");
    }
    // displaying the full report consisting of the details of the sent messages
    public void showFullReport(){
        if(sentMessage.isEmpty()){
            JOptionPane.showMessageDialog(null, "There are no messages in the report");
            return;
        }
        String report= "The full message report:\n";
        for(int i=0; i<sentMessage.size(); i++){
            MessageClass message= sentMessage.get(i);
            report= report.concat("Hash:").concat(message.getMessageHash()).concat("\n");
            report= report.concat("Recipient:").concat(message.getRecipient()).concat("\n");
            report= report.concat("Message:").concat(message.getMessage()).concat("\n");
        }
          JOptionPane.showMessageDialog(null, report);
}

    String testShowSentMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String testDeleteMessagewithMessageHash(String delme) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

   


