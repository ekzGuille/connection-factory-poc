package model.utils.reader;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.beans.Taxista;
import model.beans.others.Taxistas;

public class XMLReaderTaxista implements XMLReaderI<Taxista> {

	@Override
	public List<Taxista> readTable(File file) {
		List<Taxista> lstTaxista = null;
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Taxistas.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		lstTaxista = ((Taxistas)jaxbUnmarshaller.unmarshal(file)).getTaxistas();
		}catch(JAXBException e) {
			e.printStackTrace();
		}
		return lstTaxista;
	}

}
