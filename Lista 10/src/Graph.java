import java.util.*;

public class Graph<T> {
    private final int[][] adjacencyMatrix;
    private final ArrayList<T> vertices;
    public Graph(List<Edge<T>> edges) {
        vertices = new ArrayList<>();

        for (Edge<T> edge : edges) {
            putValue(edge.getSource());
            putValue(edge.getDestination());
        }
        int verticesAmount = vertices.size();
        adjacencyMatrix = new int[verticesAmount][verticesAmount];

        for (Edge<T> edge : edges) {
            int destinationIndex = vertices.indexOf(edge.getDestination());
            int sourceIndex = vertices.indexOf(edge.getSource());
            adjacencyMatrix[sourceIndex][destinationIndex] = edge.getWeight();
        }
    }

    private void putValue(T value){
        if(!vertices.contains(value)) {
            vertices.add(value);
        }
    }

    public String depthFirst(T startNode) throws NoSuchElementException {
        int startNodeIndex = vertices.indexOf(startNode);
        if(startNodeIndex < 0)
            throw new NoSuchElementException();
        StringBuilder retString = new StringBuilder(startNode.toString());
        boolean[] isVisited = new boolean[adjacencyMatrix.length];
        isVisited[startNodeIndex] = true;
        for(int i = 0; i < adjacencyMatrix.length; i++)
            if(adjacencyMatrix[startNodeIndex][i] > 0 && !isVisited[i])
                retString.append(", ").append(appenderDFS(isVisited, vertices.get(i)));
        return retString.toString();
    }

    private String appenderDFS(boolean[] isVisited, T node){
        int nodeIndex = vertices.indexOf(node);
        StringBuilder retString = new StringBuilder(node.toString());
        isVisited[nodeIndex] = true;
        for(int i = 0; i < adjacencyMatrix.length; i++){
            if(adjacencyMatrix[nodeIndex][i] > 0 && !isVisited[i]){
                retString.append(", ").append(appenderDFS(isVisited, vertices.get(i)));
            }
        }
        return retString.toString();
    }

    public String breadthFirst(T startNode) throws NoSuchElementException {
        int nodeIndex = vertices.indexOf(startNode);
        if (nodeIndex < 0)
            throw new NoSuchElementException();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[adjacencyMatrix.length];
        StringBuilder retString = new StringBuilder(startNode.toString());
        queue.offer(nodeIndex);
        isVisited[nodeIndex] = true;
        while(!queue.isEmpty()){
            nodeIndex = queue.poll();
            for(int i = 0; i < adjacencyMatrix.length; i++){
                if(adjacencyMatrix[nodeIndex][i] > 0 && !isVisited[i]){
                    queue.offer(i);
                    isVisited[i] = true;
                    retString.append(", ").append(vertices.get(i).toString());
                }
            }
        }
        return retString.toString();
    }

    public int connectedComponents() {
        int adjLength = adjacencyMatrix.length;
        DisjointSetForest forest = new DisjointSetForest(adjLength);
        for(int i = 0; i < adjLength; i++){
            for(int j = 0; j < adjLength; j++)
                if (adjacencyMatrix[i][j] > 0) {
                    try {
                        forest.union(i, j);
                    } catch (ItemOutOfRangeException e) {
                        System.out.println("ItemOutOfRangeException");
                    }
                }
        }
        int setsAmount = 0;
        for(int i = 0; i < adjLength; i++){
            try {
                if(forest.findSet(i) == i)
                    setsAmount++;
            } catch (ItemOutOfRangeException e) {
                System.out.println("ItemOutOfRangeException");
            }
        }
        return setsAmount;
    }
}
