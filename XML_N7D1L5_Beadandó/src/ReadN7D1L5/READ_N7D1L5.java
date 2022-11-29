package ReadN7D1L5;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class READ_N7D1L5 {

	public static void main(String[] args) {
		
		
		//Az esetleges keletkezõ hibák lekezelése try-catch blokkban
		try {
//Az olvasandó fájl megadása
			 File xmlFile = new File("XMLN7D1L5.xml");
			 
//Az XML fájl átalakítása DOM objektumokká
		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder dBuilder = factory.newDocumentBuilder();
		        Document doc = dBuilder.parse(xmlFile);
// Node-ok normalizálása
		        doc.getDocumentElement().normalize();
//A gyökér elem nevének kiolvasása és kiíratása konzolra		        
		        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
//A gyökér elem alatt fellelhetõ gyermek/testvér elemek listába illesztése
		        NodeList nList1 = doc.getElementsByTagName("elado");
		        NodeList nList2 = doc.getElementsByTagName("kiszallitas");
		        NodeList nList3 = doc.getElementsByTagName("gyarto");
		        NodeList nList4 = doc.getElementsByTagName("gyar");
		        NodeList nList5 = doc.getElementsByTagName("dolgozo");
		        NodeList nList6 = doc.getElementsByTagName("auto");
		        NodeList nList7 = doc.getElementsByTagName("alkatresz");
		        NodeList nList8 = doc.getElementsByTagName("motor");
		        System.out.println("----------------------------------------");

//Ciklus segítségével végignézzük az aktuális node lista elemeit
		        for (int i = 0; i < nList1.getLength(); i++) {
//Az egyes lista elemeket meghatározzuk node ként
		            Node nNode = nList1.item(i);
//Ha egyezést talál akkor Kiírjuk az adott elemhez tartozó nevet és adatokat is
		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		   //A node-ot elemként definiáljuk
		            	Element elem = (Element) nNode;

		                System.out.println("\nAktuális elem: " + nNode.getNodeName());
		        // Az aktuális elemhez tartozó aazonosító letárolása egy String adattípusban
		                String id = elem.getAttribute("EID");
		        // Az adott elem gyermekelem értékének eltárolása String adattíõusban
		                Node node1 = elem.getElementsByTagName("Etulaj").item(0);
		                String nev = node1.getTextContent();
		                
		                Node node2_1 = elem.getElementsByTagName("Eadsz").item(0);
		                Node node2_2 = elem.getElementsByTagName("Ecim").item(0);
		                Node node2_3 = elem.getElementsByTagName("Eneve").item(0);
		                String varos = node2_1.getTextContent();
		                String Eneve = node2_3.getTextContent();

		                // Az eltárolt értékek kiíratása
		                System.out.println("Elado ID-ja: " + id);
		                System.out.println("Adoszama: " + nev);
		                System.out.println("Cime: " + varos);
		                System.out.println("Eneve: "+ Eneve);

		            }
		        }
// A továbbiakban minden listához ezt a megoldást alkalmazzuk		        
		        for (int i = 0; i < nList2.getLength(); i++) {
		            Node nNode = nList2.item(i);

		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		                Element elem = (Element) nNode;

		                System.out.println("\nAktuális elem: " + nNode.getNodeName());

		                String id1 = elem.getAttribute("GYID");
		                String id2 = elem.getAttribute("EID");

		                System.out.println("Gyarto ID-ja: " + id1);
		                System.out.println("Elado ID-JA: " + id2);

		            }
		        }
		        
		        for (int i = 0; i < nList3.getLength(); i++) {
		            Node nNode = nList3.item(i);

		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		                Element elem = (Element) nNode;

		                System.out.println("\nAktuális elem: " + nNode.getNodeName());

		                String id = elem.getAttribute("GYID");

		                Node node1 = elem.getElementsByTagName("gyneve").item(0);
		                String gyneve = node1.getTextContent();
		                
		                Node node2 = elem.getElementsByTagName("gytulaj").item(0);
		                String gytulaj = node2.getTextContent();
		                
		                Node node3 = elem.getElementsByTagName("gycim").item(0);
		                String gycim = node3.getTextContent();
		                
		                Node node4 = elem.getElementsByTagName("gyadsz").item(0);
		                String gyadsz = node4.getTextContent();


		                System.out.println("Könyv ID-ja: " + id);
		                System.out.println("Cím: " + gyneve);
		                System.out.println("Ár: "+gytulaj);
		                System.out.println("Szerző: "+gycim);
		                System.out.println("Oldalszám: "+gyadsz);
			            }
		        }
		        
		        for (int i = 0; i < nList4.getLength(); i++) {
		            Node nNode = nList4.item(i);

		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		                Element elem = (Element) nNode;

		                System.out.println("\nAktuális elem: " + nNode.getNodeName());

		                String id2 = elem.getAttribute("GYID");
		                String id3 = "";
		                if(elem.getAttribute("DID") == "") {
		                	id3 = elem.getAttribute("KID");
		                	System.out.println("Gyar ID-ja: " + id2);
			                System.out.println("Kocsi ID-ja: " + id3);
			                }
		                else {
		                	id3 = elem.getAttribute("DID");
		                	System.out.println("Gyar ID-ja: " + id2);
			                System.out.println("Dolgozo ID-ja: " + id3);
		                }
		            }
		        }
		        
		        for (int i = 0; i < nList5.getLength(); i++) {
		            Node nNode = nList5.item(i);

		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		                Element elem = (Element) nNode;

		                System.out.println("\nAktuális elem: " + nNode.getNodeName());

		                String id = elem.getAttribute("DID");

		                Node node1 = elem.getElementsByTagName("dneve").item(0);
		                String nev = node1.getTextContent();
		                
		                Node node2 = elem.getElementsByTagName("dcime").item(0);
		                String cim = node2.getTextContent();
		                Node node3 = elem.getElementsByTagName("dszul").item(0);
		                String dszul = node3.getTextContent();		             
		                Node node4 = elem.getElementsByTagName("dtel").item(0);
		                String tel = node4.getTextContent();
		                
		                System.out.println("Dolgozo ID-ja: " + id);
		                System.out.println("Név: " + nev);
		                System.out.println("Tel: "+tel);
		                System.out.println("Címe: "+cim);
		                System.out.println("Született: "+dszul);

		            }
		        }
		        
		        for (int i = 0; i < nList6.getLength(); i++) {
		            Node nNode = nList6.item(i);

		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		                Element elem = (Element) nNode;

		                System.out.println("\nAktuális elem: " + nNode.getNodeName());

		                String id = elem.getAttribute("KID");

		                Node node1 = elem.getElementsByTagName("kneve").item(0);
		                String nev = node1.getTextContent();
		                Node node2 = elem.getElementsByTagName("ktip").item(0);
		                String tip = node2.getTextContent();
		                Node node3 = elem.getElementsByTagName("khajt").item(0);
		                String hajt = node3.getTextContent();		             
		                
		                System.out.println("Kocsi ID-ja: " + id);
		                System.out.println("Név: " + nev);
		                System.out.println("Tel: "+tip);
		                System.out.println("Címe: "+hajt);

		            }
		        }
		        
		        for (int i = 0; i < nList7.getLength(); i++) {
		            Node nNode = nList7.item(i);

		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		                Element elem = (Element) nNode;

		                System.out.println("\nAktuális elem: " + nNode.getNodeName());

		                String id2 = elem.getAttribute("KID");
		                String id3 = elem.getAttribute("MID");
  
		                System.out.println("Kocsi ID-ja: " + id2);
			            System.out.println("Motor ID-ja: " + id3);

		            }
		        }
		        
		        for (int i = 0; i < nList8.getLength(); i++) {
		            Node nNode = nList8.item(i);

		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		                Element elem = (Element) nNode;

		                System.out.println("\nAktuális elem: " + nNode.getNodeName());

		                Node node1 = elem.getElementsByTagName("urtart").item(0);
		                String urtart = node1.getTextContent();
		                Node node2 = elem.getElementsByTagName("mtip").item(0);
		                String mtip = node2.getTextContent();
		                Node node3 = elem.getElementsByTagName("telj").item(0);
		                String telj = node3.getTextContent();	

		                System.out.println("Termék ID-ja: " + urtart);
		                System.out.println("Gyártó ID-ja: " + mtip);
		                System.out.println("Termék ID-ja: " + telj);

		            }
		        }
			
		}catch(SAXException sxe) {
			sxe.printStackTrace();
		}catch(ParserConfigurationException pe) {
			pe.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}

	}
}
