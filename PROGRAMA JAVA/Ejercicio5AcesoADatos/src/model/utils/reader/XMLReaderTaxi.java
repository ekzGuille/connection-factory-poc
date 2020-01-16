package model.utils.reader;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.beans.Taxi;
import model.beans.others.Taxis;

public class XMLReaderTaxi implements XMLReaderI<Taxi> {


	@Override
	public List<Taxi> readTable(File file){
		List<Taxi> lstTaxi = null;
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Taxis.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		lstTaxi = ((Taxis)jaxbUnmarshaller.unmarshal(file)).getTaxis();
		}catch(JAXBException e) {
			e.printStackTrace();
		}
		return lstTaxi;
	}

}
