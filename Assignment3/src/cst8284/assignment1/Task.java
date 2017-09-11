package cst8284.assignment1;

import java.util.Date;

public abstract class Task {
	private Date dateCreated;
	
	public Task(){setDateCreated();}
	
	public abstract String getSubject();	
	public abstract void setSubject(String subject);
	
	public abstract String getTitle();	
	public abstract void setTitle(String title);
	
	public Date getDateCreated(){return dateCreated;}
	private void setDateCreated(){this.dateCreated = new Date();}

	@Override
	public String toString(){return (getDateCreated().toString());}
	
}