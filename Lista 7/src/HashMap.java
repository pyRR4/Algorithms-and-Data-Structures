import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class HashMap<TKey, TValue> {

    private static class Node<TKey, TValue>{
        final TKey key;
        TValue value;

        Node(TKey key, TValue value) {
            this.key = key;
            this.value = value;
        }
        public TKey getKey() {
            return key;
        }

        public TValue getValue() {
            return value;
        }

        public void setValue(TValue value) {
            this.value = value;
        }
    }
    private Function<TKey, Integer> hashFunction;
    private int capacity;
    private int size;
    private final double loadFactor;
    private LinkedList<Node<TKey, TValue>>[] buckets;
    public HashMap(int initialSize, double loadFactor, Function<TKey, Integer> hashFunction) {
        // TODO: Zainicjuj nową instancję klasy HashMap według podanych parametrów.
        //    InitialSize - początkowy rozmiar HashMap
        //    LoadFactor - stosunek elementów do rozmiaru HashMap po przekroczeniu którego należy podwoić rozmiar HashMap.
        //    HashFunction - funkcja, według której liczony jest hash klucza.
        //       Przykład użycia:   int hash = hashFunction.apply(key);
        this.hashFunction = hashFunction;
        this.capacity = initialSize;
        this.size = 0;
        this.loadFactor = loadFactor;

        createEmptyBuckets(initialSize);
    }
    private void createEmptyBuckets(int size){
        this.buckets = (LinkedList<Node<TKey, TValue>>[])(new LinkedList[size]);
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<Node<TKey, TValue>>();
        }
    }
    private Node<TKey, TValue> getNode(TKey key){
        for(Node<TKey, TValue> node : buckets[getIndex(key)])
            if(node.getKey().equals(key))
                return node;
        return null;
    }
    private int getIndex(TKey key){
        return hashFunction.apply(key) % capacity;
    }
    public void add(TKey key, TValue value) throws DuplicateKeyException {
        // TODO: Dodaj nową parę klucz-wartość. Rzuć wyjątek DuplicateKeyException, jeżeli dany klucz już istnieje w HashMap.
        if(containsKey(key)){
            throw new DuplicateKeyException();
        }
        buckets[getIndex(key)].add(new Node<>(key, value));
        size++;

        if(((double) size)/capacity > loadFactor){
            resize(capacity * 2);
        }
    }
    private void resize(int newCapacity){
        this.capacity = newCapacity;
        LinkedList<Node<TKey, TValue>>[] buckets = (LinkedList<Node<TKey, TValue>>[]) this.buckets;

        createEmptyBuckets(newCapacity);

        for (LinkedList<Node<TKey, TValue>> bucket : buckets) {
            for (Node<TKey, TValue> node : bucket) {
                this.buckets[getIndex(node.getKey())].add(node);
            }
        }
    }

    public void clear() {
        for (LinkedList<Node<TKey, TValue>> bucket : buckets)
            bucket.clear();
        size = 0;
    }

    public boolean containsKey(TKey key) {
        // TODO: Sprawdź, czy HashMap zawiera już dany klucz.
        return getNode(key) != null;
    }

    public boolean containsValue(TValue value) {
        // TODO: Sprawdź, czy HashMap zawiera już daną wartość.
        for (LinkedList<Node<TKey, TValue>> bucket : buckets)
            for (Node<TKey, TValue> node : bucket)
                if(node.getValue().equals(value))
                    return true;
        return false;
    }

    public int elements() {
        // TODO: Zwróć liczbę par klucz-wartość przechowywaną w HashMap.
        return size;
    }

    public TValue get(TKey key) throws NoSuchElementException {
        // TODO: Pobierz wartość powiązaną z danym kluczem. Rzuć wyjątek NoSuchElementException, jeżeli dany klucz nie istnieje.
        Node<TKey, TValue> node = getNode(key);
        if(node != null)
            return node.getValue();
        else
            throw new NoSuchElementException();
    }

    public void put(TKey key, TValue value) {
        // TODO: Przypisz daną wartość do danego klucza.
        //   Jeżeli dany klucz już istnieje, nadpisz przypisaną do niego wartość.
        //   Jeżeli dany klucz nie istnieje, dodaj nową parę klucz-wartość.
        Node<TKey, TValue> node = getNode(key);
        if(node != null){
            node.setValue(value);
        } else {
            try {
                add(key, value);
            } catch (DuplicateKeyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public TValue remove(TKey key) {
        // TODO: Usuń parę klucz-wartość, której klucz jest równy podanej wartości.
        Node<TKey, TValue> node = getNode(key);
        if(node != null) {
            buckets[getIndex(key)].remove(node);
            size--;
            return node.getValue();
        } else
            return null;
    }

    public int size() {
        // TODO: Zwróć obecny rozmiar HashMap.
        return capacity;
    }
    public void rehash(Function<TKey, Integer> newHashFunction) {
        // TODO: Zmień obecną funkcję hashującą na nową (wymaga przeliczenia dla wszystkich par klucz-wartość).
        hashFunction = newHashFunction;
        resize(capacity);
    }
}
