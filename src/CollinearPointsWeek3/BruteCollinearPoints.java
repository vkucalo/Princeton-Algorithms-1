package CollinearPointsWeek3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Viktor on 1.6.2017..
 */
public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments = new ArrayList<>();
    public BruteCollinearPoints(Point[] points){
        Arrays.sort(points);
        for(int i = 0; i < points.length; i++){
            for(int j = i+1; j < points.length; j++){
                for(int k = j+1; k < points.length; k++){
                    for(int l = k+1; l < points.length; l++){
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];
                        if(p.slopeTo(q) == q.slopeTo(r) && q.slopeTo(r) == r.slopeTo(s)){
                            lineSegments.add(new LineSegment(p,s));
                        }
                    }
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
    }
}