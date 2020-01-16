package model.utils.writer;

import java.io.File;
import java.util.ArrayList;

public interface XMLWriterI<E> {

	public void writeTable(ArrayList<E> lstDatos, File file);
//	https://www.lawebdelprogramador.com/codigo/Java/3202-Como-crear-un-archivo-XML-con-Java.html
}
