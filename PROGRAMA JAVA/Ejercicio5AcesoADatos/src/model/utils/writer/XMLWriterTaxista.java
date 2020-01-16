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

import model.beans.Taxista;

public class XMLWriterTaxista implements XMLWriterI<Taxista> {

	@Override
	public void writeTable(ArrayList<Taxista> lstDatos, File file) {
		
		if (lstDatos != null && !lstDatos.isEmpty()) {

			try {

				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("taxistas");
				doc.appendChild(rootElement);

				for (Taxista taxista : lstDatos) {

					Element taxistaElemento = doc.createElement("taxista");
					rootElement.appendChild(taxistaElemento);

					Attr d_dni = doc.createAttribute("d_dni");
					d_dni.setValue(taxista.getD_dni());
					taxistaElemento.setAttributeNode(d_dni);

					Element d_nom = doc.createElement("d_nom");
					d_nom.appendChild(doc.createTextNode(taxista.getD_nom()));
					taxistaElemento.appendChild(d_nom);
					
					Element d_tfno = doc.createElement("d_tfno");
					d_tfno.appendChild(doc.createTextNode(String.valueOf(taxista.getD_tfno())));
					taxistaElemento.appendChild(d_tfno);

					Element d_dir = doc.createElement("d_dir");
					d_dir.appendChild(doc.createTextNode(String.valueOf(taxista.getD_dir())));
					taxistaElemento.appendChild(d_dir);

					Element d_edad = doc.createElement("d_edad");
					d_edad.appendChild(doc.createTextNode(String.valueOf(taxista.getD_edad())));
					taxistaElemento.appendChild(d_edad);

				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(file);

				transformer.transform(source, result);

				System.out.println("Tabla Taxista exportada!");

			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			}
		}
	}
	

}
