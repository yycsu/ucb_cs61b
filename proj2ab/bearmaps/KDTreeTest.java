package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    private static Random r = new Random(500);

    private Point getRamdom(){
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x, y);
    }

    public List<Point> getArray(int N){
        List<Point> p = new ArrayList<>();
        for (int i = 0; i < N; i++){
            p.add(getRamdom());
        }
        return p;
    }


    public void testWithNPointsAndQQueries(int N, int Q){

        List<Point> testPoints = getArray(N);
        NaivePointSet NP = new NaivePointSet(testPoints);
        KDTree kd = new KDTree(testPoints);
        List<Point> queryPoints = getArray(Q);

        for (Point p : queryPoints){
            Point expected = NP.nearest(p.getX(), p.getY());
            Point actual = kd.nearest(p.getX(), p.getY());
            assertEquals(expected, actual);
        }
    }

    public void testTimeNPointsAndQQueries(int N, int Q){

        List<Point> testPoints = getArray(N);
        NaivePointSet NP = new NaivePointSet(testPoints);
        KDTree kd = new KDTree(testPoints);
        List<Point> queryPoints = getArray(Q);

        Stopwatch sw = new Stopwatch();
        for (Point p : queryPoints){
            Point expected = NP.nearest(p.getX(), p.getY());
        }
        System.out.println("NP Total time elapsed: " + sw.elapsedTime() +  " seconds.");

        sw = new Stopwatch();
        for (Point p : queryPoints){
            Point expected = kd.nearest(p.getX(), p.getY());
        }
        System.out.println("kd Total time elapsed: " + sw.elapsedTime() +  " seconds.");

    }

    @Test
    public void testWith1000PointsAnd200Queries(){
        int pointCount = 1000;
        int queryCount = 200;
        testWithNPointsAndQQueries(pointCount, queryCount);
    }

    @Test
    public void testWith10000PointsAnd2000Queries(){
        int pointCount = 10000;
        int queryCount = 2000;
        testWithNPointsAndQQueries(pointCount, queryCount);
    }

    @Test
    public void testWith100000PointsAnd10000Queries(){
        int pointCount = 100000;
        int queryCount = 10000;
        testTimeNPointsAndQQueries(pointCount, queryCount);
    }

}
