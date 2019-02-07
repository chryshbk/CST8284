package fileIO;
import java.io.File;

public class FileInfo {
	
	public String infoStr;
	
	public FileInfo(File f){
	   if (f.exists() && f.isFile()){
		  infoStr = "File Name: " + f.getName() + "\n";
		  infoStr += "File Path: " + f.getAbsolutePath() + "\n";
		  infoStr += "Length " + f.length() + " bytes" + "\n";
		  infoStr += "Total Number of Words: " + WordDump.getWordCtr() + "\n";
		  infoStr += "File Read Status: " + f.canRead() + "\n";
		  infoStr += "File Write Status: " + f.canWrite() + "\n";
		  infoStr += "Is File Hidden?: " + f.isHidden() + "\n";
	   }
	   else
		  infoStr = f.getName() + " does not exist or is not a file";
	}
	
	public String getInfoString(){
		return infoStr;
	}
	
	
}
