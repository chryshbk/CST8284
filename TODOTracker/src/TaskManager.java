/**
 * File name: TaskManager.java
 * Author: Chrystian Rafael Sanches dos Santos, 040862854
 * Course: CST8284 - OOP
 * Assignment: 3
 * Date: 21/04/2017
 * Professor: David Houtmad
 * Purpose: The purpose of this class is to load a ToDo list containing the title, subject, priority and due date into a Stage, allow the user to modify the data and make their
 * own ToDo list. This class inherits javafx.application.Application in order to load everything into a Stage. It also uses the FileUtils class for some functions and
 * the ToDo class as well to get all the data from the ToDo file.
 * Class list: FileUtils.java, ToDo.java, Task.java
 * 
 * 
 */
package cst8284.assignment1;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.util.Duration;
	/**
	 * This class has the purpose to open .todo files and load into a scene. This class instantiates objects from the ToDo class and uses the FileUtils class to load the files.
	 * The user is able to manage his ToDo list by editing task, subject, priority or due date and it asks if the user wants to save changes made in the file. It also shows a 
	 * table list of title and priority on the left hand side where the user can click on the desired todo title/priority and it pops up in the center of the scene.
	 * On the right hand side there are 6 buttons where the user can sort the elements by title(task), subject, duedate, priority, completed or reverse the order of the ToDos.
	 * On the top left there is the file menu bar, where the user can open a new ToDo file, save the current one, add a new blank one, remove or exit the application.
	 * 
	 * 
	 * @author Chrystian Rafael Sanches dos Santos, David Houtmad, Carlos Guillermo Rivera Negrete.
	 * @version 3.0
	 * @see cst8284.assignment1
	 * @since 2017-03-26
	 *
	 */
public class TaskManager extends Application {

	private static ArrayList<ToDo> toDoArray;
	private static int currentToDoElement;
	private Stage primaryStage;
	
	private FileChooser fc;
	
	private TextField tempTitle;
	private TextField tempDueDate;
	private TextArea tempSubject;
	private RadioButton tempP1, tempP2, tempP3;
	
	private int priority;
	/**
	 * This overriden method will start the application. It will pop up a scene containing the splash scene. 
	 * Before closing, at any moment the user wants to shutdown the application it will check if the current ToDo was modified and if yes, it asks if the user wants 
	 * to save before exiting and after that it will ask if the user really wants to exit.
	 * @param primaryStage the primary Stage
	 * @throws IOException in case the file does not work.
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
	try{
		primaryStage.setScene(getSplashScene(new Text("Click here to open")));
		setPrimaryStage(primaryStage);
		getPrimaryStage().setTitle("To Do List 2");
		getPrimaryStage().setResizable(false);
		getPrimaryStage().show();
		
		getPrimaryStage().setOnCloseRequest(e-> {
			if (getToDoArray() != null){
				if (isToDoArrayListDirty()){
					saveToDo();
				} // end of the isToDoArrayListDirty if statement.
				Alert alert = getAlertBox("Exit");
				Optional <ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.CANCEL){
					e.consume();
				} // end of the result check if statement.
			} else
				Platform.exit();
		}); // end of the setOnCloseRequest function.
		} catch (Exception e){
			e.printStackTrace();
		} // end of the catch.
	} // end of the start method.
	/**
	 * Gets the splash scene, which is the first scene before opening the file.
	 * When clicked, it will call the method openToDo and it will occupy the current scene after
	 * chosen the correct .todo file.
	 * @param defaultText the clickable animated text.
	 * @return scene the current scene.
	 */
	private Scene getSplashScene(Text defaultText) {
		
		defaultText.setStyle("-fx-font: 40px Tahoma; -fx-stroke: black; -fx-stroke-width: 1;");
		StackPane startPane = new StackPane();
		startPane.getChildren().add(defaultText);
		
		ScaleTransition scaleTrans = new ScaleTransition(Duration.seconds(5), defaultText);
		scaleTrans.setFromX(3.20);
		scaleTrans.setToX(1.40);
		scaleTrans.setFromY(3.20);
		scaleTrans.setToY(1.20);
		scaleTrans.setCycleCount(10);
		
		PathTransition pt = new PathTransition(Duration.INDEFINITE, defaultText);
		pt.setCycleCount(4);
		pt.isAutoReverse();
		
		SequentialTransition st = new SequentialTransition(defaultText, scaleTrans, pt);
		st.play();
		
		Scene scene = new Scene(startPane, 1024, 768);
		
		scene.setOnMouseClicked(e ->openToDo(scene));
		
		return scene;
	} // end of the getSplashScene method.
	/**
	 * Gets the scene where the ToDo file will pops up.
	 * @param td the ToDo.
	 * @return scene the scene where the ToDo will appear.
	 */
	private Scene getToDoScene(ToDo td) {
		Scene scene = getPrimaryStage().getScene();
		scene.setRoot(getToDoPane(td));
		return scene;
	} // end of the getToDoScene method.
	/**
	 * Gets the pane loaded in the scene containing all the fields of the ToDo file, a TableView on the left hand side inside a VBox and six buttons on the right hand side
	 * inside a VBox. It also gets the bottom buttons where the user can change scenes going to another ToDo. There is also a menuBar "File" on the top-right corner
	 * containing all its functions. The buttons are used to sort the ToDos by title, subject, priority, dueDate and if it is completed. It also has a reverse button which
	 * reverses the order of the ToDos.
	 * @param td the ToDo object
	 * @return rootNode the node where everything in the scene is loaded.
	 */
	private BorderPane getToDoPane(ToDo td) {
		BorderPane rootNode = new BorderPane();
		
		VBox vbLeft = new VBox();
		vbLeft.setAlignment(Pos.CENTER);
		vbLeft.setMinWidth(120);
		
		ObservableList <ToDo> list = FXCollections.observableList(getToDoArray());
		TableView <ToDo> table = new TableView<>(list);
		
		TableColumn <ToDo, String> tcT = new TableColumn<>("Title");
		tcT.setCellValueFactory(new PropertyValueFactory<ToDo, String>("Title"));
		
		TableColumn <ToDo, String> tcP = new TableColumn<>("Priority");
		tcP.setCellValueFactory(new PropertyValueFactory<ToDo, String>("Priority"));
		
		table.getColumns().setAll(tcT, tcP);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		table.setOnMouseClicked(e ->{
			checkArrayListDirty();
			if (table.getSelectionModel().getSelectedIndex() < 0 || table.getSelectionModel().getSelectedIndex() > getToDoArray().size()-1);
			else{
			setToDoElement(table.getSelectionModel().getSelectedIndex());
			skipScene();
			}
		}); // end of the setOnMouseClicked function.
		
		vbLeft.getChildren().add(table);

		VBox vbRight = new VBox();
		vbRight.setMinWidth(120);
		
		vbLeft.setAlignment(Pos.CENTER);
		vbRight.setAlignment(Pos.CENTER);
		
		Button sortByTitle = new Button("SortByTitle"); 
		sortByTitle.setMaxWidth(Double.MAX_VALUE);
		sortByTitle.setOnAction(e -> {checkArrayListDirty(); getToDoArray().sort((td1, td2)->td1.getTitle().compareTo(td2.getTitle())); setToDoElement(0); skipScene();});
		
		Button sortBySubject = new Button("SortBySubject");
		sortBySubject.setMaxWidth(Double.MAX_VALUE);
		sortBySubject.setOnAction(e -> {checkArrayListDirty(); getToDoArray().sort((td1, td2)->td1.getSubject().compareTo(td2.getSubject())); setToDoElement(0); skipScene();});
		
		Button sortByDueDate = new Button("SortByDueDate"); 
		sortByDueDate.setMaxWidth(Double.MAX_VALUE);
		sortByDueDate.setOnAction(e -> {checkArrayListDirty(); getToDoArray().sort((td1, td2)->td1.getDueDate().compareTo(td2.getDueDate())); setToDoElement(0); skipScene();});
		
		Button sortByPriority = new Button("SortByPriority");
		sortByPriority.setMaxWidth(Double.MAX_VALUE);
		sortByPriority.setOnAction(e -> {checkArrayListDirty(); getToDoArray().sort((td1, td2)->Integer.compare(td1.getPriority(), td2.getPriority())); setToDoElement(0); skipScene();});
		
		Button sortByCompleted = new Button("SortByCompleted");
		sortByCompleted.setMaxWidth(Double.MAX_VALUE);
		sortByCompleted.setOnAction(e -> {checkArrayListDirty(); getToDoArray().sort((td1, td2)->Boolean.compare(td1.isCompleted(), td2.isCompleted())); setToDoElement(0); skipScene();});
		
		Button sortReverse = new Button("SortReverse");
		sortReverse.setMaxWidth(Double.MAX_VALUE);
		sortReverse.setOnAction(e -> {checkArrayListDirty(); Collections.reverse(getToDoArray()); setToDoElement(0); skipScene();});
		
		vbRight.setSpacing(10);
		vbRight.setPadding(new Insets(0, 20, 10, 20));
		vbRight.getChildren().addAll(sortByTitle, sortBySubject, sortByDueDate, sortByPriority,
									 sortByCompleted, sortReverse);
		
		rootNode.setLeft(vbLeft);
		rootNode.setRight(vbRight);
		rootNode.setBottom(getBottomPane(td));
		rootNode.setCenter(getCenterPane(td));
	    rootNode.setTop(getMenuBar());
	    
		return rootNode;
	} // end of the getToDoPane method
	/**
	 * This getter contains a GridPane containing the temporary boxes, its labels and its positions in the scene.
	 * @param td the ToDo object.
	 * @return gp the GridPane containing the position of the elements.
	 */
	private GridPane getCenterPane(ToDo td) {

		setTempTitle(new TextField(td.getTitle()));
		setTempSubject(new TextArea(td.getSubject()));
		setTempDueDate(new TextField(td.getDueDate().toString().substring(0,10)));
		
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(50, 10, 10, 10));
		gp.setVgap(10);
		gp.setHgap(40);
		gp.setPrefWidth(1200);

		gp.setStyle("-fx-font: 12px Tahoma; -fx-stroke: black; -fx-stroke-width: 1;");

		Label lblTask = new Label("Task");
		gp.add(lblTask, 0, 0);
		gp.add(getTempTitle(), 1, 0);

		Label lblSubject = new Label("Subject");
		gp.add(lblSubject, 0, 1);
		gp.add(getTempSubject(), 1, 1);

		Label lblDate = new Label("Due Date");
		gp.add(lblDate, 0, 2);
		gp.add(getTempDueDate(), 1, 2);

		Label lblPriority = new Label("Priority");
		gp.add(lblPriority, 0, 3);
		
		getTempTitle().setOnMouseClicked(e -> getToDoArray().get(currentToDoElement).setRemove(true));
		getTempTitle().setOnKeyPressed(e -> getToDoArray().get(currentToDoElement).setRemove(true));
		
		getTempDueDate().setOnMouseClicked(e -> getToDoArray().get(currentToDoElement).setRemove(true));
		getTempDueDate().setOnKeyPressed(e -> getToDoArray().get(currentToDoElement).setRemove(true));
		
		getTempSubject().setOnMouseClicked(e -> getToDoArray().get(currentToDoElement).setRemove(true));
		getTempSubject().setOnKeyPressed(e -> getToDoArray().get(currentToDoElement).setRemove(true));
		
		gp.add(getTempPriority(), 1, 3);
		
		return gp;
	} // end of the getCenterPane method.
	/**
	 * Returns a HBox containing RadioButtons for the Priority field.
	 * @return HBox
	 */
	private HBox getTempPriority(){
		ToggleGroup tglGroup = new ToggleGroup();
		
		setTempP1(new RadioButton("1   "));
		setTempP2(new RadioButton("2   "));
		setTempP3(new RadioButton("3   "));
		
		if (getToDoArray().get(currentToDoElement).getPriority() == 1){
			getTempP1().setSelected(true);}
		else if (getToDoArray().get(currentToDoElement).getPriority() == 2){
			getTempP2().setSelected(true);}
		else if (getToDoArray().get(currentToDoElement).getPriority() == 3){
			getTempP3().setSelected(true);}
		
		getTempP1().setToggleGroup(tglGroup);
		getTempP2().setToggleGroup(tglGroup);
		getTempP2().setPadding(new Insets(0, 15, 0, 15));
		getTempP3().setToggleGroup(tglGroup);
			
		getTempP1().setOnMouseClicked(e -> getToDoArray().get(currentToDoElement).setRemove(true));
		getTempP2().setOnMouseClicked(e -> getToDoArray().get(currentToDoElement).setRemove(true));
		getTempP3().setOnMouseClicked(e -> getToDoArray().get(currentToDoElement).setRemove(true));
		
		HBox hRadioButtons = new HBox();
		hRadioButtons.getChildren().addAll(getTempP1(), getTempP2(), getTempP3());
		
		return hRadioButtons;
	} // end of the getTempPriority method.
	/**
	 * It is basically a method to skip to the next scene.
	 */
	private void skipScene(){
		getPrimaryStage().setScene(getToDoScene(getToDoArray().get(currentToDoElement)));
	} // end of the skipScene method.
	/**
	 * Gets all the File menu containing MenuItems(Open ToDo, Save ToDo, Add ToDo, Remove ToDo and Exit) and its functions when clicked.
	 * @return MenuBar
	 */
	private MenuBar getMenuBar(){
		MenuBar menuBar = new MenuBar();
	    menuBar.prefWidthProperty().bind(getPrimaryStage().widthProperty());
	    
	    Menu fileMenu = new Menu("File");
	    
	    MenuItem openMenuItem = new MenuItem("Open");
	    openMenuItem.setOnAction(actionEvent -> { checkArrayListDirty(); openToDo(getPrimaryStage().getScene()); });
	    
	    MenuItem saveMenuItem = new MenuItem("Save");
	    saveMenuItem.setOnAction(actionEvent -> saveToDo());
	    
	    MenuItem addMenuItem = new MenuItem("Add ToDo");
	    addMenuItem.setOnAction(actionEvent -> addToDo());
	    
	    MenuItem removeMenuItem = new MenuItem ("Remove ToDo");
	    removeMenuItem.setOnAction(actionEvent -> deleteToDo());
	    
	    MenuItem exitMenuItem = new MenuItem("Exit");
	    exitMenuItem.setOnAction(actionEvent -> {
	    	if (isToDoArrayListDirty()){
	    		saveToDo();
	    	} // end of the isToDoArrayListDirty if statement.
	    	Alert exit = getAlertBox("Exit");
	    	Optional<ButtonType> result = exit.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    	Platform.exit();
	    	} // end of the result check if statement.
	    }); // end of the setOnAction function.

	    fileMenu.getItems().addAll(openMenuItem, saveMenuItem, addMenuItem,
	    	removeMenuItem, new SeparatorMenuItem(), exitMenuItem);
	    menuBar.getMenus().addAll(fileMenu);
	    
	    return menuBar;
	} // end of the getMenuBar method.
	/**
	 * This method gets the node of the ToDo scene, where the function of the four buttons are: skip scenes to the first element, previous element, next element or 
	 * last and check if the element was modified. 
	 * @param td the ToDo object 
	 * @return paneCtlBtns the HBox containing all the buttons
	 */
	private HBox getBottomPane(ToDo td) {
		
		HBox paneCtlBtns = new HBox(10);
		paneCtlBtns.setStyle("-fx-font: 50px Tahoma; -fx-stroke: black; -fx-stroke-width: 1;");
		paneCtlBtns.setAlignment(Pos.CENTER);
		paneCtlBtns.setPadding(new Insets(50));
		
		Button btnFirst = new Button("\u23ee"); // btnFirst.setMinSize(80, 80);
		Button btnBack = new Button("\u23ea"); // btnBack.setMinSize(80, 80);
		Button btnNext = new Button("\u23e9"); // btnNext.setMinSize(80, 80);
		Button btnLast = new Button("\u23ed"); // btnLast.setMinSize(80, 80);

		if (currentToDoElement == getToDoArray().size()-1){
			btnLast.setDisable(true);
			btnNext.setDisable(true);
		}
		
		if (currentToDoElement == 0){
			btnFirst.setDisable(true);
			btnBack.setDisable(true);
		}
		
		btnFirst.setOnAction((e) -> { checkArrayListDirty(); setToDoElement(0); skipScene(); });
			
		btnBack.setOnAction((e) -> { checkArrayListDirty(); setToDoElement(--currentToDoElement); skipScene(); });
		
		btnLast.setOnAction((e) -> { checkArrayListDirty(); setToDoElement(getToDoArray().size()-1); skipScene(); });
		
		btnNext.setOnAction((e) -> { checkArrayListDirty(); setToDoElement(++currentToDoElement); skipScene(); });
		
		paneCtlBtns.getChildren().addAll(btnFirst, btnBack, btnNext, btnLast);
		return paneCtlBtns;
	} // end of the getBottomPane method.
	/**
	 * This static method array loops through every element in the array and checks if the array is empty.
	 */
	private static void getToDoArrayWithoutEmpties() {
		for (Iterator<ToDo> iterator = getToDoArray().iterator(); iterator.hasNext(); ) {
		    ToDo td = iterator.next();
		    if (td.isEmptySet())  iterator.remove();
		} // end of the loop
	} // end of the getToDoArrayWithoutEmpties method.
	/**
	 * This method checks if the element was modified. If so, it asks if the user wants to save and then it cleans the cache memory.
	 */
	private void checkArrayListDirty(){
		if (isToDoArrayListDirty()){
			saveToDo();
			clearArray();
		} // end of the if statement.
	} // end of the checkArrayListDirty method.
	/**
	 * getToDoFile method basically uses a javafx.stage.FileChooser object to look for a ToDo file and with a java.io.File object it pops up in the primary stage and stores
	 * the file choosen in the File object.
	 * @return File the ToDo file.
	 */
	private File getToDoFile(){
		 fc = new FileChooser();
		 fc.setTitle("Open ToDO File");
		 fc.getExtensionFilters().addAll(
		 new ExtensionFilter("Quiz Files", "*.todo"),
		 new ExtensionFilter("All Files", "*.*"));
		 fc.setInitialDirectory(new File("D:\\"));
		File todoFile = fc.showOpenDialog(primaryStage);
		
		return todoFile;
	} // end of the method getToDoFile.
	/**
	 * This method opens a ToDo file by using the FileUtils class to check for the file if it is valid and if it exists. It gets its absolute path from the FileUtils class,
	 * checks if there are any empty ToDo and sets to start in the first ToDo of the list. If the file was not found or it is invalid, the program will pop up an alertbox
	 * showing the user that the file was not valid and if the user wants to try to look again for a file or exit. It will loops until the user decides to leave the
	 * application or chooses a ToDo file that is not empty.
	 * @param scene. When the user clicks in the Open ToDo menuItem, it will do all the process to look for a valid ToDo file and pops up in the scene. 
	 */
	private void openToDo(Scene scene){
		 Optional<ButtonType> result = null;
		 while(true){
			 FileUtils fUtils = new FileUtils(); 
			 File todoFile = getToDoFile();
		
		if (FileUtils.fileExists(todoFile) && todoFile.getName().endsWith(".todo")){
			FileUtils.setAbsPath(todoFile);
			setToDoArray(fUtils.getToDoArray(FileUtils.getAbsPath()));
			getToDoArrayWithoutEmpties();
			setToDoElement(0);
			scene.setOnMouseClicked(null);
			skipScene();
			break;
		} else {
			Alert alert = getAlertBox("FileNotFound");
			result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Platform.exit();
				break;
			} // end of the result check if statement.
		} // end of the FileUtils check if statement.
		 
		if (getToDoArray() != null) 
			break;
		} // end of the while loop
	} // end of the openToDo method.
	/**
	 * addToDo will add a blank ToDo containing only the DueDate in the next ToDo element and the scene will change to this blank ToDo.
	 */
	private void addToDo(){
		setToDoElement(currentToDoElement+1);
    	ToDo addToDo = new ToDo();
    	getToDoArray().add(getToDoElement(), addToDo);
    	skipScene();
	} // end of the addToDo method.
	/**
	 * This method asks if the user wants to save any changes made. If yes, it calls a static method from the FileUtils class called setToDoArrayListToFile which stores
	 * any changes made in the current array list to the file and it also calls the saveCenterPaneContents2ToDo method which replaces the previous content in the boxes with
	 * the ones that the user just added or removed and there is the clearArray method which clears the cache memory in the array.
	 */
	private void saveToDo(){
		Alert alert = getAlertBox("Save");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			saveCenterPaneContents2ToDo();
			clearArray();
		} // end of the if statement
		FileUtils.setToDoArrayListToFile(getToDoArray(), FileUtils.getAbsPath());
	} // end of the saveToDo method.
	/**
	 * Delete ToDo removes the current ToDo and it goes to the next non-empty ToDo. If it is the last ToDo in the file it will create a blank ToDo with dueDate set
	 * equals to the current day.
	 */
	private void deleteToDo(){
		Alert alert = getAlertBox("Remove");
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if (result.get() == ButtonType.OK){
    		if (getToDoArray().size() == 1){
    			getToDoArray().remove(currentToDoElement);
    			getToDoArray().add(0, new ToDo());
    		} else if (currentToDoElement == getToDoArray().size()-1){
    			getToDoArray().remove(currentToDoElement);
    			do{
    				setToDoElement(--currentToDoElement);
    			} while (getToDoArray().get(currentToDoElement).isEmptySet());
    		} else {
    			getToDoArray().remove(currentToDoElement);
    		}
    			skipScene();
    			getToDoArray().get(currentToDoElement).setRemove(true);
    	} // end of the if statement.
	} // end of the deleteToDo file.
	/**
	 * Method used to see if the ToDo ArrayList was modified.
	 * @return boolean. Returns true if the ToDo was modified.
	 */
	private boolean isToDoArrayListDirty(){
		boolean dirty = false;
		for (ToDo toDo : getToDoArray())
			if (toDo.isRemoveSet()){
				dirty = true;
			} // end of the loop and if statement
		return dirty;
	} // end of the isToDoArrayListDirty method.
	/**
	 * It cleans the cache memory left in the ToDo.
	 */
	private void clearArray(){
		for(ToDo td: getToDoArray()){
			if(td.isRemoveSet()) {
				td.setRemove(false);
			} // end of the if statement.
		} // end of the loop.
	} // end of the clearArray method.
	/**
	 * This method saves the contents in the center pane to the ToDo file. It also sets the priority levels and sets the dueDate to the actual date into a TextField.
	 * It creates a temporary ToDo with the properties of the ToDo constructor.
	 */
	private void saveCenterPaneContents2ToDo(){
	DateFormat df = new SimpleDateFormat("E MMM dd");
	Date dueDate = null;
	try {
		dueDate = df.parse(getTempDueDate().getText());
	} catch (ParseException e){
		e.printStackTrace();
	} // end of the try catch statement.
	
	if (getTempP1().isSelected())
		setPriority(1);
	else if (getTempP2().isSelected())
		setPriority(2);
	else if (getTempP3().isSelected())
		setPriority(3);
	
	ToDo tempToDo = new ToDo( getTempSubject().getText(), getTempTitle().getText(), getPriority(), dueDate, false, true, false );
	getToDoArray().set(currentToDoElement, tempToDo);
	} // end of the saveCenterPaneContents2ToDo method.
	/**
	 * getAlertBox creates different kinds of alerts depending on the action. The type of the alerts are "Confirmation" so it contains an OK button and CANCEL button.
	 * @param op. This parameter reads the option of what kind of alert it will shows up.
	 * @return alert.
	 */
	private Alert getAlertBox(String op){
		Alert alert = new Alert(AlertType.CONFIRMATION);	
		switch(op){
		case "Exit":
			alert.setTitle("Exit");
			alert.setHeaderText("Do you wish to exit (OK)?");
			break;
		case "Remove":
			alert.setTitle("Delete");
			alert.setHeaderText("Are you sure you want to delete your ToDo?");
			break;
			
		case "FileNotFound":
			alert.setTitle("No File Entered");
			alert.setHeaderText("No file entered. Do you wish to exit?");
			break;
			
		case "Save":
			alert.setTitle("Save File");
			alert.setHeaderText("Do you wish to save the file?");
			break;	
		} // end of the switch case statement.
		return alert;		
	} // end of the getAlertBox method.
	/**
	 * Gets the primary stage.
	 * @return stage.
	 */
	public Stage getPrimaryStage() {
		return this.primaryStage;
	} // end of the getter.
	/**
	 * Sets the primary stage.
	 * @param stage the primary stage.
	 */
	public void setPrimaryStage(Stage stage) {
		this.primaryStage = stage;
	} // end of the setter.
	/**
	 * Sets the ToDoArray into an ArrayList of ToDos.
	 * @param td ArrayList of ToDos.
	 */
	private static void setToDoArray(ArrayList<ToDo> td) {
		toDoArray = td;
	} // end of the setter.
	/**
	 * Gets the ToDoArray.
	 * @return ToDoArray.
	 */
	private static ArrayList<ToDo> getToDoArray() {
		return toDoArray;
	} // end of the getter.
	/**
	 * Gets the TextField containing a temporary title.
	 * @return tempTitle.
	 */
	private TextField getTempTitle(){
		return tempTitle;
	} // end of the getter.
	/**
	 * Sets a temporary title in the TextField.
	 * @param title the title of the field.
	 */
	private void setTempTitle(TextField title) {
		tempTitle = title;
	} // end of the setter.
	/**
	 * Gets the TextField containing a temporary dueDate.
	 * @return tempDueDate the temporary dueDate.
	 */
	private TextField getTempDueDate(){
		return tempDueDate;
	} // end of the getter.
	/**
	 * Sets a temporary dueDate in the TextField.
	 * @param tempDueDate the temporary dueDate.
	 */
	private void setTempDueDate(TextField tempDueDate){
		this.tempDueDate = tempDueDate;
	} // end of the setter.
	/**
	 * Gets the TextArea containing a temporary subject.
	 * @return tempSubject the temporary subject.
	 */
	private TextArea getTempSubject(){
		return tempSubject;
	} // end of the getter.
	/**
	 * Sets a temporary subject in the TextArea.
	 * @param tempSubject the temporary subject.
	 */
	private void setTempSubject(TextArea tempSubject){
		this.tempSubject = tempSubject;
	} // end of the setter.
	/**
	 * Sets a temporary RadioButton priority 1.
	 * @param tempP1 the temporary priority 1.
	 */
	private void setTempP1(RadioButton tempP1){
		this.tempP1 = tempP1;
	} // end of the setter.
	/**
	 * Gets the RadioButton containing a temporary priority 1.
	 * @return tempP1 the temporary priority 1.
	 */
	private RadioButton getTempP1() {
		return tempP1;
	} // end of the getter.
	/**
	 * Sets a temporary RadioButton priority 2.
	 * @param tempP2 the temporary priority 2.
	 */
	private void setTempP2(RadioButton tempP2){
		this.tempP2 = tempP2;
	} // end of the setter.
	/**
	 * Gets the RadioButton containing a temporary priority 2.
	 * @return tempP2 the temporary priority 2.
	 */
	private RadioButton getTempP2() {
		return tempP2;
	} // end of the getter.
	/**
	 * Sets a temporary RadioButton priority 3.
	 * @param tempP3 the temporary priority 3.
	 */
	private void setTempP3(RadioButton tempP3){
		this.tempP3 = tempP3;
	} // end of the setter.
	/**
	 * Gets the RadioButton containing a temporary priority 3.
	 * @return tempP3 the temporary priority 3.
	 */
	private RadioButton getTempP3() {
		return tempP3;
	} // end of the getter.
	/**
	 * Sets the priority field.
	 * @param priority the priority.
	 */
	private void setPriority(int priority){
		this.priority = priority;
	} // end of the setter.
	/**
	 * Gets the priority.
	 * @return priority the priority.
	 */
	private int getPriority(){
		return priority;
	} // end of the getter.
	/**
	 * Sets the current ToDo element number.
	 * @param currentElementNumber the current ToDo element number.
	 */
	private static void setToDoElement(int currentElementNumber){
		currentToDoElement = currentElementNumber;
	} // end of the setter.
	/**
	 * Gets the current ToDo element number.
	 * @return currentToDoElement the current ToDo element.
	 */
	public static int getToDoElement(){
		return currentToDoElement;
	} // end of the getter.
	/**
	 * The main method of the class that has the Application.launch, which replaces this main method with the method Start from the javafx.application.Application.
	 * @param args the array of strings args.
	 * @throws IOException in case of a problem in the file.
	 */
	public static void main(String[] args) throws IOException {
		Application.launch(args);
	}
} // end of the class.