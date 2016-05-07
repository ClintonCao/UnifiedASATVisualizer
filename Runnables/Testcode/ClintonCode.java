import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ClintonCode {

  private static Pair[] nn;
  public ClintonCode(){ }

  public static void main(String[] args) throws FileNotFoundException {
    String s = "2\n5 5\n4 6";

    InputStream stream = new ByteArrayInputStream(
        s.getBytes(StandardCharsets.UTF_8));
    String answer = run(stream);

    System.out.println(answer);
  }

  public static String run(InputStream in) throws FileNotFoundException {
    BufferedReader reader = new BufferedReader(new FileReader("example.txt")); // instantiate a BufferedReader
    StringBuilder sb = new StringBuilder();

    try {

      int numPoints = Integer.parseInt(reader.readLine()); // Read how many number of points there are.
      ArrayList<Point> points = new ArrayList<Point>(); // List to store the coordinates of points.
      nn = new Pair[numPoints];  // array to store the closest neighbour of each point.

      // Parse the inputs.
      for(int i = 0; i < numPoints; i++) {
        String [] coordinates = reader.readLine().split(" ");
        Double x = Double.parseDouble(coordinates[0]); // Read the x coordinate.
        Double y = Double.parseDouble(coordinates[1]); // Read the y coordinate.
        Point p = new Point(x,y,i);
        points.add(p);
      }

      sortX(points);
      nearestNeighbour(points);

      for (int j = 0; j < nn.length; j++) {
        int neighbour = nn[j].getSecondPoint().getId();
        sb.append("Nearest neighbour for "+ j+ ": " + neighbour + "\n");
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return sb.toString();
  }

  /**
   * Recursive function to compute the closest pair of points.
   * @param points the list of points.
   * @return the closest pair of points.
   */
  public static ArrayList<Point> nearestNeighbour(ArrayList<Point> points) {

    int n =  points.size();

    if (n == 1) {
      nn[points.get(0).getId()] = new Pair(points.get(0), null);
      return points;
    }

    int mid  = (int) Math.floor(n / 2); // calculate the middle of the list.
    Point median = points.get(mid);

    ArrayList<Point> leftPoints = nearestNeighbour(new ArrayList<Point>(points.subList(0, mid))); // recursive call on the left half
    ArrayList<Point> rightPoints  = nearestNeighbour(new ArrayList<Point> (points.subList(mid, n))); // recursive call on the right half
    
    double distance = Double.MAX_VALUE;
    if(leftPoints.size() == 1) {
    	Point left = leftPoints.get(0);
    	for(Point p : rightPoints) {
    		double newDistance = Math.sqrt(Math.pow(p.getX() - left.getX(), 2) + Math.pow(p.getY() - left.getY(), 2));
    		if(newDistance < distance) {
    			nn[left.getId()] = new Pair(left, p);
        		distance = newDistance;
    		}
    	}
    }
    distance = Double.MAX_VALUE;
    if(rightPoints.size() == 1) {
    	Point right = rightPoints.get(0);
    	for(Point p : leftPoints) {
    		double newDistance = Math.sqrt(Math.pow(p.getX() - right.getX(), 2) + Math.pow(p.getY() - right.getY(), 2));
    		if(newDistance < distance) {
    			nn[right.getId()] = new Pair(right, p);
        		distance = newDistance;
    		}
    	}
    }

    // calculate the intersection points on the left half
    HashMap<Point, Point> intersectionPointsLeft = new HashMap<Point, Point>();
    for (int i = 0; i < leftPoints.size(); i++) {
      Point p = points.get(i);
      Point intersecPoint =  calcIntersecPoint(p, median);
      intersectionPointsLeft.put(intersecPoint, p);
    }

    // calculate the intersection points on the right half
    HashMap<Point, Point> intersectionPointsRight = new HashMap<Point, Point>();
    for (int i = 0; i < rightPoints.size(); i++) {
      Point p = points.get(i);
      Point intersecPoint =  calcIntersecPoint(p, median);
      intersectionPointsRight.put(intersecPoint, p);
    }

    sortY(leftPoints); // sort the points on the left by the y coordinates.
    sortY(rightPoints); // sort the points on the right by the y coordinates.

    for (Point key : intersectionPointsRight.keySet()) {
      double yRight = intersectionPointsRight.get(key).getY();
      double yIntersec = key.getY();
      Point right = intersectionPointsRight.get(key);
        for (int k = 0; k < leftPoints.size(); k++) {
          Point left = leftPoints.get(k);
          double yLeft = leftPoints.get(k).getY();
          if (yLeft >= (yRight + yIntersec) && yLeft <= (yRight - yIntersec)) {
            break;  // skip if it the project point is not within the two intersection points of the circle.
          }
          
          if (left.getId() != right.getId()) {
            Pair pair = new Pair(left, right);
          
            if (pair.getDistance() < nn[left.getId()].getDistance()) {
              nn[left.getId()] = pair;
            }
          }
          
        }
      
    }
    
    
    for (Point key : intersectionPointsLeft.keySet()) {
      double yLeft = intersectionPointsLeft.get(key).getY();
      double yIntersec = key.getY();
      Point left = intersectionPointsLeft.get(key);
        for (int k = 0; k < leftPoints.size(); k++) {
          Point right = leftPoints.get(k);
          double yRight = leftPoints.get(k).getY();
          if (yRight >= (yLeft + yIntersec) && yRight <= (yLeft - yIntersec)) {
            break;  // skip if it the project point is not within the two intersection points of the circle.
          }
          
          if (right.getId() != left.getId()) {
            Pair pair = new Pair(right, left);
          
            if (pair.getDistance() < nn[right.getId()].getDistance()) {
              nn[right.getId()] = pair;
            }
          }
          
        }
      
    }


    return points;
  }

  
  public static Point calcIntersecPoint(Point p, Point median) {
    double x = p.getX();
    double xm = median.getX();
    double hypotenuse  = (double) Math.pow(nn[p.getId()].getDistance(), 2);
    double adjacent = (double) Math.pow(x - xm, 2);
    double opposite = Math.sqrt(hypotenuse + adjacent);
    double y = p.getY() + opposite;
    return new Point(xm, y);
  }

  public static void sortX(ArrayList<Point> points) {
    Collections.sort(points, new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
          if (p1.getX() < p2.getX())
            return -1;
          if (p1.getX() > p2.getX())
            return 1;
          return 0;
        }
      }
    );
  }

  public static void sortY(ArrayList<Point> points) {
    Collections.sort(points, new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
          if (p1.getY() < p2.getY())
            return -1;
          if (p1.getY() > p2.getY())
            return 1;
          return 0;
        }
      }
    );
  }

}

/**
* Class to represent a point.
* @author Clinton
*
*/
class Point {

 private double x;
 private double y;
 private int id = Integer.MAX_VALUE;

 public Point(double x, double y) {
   this.x = x;
   this.y = y;
 }

 public Point(double x, double y, int id) {
   this.x = x;
   this.y = y;
   this.id = id;
 }

 public double getX() {
   return x;
 }

 public double getY() {
   return y;
 }

 public int getId() {
   return id;
 }

}

class Pair {

 private Point point1 = null;
 private Point point2 = null;
 private double distance = Double.MAX_VALUE;

 public Pair() { }

 public Pair(Point p1, Point p2) {
   point1 = p1;
   point2 = p2;

   if (p1 != null && p2 != null) {
     distance = calculateDistance(point1, point2);
   }
 }

 public double calculateDistance(Point p1, Point p2) {

   double x_diff = Math.pow(p2.getX() - p1.getX() , 2);
   double y_diff = Math.pow(p2.getY() - p1.getY() , 2);

   return Math.sqrt(x_diff + y_diff);

 }

 public Point getFirstPoint() {
   return point1;
 }

 public Point getSecondPoint() {
   return point2;
 }

 public double getDistance() {
   return distance;
 }

}