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

import model.beans.Conducir;

public class XMLWriterConducir implements XMLWriterI<Conducir> {

	@Override
	public void writeTable(ArrayList<Conducir> lstDatos, File file) {
		
		if (lstDatos != null && !lstDatos.isEmpty()) {

			try {

				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("listaConducir");
				doc.appendChild(rootElement);

				for (Conducir conducir : lstDatos) {

					Element conducirElemento = doc.createElement("conducir");
					rootElement.appendChild(conducirElemento);

					Attr d_dni = doc.createAttribute("d_dni");
					d_dni.setValue(conducir.getD_dni());
					conducirElemento.setAttributeNode(d_dni);

					Attr d_fecha = doc.createAttribute("d_fecha");
					d_fecha.setValue(conducir.getD_fecha());
					conducirElemento.setAttributeNode(d_fecha);

					Element d_mat = doc.createElement("d_mat");
					d_mat.appendChild(doc.createTextNode(conducir.getD_mat()));
					conducirElemento.appendChild(d_mat);

					Element d_hora_inicio = doc.createElement("d_hora_inicio");
					d_hora_inicio.appendChild(doc.createTextNode(conducir.getD_hora_inicio()));
					conducirElemento.appendChild(d_hora_inicio);

					Element d_hora_fin = doc.createElement("d_hora_fin");
					d_hora_fin.appendChild(doc.createTextNode(conducir.getD_hora_fin()));
					conducirElemento.appendChild(d_hora_fin);

				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(file);

				transformer.transform(source, result);

				System.out.println("Tabla Conducir exportada!");

			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			}
		}
	}


}
