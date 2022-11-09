package domN7D1L5;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class DomModifyKPR{
    public static void main(String argv[]){
        try{
            File inputFile = new File("carsN7D1L5.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.parsers(inputFile);

            Node cars = doc.getFirstChild();

            Node supercar = doc.getElementsByTagName("supercars").item(0);

            NamedNodeMap attr = supercar.getAttributes();
            Node nodeAttr = attr.getNamedItem("company");
            nodeAttr.setTextContent("Lamborghini");

            NodeList list = supercar.getChildNodes();

            for(int temp = 0; temp < list.getLength(); temp++){
                Node node = list.item(temp);

                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) node;

                    if("carname".equals(eElement.getNodeName())){
                        if("Ferrari 01".equals(eElement.getTextContent())){
                            eElement.setTextContent("Lamborghini 01");
                        }
                        if("Ferrari 02".equals(eElement.getTextContent())){
                            eElement.setTextContent("Lamborghini 02");
                        }
                    }
                }
                NodeList childNodes = cars.getChildNodes();

                for(int count = 0; count < childNodes.getLength(); count++){
                    Node node = childNodes.item(count);

                    if("luxurycars".equals(node.getNodeName()))
                        cars.removeChild(node);
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                DOMSource source = new DOMSource(doc);

                System.out.println("-----Módosított fájl--------");
                StreamResult consoleResult = new StreamResult(System.out);
                transformer.transform(source, consoleResult);

            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}