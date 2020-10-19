package bearmaps;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class NaivePointSet implements PointSet{
    private ArrayList<Point> Mypoints;
    private Point best;

    public NaivePointSet(List<Point> points){
        Mypoints = new ArrayList<>();
        for (Point i: points){
            Mypoints.add(i);
        }
        best = Mypoints.get(0);
    }

    @Override
    public Point nearest(double x, double y){
        Point goal = new Point(x, y);
        double current_distance = goal.distance(goal,Mypoints.get(0));
        for(Point i : Mypoints){
            if (goal.distance(goal,i) < current_distance){
                best = i;
            }
        }
        return best;
    }

    public static void main(String[] args){
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }
}
