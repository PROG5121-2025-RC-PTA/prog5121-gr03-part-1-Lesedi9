/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package studentauthsystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lesedi
 */
public class QuickChatMessagesIT {
    
    
    @Test
    public void testShowSentMessage() {
        QuickChatMessages qcm = new QuickChatMessages();
        qcm.addSentMessages(new MessageClass("1", "01:0:DIDCAKE", "+27834557896", "Did you get the cake?"));

        String result = qcm.testShowSentMessage();
        assertTrue(result.contains("Did you get the cake?"));
        assertTrue(result.contains("ID:1"));
    }

    /**
     * Test of showtheLongestMessage method, of class QuickChatMessages.
     */
    @Test
    public void testShowtheLongestMessage() {
        QuickChatMessages qcm = new QuickChatMessages();
        qcm.addSentMessages(new MessageClass("3", "03:1:SHORT", "+27834484567", "Hi"));
        qcm.addSentMessages(new MessageClass("4", "04:1:LONG", "0838884567", "Where are you? You are late! I have asked you to be on time."));

        String result = qcm.testShowtheLongestMessage();
        assertTrue(result.contains("Longest Message:"));
        assertTrue(result.contains("Where are you? You are late!"));
    }

    /**
     * Test of searchtheMessageID method, of class QuickChatMessages.
     */
    @Test
    public void testSearchtheMessageID() {
        QuickChatMessages qcm = new QuickChatMessages();
        qcm.addSentMessages(new MessageClass("0838884567", "04:1:HASH", "Developer", "It is dinner time !"));

        String result = qcm.testSearchtheMessageID("0838884567");
        assertTrue(result.contains("It is dinner time !"));
        assertTrue(result.contains("The message has been found:"));
    }

    /**
     * Test of showStoredMessages method, of class QuickChatMessages.
     */
    @Test
    public void testShowStoredMessages() {
        QuickChatMessages qcm = new QuickChatMessages();
        qcm.addStoredMessages(new MessageClass("2", "02:1:STORED", "+27838884567", "Ok, I am leaving without you."));

        String result = qcm.testShowStoredMessages();
        assertTrue(result.contains("Stored Messages:"));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    /**
     * Test of searchtheRecipient method, of class QuickChatMessages.
     */
    @Test
    public void testSearchtheRecipient() {
       QuickChatMessages qcm = new QuickChatMessages();
        qcm.addSentMessages(new MessageClass("5", "05:1:REC1", "+27838884567", "Where are you?"));
        qcm.addSentMessages(new MessageClass("6", "06:1:REC2", "+27838884567", "I’m leaving without you."));

        String result = qcm.testSearchtheRecipient("+27838884567");
        assertTrue(result.contains("Where are you?"));
        assertTrue(result.contains("I’m leaving without you."));
    }

    /**
     * Test of deleteMessagewithMessageHash method, of class QuickChatMessages.
     */
    @Test
    public void testDeleteMessagewithMessageHash() {
        QuickChatMessages qcm = new QuickChatMessages();
        qcm.addSentMessages(new MessageClass("7", "07:1:DELME", "+27830000000", "Delete this"));

        String result = qcm.testDeleteMessagewithMessageHash("07:1:DELME");
        assertTrue(result.contains("The message deleted:"));
        assertTrue(result.contains("Delete this"));
    }

    /**
     * Test of showFullReport method, of class QuickChatMessages.
     */
    @Test
    public void testShowFullReport() {
         QuickChatMessages qcm = new QuickChatMessages();
        qcm.addSentMessages(new MessageClass("8", "08:1:HASH", "+27830000001", "Report message"));

        String result = qcm.testShowFullReport();
        assertTrue(result.contains("Hash:08:1:HASH"));
        assertTrue(result.contains("Recipient:+27830000001"));
        assertTrue(result.contains("Message:Report message"));
    }
    
}
