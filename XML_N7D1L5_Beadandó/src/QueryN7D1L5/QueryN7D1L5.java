package QueryN7D1L5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
		System.out.println("--------------");
		System.out.println("3. lekérdezés:");
		System.out.println("Azon Dolgozók listája akik Miskolcon élnek:\n");
		
		NodeList lakosList = doc.getElementsByTagName("dolgozo");
		
		for(int i = 0; i < lakosList.getLength(); i++) {
				
			Node a = lakosList.item(i);
				
			if(a.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) a;
				
				Node node6 = elem.getElementsByTagName("varos").item(0);
				String citynode = node6.getTextContent();
				
				if("Miskolc".equals(citynode)) {
					String DID = elem.getAttribute("DID");
					
					Node node7 = elem.getElementsByTagName("dneve").item(0);
					String name = node7.getTextContent();
					
		            Node node8_1 = elem.getElementsByTagName("irsz").item(0);
		            String irsz = node8_1.getTextContent();
		            	  
		            Node node8_2 = elem.getElementsByTagName("varos").item(0);
		            String city = node8_2.getTextContent();
		            	  
		            Node node8_3 = elem.getElementsByTagName("utca").item(0);
		            String street = node8_3.getTextContent();
					
					 ArrayList<String> tel = new ArrayList<String>();
		                for(int j = 0; j < elem.getElementsByTagName("dtel").getLength(); j++) {
		                	Node node9 = elem.getElementsByTagName("dtel").item(j);
		                	tel.add(node9.getTextContent());
		             }
		                
		             Node node10 = elem.getElementsByTagName("dszul").item(0);
		             String szul = node10.getTextContent();
		                
		             System.out.println("ID: " + DID);
		             System.out.println("Név: " + name);
		             System.out.println("Irsz: " + irsz);
		             System.out.println("Város: " + city);
		             System.out.println("Utca: " + street);
		             System.out.println("Telefonszama: " + tel);
		             System.out.println("Szuletes datum: " + szul);
		            
					
				}
			}
		}
		System.out.println("--------------");
		System.out.println("4. lekérdezés:");
		System.out.println("Legkisebb motor:\n");
		
		NodeList engineList = doc.getElementsByTagName("motor");
		
		ArrayList<String> MID = new ArrayList<String>();
		ArrayList<Integer> urtart = new ArrayList<Integer>();
		ArrayList<String> mtip = new ArrayList<String>();
		ArrayList<String> mtelj = new ArrayList<String>();
		
		for(int i = 0; i < engineList.getLength(); i++) {
			
			Node a = engineList.item(i);
			
			if(a.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) a;
				
				MID.add(elem.getAttribute("MID"));
				
				Node node11 = elem.getElementsByTagName("urtart").item(0);
				urtart.add(Integer.parseInt(node11.getTextContent()));
				
				Node node12 = elem.getElementsByTagName("mtip").item(0);
				mtip.add(node12.getTextContent());
				
				Node node13 = elem.getElementsByTagName("telj").item(0);
				mtelj.add(node13.getTextContent());
				
			}
		}
		
		int min_urtart = urtart.get(0);
		String min_MID = MID.get(0);
		String min_MTIP = mtip.get(0);
		String min_telj = mtelj.get(0);
		for(int i = 1; i < MID.size(); i++) {
			if(min_urtart > urtart.indexOf(i))  {
				min_MID = MID.get(i);
				min_MTIP = mtip.get(i);
				min_telj = mtelj.get(i);
				min_urtart = urtart.get(i);
			}
		}
		System.out.println("Motor ID: " + min_MID);
		System.out.println("Motor típusa: " + min_MTIP);
		System.out.println("Motor űrtartalma: " + min_urtart);
		System.out.println("Motor teljesítménye: " + min_telj + "\n");
		
		
		System.out.println("--------------");
		System.out.println("5. lekérdezés:");
		System.out.println("Melyik eladóval van kapcsolatban a Dodge gyártó:\n");
		
		NodeList salerList = doc.getElementsByTagName("elado");
		NodeList factoryList = doc.getElementsByTagName("gyarto");
		NodeList deliveryList = doc.getElementsByTagName("kiszallitas");
		
		String GYID = new String();
		String Ekiszallitas = new String();

		for(int i = 0; i < factoryList.getLength(); i++) {
			
			Node n = factoryList.item(i);
				
			if(n.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) n;
				
					Node node18 = elem.getElementsByTagName("gyneve").item(0);
					String gyneve = node18.getTextContent();
					
					if(gyneve.equals("Dodge"))
						GYID = elem.getAttribute("GYID");       						
			}
		}
		
		
		for(int i = 0; i < deliveryList.getLength(); i++) {
			
			Node n = deliveryList.item(i);
				
			if(n.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) n;
				
				
				if(GYID.equals(elem.getAttribute("GYID"))) {
					Ekiszallitas = elem.getAttribute("EID");
				}

			}
		}
				
		Node a = salerList.item(Integer.parseInt(Ekiszallitas) - 1);
				
		if(a.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) a;
				
			String EID = elem.getAttribute("EID"); 
					
			Node node14 = elem.getElementsByTagName("etulaj").item(0);
			String name = node14.getTextContent();
					
		    Node node15 = elem.getElementsByTagName("eadsz").item(0);
		    String adosz = node15.getTextContent();
		            	  
		    Node node16 = elem.getElementsByTagName("ecim").item(0);
		    String address = node16.getTextContent();
		            	  
		    Node node17 = elem.getElementsByTagName("eneve").item(0);
		    String ename = node17.getTextContent();
		                
		    System.out.println("ID: " + EID);
		    System.out.println("Név: " + name);
		    System.out.println("Adószám: " + adosz);
		    System.out.println("Címe: " + address);
		    System.out.println("Autóbolt neve: " + ename);
		           					
		}
	}
}
