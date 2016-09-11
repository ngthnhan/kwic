package test;

import CS3213.RequiredWords;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author nhan.
 */
public class RequiredWordsTest {

    @Test
    public void testAddRequiredWords() {
        // Arrange
        RequiredWords requiredWords = RequiredWords.getInstance();
        String word1 = "hello";
        String word2 = "world!";

        // Act
        requiredWords.addRequiredWord(word1);
        requiredWords.addRequiredWord(word2);

        // Assert
        assertTrue(requiredWords.isWordRequired(word1));
        assertTrue(requiredWords.isWordRequired(word2));
    }
}
