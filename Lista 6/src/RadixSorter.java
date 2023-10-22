public class RadixSorter implements ISorter {
    private final IChecker checker;

    public RadixSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        int max = findMaxValue(values);
        for(int i = 1; max / i > 0; i*=10) {
            countSort(values, i);
            checker.check(values);
        }
    }
    private int findMaxValue(int[] values){
        int max = values[0];
        for(int i = 1; i < values.length; i++){
            if(values[i] > max)
                max = values[i];
        }
        return max;
    }

    private void countSort(int[] values, int n){
        int[] result = new int[values.length];
        int[] lookUpTab = new int[10];
        for (int i = 0; i < values.length; i++) {
            int number = (values[i] / n) % 10;
            lookUpTab[number]++;
        }
        for (int i = 1; i < lookUpTab.length; i++) {
            lookUpTab[i] += lookUpTab[i-1];
        }
        for (int i = values.length-1; i >= 0; i--) {
            int number = (values[i] / n) % 10;
            result[lookUpTab[number]-1] = values[i];
            lookUpTab[number]--;
        }
        System.arraycopy(result, 0, values, 0, values.length);
    }
}
