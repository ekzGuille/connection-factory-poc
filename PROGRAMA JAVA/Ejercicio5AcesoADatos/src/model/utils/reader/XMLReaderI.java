package model.utils.reader;

import java.io.File;
import java.util.List;

public interface XMLReaderI<E> {
	
	public List<E> readTable(File file);
//	https://codippa.com/how-to-convert-xml-to-java-object-using-jaxb/
//	https://stackoverflow.com/questions/16364547/how-to-parse-xml-to-java-object
//	https://howtodoinjava.com/jaxb/read-xml-to-java-object/
//	https://www.javatpoint.com/jaxb-unmarshalling-example
}
