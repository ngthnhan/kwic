package CS3213;

/**
 * Implements Singleton pattern.
 */
public class RequiredWords {
    private static final RequiredWords _instance = new RequiredWords();
    private RequiredWords() {
        // TODO: Implements initialization
    }

    public static RequiredWords getInstance() {
        return _instance;
    }


    public void addRequiredWord(String word) {
        // TODO: Implement
    }

    public void removeRequiredWord(String word) {
        // TODO: Implement
    }

    public boolean isWordRequired(String word) {
        // TODO: Implement
        return false;
    }
}
