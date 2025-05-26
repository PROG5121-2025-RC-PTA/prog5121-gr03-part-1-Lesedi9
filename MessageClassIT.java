/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package studentauthsystem;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageClassIT {

    @Test
    public void testCheckRecipientCell_valid() {
        MessageClass message = new MessageClass();
        message.setRecipient("+27821234567");
        assertTrue(message.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_invalid() {
        MessageClass message = new MessageClass();
        message.setRecipient("0821234567");  // Missing +27
        assertFalse(message.checkRecipientCell());
    }

    @Test
    public void testCheckMessageLength_valid() {
        MessageClass message = new MessageClass();
        message.setMessage("This is a short message.");
        assertTrue(message.checkMessageLenght());
    }

    @Test
    public void testCheckMessageLength_invalid() {
        MessageClass message = new MessageClass();
        message.setMessage("A".repeat(300));  
        assertFalse(message.checkMessageLenght());
    }

    @Test
    public void testSendMessage_send() {
        MessageClass message = new MessageClass();
        String result = message.sendMessage("send");
        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testSendMessage_store() {
        MessageClass message = new MessageClass();
        message.setRecipient("+27821234567");
        message.setMessage("Test store message");
        message.setNumberofMessages(1);
        message.createMessageID();
        message.createMessageHash();
        String result = message.sendMessage("store");
        assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testSendMessage_discard() {
        MessageClass message = new MessageClass();
        String result = message.sendMessage("discard");
        assertEquals("Press 0 to delete message.", result);
    }

    @Test
    public void testSendMessage_invalidOption() {
        MessageClass message = new MessageClass();
        String result = message.sendMessage("unknown");
        assertEquals("Invalid option.", result);
    }

    @Test
    public void testCreateMessageID_length() {
        MessageClass message = new MessageClass();
        message.createMessageID();
        assertEquals(10, message.getMessageID().length());
    }

    @Test
    public void testCreateMessageHash_format() {
        MessageClass message = new MessageClass();
        message.setMessage("Hello student");
        message.setNumberofMessages(1);
        message.createMessageID();
        message.createMessageHash();
        String hash = message.getMessageHash();
        assertTrue(hash.contains(":1:HELLOSTUDENT"));
    }
}