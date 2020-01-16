package model.beans.others;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.beans.Revisar;

@XmlRootElement(name = "listaRevisar")
public class ListaRevisar {

	private List<Revisar> revisar;

	public ListaRevisar() {
	}

	public ListaRevisar(List<Revisar> revisar) {
		this.revisar = revisar;
	}

	@XmlElement(name = "revisar")
	public List<Revisar> getListaRevisar() {
		return revisar;
	}

	public void setListaRevisar(List<Revisar> revisar) {
		this.revisar = revisar;
	}

	@Override
	public String toString() {
		return "Revisar [revisar=" + revisar + "]";
	}

}
