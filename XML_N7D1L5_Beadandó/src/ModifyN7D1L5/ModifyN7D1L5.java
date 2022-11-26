package ModifyN7D1L5;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;     //szükséges csomagok importálása
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ModifyN7D1L5 {

	public static void main(String[] args) {
		
		try {
			File xmlFile = new File("XMLN7D1L5.xml");  //felhasznált XML fájl

			//DocumentumBuilder létrehozása
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
					
			//A parse() metódus elemzi az XML fájlt
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();
			
			
			//Végig iterálunk a konyv elemeken, a 240-es oldalszámúakat 300-assá módosítjuk,
			
			//A getElementByTagName() metódus segítségével megkapjuk a könyv elem NodeListjét a dokumentumban.
			NodeList nList = doc.getElementsByTagName("motor");
			
			//A listán for ciklussal megyünk végig
			for(int i = 0; i < nList.getLength(); i++) {
				Node motor = doc.getElementsByTagName("motor").item(i);
		        
		        NodeList list = motor.getChildNodes();
		        
		        for (int temp = 0; temp < list.getLength(); temp++) {
		           Node node = list.item(temp);
		           if (node.getNodeType() == Node.ELEMENT_NODE) {
		              Element eElement = (Element) node;
		              if ("urtart".equals(eElement.getNodeName())) {
		                 if("1400".equals(eElement.getTextContent())) {
		                    eElement.setTextContent("1200");
		                 }
		              }
		           }
		        }
			}
			
			NodeList nList2 = doc.getElementsByTagName("dolgozo");
			
			for(int i = 0; i < nList2.getLength(); i++) {
				Node dolgozo = doc.getElementsByTagName("dolgozo").item(i);
		        
		        NodeList list2 = dolgozo.getChildNodes();
		        
		        for (int temp = 0; temp < list2.getLength(); temp++) {
		           Node node = list2.item(temp);
		           if (node.getNodeType() == Node.ELEMENT_NODE) {
		              Element eElement = (Element) node;
		              if ("dneve".equals(eElement.getNodeName())) {
		                 if("Fekete Peter".equals(eElement.getTextContent())) {
		                	 eElement.setTextContent("Fekete Istvan");
		                 }
		              }
		           }
		        }
			}
			//Dolgozok fizetés létrehozása egy alap 350k értékkel 
			NodeList nList3 = doc.getElementsByTagName("dolgozo");
			Element dolgozo = null;
			
			for(int temp = 0; temp < nList3.getLength(); temp++) {
				dolgozo = (Element) nList3.item(temp);
				Element salaryElement = doc.createElement("Fizetes");
				salaryElement.appendChild(doc.createTextNode("350000"));
				dolgozo.appendChild(salaryElement);
			}
			
			
			
			System.out.println("Az adott kategóriákban módosított adatok:");
			System.out.println("\n1. módosítás: [MotorUrtartalom: 1400 -> Oldalszám: 1200]");
			System.out.println("2. módosítás: [Dolgozo neve: Fekete Peter -> Dolgozo neve: Fekete Istvan]");
			
			for (int i2 = 0; i2 < nList.getLength(); i2++) {
	            Node nNode = nList.item(i2);

	            System.out.println("\nAktuális elem: " + nNode.getNodeName());

	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement2 = (Element) nNode;

	                System.out.println("Motor ID-ja: " + eElement2.getAttribute("MID"));

	                System.out.println("Henger Urtatalom: " + eElement2.getElementsByTagName("urtart").item(0).getTextContent());
	                
	                System.out.println("Tipusa: " + eElement2.getElementsByTagName("mtip").item(0).getTextContent());
	                
	                System.out.println("Teljesitmenye: " + eElement2.getElementsByTagName("telj").item(0).getTextContent());

	            }
	        }
			System.out.println("\n3. módosítás: [Név: Haragos Hugó -> Név: Haragos Hedvig]");
			
			for (int i = 0; i < nList2.getLength(); i++) {
	            Node nNode = nList2.item(i);

	            System.out.println("\nAktuális elem: " + nNode.getNodeName());

	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;

	                System.out.println("Dolgozo ID-ja: " + eElement.getAttribute("DID"));

	                System.out.println("Név: " + eElement.getElementsByTagName("dneve").item(0).getTextContent());
	                System.out.println("Címe: " + eElement.getElementsByTagName("dcime").item(0).getTextContent());
	                System.out.println("Neve: " + eElement.getElementsByTagName("dszul").item(0).getTextContent());
	                System.out.println("Telefonszama: " + eElement.getElementsByTagName("dtel").item(0).getTextContent());
	            }
	        }
			
			System.out.println("\n4. módosítás: [Alap fizetés beállítása a könyvtárosoknak]");
			
			for (int i = 0; i < nList3.getLength(); i++) {
	            Node nNode = nList3.item(i);

	            System.out.println("\nAktuális elem: " + nNode.getNodeName());

	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement3 = (Element) nNode;

	                System.out.println("Név: " + eElement3.getElementsByTagName("dneve").item(0).getTextContent());
	                System.out.println("Címe: " + eElement3.getElementsByTagName("dcime").item(0).getTextContent());
	                System.out.println("Neve: " + eElement3.getElementsByTagName("dszul").item(0).getTextContent());
	                System.out.println("Telefonszama: " + eElement3.getElementsByTagName("dtel").item(0).getTextContent());
	                System.out.println("Fizetes: " + eElement3.getElementsByTagName("Fizetes").item(0).getTextContent());
	            }
	        }
	        
	        
	        //Módosított XML kiírása a konzolra
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        System.out.println("\n-----------A teljes módosított XML dokumentum-----------");
	        StreamResult consoleResult = new StreamResult(System.out);
	        transformer.transform(source, consoleResult);
			
		    }catch (Exception e) {
				e.printStackTrace();
		    }
		}

	}
