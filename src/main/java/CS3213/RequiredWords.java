package CS3213;

import java.util.HashSet;

/**
 * Implements Singleton pattern.
 */
public class RequiredWords {
    private static final RequiredWords _instance = new RequiredWords();
    private HashSet<String> requiredWords;
    // Record whether the user is using the optional feature of required words
    // If not, we do not apply required words filter and maintain the original behavior
    private boolean applyRequiredWords = false;

    private RequiredWords() {
        requiredWords = new HashSet<String>();
    }

    public static RequiredWords getInstance() {
        return _instance;
    }

    public void activateRequiredWordFilter() {
        applyRequiredWords = true;
    }

    public boolean isRequiredWordFilterActive() {
        return applyRequiredWords;
    }

    public void addRequiredWord(String word) {
        requiredWords.add(word.toLowerCase());
    }

    public void removeRequiredWord(String word) {
        requiredWords.remove(word.toLowerCase());
    }

    public boolean isWordRequired(String word) {
        return requiredWords.contains(word.toLowerCase());
    }
}
