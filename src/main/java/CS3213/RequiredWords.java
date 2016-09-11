package CS3213;

import java.util.HashSet;

/**
 * Implements Singleton pattern.
 */
public class RequiredWords {
    private static final RequiredWords _instance = new RequiredWords();
    private HashSet<String> requiredWords;
    private RequiredWords() {
        requiredWords = new HashSet<String>();
    }

    public static RequiredWords getInstance() {
        return _instance;
    }


    public void addRequiredWord(String word) {
        requiredWords.add(word);
    }

    public void removeRequiredWord(String word) {
        requiredWords.remove(word);
    }

    /**
     * Removes all the words stored.
     */
    public void removeAllRequiredWords() {
        assert(null != this.requiredWords);
        this.requiredWords.clear();
    }

    public boolean isWordRequired(String word) {
        return requiredWords.contains(word);
    }
}
