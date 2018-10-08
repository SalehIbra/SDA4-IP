import java.util.Date;

public class Task {
    private int taskId;
	private String taskTittle;
	private String taskProject;
	private String taskStatus = "Not Done";
	private Date taskDate ;
	
	public Task(int id, String tittle , String project, String status,Date date) {
        taskId = id;
		taskTittle = tittle;
		taskProject = project;
		taskStatus = status;
		taskDate = date;
		
	}
	
	public Task() {

	}
	
	public void setId(int id) {
		taskId = id;
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
	
	public int getId() {
		return taskId;
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
