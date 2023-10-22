public class InvertedSelectionSorter implements ISorter {
    private final IChecker checker;

    public InvertedSelectionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        for (int i = values.length - 1; i > 0; i--) {
            int biggest = i;
            for (int j = i - 1; j >= 0; j--) {
                if (values[j] > values[biggest]) {
                    biggest = j;
                }
            }
            int tmp = values[biggest];
            values[biggest] = values[i];
            values[i] = tmp;
            checker.check(values);
        }
    }
}
