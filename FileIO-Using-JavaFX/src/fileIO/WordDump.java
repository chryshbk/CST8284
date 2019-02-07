package fileIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

@SuppressWarnings("restriction")
public class WordDump extends Application {

		private String fileName;
		private static int wordCtr;
		private FileInfo fileInfo;
		
		public String getFileName(){
			return fileName; 
		}
		public void setFileName(String f){
			this.getFileName();
		}
		
		public static int getWordCtr(){
			return wordCtr;
		}
		
		public static void incrWordCtr(){
			wordCtr++;
		}
		
		public static String getFileContents(File f){
			Scanner s = null;
			String word = "";
			
			try{
				s = new Scanner(f);
			
			} catch(FileNotFoundException e){
				e.printStackTrace();
				System.out.println("Could not find the file");
			}
			while (s.hasNext()){
				word += s.next() + "\n";
				incrWordCtr();
			}
			return word;
		}
		
		public static void main(String[] args){
			launch(args);
		}
		
		@Override
		public void start(Stage primaryStage){
			
			try{
			
			BorderPane border = new BorderPane();
			
			primaryStage.setTitle("Welcome to JavaFx");
			
			File LongTxt = new File ("\\Users\\User\\workspace\\CST8284_Lab6\\src\\LongWordList.txt");
			Text words = new Text(getFileContents(LongTxt));
			
			fileInfo = new FileInfo(LongTxt);
			
			Text info = new Text(fileInfo.getInfoString());
			
			ScrollPane scroll = new ScrollPane(words);
			
			scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			scroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
			
			border.setCenter(info);
			border.setLeft(scroll);
			
			Scene myNewScene = new Scene(border, 640, 480);
			primaryStage.setScene(myNewScene);
			primaryStage.show();
			
		}catch(Exception e){
			e.printStackTrace();		
		}
		
	}		
}
