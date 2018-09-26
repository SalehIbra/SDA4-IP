import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Task {

	private String taskTittle;
	private String taskProject;
	private String taskStatus = "Not Done";
	private String taskDate ;
	private XmlFile xmlFile = new XmlFile();
	private ArrayList<Task> showList = new ArrayList<>();
	
	public Task(String tittle , String project, String status,String date) {

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
	public void setDate(String date) {
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
	
	public String getDate() {
		return taskDate;
	}
	
	
	public void showTask() {
		showList = xmlFile.readFile(TodoMain.filename);
		
		for(int i = 0 ; i < showList.size(); i++)
		{
			System.out.println("Task id : "+ (i+1));
			System.out.println("Tittle : "+ showList.get(i).getTittle());
			System.out.println("Project : "+ showList.get(i).getProject());
			System.out.println("Due date : "+ showList.get(i).getDate());
			System.out.println("Status : "+ showList.get(i).getStatus());
		}
	}
	
	public Task addNewTask() {
	
		Scanner taskInput = new Scanner(System.in);
		Task newTask = new Task();
		System.out.println("Enter task tittle : ");
		newTask.taskTittle = taskInput.nextLine();
		System.out.println("Enter task Project : ");
		newTask.taskProject = taskInput.nextLine();
		System.out.println("Enter task Date : ");
		newTask.taskDate = taskInput.nextLine();
		newTask.taskStatus = "Not Done";
		
		return newTask;
		
	}
	
    public void editTask() {
		
	}
	
	public void removeTask() {
		
	}
	
	public void exit() {
		
	}

}
