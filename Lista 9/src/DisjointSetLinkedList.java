public class DisjointSetLinkedList implements IDisjointSetStructure {
    int[] represent;
    int[] next;
    int[] last;
    int[] setSize;
    int size;
    public DisjointSetLinkedList(int size) {
        this.size = size;
        represent = new int[size];
        next = new int[size];
        last = new int[size];
        setSize = new int[size];
        for(int i = 0; i < size; i++){
            next[i] = -1;
            represent[i] = -1;
            last[i] = -1;
            setSize[i] = 1;
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if(item >= size || item < 0)
            throw new ItemOutOfRangeException();
        if(represent[item] == -1)
            return item;
        represent[item] = findSet(represent[item]);
        return represent[item];
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        int represent1 = findSet(item1);
        int represent2 = findSet(item2);
        if(represent1 == represent2)
            return;
        if(setSize[represent1] > setSize[represent2]){ //reprezentant1 staje sie reprezentantem nowego zbioru
            link(represent1, represent2);
        } else { //reprezentant2 staje sie reprezentantem nowego zbioru
            link(represent2, represent1);
        }
    }

    private void link(int rep1, int rep2) {
        //podłączenie
        if(last[rep1] != -1)
            next[last[rep1]] = rep2;
        else
            next[rep1] = rep2;
        represent[rep2] = rep1;

        //ustawienie lasta nowego zbioru
        if(last[rep2] != -1)
            last[rep1] = last[rep2];
        else
            last[rep1] = rep2;

        //ustawienie size nowego zbioru
        setSize[rep1] += setSize[rep2];
    }
}
