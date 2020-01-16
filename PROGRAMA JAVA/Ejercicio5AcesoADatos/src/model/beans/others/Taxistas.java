package model.beans.others;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.beans.Taxista;

@XmlRootElement(name = "taxistas")
public class Taxistas {

	private List<Taxista> taxista;

	public Taxistas() {
	}

	public Taxistas(List<Taxista> taxista) {
		this.taxista = taxista;
	}

	@XmlElement(name = "taxista")
	public List<Taxista> getTaxistas() {
		return taxista;
	}

	public void setTaxistas(List<Taxista> taxista) {
		this.taxista = taxista;
	}

	@Override
	public String toString() {
		return "Taxistas [taxista=" + taxista + "]";
	}

}
