package todoTask;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.TodoInterface;

class TaskTest {
	private Task task;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		task = new Task();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@DisplayName("Object of task is created")
	@Test
	void testTask() {
		assertNotNull(task);
	}

	@DisplayName("Task id is added")
	@Test
	void testSetId() {
		int expectedId = 123;
		task.setId(expectedId);
		int actualId = task.getId();
		assertEquals(expectedId, actualId);
	}

	@DisplayName("Task title is added")
	@Test
	void testSetTitle() {
		String expectedTitle = "New title";
		task.setTitle(expectedTitle);
		String actualTitle = task.getTitle();
		assertEquals(expectedTitle, actualTitle);
	}

	@DisplayName("Task project is added")
	@Test
	void testSetProject() {
		String expectedProject = "New project";
		task.setTitle(expectedProject);
		String actualProject = task.getTitle();
		assertEquals(expectedProject, actualProject);
	}

	@DisplayName("Task status is added")
	@Test
	void testSetStatus() {
		String expectedStatus = "Not Done";
		String actualStatus = task.getStatus();
		assertEquals(expectedStatus, actualStatus);

	}

	@DisplayName("Task date is valid")
	@Test
	void testSetDate() throws ParseException {
		TodoListManager tlmanager = new TodoListManager();
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		formater.setLenient(false);
		Date testTaskDate = tlmanager.stringToDate("01-01-2018");
		task.setDate(testTaskDate);
		Date actualDate = task.getDate();
		assertEquals(0, testTaskDate.compareTo(actualDate));
	}

	@DisplayName("Get max task id")
	@Test
	void testgetMaxTaskId() {
		ArrayList<Task> testTask = new ArrayList<>();
		testTask.add(new Task(10, "title1", "project1", "Not Done", null));
		testTask.add(new Task(20, "title2", "project2", "Not Done", null));
		testTask.add(new Task(30, "title3", "project3", "Not Done", null));
		int actualId = TodoInterface.getMaxTaskId(testTask);
		assertEquals(30, actualId);

	}

}
