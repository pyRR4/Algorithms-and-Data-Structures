public class InsertionSorter implements ISorter {
    private final IChecker checker;

    public InsertionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        for(int i = 1; i < values.length; i++){
            int value = values[i];
            int j = i - 1;
            while(j >= 0  && values[j] > value){
                values[j + 1] = values[j];
                j--;
            }
            values[j + 1] = value;
            checker.check(values);
        }
    }
}
