package BlueTurtle.finders;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This class can be used for finding the package name of a component.
 * 
 * @author BlueTurtle.
 *
 */
@SuppressWarnings("checkstyle:hideutilityclassconstructor")
public class PackageNameFinder {

	/**
	 * Find the package name based on the given file path.
	 * 
	 * @param filePath
	 *            the path to the file.
	 * @return the package name.
	 */
	public static String findPackageName(String filePath) {
		String packageName = "default";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			String[] packageInfo = line.split(" ");
			if (packageInfo[0].equals("package")) {
				packageName = packageInfo[1].split(";")[0];
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
		return packageName;
	}

}
