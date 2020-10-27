package bearmaps;
import java.util.List;

public class KDTree implements PointSet{
    private static  final boolean horizontal = false;
    private static final boolean vertical = true;
    private Node root;

    private class Node{
        private Point p;
        private boolean orientation;
        private Node LeftChild;// also reference to DownChild
        private Node RightChild;// also reference to UpChild

        public Node(Point given_p, boolean o){
            p = given_p;
            orientation = o;
        }


    };

    public KDTree(List<Point> points) {
        for (Point p : points){
            root = add(p, root, horizontal);
        }
    }

    private Node add(Point p, Node n, boolean orientation){
        if (n == null){
            return new Node(p, orientation);
        }
        if (p.equals(n.p)){
            return n;
        }
        int compare = ComparePoints(p,n.p, orientation);
        if (compare < 0){
            n.LeftChild = add(p, n.LeftChild, !orientation);
        }
        else if (compare >= 0)
            n.RightChild = add(p, n.RightChild, !orientation);
        return n;
    }

    private int ComparePoints(Point x, Point y, boolean orientation){
        if (orientation == horizontal){
            return Double.compare(x.getX(), y.getX());
        }
        else
            return Double.compare(x.getY(), y.getY());
    }

    @Override
    public Point nearest(double x, double y){
        Point p = new Point(x, y);
        return nearest(root, p, root.p);
    }

    private Point nearest(Node n, Point goal , Point best) {
        Node goodSide, badSide;
        if (n == null)
            return best;
        if (Point.distance(n.p, goal) <= Point.distance(best, goal))
            best = n.p;
        if (better(n, goal) > 0){
            goodSide = n.RightChild;
            badSide = n.LeftChild;
        }
        else{
            goodSide = n.LeftChild;
            badSide = n.RightChild;
        }

        best = nearest(goodSide, goal, best);

        if (worthToLookBack(n, goal, best)){
            best = nearest(badSide, goal, best);
        }
        return best;
    }

    public boolean worthToLookBack(Node n, Point goal, Point best){
        double distance;
        double current_distance = Point.distance(best, goal);
        if (n.orientation == horizontal){
            distance = Point.distance(new Point(n.p.getX(), goal.getY()), goal);
        }
        else
            distance = Point.distance(new Point(goal.getX(), n.p.getY()), goal);
        return distance < current_distance;
    }


    public int better(Node n, Point goal){
        if (n.orientation == horizontal){
            return Double.compare(goal.getX() , n.p.getX());
        }
        else {
            return Double.compare(goal.getY() , n.p.getY());
        }
    }

    public static void buildLectureTree(){
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));

    }

    public static void buildTreewithDoubles(){
        Point p1 = new Point(2, 3);
        Point p2 = new Point(2, 3);

        KDTree kd = new KDTree(List.of(p1, p2));
    }

    public static void main(String[] args) {
        buildTreewithDoubles();
        System.out.println("OK!!");
    }
}


