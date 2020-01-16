package model.utils.writer;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.beans.Taller;

public class XMLWriterTaller implements XMLWriterI<Taller> {

	@Override
	public void writeTable(ArrayList<Taller> lstDatos, File file) {
		
		if (lstDatos != null && !lstDatos.isEmpty()) {

			try {

				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("talleres");
				doc.appendChild(rootElement);

				for (Taller taller : lstDatos) {

					Element tallerElemento = doc.createElement("taller");
					rootElement.appendChild(tallerElemento);

					Attr d_cod = doc.createAttribute("d_cod");
					d_cod.setValue(String.valueOf(taller.getD_cod()));
					tallerElemento.setAttributeNode(d_cod);

					Element d_nom1 = doc.createElement("d_nom1");
					d_nom1.appendChild(doc.createTextNode(taller.getD_nom1()));
					tallerElemento.appendChild(d_nom1);

					Element d_tfno = doc.createElement("d_tfno");
					d_tfno.appendChild(doc.createTextNode(String.valueOf(taller.getD_tfno())));
					tallerElemento.appendChild(d_tfno);

					Element d_nom = doc.createElement("d_nom");
					d_nom.appendChild(doc.createTextNode(taller.getD_nom()));
					tallerElemento.appendChild(d_nom);

				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");	
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(file);

				transformer.transform(source, result);
				
				System.out.println("Tabla Taller exportada!");

			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			}
		}
	}
	
}
