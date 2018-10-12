package fileReadWrite;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import todoTask.Task;
import todoTask.TodoListManager;

/**
 * This class is builded to load an retrieve my list of tasks into a file We
 * read from or write to a file. We used XML files in this solution.
 * 
 * @author tmp-sda-1156
 *
 */
public class XmlFile {
	private TodoListManager newTask = new TodoListManager();

	/**
	 * Write all tasks in todo list to XML file , here we build xml document, fill
	 * the date into XML elements and nodes Then transform this structure into XML
	 * file.
	 * 
	 * @param mainTaskList
	 * @param fileName
	 */
	public void writeFile(ArrayList<Task> mainTaskList, String fileName) {
		// all elements should be string to write to xml
		String id;
		String title;
		String project;
		String status;
		String date;

		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// use factory to build instance
			DocumentBuilder db = dbf.newDocumentBuilder();
			// create instance of DOM
			dom = db.newDocument();
			// Add task details into xml structure
			Element mainRootElement = dom.createElementNS("https://crunchify.com/CrunchifyCreateXMLDOM", "Tasks");
			dom.appendChild(mainRootElement);

			for (int i = 0; i < mainTaskList.size(); i++) {
				id = Integer.toString(mainTaskList.get(i).getId());
				title = mainTaskList.get(i).getTitle();
				project = mainTaskList.get(i).getProject();
				date = newTask.dateToString(mainTaskList.get(i).getDate());
				status = mainTaskList.get(i).getStatus();

				mainRootElement.appendChild(addElement(dom, id, title, project, status, date));
			}

			// send DOM to a file
			Transformer transform = TransformerFactory.newInstance().newTransformer();
			transform.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(dom);
			StreamResult file = new StreamResult(fileName);

			transform.transform(source, file);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Build a complete xml task elements an nodes and element.
	 * 
	 * @param doc
	 * @param id
	 * @param title
	 * @param project
	 * @param status
	 * @param date
	 * @return xml node
	 */
	private static Node addElement(Document doc, String id, String title, String project, String status, String date) {
		Element taskNode = doc.createElement("Task");
		taskNode.setAttribute("id", id);
		// add title node
		Element node = doc.createElement("Title");
		node.appendChild(doc.createTextNode(title));
		taskNode.appendChild(node);
		// add project node
		node = doc.createElement("Project");
		node.appendChild(doc.createTextNode(project));
		taskNode.appendChild(node);
		// add status node
		node = doc.createElement("Status");
		node.appendChild(doc.createTextNode(status));
		taskNode.appendChild(node);
		// add date node
		node = doc.createElement("Date");
		node.appendChild(doc.createTextNode(date));
		taskNode.appendChild(node);

		return taskNode;
	}

	/**
	 * Read the data from XML file nodes and fill this data into a list of tasks.
	 * 
	 * @param filename
	 * @return list of tasks
	 * @throws DOMException
	 * @throws ParseException
	 */
	public ArrayList<Task> readFile(String filename) throws DOMException, ParseException {

		ArrayList<Task> rdArrayList = new ArrayList<>();

		DocumentBuilderFactory rdFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder rdBuilder = rdFactory.newDocumentBuilder();
			Document rdDoc = rdBuilder.parse(filename);
			// get the task tag
			NodeList rdNodeList = rdDoc.getElementsByTagName("Task");
			for (int temp = 0; temp < rdNodeList.getLength(); temp++) {
				// get the items in task list
				Node rdNode = rdNodeList.item(temp);
				Task lsTask = new Task();

				if (rdNode.getNodeType() == Node.ELEMENT_NODE) {
					Element rdElement = (Element) rdNode;
					lsTask.setId(Integer.parseInt(rdElement.getAttribute("id")));
					lsTask.setTitle(rdElement.getElementsByTagName("Title").item(0).getTextContent());
					lsTask.setProject(rdElement.getElementsByTagName("Project").item(0).getTextContent());
					lsTask.setStatus(rdElement.getElementsByTagName("Status").item(0).getTextContent());
					lsTask.setDate(
							newTask.stringToDate(rdElement.getElementsByTagName("Date").item(0).getTextContent()));

				}
				rdArrayList.add(lsTask);
			}
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rdArrayList;

	}

}
