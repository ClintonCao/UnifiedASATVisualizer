package BlueTurtle.finders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import BlueTurtle.TSE.CodeFile;
import BlueTurtle.computers.LOCComputer;
import BlueTurtle.writers.JSWriter;
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
	@Getter private ArrayList<CodeFile> codeFiles = new ArrayList<CodeFile>();

	/**
	 * Find the class files (recursively) in the directory.
	 * 
	 * @param srcDir
	 *            the source directory to search in.
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the files.
	 */
	public void findFiles(File srcDir) throws IOException {
		// Find all subdirectories.
		File[] subdirs = srcDir.listFiles();

		// Go through all subdirectories.
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
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 */
	public void computeInformation(File file) throws IOException {
		// if it is a java file, then compute information for the class.
		if (file.getName().endsWith(".java")) {
			String path = file.getAbsolutePath();
			classPaths.add(path); // add the path
			classLocs.put(path, LOCComputer.getInstance().computeLOC(path)); // add the LOC
			String packageName = PackageNameFinder.getInstance().findPackageName(path); // find the package name
			classPackage.put(path, packageName); // put entry of class and its package
			packages.add(packageName); // add package to the list of packages
		}
	}

	/**
	 * Retrieves the code from every file located through the filepath in classPaths. 
	 * The code is stored in a CodeFile object which is added to the codeFiles field.
	 * These files are then written to an output file.
	 * @throws IOException
	 * 				if file is not found, inaccessible, etc.
	 */
	public void retrieveCodeFiles() throws IOException {
		for(String classPath : classPaths) {
			File currFile = new File(classPath);
			CodeFile codeFile = new CodeFile(); 
			codeFile.setPath(classPath);
			codeFile.getCodeFromFile(currFile);
			codeFiles.add(codeFile);
		}
		JSWriter jswriter = JSWriter.getInstance();

		jswriter.writeSourceCodeToJS(codeFiles, "./src/main/resources/CodeFiles.js");
	}
}
