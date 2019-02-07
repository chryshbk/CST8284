package collections;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class WordDump extends Application {

	private static String wordFileName = "\\Users\\User\\Workspace\\CST8284_Lab9\\src\\cst8284\\collections\\LongWordList.txt";

		public static String getFileName(){
			return wordFileName;
		}
		public static void setFileName(String f){
			wordFileName = f;
		}
		
		public static String getStringFromFile(){
			Scanner s = null;
			String word = "";
			try{
				s = new Scanner(new File(getFileName()));
			
			} catch(FileNotFoundException e){
				e.printStackTrace();
				System.out.println("Could not find the file");
			}
			while (s.hasNext()){
				word += s.next() + "\n";
			}
			return word;
		}
		
		public static StringBuilder getStringBuilderFromFile(){
			Scanner s = null;
			StringBuilder sb = new StringBuilder();
			
			try{
				s = new Scanner(new File(getFileName()));
			
			} catch(FileNotFoundException e){
				e.printStackTrace();
				System.out.println("Could not find the file");
			}
			while (s.hasNext()){
				sb.append(s.next() + "\n");
			}
			return sb;
		}
		
		public static ArrayList<String> getArrayListFromFile(){
			Scanner s = null;
			ArrayList <String> arList = new ArrayList<>();
			
			try{
				s = new Scanner(new File(getFileName()));
			
			} catch(FileNotFoundException e){
				e.printStackTrace();
				System.out.println("Could not find the file");
			}
			while (s.hasNext()){
				arList.add(s.next() + "\n");
			}
			return arList;
		}
		
		public static void main(String[] args){
			launch(args);
		}
		
		@Override
		public void start(Stage primaryStage){
			
			try{
			
			BorderPane border = new BorderPane();
			
			primaryStage.setTitle("Welcome to JavaFx");
			
			ObservableList <String> word = FXCollections.observableList(getArrayListFromFile());
			ListView<String> list = new ListView<>(word);
			
			VBox orders = new VBox();
			Button alphOrder = new Button("Alphabetical Order");
			Button reverseOrder = new Button("Reverse Order");
			
			reverseOrder.setOnMouseClicked(eventOne -> {
			FXCollections.sort(word);
			FXCollections.reverse(word);});
			
			alphOrder.setOnMouseClicked(eventTwo ->
			FXCollections.sort(word));

			
			orders.getChildren().addAll(alphOrder, reverseOrder);
			
			border.setLeft(list);
			border.setRight(orders);
			
			Scene myNewScene = new Scene(border, 640, 480);
			primaryStage.setScene(myNewScene);
			primaryStage.show();
			
		}catch(Exception e){
			e.printStackTrace();		
		}
		
	}		
}
