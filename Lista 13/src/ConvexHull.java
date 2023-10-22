import java.awt.*;
import java.util.*;
import java.util.List;

public class ConvexHull {
    public static List<Point> solve(List<Point> points) throws IllegalArgumentException {
        if(points.size() < 3)
            throw new IllegalArgumentException();
        LinkedList<Point> tmpPoints = new LinkedList<>(points);
        Queue<Point> list = new LinkedList<>();
        Point lowest = getLowest(tmpPoints);
        tmpPoints.sort(new Sorter(lowest));
        int size = tmpPoints.size();

        for (int i = 0; i < size; i++){
            list.add(tmpPoints.get(i));
            while(i < size - 1 && det(lowest, tmpPoints.get(i), tmpPoints.get(i + 1)) == 0) {
                i++;
            }
        }
        list.add(lowest);
        int listSize = list.size();
        if (listSize < 3)
            throw new IllegalArgumentException();

        Stack<Point> stack = new Stack<>();
        stack.push(lowest);
        stack.push(list.remove());
        stack.push(list.remove());

        Point p;
        for(int i = 2; i < listSize; i++){
            p = list.remove();
            while (det(nextToTop(stack), stack.peek(), p) <= 0){
                stack.pop();
            }
            stack.push(p);
        }
        return stack;
    }

    private static Point nextToTop(Stack<Point> points){
        Point p = points.pop();
        Point retPoint = points.peek();
        points.push(p);
        return retPoint;
    }

    private static Point getLowest(List<Point> points){
        Point retPoint = points.get(0);
        int size = points.size();
        for(int i = 1; i < size; i++){
            Point point = points.get(i);
            if(point.y < retPoint.y || (point.y == retPoint.y && point.x < retPoint.x))
                    retPoint = point;
        }
        return retPoint;
    }

    private static class Sorter implements Comparator<Point>{
        private final Point referencePoint;
        public Sorter(Point referencePoint){
            this.referencePoint = referencePoint;
        }
        @Override
        public int compare(Point o1, Point o2) {
            int result = det(referencePoint, o1, o2);
            if(result < 0)
                return 1;
            else if (result > 0)
                return -1;
            else {
                if (distance(o1) < distance(o2))
                    return 1;
                else if (distance(o1) > distance(o2))
                    return -1;
                else
                    return 0;
            }
        }
        private double distance(Point p){
            return Math.sqrt(Math.pow(p.x - referencePoint.x, 2) + Math.pow(p.y - referencePoint.y, 2));
        }
    }
    private static int det(Point referencePoint, Point p1, Point p2){
        return (referencePoint.x * p1.y + referencePoint.y * p2.x + p1.x * p2.y) -
                (p1.y * p2.x + referencePoint.x * p2.y + referencePoint.y * p1.x);
    }
}
