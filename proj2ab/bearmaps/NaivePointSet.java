package bearmaps;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class NaivePointSet implements PointSet{
    private ArrayList<Point> MyPoints;
    private Point best;

    public NaivePointSet(List<Point> Points){
        MyPoints = new ArrayList<Point>();
        for (Point i: Points){
            MyPoints.add(i);
        }
        best = MyPoints.get(0);
    }

    @Override
    public Point nearest(double x, double y){
        Point goal = new Point(x, y);
        double current_distance = Point.distance(goal, MyPoints.get(0));
        for (Point i: MyPoints){
            if (Point.distance(i, goal) <= current_distance){
                best = i;
                current_distance = Point.distance(i, goal);
            }
        }
        return best;
    }

    public static void main(String[] args){
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet NP = new NaivePointSet(List.of(p1, p2, p3));
        Point p = NP.nearest(3.0, 4.0);
        System.out.println(p.getX());
        System.out.println(p.getY());
    }
}