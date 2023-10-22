public class DisjointSetForest implements IDisjointSetStructure {
    int[] p;
    int[] rank;
    int size;
    public DisjointSetForest(int size) {
        this.size = size;
        this.p = new int[size];
        this.rank = new int[size];

        //MakeSet
        for(int i = 0; i < size; i++){
            p[i] = i;
            rank[i] = 0;
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if(item >= size || item < 0)
            throw new ItemOutOfRangeException();
        if(item != p[item])
            p[item] = findSet(p[item]);
        return p[item];
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        link(findSet(item1), findSet(item2));
    }

    private void link(int item1, int item2){
        if(rank[item1] > rank[item2]){
            p[item2] = item1;
        } else {
            p[item1] = item2;
            if(rank[item1] == rank[item2])
                rank[item2]++;
        }
    }
}
