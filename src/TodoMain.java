import java.io.*;
import java.util.ArrayList;
public class TodoMain {
	public static String filename = "tasksfile.xml";

	public static void main(String[] args) {
		int selectedTask = -1;
		TaskMenu itemMenu = new TaskMenu();
		XmlFile  xmlfile = new XmlFile();
		Task task = new Task();
		ArrayList<Task> taskList = new ArrayList<>();
		
	while (selectedTask != 5) {
		
		selectedTask = itemMenu.showMenu();
		switch(selectedTask) {
		case 1:
			task.showTask();
			break;
		case 2:
			taskList.add(task.addNewTask());
			
			break;
		case 3:
			task.editTask();
			break;
		case 4:
			task.removeTask();
			break;
		case 5:
			xmlfile.writeFile(taskList, filename);
			break;
		default:
			System.out.println("Can't recognize input choice ....");
		
		}
	  }	

	}

}
