/**
 * File name: TaskManager.java
 * Author: Chrystian Rafael Sanches dos Santos, 040862854
 * Course: CST8284 - OOP
 * Assignment: 3
 * Date: 21/04/2017
 * Professor: David Houtmad
 * Purpose: The purpose of this class is check a ToDo file list before loading, check if it is valid and to load all the changes made by the user into the file. It also
 * checks the absolute path of the file and if it exists.
 * Class list: ToDo.java
 * 
 * 
 */
package cst8284.assignment1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
	/**
	 * FileUtils has the purpose to check the File which will load in the class TaskManager, check the name of the file, its absolute path (from where the file has been
	 * taken from) and writes all the changes made there.
	 * @author Chrystian Rafael Sanches dos Santos and David Houtmad.
	 * @version 3.0
	 * @see cst8284.assignment1
	 * @since 2017-03-26
	 */
public class FileUtils {

	private static String relPath = "ToDoArray.todo";
	/**
	 * Gets all the data from the File and store into an ArrayList of ToDos. It uses the java.io.FileInputStream and ObjectInputStream to make this function.
	 * @param fileName the file name
	 * @return toDos the ArrayList containing the ToDo objects.
	 */
	public ArrayList<ToDo> getToDoArray(String fileName) {
		ArrayList<ToDo> toDos = new ArrayList<>();
		try {
			FileInputStream fis = getFIStreamFromAbsPath(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);

			while(true) {
				 toDos.add((ToDo)(ois.readObject()));
			} // end of the while loop.
		} catch (ClassNotFoundException | IOException e ) {
		} // end of the try catch statement.
		return toDos;
	} // end of the getToDoArray method.
	/**
	 * Gets the FileInputStream from the absolute path of the File.
	 * @param absPath the absolute path of the file.
	 * @return fis the FileInputStream object.
	 */
	public static FileInputStream getFIStreamFromAbsPath(String absPath){
		FileInputStream fis = null;
		absPath = FileUtils.getAbsPath();
		try {
     		fis = new FileInputStream(absPath);
		} catch (IOException e){
			e.printStackTrace();
		} // end of the try catch statement.
		return fis;
	} // end of the method.
	/**
	 * Sets the changes made in the ArrayList and stores it in the File using the java.io.FileOutputStream and ObjectOutputStream.
	 * @param toDo the ArrayList of ToDo objects.
	 * @param fileName the file name.
	 */
	public static void setToDoArrayListToFile(ArrayList<ToDo> toDo, String fileName) {
		fileName = FileUtils.getAbsPath();
		try(
			FileOutputStream output = new FileOutputStream(fileName);
			ObjectOutputStream objOut = new ObjectOutputStream(output);
			){
			for (ToDo td : toDo){
				objOut.writeObject(td);
			} // end of the for loop.
			objOut.close();
		} catch (IOException e){
			e.printStackTrace();
		} // end of the try catch statement.
	} // end of the method.
	/**
	 * Gets the relative path of the file.
	 * @return relPath the relative path of the file.
	 */
	public static String getAbsPath() {
		return relPath;
	} // end of the getter.
	/**
	 * Gets the absolute path of the file.
	 * @param f the file.
	 * @return f the absolute path of the file.
	 */
	public static String getAbsPath(File f) {
		return f.getAbsolutePath();
	} // end of the getter.
	/**
	 * Sets the absolute path of the file.
	 * @param f the file
	 */
	public static void setAbsPath(File f) { 
		relPath = (fileExists(f))? f.getAbsolutePath():""; 
	} // end of the setter.
	/**
	 * Checks if the file exists or if it is null.
	 * @param f the file
	 * @return f the existing, readable and not null file.
	 */
	public static Boolean fileExists(File f) {
		return (f != null && f.exists() && f.isFile() && f.canRead());
	} // end of the method
} // end of the class.