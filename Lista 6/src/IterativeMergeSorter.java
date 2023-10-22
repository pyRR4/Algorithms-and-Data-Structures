public class IterativeMergeSorter implements ISorter {
    private final IChecker checker;

    public IterativeMergeSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        for(int steps = 1; steps < values.length; steps *= 2){
            for(int begin = 0; begin < values.length - 1; begin += (steps * 2)){
                int secondBegin = begin + steps;
                int[] tmp = new int[2 * steps];
                int tmpIt = 0;
                int firstArrIt = begin;
                int secArrIt = secondBegin;
                while(firstArrIt != begin + steps && secArrIt != secondBegin + steps && secArrIt < values.length && firstArrIt < values.length){
                    if(values[firstArrIt] < values[secArrIt]){
                        tmp[tmpIt] = values[firstArrIt];
                        firstArrIt++;
                    } else {
                        tmp[tmpIt] = values[secArrIt];
                        secArrIt++;
                    }
                    tmpIt++;
                }
                while(firstArrIt != begin + steps && firstArrIt < values.length){
                    tmp[tmpIt] = values[firstArrIt];
                    firstArrIt++;
                    tmpIt++;
                }
                while(secArrIt != secondBegin + steps && secArrIt < values.length){
                    tmp[tmpIt] = values[secArrIt];
                    secArrIt++;
                    tmpIt++;
                }
                int i = 0;
                while(i < tmp.length && i + begin < values.length) {
                    values[i + begin] = tmp[i];
                    i++;
                }
            }
            checker.check(values);
        }
    }
}

