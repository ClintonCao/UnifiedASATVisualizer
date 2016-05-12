import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.StringBuilder;
import java.util.ArrayList;

class naiveNearestNeighbour {
	
	public static void main(String[] args) throws FileNotFoundException {
		int i = 1;
		while(i <= 10000000) {		
			int points = i;
			long startTime = System.currentTimeMillis();
			run(points);
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("Execution naive nearest neighbours with " + points + " took " + elapsedTime + " ms\n");
			i*=10;
		}
	}

public static class Point {
		public double x;
		public double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	public static String run(int numPoints){
		
		ArrayList<Point> temp = new ArrayList<Point>();
		for(int i = 0; i < numPoints; i++) {
			double x = i;
			double y = i;
			temp.add(new Point(x, y));
		}

		Point[] points = new Point[temp.size()];

		for(int i = 0; i < temp.size(); i++) {
			points[i] = temp.get(i);
		}

		double[] distances = new double[numPoints];
		int[] closestPointID = new int[numPoints];

		for(int i = 0; i < distances.length; i++) {
			distances[i] = Double.MAX_VALUE;
		}

		for(int i = 0; i < points.length; i++) { 
			Point currPoint = points[i];
			for(int z = 0; z < points.length; z++) {
				if(i != z) {
					Point comparePoint = points[z];
					double distance = Math.sqrt(Math.pow((comparePoint.x - currPoint.x), 2) + Math.pow((comparePoint.y - currPoint.y), 2));
					if(distance < distances[i]) {
						distances[i] = distance;
						closestPointID[i] = z;
					}
				}
			}
		}

		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < closestPointID.length; i++) {
			stringBuilder.append(closestPointID[i]);
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
}
//