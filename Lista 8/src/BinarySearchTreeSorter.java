import java.util.List;

public class BinarySearchTreeSorter {
    public static <T extends Comparable<T>> void sort(List<T> list) throws DuplicateElementException {
        // TODO: Posortuj listę używając klasy BinarySearchTree.
        BinarySearchTree<T> tree = new BinarySearchTree<>();
        int size = list.size();
        while(!list.isEmpty()){
            tree.add(list.remove(0));
        }
        for (int i = 0; i < size; i++){
            T value = tree.minValue();
            list.add(value);
            tree.delete(value);
        }
    }
}
