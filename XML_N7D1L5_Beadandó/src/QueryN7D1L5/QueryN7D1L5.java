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
		System.out.println("A dolgozók adatai:\n");
		
		//Végigiterálás a listán
		NodeList kolcsonzoList = doc.getElementsByTagName("dolgozo");
		for(int i=0; i<kolcsonzoList.getLength(); i++) {
			Node k = kolcsonzoList.item(i);
			if(k.getNodeType()==Node.ELEMENT_NODE) {
				Element kolcsonzo = (Element) k;
				String DID = kolcsonzo.getAttribute("DID");
				NodeList nevList = kolcsonzo.getChildNodes();
				System.out.println("Dolgozó"+ DID +":");
				for(int j=0; j<nevList.getLength(); j++) {
					Node n = nevList.item(j);
					if (n.getNodeType()==Node.ELEMENT_NODE) {
						Element nev = (Element) n;
						System.out.println(nev.getTagName() + "= " + nev.getTextContent());
						
					}
			}
		}
	}
		System.out.println("--------------");
		System.out.println("2. lekérdezés:");
		System.out.println("Azon motorok, amelyeknek teljesítménye meghaladja a 200LE-t:\n");
		
		NodeList motorList = doc.getElementsByTagName("motor");
		
		for(int i = 0; i < motorList.getLength(); i++) {
				
			Node a = motorList.item(i);
				
			if(a.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) a;
				
				Node node5 = elem.getElementsByTagName("telj").item(0);
				int telj = Integer.parseInt(node5.getTextContent());
				
				if(200 <= telj) {
					String MID = elem.getAttribute("MID");

					Node node1 = elem.getElementsByTagName("mtip").item(0);
					String mtip = node1.getTextContent();

					Node node2 = elem.getElementsByTagName("urtart").item(0);
					String urtart = node2.getTextContent();
					
					
					System.out.println("Motor ID: " + MID);
					System.out.println("Motor típusa: " + mtip);
					System.out.println("Motor űrtartalma: " + urtart + "\n");
				}
			}
		}
	}
}
