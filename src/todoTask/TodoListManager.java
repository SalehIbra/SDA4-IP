package todoTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import main.TodoInterface;

/**
 * This class is build to manage all operation on our todo list or specific task
 * in the list like view , edit , remove , sort by date, filter by project and
 * adding tasks to my list after validating the data.
 * 
 * @author tmp-sda-1156
 *
 */

public class TodoListManager {

	private final String dateFormat = "dd-MM-yyyy";
	SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

	/**
	 * view my list of tasks in the interface
	 * 
	 * @param showList
	 */
	public void displayListOfTask(ArrayList<Task> showList) {

		Scanner input = new Scanner(System.in);
		System.out.println("Enetr view option:");
		System.out.println(">> 1 - To display tasks list by date");
		System.out.println(">> 2 - To display tasks list by a project");
		System.out.println(">> 0 - To return to main menu");
		System.out.print(">> ");
		int choice = TodoInterface.validateIntEntry(input);

		switch (choice) {
		case 0:
			break;
		case 1:
			sortByDate(showList);
			printTaskList(showList);
			displayListOfTask(showList);
			break;
		case 2:
			String filterProjectName = getDistinctProject(showList, input);
			ArrayList<Task> filterTask = filterByProject(showList, filterProjectName);
			printTaskList(filterTask);
			displayListOfTask(showList);
			break;
		default:
			System.out.println("Can't recognize input choice , please enter correct choice");
		}

	}

	/**
	 * Add a new task to my todo list
	 * 
	 * @param newTaskId
	 * @return
	 */
	public Task addNewTask(int newTaskId) {

		Scanner taskInput = new Scanner(System.in);
		Date regularDate = null;
		boolean isValidDate = true;
		// setLenient do regular expression and date format
		formatter.setLenient(false);
		System.out.println("Enter task title : ");
		String newTitle = taskInput.nextLine();
		System.out.println("Enter task Project : ");
		String newProject = taskInput.nextLine();
		System.out.println("Enter task Date (dd-mm-yyyy)");
		while (isValidDate) {
			try {
				regularDate = stringToDate(taskInput.nextLine());
				isValidDate = false;
			} catch (ParseException e) {
				System.out.println("Wrong date, Please enter a date such (dd-mm-yyyy) :");

			}
		}

		System.out.println(">> Task added correctly ....");
		System.out.println("Task Id   Title    Project     Due Date                      Status");
		System.out.println("-------   ------    ---------   ----------------------------  ------");
		System.out.println(newTaskId+"          "+ newTitle+"     "+newProject+"      "+dateToString(regularDate)+"                   Not Done");
		return fillTaskDate(newTaskId, newTitle, newProject, regularDate);

	}

	/**
	 * Use a task constructor to fill tasks details
	 * 
	 * @param taskId
	 * @param taskTitle
	 * @param taskProject
	 * @param taskDate
	 * @return
	 */
	private Task fillTaskDate(int taskId, String taskTitle, String taskProject, Date taskDate) {
		Task newTask = new Task(taskId, taskTitle, taskProject, "Not Done", taskDate);
		return newTask;
	}

	/**
	 * This methods edits a task details in todo list
	 * 
	 * @param taskList
	 */
	public void editTask(ArrayList<Task> taskList) {
		Scanner input = new Scanner(System.in);
		printTaskList(taskList);
		System.out.println(">> Enter 1 to edit a task (Title , Project , Date)");
		System.out.println(">> Enter 2 to update the status");
		System.out.println(">> Enter 0 to return to main menu");
		System.out.print(">> ");
		int choice = TodoInterface.validateIntEntry(input);
		switch (choice) {
		case 0:
			break;
		case 1:
			try {
				int editTaskId = getTaskIndex(taskList, input);
				updateTask(taskList, editTaskId);
				editTask(taskList);
				break;
			} catch (Exception e) {
				System.out.println("Wrong entry , Enter task id ....");
			}
		case 2:
			try {

				int editStatusId = getTaskIndex(taskList, input);
				updateStatus(taskList, editStatusId);
				editTask(taskList);
				break;
			} catch (Exception e) {
				System.out.println("Wrong entry , Enter task id ....");
			}
		default:
			System.out.println("Can't recognize the choice , please enter correct choice...");
		}

	}

	/**
	 * Get the task id of a given list's index
	 * 
	 * @param taskList
	 * @param input
	 * @return
	 */
	private int getTaskIndex(ArrayList<Task> taskList, Scanner input) {
		System.out.println(">> Enetr task id :");
		System.out.print(">> ");
		int taskId = TodoInterface.validateIntEntry(input);
		// get index id
		int editTaskId = find(taskList, taskId);
		return editTaskId;
	}

	/**
	 * Delete a task from list by givin task id
	 * 
	 * @param taskList
	 */
	public void removeTask(ArrayList<Task> taskList) {
		Scanner input = new Scanner(System.in);
		printTaskList(taskList);
		System.out.println(">> Enter task id to remove :");
		System.out.print(">> ");
		int taskId = TodoInterface.validateIntEntry(input);
		int removeTaskId = find(taskList, taskId);
		if (removeTaskId == -1) {
			System.out.println("Can''t find this task...");
		} else {
			printTaskByTaskId(taskList, removeTaskId);
			System.out.println(">> Enter 1 to confirm");
			System.out.println(">> Enter 0 to return to main menu");
			System.out.print(">> ");
			int choice = TodoInterface.validateIntEntry(input);
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

	/**
	 * Get the index of task object in todo list
	 * 
	 * @param taskList
	 * @param taskId
	 * @return
	 */

	private int find(ArrayList<Task> taskList, int taskId) {

		for (Task task : taskList) {
			if (task.getId() == taskId)
				return taskList.indexOf(task);
		}

		return -1;

	}

	/**
	 * format to string Change date from string to date before inserting into list
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public Date stringToDate(String date) throws ParseException {

		Date parseDate = null;
		parseDate = formatter.parse(date);
		return parseDate;

	}

	/**
	 * parse to date Change from date to string before writing into the file
	 * 
	 * @param date
	 * @return
	 */
	public String dateToString(Date date) {
		return formatter.format(date);
	}

	/**
	 * Print one task in my list
	 * 
	 * @param printList
	 * @param taskId
	 */
	private void printTaskByTaskId(ArrayList<Task> printList, int taskId) {
		String format = "%-10s %-" + getMaxTaskLength(printList) + "s      %-21s %-15s %-8s%n";
		System.out.format(format, "Task Id", "Title", "Project", "Due Date", "Status");
		System.out.format(format, "---------", "--------", "-----------", "---------", "---------");
		System.out.format(format, printList.get(taskId).getId(), printList.get(taskId).getTitle(),
				printList.get(taskId).getProject(), dateToString(printList.get(taskId).getDate()),
				printList.get(taskId).getStatus());

	}

	/**
	 * Print my whole todo list
	 * 
	 * @param printList
	 */
	private void printTaskList(ArrayList<Task> printList) {
		String format = "%-10s %-" + getMaxTaskLength(printList) + "s      %-21s %-15s %-8s%n";
		System.out.format(format, "Task Id", "Title", "Project", "Due Date", "Status");
		System.out.format(format, "---------", "--------", "-----------", "---------", "---------");
		for (int i = 0; i < printList.size(); i++) {
			System.out.format(format, printList.get(i).getId(), printList.get(i).getTitle(),
					printList.get(i).getProject(), dateToString(printList.get(i).getDate()),
					printList.get(i).getStatus());

			System.out.format(format, "---------", "--------", "-----------", "---------", "---------");
		}
	}

	/**
	 * Return max length of task title to format list
	 * 
	 * @param taskList
	 * @return
	 */
	private int getMaxTaskLength(ArrayList<Task> taskList) {
		Task maxLengthTask = Collections.max(taskList, Comparator.comparing(t -> t.getTitle().length()));
		return maxLengthTask.getTitle().length();
	}

	/**
	 * Sort todo list of tasks by date
	 * 
	 * @param taskList
	 */
	private void sortByDate(ArrayList<Task> taskList) {
		Collections.sort(taskList, new Comparator<Task>() {
			public int compare(Task t1, Task t2) {
				return t1.getDate().compareTo(t2.getDate());
			}
		});
	}

	/**
	 * get list of distinct projects
	 * 
	 * @param taskList
	 * @param projectName
	 * @return
	 */
	private String getDistinctProject(ArrayList<Task> taskList, Scanner input) {
		String projectName;
		int choice;
		List<String> projectList = (ArrayList<String>) taskList.stream().map(x -> x.getProject()).distinct()
				.collect(Collectors.toList());

		System.out.println(">> These are the list of existed projects");

		for (int index = 1; index <= projectList.size(); index++) {
			System.out.println(index + " - " + projectList.get(index - 1));
		}

		do {
			System.out.println(">> Select Project Number :");
			System.out.print(">> ");
			choice = TodoInterface.validateIntEntry(input);

		} while (choice > projectList.size());

		projectName = projectList.get(choice - 1);
		return projectName;

	}

	/**
	 * Filter todo list of tasks by task project
	 * 
	 * @param taskList
	 * @param projectName
	 * @return
	 */
	private ArrayList<Task> filterByProject(ArrayList<Task> taskList, String projectName) {
		ArrayList<Task> filterdTask = (ArrayList<Task>) taskList.stream()
				.filter(x -> x.getProject().equals(projectName)).collect(Collectors.toList());

		return filterdTask;
	}

	/**
	 * Update a selected task details (title , project , date) or keep any of them
	 * without change and set the updated task into todo list after validating.
	 * 
	 * @param taskList
	 * @param taskId
	 */
	private void updateTask(ArrayList<Task> taskList, int taskId) {
		Date editDate = null;
		if (taskId == -1) {
			System.out.println("Can''t find this task...");
		} else {
			Task updateTask = taskList.get(taskId);
			Scanner editInput = new Scanner(System.in);
			printTaskByTaskId(taskList, taskId);
			System.out.println("Enter a new title or press Enter to keep the old one");
			System.out.print(">> ");
			String editTitle = editInput.nextLine();
			System.out.println("Enter a new project or press Enter to keep the old one");
			System.out.print(">> ");
			String editProject = editInput.nextLine();
			System.out.println("Enter a new date (dd-mm-yyyy) or press Enter to keep the old one");
			System.out.print(">> ");

			formatter.setLenient(false);
			boolean isValidDate = true;
			while (isValidDate) {
				String stringDate = editInput.nextLine();
				try {
					if (stringDate.length() == 0) {
						editDate = updateTask.getDate();
						isValidDate = false;
					} else {
						editDate = stringToDate(stringDate);
						isValidDate = false;
					}
				} catch (ParseException e) {
					System.out.println("Wrong date, Please enter a date such (dd-mm-yyyy) :");

				}
			}
			editTitle = editTitle.length() == 0 ? updateTask.getTitle() : editTitle;
			editProject = editProject.length() == 0 ? updateTask.getProject() : editProject;
			editTaskData(taskList, taskId, editDate, updateTask, editTitle, editProject);
			System.out.println(">> Task is edited .....");

		}
	}

	/**
	 * Fill the updated details of task and set this task into todo list
	 * 
	 * @param taskList
	 * @param taskId
	 * @param editDate
	 * @param updateTask
	 * @param editTitle
	 * @param editProject
	 */
	private void editTaskData(ArrayList<Task> taskList, int taskId, Date editDate, Task updateTask, String editTitle,
			String editProject) {

		updateTask.setTitle(editTitle);
		updateTask.setProject(editProject);
		updateTask.setDate(editDate);
		taskList.set(taskId, updateTask);
	}

	/**
	 * Updated status of a task and set this task into todo list
	 * 
	 * @param taskList
	 * @param editStatusId
	 */

	private void updateStatus(ArrayList<Task> taskList, int editStatusId) {

		if (editStatusId == -1) {
			System.out.println("Can''t find this task...");
		} else {
			Task newTask = taskList.get(editStatusId);
			Scanner input = new Scanner(System.in);
			printTaskByTaskId(taskList, editStatusId);
			System.out.println("Enter (y) to change the status (c) to cancel");
			System.out.print(">> ");
			String editStatus = input.nextLine();
			switch (editStatus.toLowerCase()) {
			case "y":
				if (newTask.getStatus().toLowerCase() == "done") {
					newTask.setStatus("Not Done");
					System.out.println(">> Task is edited .....");
				} else {
					newTask.setStatus("Done");
					System.out.println(">> Task is edited .....");
				}

				taskList.set(editStatusId, newTask);

			case "c":
				break;
			default:
				System.out.println("Wrong entry ....");
				break;
			}

		}

	}

}
