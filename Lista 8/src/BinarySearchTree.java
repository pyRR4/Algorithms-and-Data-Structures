import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearchTree<T extends Comparable<T>> {
    private static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;
        Node<T> parent;
        Node(T value, Node<T> parent){
            this.value = value;
            this.parent = parent;
        }

        public T getValue() {
            return value;
        }
        public void setValue(T value) {
            this.value = value;
        }
        public Node<T> getLeft() {
            return left;
        }
        public void setLeft(Node<T> left) {
            this.left = left;
        }
        public Node<T> getRight() {
            return right;
        }
        public void setRight(Node<T> right) {
            this.right = right;
        }
        public Node<T> getParent() {
            return parent;
        }
        public void setParent(Node<T> parent) {
            this.parent = parent;
        }
    }
    private Node<T> root = null;
    private Node<T> findNode(T value){
        Node<T> node = root;
        while(node != null) {
            int compareResult = value.compareTo(node.getValue());
            if (compareResult == 0){
                return node;
            } else if(compareResult < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return null;
    }
    public void add(T value) throws DuplicateElementException {
        // TODO: Dodawanie nowej wartości do drzewa. Rzuć DuplicateElementException, jeśli element już istnieje.
        if(root == null) {
            root = new Node<>(value, null);
            return;
        }
        Node<T> node = root;
        Node<T> parent = root;
        while(node != null) {
            parent = node;
            int compareResult = value.compareTo(node.getValue());
            if (compareResult == 0){
                throw new DuplicateElementException();
            } else if(compareResult < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        int compareParent = value.compareTo(parent.getValue());
        if (compareParent == 0){
            throw new DuplicateElementException();
        } else if(compareParent < 0)
            parent.setLeft(new Node<>(value, parent));
        else
            parent.setRight(new Node<>(value, parent));
    }

    public boolean contains(T value) {
        // TODO: Sprawdzenie, czy drzewo zawiera podaną wartość.
        return findNode(value) != null;
    }

    public void delete(T value) {
        // TODO: Usunięcie wskazanej wartości z drzewa.
        if(root == null){
            return;
        }
        Node<T> node = findNode(value);
        if(node.getRight() == null || node.getLeft() == null)
            deleteSingleOrNone(node);
        else {
            Node<T> tmp = findNext(node);
            T tmpVal = tmp.getValue();
            tmp.setValue(node.getValue());
            node.setValue(tmpVal);
            deleteSingleOrNone(tmp);
        }
    }
    public void deleteSingleOrNone(Node<T> node){
        if(node.getRight() == null && node.getLeft() == null) {
            if (node == root)
                root = null;
            else if (node.getParent().getRight() != null && node.getParent().getRight().equals(node))
                node.getParent().setRight(null);
            else
                node.getParent().setLeft(null);
        }
        else {
            Node<T> tmp;
            if(node == root){
                if (node.getRight() == null)
                    root = node.getLeft();
                else
                    root = node.getRight();
            } else {
                if (node.getRight() == null)
                    tmp = node.getLeft();
                else
                    tmp = node.getRight();
                if (node.getParent().getRight() != null && node.getParent().getRight().equals(node))
                    node.getParent().setRight(tmp);
                else
                    node.getParent().setLeft(tmp);
                tmp.setParent(node.getParent());
            }
        }

    }
    private Node<T> findNext(Node<T> node){
        Node<T> tmp = node;
        if(tmp.getRight() != null){
            tmp = tmp.getRight();
            while(tmp.getLeft() != null){
                tmp = tmp.getLeft();
            }
            return tmp;
        }
        Node<T> parent = tmp.getParent();
        while(parent != null && tmp == parent.getRight()){
            tmp = parent;
            parent = parent.getParent();
        }
        return parent;
    }
    public String toStringPreOrder() {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą pre-order.
        return appendPre(root, "");
    }
    private String appendPre(Node<T> node, String s) {
        if(node == null)
            return s;
        if(s.equals(""))
            s = s + node.getValue();
        else
            s = s + ", " + node.getValue();
        s = appendPre(node.getLeft(), s);
        s = appendPre(node.getRight(), s);
        return s;
    }

    public String toStringInOrder() {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą in-order.
        return appendIn(root, "");
    }
    private String appendIn(Node<T> node, String s){
        if(node == null)
            return s;
        s = appendIn(node.getLeft(), s);
        if(s.equals(""))
            s = s + node.getValue();
        else
            s = s + ", " + node.getValue();
        s = appendIn(node.getRight(), s);
        return s;
    }

    public String toStringPostOrder() {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą in-order.
        return appendPost(root, "");
    }

    private String appendPost(Node<T> node, String s){
        if(node == null)
            return s;
        s = appendPost(node.getLeft(), s);
        s = appendPost(node.getRight(), s);
        if(s.equals(""))
            s = s + node.getValue();
        else
            s = s + ", " + node.getValue();
        return s;
    }
    public T minValue(){
        Node<T> tmp = root;
        while(tmp.getLeft() != null){
            tmp = tmp.getLeft();
        }
        return tmp.getValue();
    }
}
