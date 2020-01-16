package model.beans.others;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bdCompleta")
public class BdCompleta {

	private Taxis taxis;
	private Taxistas taxistas;
	private Talleres talleres;
	private ListaConducir listaConducir;
	private ListaRevisar listaRevisar;

	@XmlElement(name = "taxis")
	public Taxis getTaxis() {
		return taxis;
	}

	public void setTaxis(Taxis taxis) {
		this.taxis = taxis;
	}

	@XmlElement(name = "taxistas")
	public Taxistas getTaxistas() {
		return taxistas;
	}

	public void setTaxistas(Taxistas taxistas) {
		this.taxistas = taxistas;
	}

	@XmlElement(name = "talleres")
	public Talleres getTalleres() {
		return talleres;
	}

	public void setTalleres(Talleres talleres) {
		this.talleres = talleres;
	}

	
	@XmlElement(name = "listaConducir")
	public ListaConducir getListaConducir() {
		return listaConducir;
	}

	public void setListaConducir(ListaConducir listaConducir) {
		this.listaConducir = listaConducir;
	}
	

	@XmlElement(name = "listaRevisar")
	public ListaRevisar getListaRevisar() {
		return listaRevisar;
	}

	public void setListaRevisar(ListaRevisar listaRevisar) {
		this.listaRevisar = listaRevisar;
	}

	@Override
	public String toString() {
		return "BdCompleta [taxis=" + taxis + ", taxistas=" + taxistas + ", talleres=" + talleres + ", listaConducir="
				+ listaConducir + ", listaRevisar=" + listaRevisar + "]";
	}

}
