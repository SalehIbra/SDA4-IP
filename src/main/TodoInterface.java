package main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import org.w3c.dom.DOMException;
import fileReadWrite.XmlFile;
import todoTask.Task;
import todoTask.TodoListManager;

/**
 * Main interface , views a list of the available options and operations that
 * can be performed in todo list in this solution.
 * 
 * @author tmp-sda-1156
 */

public class TodoInterface {
	public static String filename = "/Users/tmp-sda-1156/git/SDA4-IP/src/tasksfile.xml";

	public void runInterface() throws DOMException, ParseException {
		int selectedChoice = -1;
		XmlFile xmlfile = new XmlFile();
		TodoListManager taskManager = new TodoListManager();
		ArrayList<Task> taskList = new ArrayList<>();
		taskList = xmlfile.readFile(filename);

		while (selectedChoice != 5) {

			selectedChoice = showMenu(taskList);

			switch (selectedChoice) {
			case 1:
				taskManager.displayListOfTask(taskList);
				break;
			case 2:
				int maxTaskId = getMaxTaskId(taskList);
				taskList.add(taskManager.addNewTask(maxTaskId + 1));
			//	taskManager.printTaskByTaskId(taskList, maxTaskId + 1);
				break;
			case 3:
				taskManager.editTask(taskList);
				break;
			case 4:
				taskManager.removeTask(taskList);
				break;
			case 5:
				
				xmlfile.writeFile(taskList, filename);
				System.out.println("Changes is saved .....");
				System.out.println("Thanks for using Todo App .....");
				break;
			default:
				System.out.println("Can't recognize input choice , please enter correct choice");

			}
		}
	}

	/**
	 * View a list of the available options in this solution.
	 * 
	 * @param listOfTask
	 * @return
	 */
	public int showMenu(ArrayList<Task> listOfTask) {
		int choice;
		Scanner userInput = new Scanner(System.in);
		System.out.println(">> ToDoly Application");
		System.out.println(">> -----------------------------------------------");
		System.out.println(">> You have " + (listOfTask.size() - doneTask(listOfTask)) + " tasks todo and "
				+ doneTask(listOfTask) + " tasks are done!");
		System.out.println(">> Pick an option:");
		System.out.println(">> (1) Show task list (by date or project)");
		System.out.println(">> (2) Add new task");
		System.out.println(">> (3) Edit task (update, mark as done)");
		System.out.println(">> (4) Remove a task");
		System.out.println(">> (5) Save and Quit");
		System.out.println(">> -----------------------------------------------");
		System.out.println(">> Enter a choice : ");
		System.out.print(">> ");

		// choice = userInput.nextInt();
		choice = validateIntEntry(userInput);
		return choice;

	}

	/**
	 * Get the number of tasks which setted as done.
	 * 
	 * @param listOfTask
	 * @return number of done tasts
	 */
	private static int doneTask(ArrayList<Task> listOfTask) {
		int done = 0;
		for (Task t : listOfTask) {
			if (t.getStatus().equals("Done"))
				done = done + 1;
		}
		return done;
	}

	/**
	 * Get the max id of tasks to generate the next id when we create new task.
	 * 
	 * @param listOfTask
	 * @return max task id
	 */
	public static int getMaxTaskId(ArrayList<Task> listOfTask) {
		Task maxTask = Collections.max(listOfTask, Comparator.comparing(t -> t.getId()));
		return maxTask.getId();
	}

	/**
	 * Validate user input choice
	 * 
	 * @param input
	 * @return
	 */
	public static int validateIntEntry(Scanner input) {
		int choice = 0;
		try {
			choice = Integer.parseInt(input.nextLine());
			return choice;
		} catch (Exception e) {
			System.out.println("Invalid input,try again :");
			System.out.print(">> ");
		}
		return validateIntEntry(input);
	}

}
