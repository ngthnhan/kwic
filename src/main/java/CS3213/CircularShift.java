package CS3213;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * This class is to generate a list of all circular shifts based on the string given.
 */
public class CircularShift {
    public static String DELIMITER = " ";
    private String _line;
    private WordsToIgnore _wordsToIgnore;
    private RequiredWords _requiredWords;

    /**
     * input should not be null
     * @param line
     */
    public CircularShift(String line) {
        assert(line != null);
        this._line = line.toLowerCase();
        this._wordsToIgnore = WordsToIgnore.getWordsToIgnore();
        this._requiredWords = RequiredWords.getInstance();
    }

    public String[] getCircularShifts() {
        String[] words = this._line.split(DELIMITER);
        String[] shifts = new String[words.length];
        shifts[0] = this._line;

        for (int i=1;i<words.length;i++) {
            shifts[i] = this.getShiftedLine(i, words);
        }

        String[] filteredByIgnoreShifts = getShiftsWithoutIgnoredWordLeading(shifts);
        String[] filteredByRequireShifts = filteredByIgnoreShifts;
        if(_requiredWords.isRequiredWordFilterActive()) {
            filteredByRequireShifts = getShiftsWithRequiredWordLeading(filteredByIgnoreShifts);
        }
        for (int i=0;i<filteredByRequireShifts.length;i++) {
            filteredByRequireShifts[i] = capitalizeWordsNotIgnoredInShift(filteredByRequireShifts[i]);
        }

        return filteredByRequireShifts;
    }

    private String getShiftedLine(int index, String[] words) {
        StringBuilder builder = new StringBuilder();

        for (int i=index;i<words.length;i++) {
            builder.append(words[i]);
            builder.append(DELIMITER);
        }
        for (int i=0;i<index;i++) {
            builder.append(words[i]);
            builder.append(DELIMITER);
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

    private String[] getShiftsWithoutIgnoredWordLeading(String[] shifts) {
        List<String> shiftList = new ArrayList<String>(Arrays.asList(shifts));

        Iterator<String> iter = shiftList.iterator();
        while (iter.hasNext()) {
            if (isShiftStartingWithIgnoredWord(iter.next())) {
                iter.remove();
            }
        }

        return shiftList.toArray(new String[shiftList.size()]);
    }


    private boolean isShiftStartingWithIgnoredWord(String line) {
        String firstWord = line.split(DELIMITER)[0];
        if ( _requiredWords.isRequiredWordFilterActive() && _requiredWords.isWordRequired(firstWord)) {
            // Required word filter is active and the word is required, take precedence over ignored words
            return false;
        } else {
            return this._wordsToIgnore.isWordIgnored(firstWord);
        }
    }

    private String[] getShiftsWithRequiredWordLeading(String[] shifts) {
        List<String> shiftList = new ArrayList<String>(Arrays.asList(shifts));

        Iterator<String> iter = shiftList.iterator();
        while (iter.hasNext()) {
            if (!isShiftStartingWithRequiredWord(iter.next())) {
                iter.remove();
            }
        }

        return shiftList.toArray(new String[shiftList.size()]);
    }

    private boolean isShiftStartingWithRequiredWord(String line) {
        String firstWord = line.split(DELIMITER)[0];
        return _requiredWords.isWordRequired(firstWord);
    }

    private String capitalizeWordsNotIgnoredInShift(String shift) {
        String[] words = shift.split(DELIMITER);
        StringBuilder builder = new StringBuilder();

        for (String str : words) {
            if ( _requiredWords.isRequiredWordFilterActive() && _requiredWords.isWordRequired(str)) {
                // Required word filter is active and the word is required, take precedence over ignored words
                builder.append(Character.toUpperCase(str.charAt(0))).append(str.substring(1));
            } else if (this._wordsToIgnore.isWordIgnored(str)) {
                builder.append(str);
            } else if (str.trim().isEmpty()) {
                continue;
            } else {
                builder.append(Character.toUpperCase(str.charAt(0))).append(str.substring(1));
            }
            builder.append(DELIMITER);
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }
}
