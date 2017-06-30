package CollinearPointsWeek3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Viktor on 1.6.2017..
 */
public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments  = new ArrayList<>();

    public FastCollinearPoints(Point[] points){
        for(int i = 0; i < points.length; i++){
            Point[] aux = Arrays.copyOf(points,points.length);
            Arrays.sort(aux, points[i].slopeOrder());
            Point previous = aux[1];
            ArrayList<Point> line = new ArrayList<>();
            for(int j = 2; j < aux.length; j++){
                if(aux[0].slopeTo(aux[j]) != aux[0].slopeTo(previous)){
                    if(line.size() >= 3){
                        line.add(aux[0]);
                        Collections.sort(line);
                        if(line.get(0) == aux[0]) {
                            lineSegments.add(new LineSegment(line.get(0), line.get(line.size() - 1)));
                        }
                    }
                    line.clear();
                }
                line.add(aux[j]);
            }
            if(line.size() >= 3){
                line.add(aux[0]);
                Collections.sort(line);
                if(line.get(0) == aux[0]) {
                    lineSegments.add(new LineSegment(line.get(0), line.get(line.size() - 1)));
                }
            }
        }

    }

    public int numberOfSegments(){
        return lineSegments.size();
    }
    public LineSegment[] segments(){
        LineSegment[] array = new LineSegment[lineSegments.size()];
        lineSegments.toArray(array);
        return array;
    }

    public static void main(String [] args){
        Scanner unos = new Scanner(System.in);
        int n = unos.nextInt();
        Point[] array = new Point[n];
        for(int i = 0; i < n; i++){
            array[i] = new Point(unos.nextInt(), unos.nextInt());
        }
    }
}
