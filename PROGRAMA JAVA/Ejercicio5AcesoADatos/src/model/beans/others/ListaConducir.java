package model.beans.others;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.beans.Conducir;

@XmlRootElement(name = "listaConducir")
public class ListaConducir {

	private List<Conducir> conducir;

	public ListaConducir() {
	}

	public ListaConducir(List<Conducir> conducir) {
		this.conducir = conducir;
	}

	@XmlElement(name = "conducir")
	public List<Conducir> getListaConducir() {
		return conducir;
	}

	public void setListaConducir(List<Conducir> conducir) {
		this.conducir = conducir;
	}

	@Override
	public String toString() {
		return "Conducir [conducir=" + conducir + "]";
	}

}
