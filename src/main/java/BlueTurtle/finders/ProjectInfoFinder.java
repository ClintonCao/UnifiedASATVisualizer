package BlueTurtle.finders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import BlueTurtle.computers.LOCComputer;
import BlueTurtle.gui.GUIController.ASAT;
import BlueTurtle.uav.CodeFile;
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
	@Getter private static HashMap<ASAT, ArrayList<String>> outputFilesPaths = new HashMap<ASAT, ArrayList<String>>();

	/**
	 * Find the class files or the output files of the ASATs (recursively) in the directory.
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
				computeFileInfo(subdir);
			}
		}
	}

	/**
	 * Compute the information of the file that is found.
	 * 
	 * @param file
	 *            the file that is found.
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 */
	public void computeFileInfo(File file) throws IOException {
		// if it is a java file, then compute information for the class.
		if (file.getName().endsWith(".java")) {
			computeClassInfo(file);
		} else if (file.getName().endsWith(".xml") && checkForASATOutputFile(file.getName())) {
			//If it is an output file the compute the information of the output file.
			computeOutputFileInfo(file);
		}
	}

	/**
	 * Computes all the information of a class file.
	 * 
	 * @param file
	 *            the class file.
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 */
	public static void computeClassInfo(File file) throws IOException {
		String path = file.getAbsolutePath();
		classPaths.add(path); // add the path
		classLocs.put(path, LOCComputer.getInstance().computeLOC(path)); // add the LOC
		String packageName = PackageNameFinder.getInstance().findPackageName(path); //find the package name
		classPackage.put(path, packageName); // put entry of class and its package
		packages.add(packageName); // add package to the list of packages
	}
	
	/**
	 * Compute the paths of the output files of the ASAT. 
	 * @param file 
	 * 			   output file of the ASAT.
	 */
	public void computeOutputFileInfo(File file) {
		switch (file.getName()) {
		case "checkstyle-result.xml":
			addOutputFilePath(ASAT.CheckStyle, file.getAbsolutePath());
			break;
		case "findbugs.xml":
			addOutputFilePath(ASAT.FindBugs, file.getAbsolutePath());
			break;
		case "pmd.xml": 
			addOutputFilePath(ASAT.PMD, file.getAbsolutePath());
			break;
		default:
			//unreachable
			throw new IllegalArgumentException("This is not an output file of an ASAT.");
		}
	}
	
	/**
	 * Add the path of an output file to the "outputFilesPaths".
	 * @param asat 
	 * 				the ASAT type.
	 * @param filePath 
	 * 				the path of the output file.
	 */
	public void addOutputFilePath(ASAT asat, String filePath) {
		if (outputFilesPaths.containsKey(asat)) {
			outputFilesPaths.get(asat).add(filePath);
		}
		else {
			outputFilesPaths.put(asat, new ArrayList<String>());
			outputFilesPaths.get(asat).add(filePath);
		}
	}
	
	/**
	 * Check whether the file is an output file of an ASAT.
	 * @param fileName
	 *            the name of the file.
	 * @return 
	 * 			returns true if it is an output file of an ASAT.
	 */
	public Boolean checkForASATOutputFile(String fileName) {
		return (fileName.contains("checkstyle-result") || fileName.contains("pmd.") || fileName.contains("findbugs."));
	}

	/**
	 * Retrieves the code from every file located through the filepath in
	 * classPaths. The code is stored in a CodeFile object which is added to the
	 * codeFiles field. These files are then written to an output file.
	 * 
	 * @throws IOException
	 *             if file is not found, inaccessible, etc.
	 */
	public void retrieveCodeFiles() throws IOException {
		for (String classPath : classPaths) {
			File currFile = new File(classPath);
			CodeFile codeFile = new CodeFile();
			codeFile.setPath(classPath);
			codeFile.getCodeFromFile(currFile);
			codeFiles.add(codeFile);
		}
		JSWriter jswriter = JSWriter.getInstance();

		jswriter.writeSourceCodeToJS(codeFiles, "visualization/JSON/outputCodeJSON.js");
	}
}
