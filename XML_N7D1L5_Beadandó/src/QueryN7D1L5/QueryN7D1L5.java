package QueryN7D1L5;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;      //szükséges csomagok importálása
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class QueryN7D1L5 {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

		File xmlFile = new File("XMLN7D1L5.xml");     //felhasznált XML fájl
		
		//DocumentumBuilder létrehozása
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//A parse() metódus elemzi az XML fájlt
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();
		
		//Megkapjuk a dokumentum gyökérelemét.
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName() + "\n");
		
		System.out.println("--------------");
		System.out.println("1. lekérdezés:");
		System.out.println("A kölcsözõk adatai:\n");
		
		//Végigiterálás a listán
		NodeList kolcsonzoList = doc.getElementsByTagName("Kolcsonzo");
		for(int i=0; i<kolcsonzoList.getLength(); i++) {
			Node k = kolcsonzoList.item(i);
			if(k.getNodeType()==Node.ELEMENT_NODE) {
				Element kolcsonzo = (Element) k;
				String ID = kolcsonzo.getAttribute("ID");
				NodeList nevList = kolcsonzo.getChildNodes();
				for(int j=0; j<nevList.getLength(); j++) {
					Node n = nevList.item(j);
					if (n.getNodeType()==Node.ELEMENT_NODE) {
						Element nev = (Element) n;
						System.out.println("Kölcsönzõ " + ID + ": " + nev.getTagName() + "= " + nev.getTextContent());
						
					}
			}
		}
	}
		System.out.println("--------------");
		System.out.println("2. lekérdezés:");
		System.out.println("Azon könyvek adatai, amelyeknek ára 5200:\n");
		
		NodeList arList = doc.getElementsByTagName("Konyv");
		
		for(int i = 0; i < arList.getLength(); i++) {
				
			Node a = arList.item(i);
				
			if(a.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) a;
				
				Node node5 = elem.getElementsByTagName("Ar").item(0);
				String ar = node5.getTextContent();
				
				if("5200".equals(ar)) {
					String ID = elem.getAttribute("ID");

					Node node1 = elem.getElementsByTagName("Cim").item(0);
					String cim = node1.getTextContent();
					
					Node node2 = elem.getElementsByTagName("Ar").item(0);
					String ar2 = node2.getTextContent();
					
					Node node3 = elem.getElementsByTagName("Szerzo").item(0);
					String szerzo = node3.getTextContent();
					
					Node node4 = elem.getElementsByTagName("Oldalszam").item(0);
					String oldalszam = node4.getTextContent();
					
					System.out.println("Könyv ID: " + ID);
					System.out.println("Cím: " + cim);
					System.out.println("Ár: " + ar2);
					System.out.println("Szerzõ: " + szerzo);
					System.out.println("Oldalszám: " + oldalszam + "\n");
				}
			}
		}
	}
}
