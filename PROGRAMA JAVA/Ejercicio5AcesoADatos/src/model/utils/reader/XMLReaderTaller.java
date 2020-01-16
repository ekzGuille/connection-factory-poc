package model.utils.reader;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.beans.Taller;
import model.beans.others.Talleres;

public class XMLReaderTaller implements XMLReaderI<Taller> {

	@Override
	public List<Taller> readTable(File file) {
		List<Taller> lstTalleres = null;
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Talleres.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		lstTalleres = ((Talleres)jaxbUnmarshaller.unmarshal(file)).getTalleres();
		}catch(JAXBException e) {
			e.printStackTrace();
		}
		return lstTalleres;
	}

}
