package model.utils.reader;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.beans.Revisar;
import model.beans.others.ListaRevisar;

public class XMLReaderListaRevisar implements XMLReaderI<Revisar> {

	@Override
	public List<Revisar> readTable(File file) {
		List<Revisar> lstListaRevisar = null;
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(ListaRevisar.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		lstListaRevisar = ((ListaRevisar)jaxbUnmarshaller.unmarshal(file)).getListaRevisar();
		}catch(JAXBException e) {
			e.printStackTrace();
		}
		return lstListaRevisar;
	}

}
