import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TodoListManager {
	
	private final String dateFormat = "dd-mm-yyyy";
	SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
	private ArrayList<Task> taskList = new ArrayList<Task>();
	
	private void sortByDate(ArrayList<Task> taskList) {
		Collections.sort(taskList, new Comparator<Task>() {
			public int compare(Task t1,Task t2) {
		    return t1.getDate().compareTo(t2.getDate());
			}
		});
	}
	
	private ArrayList<Task> filterByProject(ArrayList<Task> taskList  ,String projectName){
		
		ArrayList<Task> filterdTask = (ArrayList<Task>) taskList.stream()
		.filter(x -> x.getProject().equals(projectName))
		.collect(Collectors.toList());
		
		return filterdTask;
	}
	
	// view my list of tasks in the interface
	public void showTask(ArrayList<Task> showList) {
         
		for(int i = 0 ; i < showList.size(); i++)
		{
			System.out.println("Task id : "+ (i+1));
			System.out.println("Tittle : "+ showList.get(i).getTittle());
			System.out.println("Project : "+ showList.get(i).getProject());
			System.out.println("Due date : "+ dateToString(showList.get(i).getDate()));
			System.out.println("Status : "+ showList.get(i).getStatus());
			System.out.println("------");
		}
	}
	
	// Add a new task to my list of task
	public Task addNewTask() {
		
		Scanner taskInput = new Scanner(System.in);
		Task newTask = new Task();
		Date regularDate ;
		boolean isValidDate = true;
		// setLenient do regular expression and date format
		formatter.setLenient(false);
		System.out.println("Enter task tittle : ");
		newTask.setTittle(taskInput.nextLine());
		System.out.println("Enter task Project : ");
		newTask.setProject(taskInput.nextLine());
		System.out.println("Enter task Date (dd-mm-yyyy)");
		while(isValidDate) {
		try {
			regularDate = stringToDate(taskInput.nextLine());
			newTask.setDate(regularDate);
			isValidDate = false;
		} 
		catch (ParseException e) {
			System.out.println("Wrong date, Please enter a date such (dd-mm-yyyy) :");
		    
		}
	}
		
		newTask.setStatus("Not Done");
		
		return newTask;
		
	}
	
	
    public void editTask() {
		
	}
	
	public void removeTask() {
		
	}
	
	public void exit() {
		
	}
	// format to string
	// change date from string to date before inserting into list
   public Date stringToDate(String date) throws ParseException {
		
		Date parseDate = null;
	    parseDate = formatter.parse(date);	
        return parseDate;

	}
	// parse to date
	// change from date to string before writing into the file 
	public String dateToString(Date date) {
		return formatter.format(date);
	}
}
