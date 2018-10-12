package todoTask;

import java.util.Date;

/**
 * This class is holds the information or structure of tasks which will be added
 * to todo list.
 * 
 * @author tmp-sda-1156
 *
 */
public class Task {
	private int taskId;
	private String taskTitle;
	private String taskProject;
	private String taskStatus = "Not Done";
	private Date taskDate;

	/**
	 * This is task object builder with all details
	 * 
	 * @param id
	 * @param title
	 * @param project
	 * @param status
	 * @param date
	 */
	public Task(int id, String title, String project, String status, Date date) {
		taskId = id;
		taskTitle = title;
		taskProject = project;
		taskStatus = status;
		taskDate = date;

	}

	/**
	 * This is task object builder with no data , it will take the default data.
	 */

	public Task() {

	}

	public void setId(int id) {
		taskId = id;
	}

	public void setTitle(String title) {
		taskTitle = title;
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

	public String getTitle() {
		return taskTitle;
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
