import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import org.w3c.dom.DOMException;

public class TodoMain {
	public static String filename = "/Users/tmp-sda-1156/git/SDA4-IP/src/tasksfile.xml";

	public static void main(String[] args) throws DOMException, ParseException {
		int selectedChoice = -1;
		XmlFile  xmlfile = new XmlFile();
		TodoListManager taskManager = new TodoListManager();
		ArrayList<Task> taskList = new ArrayList<>();
		taskList = xmlfile.readFile(filename);
		
		
	while (selectedChoice != 5) {
		//  this code added for test
        //	for (int i = 0 ;i < taskList.size() ; i++)
        //  System.out.println(taskList.size()+ " , "+taskList.get(i).getTittle()+ " , " + taskList.get(i).getProject());

		selectedChoice = showMenu(taskList);
		
		switch(selectedChoice) {
		case 1:
			taskManager.showTask(taskList);
			break;
		case 2:
			taskList.add(taskManager.addNewTask());
			
			break;
		case 3:
			taskManager.editTask();
			break;
		case 4:
			taskManager.removeTask();
			break;
		case 5:
			xmlfile.writeFile(taskList, filename);
			break;
		default:
			System.out.println("Can't recognize input choice , please enter correct choice");
		
		}
	  }	

	}
	private static int showMenu(ArrayList<Task> listOfTask) {
		int choice;
		Scanner userInput = new Scanner(System.in);
		System.out.println(">> ToDoly Application");
		System.out.println(">> -----------------------------------------------");
		System.out.println(">> You have " + (listOfTask.size()-doneTask(listOfTask)) +" tasks todo and "+ doneTask(listOfTask)  +" tasks are done!");
		System.out.println(">> Pick an option:");
		System.out.println(">> (1) Show task list (by date or project)");
		System.out.println(">> (2) Add new task");
		System.out.println(">> (3) Edit task (update, mark as done)");
		System.out.println(">> (4) Remove a task");
		System.out.println(">> (5) Save and Quit");
		System.out.println(">> -----------------------------------------------");
		System.out.println(">> Enter a choice : ");
		System.out.print(">> ");
		
		choice = userInput.nextInt();
		return choice;
		
	}
	
	private static int doneTask(ArrayList<Task> listOfTask) {
		int done = 0;
		for(Task t:listOfTask) {
			if (t.getStatus().equals("Done"))
				done = done + 1;
		}
		return done;
	}
	


}
