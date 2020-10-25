package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    private static Random r = new Random(500);

    private Point getRamdom(){
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x, y);
    }

    public ArrayList<Point> getArray(int N){
        ArrayList<Point> p = new ArrayList<>();
        for (int i = 0; i < N; i++){
            p.add(getRamdom());
        }
        return p;
    }


    public void testWithNPointsAndQQueries(int N, int Q){

        ArrayList<Point> testPoints = getArray(N);
        NaivePointSet NP = new NaivePointSet(testPoints);
        KDTree kd = new KDTree(testPoints);
        ArrayList<Point> queryPoints = getArray(Q);

        for (Point p : queryPoints){
            Point expected = NP.nearest(p.getX(), p.getY());
            Point actual = kd.nearest(p.getX(), p.getY());
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testWith1000PointsAnd200Queries(){
        int pointCount = 5;
        int queryCount = 2;
        testWithNPointsAndQQueries(pointCount, queryCount);
    }

    @Test
    public void testWith10000PointsAnd2000Queries(){
        int pointCount = 10000;
        int queryCount = 2000;
        testWithNPointsAndQQueries(pointCount, queryCount);
    }

}
