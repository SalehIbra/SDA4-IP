package main;

import java.text.ParseException;
import org.w3c.dom.DOMException;

/**
 * @author tmp-sda-1156
 *
 */
public class TodoMain {
	
	public static void main(String[] args) throws DOMException, ParseException  {
		  TodoInterface mainInterface = new TodoInterface();
		  mainInterface.runInterface();
	}
}
