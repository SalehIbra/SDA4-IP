import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;


public class XmlFile {
	
	public void writeFile(ArrayList<Task> mainTaskList,String fileName) {
		// all elements should be string to write to xml
		String id;
		String tittle;
		String project;
		String status;
		String date;
		
		Document dom ;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	
		 try {
			 // use factory to build instance 
			DocumentBuilder db = dbf.newDocumentBuilder();
			// create instance of DOM
			dom = db.newDocument();
			// Add task details into xml file
			Element mainRootElement = dom.createElementNS("https://crunchify.com/CrunchifyCreateXMLDOM", "Tasks");
			dom.appendChild(mainRootElement);
			
			for (int i = 0 ; i < mainTaskList.size(); i++) {
				id = Integer.toString(i);
				tittle = mainTaskList.get(i).getTittle();
				project = mainTaskList.get(i).getProject();
				date = mainTaskList.get(i).getDate();
				status = mainTaskList.get(i).getStatus();
/*				if (boolStatus == true) {
					status = "Done";
				} else {
					status = "Not Done";
				}*/
			 mainRootElement.appendChild(addElement(dom,id,tittle,project,status,date));
			}
			
			/*Element root = dom.createElement("task");
			rootElement = dom.createElement("tittle");
			rootElement.appendChild(dom.createTextNode(mainTaskList.get(0).getTittle()));
			root.appendChild(rootElement);*/
			
			// send DOM to a file
			Transformer transform = TransformerFactory.newInstance().newTransformer();
			transform.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(dom);
			StreamResult file = new StreamResult(fileName);
			
			transform.transform(source, file);
			
			
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) { 
			e.printStackTrace();
		}
		 
	}
	
	private static Node addElement(Document doc,String id,String tittle,
			String project,String status,String date ) {
		Element taskNode = doc.createElement("Task");
		taskNode.setAttribute("id", id);
		//add tittle node
		Element node = doc.createElement("Tittle");
		node.appendChild(doc.createTextNode(tittle));
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
	
	// read xml file
	public ArrayList<Task> readFile(String filename) {
		
		ArrayList<Task> rdArrayList = new ArrayList<>();
		Task lsTask = new Task();
		DocumentBuilderFactory rdFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder rdBuilder = rdFactory.newDocumentBuilder();
			Document rdDoc = rdBuilder.parse(filename);
			// get the task tag
			NodeList rdNodeList = rdDoc.getElementsByTagName("Task");
			for (int temp = 0 ; temp < rdNodeList.getLength(); temp++) {
				// get the items in task list
				Node rdNode = rdNodeList.item(temp);
				
				if(rdNode.getNodeType() == Node.ELEMENT_NODE) {
					Element rdElement = (Element)rdNode;
					rdElement.getAttribute("id");
					lsTask.setTittle(rdElement.getElementsByTagName("Tittle").item(0).getTextContent());
					lsTask.setProject(rdElement.getElementsByTagName("Project").item(0).getTextContent());
					lsTask.setStatus(rdElement.getElementsByTagName("Status").item(0).getTextContent());
					lsTask.setDate(rdElement.getElementsByTagName("Date").item(0).getTextContent());
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
