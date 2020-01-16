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

import model.beans.Taxi;

public class XMLWriterTaxi implements XMLWriterI<Taxi> {

	@Override
	public void writeTable(ArrayList<Taxi> lstDatos, File file) {
		
		if (lstDatos != null && !lstDatos.isEmpty()) {

			try {

				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("taxis");
				doc.appendChild(rootElement);

				for (Taxi taxi : lstDatos) {

					Element taxiElemento = doc.createElement("taxi");
					rootElement.appendChild(taxiElemento);

					Attr d_mat = doc.createAttribute("d_mat");
					d_mat.setValue(taxi.getD_mat());
					taxiElemento.setAttributeNode(d_mat);

					Element d_tfno = doc.createElement("d_tfno");
					d_tfno.appendChild(doc.createTextNode(String.valueOf(taxi.getD_tfno())));
					taxiElemento.appendChild(d_tfno);

					Element d_km = doc.createElement("d_km");
					d_km.appendChild(doc.createTextNode(String.valueOf(taxi.getD_km())));
					taxiElemento.appendChild(d_km);

				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(file);

				transformer.transform(source, result);

				System.out.println("Tabla Taxi exportada!");

			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			}
		}
	}
	

}
