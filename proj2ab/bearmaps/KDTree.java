package bearmaps;

import org.junit.Test;

import java.lang.ref.Reference;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    public Point nearest(double x, double y) {
        return null;
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        System.out.println("OK!!");
    }
}


