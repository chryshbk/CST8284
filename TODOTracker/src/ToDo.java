import java.io.Serializable;
import java.util.Date;

public class ToDo extends Task implements Serializable {
	private int priority;
	private Date dueDate;
	private boolean completed, remove, empty;
	private String subject, title;
	
	public ToDo(){
		this.subject = "";
		this.title = "";
		this.completed = false;
		this.dueDate = new Date();
		this.empty = false;
		this.remove = true;
		this.priority = 0;
	}
	
	public ToDo(String subject, String title, int priority, Date dueDate, boolean completed, boolean remove, boolean empty){
		setSubject(subject); setTitle(title); setPriority(priority); setDueDate(dueDate);
		setCompleted(completed); setRemove(remove); setEmpty(empty);
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public boolean isRemoveSet() {
		return remove;
	}
	
	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	
	public boolean isEmptySet() {
		return empty;
	}
	
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString(){
		return (getDueDate().toString());
	}
}
