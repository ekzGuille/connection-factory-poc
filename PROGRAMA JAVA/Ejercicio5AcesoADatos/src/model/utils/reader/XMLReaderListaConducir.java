package model.utils.reader;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.beans.Conducir;
import model.beans.others.ListaConducir;

public class XMLReaderListaConducir implements XMLReaderI<Conducir>{

	@Override
	public List<Conducir> readTable(File file) {
		List<Conducir> lstListaConducir = null;
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(ListaConducir.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		lstListaConducir = ((ListaConducir)jaxbUnmarshaller.unmarshal(file)).getListaConducir();
		}catch(JAXBException e) {
			e.printStackTrace();
		}
		return lstListaConducir;
	}

}
