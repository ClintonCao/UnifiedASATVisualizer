import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AllClosestPoints {
	public static Point[] nearestNeighbours;

	public static void main(String[] args) throws FileNotFoundException {
		int i = 1;
		while(i <= 10000000) {		
			int points = i;
			long startTime = System.currentTimeMillis();
			execute(points);
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("Execution for all nearest neighbours with " + points + " took " + elapsedTime + " ms\n");
			i*=10;
		}
	}


	public static class PointPair {
		public Point p1;
		public Point p2;

		public PointPair(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
		}

		public double computeDistance() {
			return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
		}
	}

	public static class PointPairIntersectionYCoordinateComparator implements Comparator<PointPair> {
		@Override
		public int compare(PointPair pp1, PointPair pp2) {
			return Double.compare(pp1.p2.y, pp2.p2.y);
		}
	}

	public static class Circle {
		public double radius;
		public Point center;

		public Circle(double radius, Point center) {
			this.radius = radius;
			this.center = center;
		}

		public Point computeIntersectionPointMedian(double median) {
			if(median > center.x + radius) {
				return null;
			}
			if(median < center.x - radius) {
				return null;
			}

			if(median == center.x + radius) {
				return new Point(Integer.MIN_VALUE, median, center.y);
			}
			if(median == center.x - radius) {
				return new Point(Integer.MIN_VALUE, median, center.y);
			}

			double yLength = Math.sqrt(Math.pow(radius, 2) - Math.pow(Math.abs(median - center.x), 2));

			return new Point(Integer.MIN_VALUE, median, center.y + yLength);
		}
	}

	public static class Point {
		public int id;
		public double x;
		public double y;

		public Point(int id, double x, double y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}

		public double computeDistanceToXMedian(double xMedian) {
			return Math.sqrt(Math.pow(xMedian - x, 2));
		}

		public boolean equals(Point other) {
			if(other instanceof Point) {
				if(id == other.id) {
					if(x == other.x) {
						if(y == other.y) {
							return true;
						}
					}
				}
			}
			return false;
		} 

		public double computeDistanceToOtherPoint(Point other) {
			if(other == null) {
				return Double.MAX_VALUE;
			}
			return Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2));
		}
	}

	public static class PointXComparator implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
			return Double.compare(p1.x, p2.x);
		}
	}

	public static class PointYComparator implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
			return Double.compare(p1.y, p2.y);
		}
	}

	public static void execute(int numPoints) {
		ArrayList<Point> points = new ArrayList<Point>();
		for(int i = 0; i < numPoints; i++) {
			double x = i;
			double y = i;
			points.add(new Point(i, x, y));
		}
		nearestNeighbours = new Point[numPoints];
		allClosestPairs(points);
	}

	public static String run(InputStream in){
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		int numPoints = 0;
		ArrayList<Point> points = new ArrayList<Point>();

		try{
			numPoints = Integer.parseInt(reader.readLine());

			for(int i = 0; i < numPoints; i++) {
				String[] line = reader.readLine().split(" ");
				double x = Double.parseDouble(line[0]);
				double y = Double.parseDouble(line[1]);
				points.add(new Point(i, x, y));
			}
		} catch (Exception e){}
		nearestNeighbours = new Point[numPoints];
		allClosestPairs(points);

		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < nearestNeighbours.length; i++) {
			stringBuilder.append(nearestNeighbours[i].id);
			stringBuilder.append('\n');
		}
		return stringBuilder.toString();
	}

	public static double computeMedian(ArrayList<Point> points){
		int middle = points.size()/2;

		if (points.size() % 2 == 1) {
			return points.get(middle).x;
		} else {
			return (points.get(middle-1).x + points.get(middle).x) / 2.0;
		}
	}

	public static int findClosestPoint(double y_coordinate, ArrayList<PointPair> pointsAndIntersections) {
		if(pointsAndIntersections.size() == 0) {
			return -1;
		}
		int highIndex = pointsAndIntersections.size() -1;
		int lowIndex = 0;
		int index = Integer.MAX_VALUE;	
		while(highIndex > lowIndex) {
			index = (highIndex + lowIndex) / 2;
			Point intersectionPoint = pointsAndIntersections.get(index).p2;
			if(pointsAndIntersections.get(lowIndex).p2.y == y_coordinate) {
				return lowIndex;
			} else if(intersectionPoint.y == y_coordinate) {
				return index;
			} else if(pointsAndIntersections.get(highIndex).p2.y == y_coordinate) {
				return highIndex;
			} else if(intersectionPoint.y > y_coordinate) {
				if (highIndex == index) {
					return highIndex;
				}
				highIndex = index;
			} else {
				if(lowIndex == index) {
					return lowIndex;
				}
				lowIndex = index;
			}
		}
		return highIndex;
	}

	public static void allClosestPairs(ArrayList<Point> points) {
		if(points.size() < 2) {
			return;
		}
		if(points.size()==2) {
			nearestNeighbours[points.get(0).id] = points.get(1);
			nearestNeighbours[points.get(1).id] = points.get(0);
			return;
		}

		if(points.size()==3) {
			for(Point currPoint : points) {
				for(Point comparePoint : points) {
					if(!currPoint.equals(comparePoint)) {
						double distance = currPoint.computeDistanceToOtherPoint(comparePoint);
						double storedDistance = currPoint.computeDistanceToOtherPoint(nearestNeighbours[currPoint.id]);
						if(distance < storedDistance) {
							nearestNeighbours[currPoint.id] = comparePoint;
						}
					}
				}
			}
			return;
		}

		Collections.sort(points, new PointXComparator());
		double median = computeMedian(points);

		ArrayList<Point> leftHalf = new ArrayList<Point>();
		ArrayList<Point> rightHalf = new ArrayList<Point>();

		for(Point currPoint : points) {
			if(currPoint.x <= median) {
				leftHalf.add(currPoint);
			} else {
				rightHalf.add(currPoint);
			}
		}

		allClosestPairs(leftHalf);
		allClosestPairs(rightHalf);

		ArrayList<PointPair> leftPointsAndIntersections = new ArrayList<PointPair>();
		ArrayList<PointPair> rightPointsAndIntersections = new ArrayList<PointPair>();

		for(Point currPoint : leftHalf) {
			Point NN = nearestNeighbours[currPoint.id];
			Circle NNBall = new Circle(currPoint.computeDistanceToOtherPoint(NN), currPoint);

			Point intersectionWithMedian = NNBall.computeIntersectionPointMedian(median);

			PointPair pointAndIntersection = new PointPair(currPoint, intersectionWithMedian);

			if(pointAndIntersection.p2 != null) {
				leftPointsAndIntersections.add(pointAndIntersection);
			}
		}

		for(Point currPoint : rightHalf) {
			Point NN = nearestNeighbours[currPoint.id];
			Circle NNBall = new Circle(currPoint.computeDistanceToOtherPoint(NN), currPoint);

			Point intersectionWithMedian = NNBall.computeIntersectionPointMedian(median);

			PointPair pointAndIntersection = new PointPair(currPoint, intersectionWithMedian);

			if(pointAndIntersection.p2 != null) {
				rightPointsAndIntersections.add(pointAndIntersection);
			}
		}

		Collections.sort(leftPointsAndIntersections, new PointPairIntersectionYCoordinateComparator());
		Collections.sort(rightPointsAndIntersections, new PointPairIntersectionYCoordinateComparator());

		for(Point currPoint : leftHalf) {
			double y_coordinate = currPoint.y;

			int index = findClosestPoint(y_coordinate, rightPointsAndIntersections);

			if(rightPointsAndIntersections.size() > 0) {
				for(int i = index; i > index - 4 && i > -1; i--) {
					Point comparePoint = rightPointsAndIntersections.get(i).p1;
					double newDistance = comparePoint.computeDistanceToOtherPoint(currPoint);
					double storedDistance = comparePoint.computeDistanceToOtherPoint(nearestNeighbours[comparePoint.id]);

					if(newDistance < storedDistance) {
						nearestNeighbours[comparePoint.id] = currPoint;
					}
				}

				for(int i = index; i < index + 4 && i < rightPointsAndIntersections.size(); i++) {
					Point comparePoint = rightPointsAndIntersections.get(i).p1;
					double newDistance = comparePoint.computeDistanceToOtherPoint(currPoint);
					double storedDistance = comparePoint.computeDistanceToOtherPoint(nearestNeighbours[comparePoint.id]);

					if(newDistance < storedDistance) {
						nearestNeighbours[comparePoint.id] = currPoint;
					}
				}
			}
		}

		for(Point currPoint : rightHalf) {
			double y_coordinate = currPoint.y;

			int index = findClosestPoint(y_coordinate, leftPointsAndIntersections);
			if(leftPointsAndIntersections.size() > 0) {
				for(int i = index; i > index -4 && i > -1; i--) {
					Point comparePoint = leftPointsAndIntersections.get(i).p1;
					double newDistance = comparePoint.computeDistanceToOtherPoint(currPoint);
					double storedDistance = comparePoint.computeDistanceToOtherPoint(nearestNeighbours[comparePoint.id]);

					if(newDistance < storedDistance) {
						nearestNeighbours[comparePoint.id] = currPoint;
					}
				}

				for(int i = index; i < index + 4 && i < leftPointsAndIntersections.size(); i++) {
					Point comparePoint = leftPointsAndIntersections.get(i).p1;
					double newDistance = comparePoint.computeDistanceToOtherPoint(currPoint);
					double storedDistance = comparePoint.computeDistanceToOtherPoint(nearestNeighbours[comparePoint.id]);

					if(newDistance < storedDistance) {
						nearestNeighbours[comparePoint.id] = currPoint;
					}
				}
			}
		}
	}
}
