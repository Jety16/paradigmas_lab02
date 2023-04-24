import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
package parser;

/* Esta clase implementa el parser de feed de tipo rss (xml)
 * https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm 
 * */

public class RssParser extends GeneralParser {

    public static Document parseXML(String file_path) throws Exception {
    
        
        //This creates a new DocumentBuilderFactory object, which is used to create a DocumentBuilder
        //later on. DocumentBuilderFactory.newInstance() is a static method that returns a new 
        //DocumentBuilderFactory instance

        // Create a DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        //This creates a new DocumentBuilder object, which is used to parse an XML file and create a
        //Document object representing the parsed document. factory.newDocumentBuilder() creates a 
        //new DocumentBuilder instance using the DocumentBuilderFactory object created earlier.

        // create a new instance of the DocumentBuilder class
        
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        //The parse() method of the DocumentBuilder class takes an InputStream, File, or URL object 
        //as input, and returns a Document object that represents the parsed XML document.
        
        // Parse the file and create a Document object
        Document document = builder.parse(new File(file_path));
        
        return document;
      }

      //the function takes in a tag name as a parameter, retrieves the first node with that tag name, gets the value of its first child node, and returns that value as a string.

      public static String getTagValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
     }

      //This function takes in the tag name as a parameter and returns a list of string values corresponding to all child nodes with that tag name in the parsed XML document
|     public static readTagValues(String tagName) {
      List<String> tagValues = new ArrayList<>();
      NodeList nodeList = doc.getElementsByTagName(tagName);

      System.out.println("------------------------------------------------------");
      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node node = nodeList.item(temp);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) node;
          //Element eElement = (Element) nNode;
          System.out.println("Title : " + getTagValue(eElement, "title"));
          System.out.println("Description: " + getTagValue(eElement, "description"));
          System.out.println("Publication Date: " + getTagValue(eElement, "pubDate"));
          System.out.println("Link : " + getTagValue(eElement, "link"));
        }
        System.out.println("------------------------------------------------------");
       }
       System.out.println("------------------------------------------------------")

    }
}
