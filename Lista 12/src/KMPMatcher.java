import java.util.LinkedList;
import java.util.List;

public class KMPMatcher implements IStringMatcher {
    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        // TODO: Zaimplementuj algorytm dopasowywania napisów Knutha-Morrisa-Pratta
        LinkedList<Integer> validShifts = new LinkedList<>();
        char[] pattern = patternToFind.toCharArray();
        char[] text = textToSearch.toCharArray();

        int[] prefixFunction = prefixFunction(pattern);
        int q = 0;

        return validShifts;
    }

    private int[] prefixFunction(char[] pattern){
        int[] function = new int[pattern.length];


        return function;
    }
}
