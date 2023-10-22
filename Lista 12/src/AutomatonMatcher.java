import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AutomatonMatcher implements IStringMatcher {
    private final static char[] alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private HashMap<Character, Integer> alphabetIndex;
    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        // TODO: Zaimplementuj algorytm dopasowywania napisów oparty na automacie skończonym
        LinkedList<Integer> validShifts = new LinkedList<>();
        int textLength = textToSearch.length();
        int patternLength = patternToFind.length();
        alphabetIndex = new HashMap<>(alphabet.length);
        for(int i = 0; i < alphabet.length; i++){
            alphabetIndex.put(alphabet[i], i);
        }

        int[][] transitionFunction = transitionFunction(patternToFind);

        int q = 0;
        for(int i = 0; i < textLength; i++){
            q = transitionFunction[q][alphabetIndex.get(textToSearch.charAt(i))];
            if(q == patternLength)
                validShifts.add((i - patternLength + 1));
        }
        return validShifts;
    }

    private int[][] transitionFunction(String patternToFind){
        int patternLength = patternToFind.length();
        int[][] function = new int[patternLength + 1][alphabet.length];
        for(int i = 0; i <= patternLength; i++){
            for(int j = 0; j < alphabet.length; j++){
                int nextState = Math.min(patternLength, i + 1);
                while((nextState > 0) && (patternToFind.charAt(nextState - 1) != alphabet[j]))
                    nextState--;
                function[i][j] = nextState;
            }
        }
        return function;
    }

    private int state(String patternToFind, int state, char letter){
        if(state < patternToFind.length() && letter == patternToFind.charAt(state))
            return state + 1;

        for(int i = state; i > 0; i--){
            if(patternToFind.charAt(i - 1) == letter) {
                for(int j = 0; j < i - 1; j++){
                    if(patternToFind.charAt(j) != patternToFind.charAt(state - i + 1 + j))
                        break;
                    return j + 1;
                }
            }
        }
        return 0;
    }
}
