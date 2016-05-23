package BlueTurtle.finders;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

/**
 * Find all information of the project given the source directory of project.
 * 
 * @author BlueTurtle.
 *
 */
public class ProjectInfoFinder {

	@Getter private static ArrayList<String> classPaths = new ArrayList<String>();
	@Getter private static HashMap<String, Integer> classLocs = new HashMap<String, Integer>();
	@Getter private static HashMap<String, String> classPackage = new HashMap<String, String>();
	@Getter private static Set<String> packages = new HashSet<String>();

	/**
	 * Find the class files (recursively) in the directory.
	 * 
	 * @param srcDir
	 *            the source directory to search in.
	 */
	public void findFiles(File srcDir) {
		// find all subdirectories;
		File[] subdirs = srcDir.listFiles();
		// Go through all subdirectories
		for (File subdir : subdirs) {
			// if it is a directory, keep searching for file.
			if (subdir.isDirectory()) {
				findFiles(subdir);
			} else {
				// if file is found, compute the informations.
				computeInformation(subdir);
			}
		}
	}

	/**
	 * Compute the informations of the class that is found.
	 * 
	 * @param file
	 *            the file of the class.
	 */
	public void computeInformation(File file) {
		// if it is a java file, then compute information for the class.
		if (file.getName().endsWith(".java")) {
			String path = file.getAbsolutePath();
			classPaths.add(path); // add the path

			int loc = LOCFinder.findLOC(path);
			classLocs.put(path, loc);

			String packageName = PackageNameFinder.findPackageName(path);
			classPackage.put(path, packageName);
			packages.add(packageName);
		}
	}

}
