import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TodoListManager {

	private final String dateFormat = "dd-MM-yyyy";
	SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
//	private ArrayList<Task> taskList = new ArrayList<Task>();



	// view my list of tasks in the interface
	public void displyListOfTask(ArrayList<Task> showList) {

		Scanner input = new Scanner(System.in);
		System.out.println("Enetr view option:");
		System.out.println(">> 1 - To disply tasks list by date");
		System.out.println(">> 2 - To disply tasks list by a project");
		System.out.println(">> 0 - To return to main menu");
		System.out.print(">> ");
		// to avoid reading enter as a input string
		int choice = Integer.parseInt(input.nextLine());

		switch (choice) {
		case 0:
			break;
		case 1:
			sortByDate(showList);
			printTaskList(showList);
			displyListOfTask(showList);
			break;
		case 2:
			System.out.println("Enter Project:");
			System.out.print(">> ");
			String filterProjectName = input.nextLine();
			ArrayList<Task> filterTask = filterByProject(showList, filterProjectName);
			printTaskList(filterTask);
			displyListOfTask(showList);
			break;	
		default:
			System.out.println("Can't recognize input choice , please enter correct choice");
		}

	}

	// Add a new task to my list of task
	public Task addNewTask(int newTaskId) {

		Scanner taskInput = new Scanner(System.in);
		Task newTask = new Task();
		Date regularDate;
		boolean isValidDate = true;
		// setLenient do regular expression and date format
		formatter.setLenient(false);
		newTask.setId(newTaskId);
		System.out.println("Enter task tittle : ");
		newTask.setTittle(taskInput.nextLine());
		System.out.println("Enter task Project : ");
		newTask.setProject(taskInput.nextLine());
		System.out.println("Enter task Date (dd-mm-yyyy)");
		while (isValidDate) {
			try {
				regularDate = stringToDate(taskInput.nextLine());
				newTask.setDate(regularDate);
				isValidDate = false;
			} catch (ParseException e) {
				System.out.println("Wrong date, Please enter a date such (dd-mm-yyyy) :");

			}
		}

		newTask.setStatus("Not Done");
		System.out.println(">> Task added correctly ....");
		return newTask;

	}

	public void editTask(ArrayList<Task> taskList) {
		Scanner input = new Scanner(System.in);
		printTaskList(taskList);
		System.out.println(">> Enter 1 to edit a task");
		System.out.println(">> Enter 0 to return to main menu");
		System.out.print(">> ");
		int choice = Integer.parseInt(input.nextLine());
		switch (choice) {
		case 0:
			break;
		case 1:			
			System.out.println(">> Enetr a task id to update :");
			System.out.print(">> ");
			int  taskId = Integer.parseInt(input.nextLine());
			// get index id
			int editTaskId = find(taskList,taskId);
			updateTask(taskList,editTaskId);
			editTask(taskList);
			break;
			
		default:
			System.out.println("Can't recognize the choice , please enter correct choice...");
		}
		
		


	}

	public void removeTask(ArrayList<Task> taskList) {
		Scanner input = new Scanner(System.in);
		printTaskList(taskList);
		System.out.println(">> Enter task id to remove :");
		System.out.print(">> ");
		int  taskId = Integer.parseInt(input.nextLine());
		int removeTaskId = find(taskList,taskId);
		if (removeTaskId == -1) {
		System.out.println("Can''t find this task...");	
		} else {
			printTaskByTaskId(taskList,removeTaskId);
			System.out.println(">> Enter 1 to confirm");
			System.out.println(">> Enter 0 to return to main menu");
			System.out.print(">> ");
			int choice = Integer.parseInt(input.nextLine());
			switch (choice) {
			case 0:
				break;
			case 1:
			    taskList.remove(removeTaskId);
			    System.out.println(">> Task removed correctly ....");
			    break;
			default:
				System.out.println("Can't recognize input choice , please enter correct choice");
			}
			
			
		}
	}
     // Get the index of object in arraylist of rasks
	private int find(ArrayList<Task> taskList, int taskId) {
		
		for (Task task: taskList) {
			if (task.getId() == taskId)
				return taskList.indexOf(task);
		}

         return -1;		
		
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
    // Print my tasks list
	private void printTaskByTaskId(ArrayList<Task> printList, int taskId) {
		String format = "%-10s %-"+ getMaxTaskLength(printList)+"s      %-21s %-15s %-8s%n";
		System.out.format(format,"Task Id","Tittle","Project","Due Date","Status");
		System.out.format(format,"---------","--------","-----------","---------","---------");
		System.out.format(format, printList.get(taskId).getId(),printList.get(taskId).getTittle(),
		          printList.get(taskId).getProject(),dateToString(printList.get(taskId).getDate()),printList.get(taskId).getStatus());
		
	}

	private void printTaskList(ArrayList<Task> printList) {
		String format = "%-10s %-"+ getMaxTaskLength(printList)+"s      %-21s %-15s %-8s%n";
		System.out.format(format,"Task Id","Tittle","Project","Due Date","Status");
		System.out.format(format,"---------","--------","-----------","---------","---------");
		
		//printList.forEach(task -> System.out.format(format, task.getId(),task.getTittle(),task.getProject()
		//		                                    ,dateToString(task.getDate()),task.getStatus()));
		for (int i = 0; i < printList.size(); i++) {
			System.out.format(format, printList.get(i).getId(),printList.get(i).getTittle(),
					          printList.get(i).getProject(),dateToString(printList.get(i).getDate()),printList.get(i).getStatus());
			
			System.out.format(format,"---------","--------","-----------","---------","---------");	
	 }
	}

	// return max length of task tittle to format list
	private int getMaxTaskLength(ArrayList<Task> taskList) {
		Task maxLengthTask = Collections.max(taskList, Comparator.comparing(t -> t.getTittle().length()));
		return maxLengthTask.getTittle().length();
	}
	// Sort todo list of tasks by date
	private void sortByDate(ArrayList<Task> taskList) {
		Collections.sort(taskList, new Comparator<Task>() {
			public int compare(Task t1, Task t2) {
				return t1.getDate().compareTo(t2.getDate());
			}
		});
	}
   // Filter todo list of tasks by task project
	private ArrayList<Task> filterByProject(ArrayList<Task> taskList, String projectName) {

		ArrayList<Task> filterdTask = (ArrayList<Task>) taskList.stream()
				.filter(x -> x.getProject().equals(projectName)).collect(Collectors.toList());

		return filterdTask;
	}
	
	
	private void updateTask(ArrayList<Task> taskList , int taskId) {
		Date editDate = null;
		if (taskId == -1) {
			System.out.println("Can''t find this task...");	
			} else {
				Task updateTask = taskList.get(taskId);
				Scanner editInput = new Scanner(System.in);
				printTaskByTaskId(taskList,taskId);
				System.out.println("Enter a new tittle or press Enter to keep the old one");
				System.out.print(">> ");
				String editTittle = editInput.nextLine();
				System.out.println("Enter a new project or press Enter to keep the old one");
				System.out.print(">> ");
				String editProject = editInput.nextLine();
				System.out.println("Enter a new date (dd-mm-yyyy) or press Enter to keep the old one");
				System.out.print(">> ");
				formatter.setLenient(false);
				boolean isValidDate = true;
				while (isValidDate) {
					try {
						editDate = stringToDate(editInput.nextLine());
						isValidDate = false;
					} catch (ParseException e) {
						System.out.println("Wrong date, Please enter a date such (dd-mm-yyyy) :");

					}
				}
              editTittle = editTittle.length()== 0 ? updateTask.getTittle():editTittle;
              editProject = editProject.length()== 0 ? updateTask.getProject():editProject;
              editDate = isValidDate == true ? updateTask.getDate(): editDate;
              updateTask.setTittle(editTittle);
              updateTask.setProject(editProject);
              updateTask.setDate(editDate);
              taskList.set(taskId, updateTask);
              System.out.println(">> Task is edited .....");
              
	 }
	}
	
}
