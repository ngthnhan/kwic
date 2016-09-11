package test;

import CS3213.RequiredWords;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Tests the functionality of RequiredWords.
 * @author nhan.
 */
public class RequiredWordsTest {
    private RequiredWords requiredWords = RequiredWords.getInstance();

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        this.requiredWords = RequiredWords.getInstance();
    }

    /**
     * Cleans up.
     */
    @After
    public void cleanUp() {
        // Remove all the words inside the singleton
        // Note: This is why I hold personal grudge against Singleton (and who use them) because it is a
        // nightmare to test
        this.requiredWords.removeAllRequiredWords();
    }

    /**
     * Tests that adding required words to RequiredWords work.
     */
    @Test
    public void testAddRequiredWords() {
        // Arrange
        String word1 = "hello";
        String word2 = "world!";

        // Act
        requiredWords.addRequiredWord(word1);
        requiredWords.addRequiredWord(word2);

        // Assert
        assertTrue(requiredWords.isWordRequired(word1));
        assertTrue(requiredWords.isWordRequired(word2));

        // Negative assert
        assertFalse(requiredWords.isWordRequired("Goodbye"));
        assertFalse(requiredWords.isWordRequired("hell"));
    }

    /**
     * Tests that removing required words in RequiredWords work.
     */
    @Test
    public void testRemoveRequiredWords() {
        // Arrange
        String word1 = "hello";
        String word2 = "world!";

        // Act
        requiredWords.addRequiredWord(word1);
        assertTrue(requiredWords.isWordRequired(word1));

        requiredWords.addRequiredWord(word2);
        assertTrue(requiredWords.isWordRequired(word2));

        requiredWords.removeRequiredWord(word1);
        requiredWords.removeRequiredWord(word2);

        // Assert
        assertFalse(requiredWords.isWordRequired(word1));
        assertFalse(requiredWords.isWordRequired(word2));
    }
}
