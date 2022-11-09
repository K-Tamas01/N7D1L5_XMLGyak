package XpathN7D1L5;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


import org.xml.sax.SAXException;


public class XpathN7D1L5 {

	public static void main(String[] args) {
		try{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse("XpathN7D1L5.xml");
			
			document.getDocumentElement().normalize();
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			String expression = "/class/student";
			
			NodeList nodeList = (NodeList)xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			for(int i = 0; i < nodeList.getLength(); i++){
				Node node = nodeList.item(i);
				
				System.out.println("\nAktu�lis elem: "+node.getNodeName());
				
				if(node.getNodeType() == javax.xml.soap.Node.ELEMENT_NODE && node.getNodeName().equals("student")){
					Element element = (Element) node;
					
					System.out.println("Hallgat� ID: "+ element.getAttribute("id"));
					
					System.out.println("Keresztn�v: "+ element.getElementsByTagName("keresztnev").item(0).getTextContent());
					
					System.out.println("Vezet�kn�v: "+ element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					
					System.out.println("Becen�v: "+ element.getElementsByTagName("becenev").item(0).getTextContent());
					
					System.out.println("Kor: "+ element.getElementsByTagName("kor").item(0).getTextContent());
				}
			}
		} catch(ParserConfigurationException e){
			e.printStackTrace();
		} catch(SAXException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} catch(XPathExpressionException e){
			e.printStackTrace();
		}

	}

}
