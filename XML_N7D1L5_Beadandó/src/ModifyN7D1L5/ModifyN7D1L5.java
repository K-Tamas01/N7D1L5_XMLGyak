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
			
			//Lada Viszont eladó neve kicserélése Audira
			NodeList nList4 = doc.getElementsByTagName("elado");
			
			for(int i = 0; i < nList4.getLength(); i++) {
				Node dolgozo4 = doc.getElementsByTagName("elado").item(i);
		        
		        NodeList list4 = dolgozo4.getChildNodes();
		        
		        for (int temp = 0; temp < list4.getLength(); temp++) {
		           Node node = list4.item(temp);
		           if (node.getNodeType() == Node.ELEMENT_NODE) {
		              Element eElement = (Element) node;
		              if("eneve".equals(eElement.getNodeName())) {
		            	  if("Lada".equals(eElement.getTextContent())) {
		            		  eElement.setTextContent("Audi");
		            	  }
		              }
		           }
		        }
			}
			
			NodeList nList5 = doc.getElementsByTagName("dolgozo");
			
			for(int i = 0; i < nList5.getLength(); i++) {
				Node dolgozo5 = doc.getElementsByTagName("dolgozo").item(i);
		        
		        NodeList list5 = dolgozo5.getChildNodes();
		        
		        for (int temp = 0; temp < list5.getLength(); temp++) {
		           Node node = list5.item(temp);
		           if (node.getNodeType() == Node.ELEMENT_NODE) {
		              Element eElement = (Element) node;
		              if ("dcime".equals(eElement.getNodeName())) {
		            	  Node address = eElement.getElementsByTagName("irsz").item(0);
		            	  if("irsz".equals(address.getNodeName())) {
		            		 if("7400".equals(address.getTextContent())) {
		                	 	address.setTextContent("7402");
		                 	}
		            	  }
		              }
		           }
		        }
			}
			
			System.out.println("Az adott kategóriákban módosított adatok:");
			System.out.println("\n1. módosítás: [MotorUrtartalom: 1400 -> MotorUrtart: 1200]");
			
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
			System.out.println("\n2. módosítás: [Dolgozo neve: Fekete Peter -> Dolgozo neve: Fekete Istvan]");
			
			for (int i = 0; i < nList2.getLength(); i++) {
	            Node nNode = nList2.item(i);

	            System.out.println("\nAktuális elem: " + nNode.getNodeName());

	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;

	                System.out.println("Dolgozo ID-ja: " + eElement.getAttribute("DID"));

	                System.out.println("Név: " + eElement.getElementsByTagName("dneve").item(0).getTextContent());
	                System.out.println("Irsz: " + eElement.getElementsByTagName("irsz").item(0).getTextContent());
	                System.out.println("Város: " + eElement.getElementsByTagName("varos").item(0).getTextContent());
	                System.out.println("Utca: " + eElement.getElementsByTagName("utca").item(0).getTextContent());
	                System.out.println("Neve: " + eElement.getElementsByTagName("dszul").item(0).getTextContent());
	                System.out.println("Telefonszama: " + eElement.getElementsByTagName("dtel").item(0).getTextContent());
	            }
	        }
			
			System.out.println("\n3. módosítás: [Alap fizetés beállítása a dolgozóknak]");
			
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
			
			System.out.println("\n4. módosítás: [Lada Viszont eladó neve kicserélése Audira]");
			
			for (int i = 0; i < nList4.getLength(); i++) {
	            Node nNode = nList4.item(i);

	            System.out.println("\nAktuális elem: " + nNode.getNodeName());

	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement4 = (Element) nNode;

	                System.out.println("Eladó Tulaj: " + eElement4.getElementsByTagName("etulaj").item(0).getTextContent());
	                System.out.println("Adószáma: " + eElement4.getElementsByTagName("eadsz").item(0).getTextContent());
	                System.out.println("Eladó címe: " + eElement4.getElementsByTagName("ecim").item(0).getTextContent());
	                System.out.println("Eladó Neve: " + eElement4.getElementsByTagName("eneve").item(0).getTextContent());
	            }
	        }
			
			System.out.println("\n5. módosítás: [Irsz 7400-ról 7402-re változtatása]");
			
			for (int i = 0; i < nList5.getLength(); i++) {
	            Node nNode = nList5.item(i);

	            System.out.println("\nAktuális elem: " + nNode.getNodeName());

	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement5 = (Element) nNode;

	                System.out.println("Név: " + eElement5.getElementsByTagName("dneve").item(0).getTextContent());
	                System.out.println("Címe: " + eElement5.getElementsByTagName("dcime").item(0).getTextContent());
	                System.out.println("Neve: " + eElement5.getElementsByTagName("dszul").item(0).getTextContent());
	                System.out.println("Telefonszama: " + eElement5.getElementsByTagName("dtel").item(0).getTextContent());
	                System.out.println("Fizetes: " + eElement5.getElementsByTagName("Fizetes").item(0).getTextContent());
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
