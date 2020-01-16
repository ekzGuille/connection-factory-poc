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
import model.beans.Revisar;
import model.beans.Taller;
import model.beans.Taxi;
import model.beans.Taxista;
import model.dao.ConducirDAO;
import model.dao.RevisarDAO;
import model.dao.TallerDAO;
import model.dao.TaxiDAO;
import model.dao.TaxistaDAO;

public class XMLWriterDB {

	public void writeDataBase(File file) {

		// Tablas: Tablas Taxi, Taxista, Taller, Conducir, Revisar

		try {
			// Documento global
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();

			// Elemento global, bdCompleta
			Element bdCompleta = doc.createElement("bdCompleta");
			doc.appendChild(bdCompleta);

			ArrayList<Taxi> lstTaxi = new TaxiDAO().findAll();
			if (lstTaxi != null && !lstTaxi.isEmpty()) {

				// Elemento array taxis
				Element taxis = doc.createElement("taxis");
				bdCompleta.appendChild(taxis);

				for (Taxi taxi : lstTaxi) {

					Element taxiElemento = doc.createElement("taxi");
					taxis.appendChild(taxiElemento);

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

				ArrayList<Taxista> lstTaxista = new TaxistaDAO().findAll();
				if (lstTaxista != null && !lstTaxista.isEmpty()) {

					// Elemento array taxistas
					Element taxistas = doc.createElement("taxistas");
					bdCompleta.appendChild(taxistas);

					for (Taxista taxista : lstTaxista) {

						Element taxistaElemento = doc.createElement("taxista");
						taxistas.appendChild(taxistaElemento);

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
				}

				ArrayList<Taller> lstTaller = new TallerDAO().findAll();
				if (lstTaller != null && !lstTaller.isEmpty()) {

					// Elemento array talleres
					Element talleres = doc.createElement("talleres");
					bdCompleta.appendChild(talleres);

					for (Taller taller : lstTaller) {

						Element tallerElemento = doc.createElement("taller");
						talleres.appendChild(tallerElemento);

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
				}

				ArrayList<Conducir> lstConducir = new ConducirDAO().findAll();
				if (lstConducir != null && !lstConducir.isEmpty()) {

					// Elemento array conducir
					Element listaConducir = doc.createElement("listaConducir");
					bdCompleta.appendChild(listaConducir);

					for (Conducir conducir : lstConducir) {

						Element conducirElemento = doc.createElement("conducir");
						listaConducir.appendChild(conducirElemento);

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
				}

				ArrayList<Revisar> lstRevisar = new RevisarDAO().findAll();
				if (lstRevisar != null && !lstRevisar.isEmpty()) {

					// Elemento array revisar
					Element listaRevisar = doc.createElement("listaRevisar");
					bdCompleta.appendChild(listaRevisar);

					for (Revisar revisar : lstRevisar) {

						Element revisarElemento = doc.createElement("revisar");
						listaRevisar.appendChild(revisarElemento);

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
				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(file);

				transformer.transform(source, result);

				System.out.println("Base de datos exportada!");
			}
		} catch (

		ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

}
