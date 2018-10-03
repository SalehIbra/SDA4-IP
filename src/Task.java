import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Task {

	private String taskTittle;
	private String taskProject;
	private String taskStatus = "Not Done";
	private Date taskDate ;
	
	public Task(String tittle , String project, String status,Date date) {

		taskTittle = tittle;
		taskProject = project;
		taskStatus = status;
		taskDate = date;
		
	}
	
	public Task() {

	}

	public void setTittle(String tittle) {
		taskTittle = tittle;
		}
	public void setProject(String project) {
		taskProject = project;
	}
	public void setStatus(String status) {
		taskStatus = status;
	}
	public void setDate(Date date) {
		taskDate = date;
	}
	

	public String getTittle() {
		return taskTittle;
	}
	
	public String getProject() {
		return taskProject;
	}
	
	public String getStatus() {
		return taskStatus;
	}
	
	public Date getDate() {
		return taskDate;
	}
	

 
}
