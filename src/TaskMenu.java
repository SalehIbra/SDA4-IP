import java.util.Scanner;

public class TaskMenu {
	private int choice;
	private int doneTasks;
	private int notDoneTasks;
	
	// Method to show welcome list and get user's option
	public int showMenu() {
		
		Scanner userInput = new Scanner(System.in);
		System.out.println(">> Welcome to ToDoly");
		System.out.println(">> -----------------------------------------------");
		System.out.println(">> You have " +doneTasks +" tasks todo and "+ notDoneTasks +" tasks are done!");
		System.out.println(">> Pick an option:");
		System.out.println(">> (1) Show task list (by date or project)");
		System.out.println(">> (2) Add tew task");
		System.out.println(">> (3) Edit task (update, mark as done)");
		System.out.println(">> (4) Remove a task");
		System.out.println(">> (5) Save and Quit");
		System.out.println(">> -----------------------------------------------");
		System.out.print(">> Enter a choice : ");
		choice = userInput.nextInt();
		return choice;
		
	}
	
	// method to return done tasks
	
	// method to return todo tasks
	

}
