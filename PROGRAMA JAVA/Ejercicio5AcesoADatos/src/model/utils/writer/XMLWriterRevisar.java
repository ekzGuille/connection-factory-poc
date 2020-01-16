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

import model.beans.Revisar;

public class XMLWriterRevisar implements XMLWriterI<Revisar>{

	@Override
	public void writeTable(ArrayList<Revisar> lstDatos, File file) {

		if (lstDatos != null && !lstDatos.isEmpty()) {

			try {

				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("listaRevisar");
				doc.appendChild(rootElement);

				for (Revisar revisar : lstDatos) {

					Element revisarElemento = doc.createElement("revisar");
					rootElement.appendChild(revisarElemento);

					Attr d_mat = doc.createAttribute("d_mat");
					d_mat.setValue(revisar.getD_mat());
					revisarElemento.setAttributeNode(d_mat);

					Attr d_fecha = doc.createAttribute("d_fecha");
					d_fecha.setValue(revisar.getD_fecha());
					revisarElemento.setAttributeNode(d_fecha);

					Element d_cod = doc.createElement("d_cod");
					d_cod.appendChild(doc.createTextNode(String.valueOf(revisar.getD_cod())));
					revisarElemento.appendChild(d_cod);

				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(file);

				transformer.transform(source, result);
				
				System.out.println("Tabla Revisar exportada!");

			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			}
		}		
	}
	

}
