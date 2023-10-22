import java.util.*;

public class Graph<T> {
    private int[][] adjacencyMatrix;
    private HashMap<T, Integer> indexes;
    private ArrayList<T> vertices;
    public Graph(List<Edge<T>> edges) {
        // TODO: Konstruktor
        vertices = new ArrayList<>();
        indexes = new HashMap<>();
        int index = 0;
        T node;
        for (Edge<T> edge : edges) {
            node = edge.getNode1();
            if(!indexes.containsKey(node)){
                indexes.put(node, index);
                vertices.add(node);
                index++;
            }
            node = edge.getNode2();
            if(!indexes.containsKey(node)){
                indexes.put(node, index);
                vertices.add(node);
                index++;
            }
        }
        int verticesAmount = vertices.size();
        adjacencyMatrix = new int[verticesAmount][verticesAmount];

        for (Edge<T> edge : edges) {
            Integer index1 = indexes.get(edge.getNode1());
            Integer index2 = indexes.get(edge.getNode2());
            adjacencyMatrix[index1][index2] = edge.getDistance();
            adjacencyMatrix[index2][index1] = edge.getDistance();
        }
    }
    public Map<T, Integer> calculateShortestPaths(T startNode) throws NoSuchElementException {
        // TODO: Wylicz najkrótsze ścieżki do każdego wierzchołka w grafie (Dijkstra)
        if(!vertices.contains(startNode))
            throw new NoSuchElementException();

        HashMap<T, Integer> paths = new HashMap<>();
        ArrayList<T> vertices = new ArrayList<>(this.vertices);

        initiate(startNode, paths, vertices);

        while(!vertices.isEmpty()){
            vertices.sort(new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    return Integer.compare(paths.get(o1), paths.get(o2));
                }
            });
            T node = vertices.remove(0);
            int nodeIndex = indexes.get(node);
            for (T vertex : vertices) {
                int index = indexes.get(vertex);
                if (adjacencyMatrix[nodeIndex][index] > 0) {
                    int pathLength = paths.get(node) + adjacencyMatrix[nodeIndex][index];
                    if (paths.get(vertex) > pathLength)
                        paths.put(vertex, pathLength);
                }
            }
        }
        return paths;
    }
    public Integer calculateShortestPath(T startNode, T endNode) throws NoSuchElementException {
        // TODO: Wylicz najkrótszą ścieżkę pomiędzy wierzchołkami w grafie
        if(!vertices.contains(endNode) || !vertices.contains(startNode))
            throw new NoSuchElementException();
        HashMap<T, Integer> paths = new HashMap<>();
        ArrayList<T> vertices = new ArrayList<>(this.vertices);

        initiate(startNode, paths, vertices);

        while(!vertices.isEmpty()){
            vertices.sort(new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    return Integer.compare(paths.get(o1), paths.get(o2));
                }
            });
            T node = vertices.remove(0);
            int nodeIndex = indexes.get(node);
            for (T vertex : vertices) {
                int index = indexes.get(vertex);
                if (adjacencyMatrix[nodeIndex][index] > 0) {
                    int pathLength = paths.get(node) + adjacencyMatrix[nodeIndex][index];
                    if (paths.get(vertex) > pathLength)
                        paths.put(vertex, pathLength);
                }
                if(node.equals(endNode))
                    return paths.get(endNode);
            }
        }
        return paths.get(endNode);
    }

    private void initiate(T startNode, HashMap<T, Integer> paths, ArrayList<T> vertices){
        vertices.remove(startNode);
        int startNodeIndex = indexes.get(startNode);
        for (T vertex : vertices) {
            int index = indexes.get(vertex);
            if(adjacencyMatrix[startNodeIndex][index] > 0)
                paths.put(vertex, adjacencyMatrix[startNodeIndex][index]);
            else
                paths.put(vertex, Integer.MAX_VALUE);
        }
    }
}
