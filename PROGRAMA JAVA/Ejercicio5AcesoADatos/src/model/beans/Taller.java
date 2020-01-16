package model.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "taller")
public class Taller {

	private int d_cod;
	private String d_nom1;
	private int d_tfno;
	private String d_nom;

	public Taller() {
	}

	public Taller(int d_cod, String d_nom1, int d_tfno, String d_nom) {
		this.d_cod = d_cod;
		this.d_nom1 = d_nom1;
		this.d_tfno = d_tfno;
		this.d_nom = d_nom;
	}

	@XmlAttribute(name = "d_cod")
	public int getD_cod() {
		return d_cod;
	}

	public void setD_cod(int d_cod) {
		this.d_cod = d_cod;
	}

	@XmlElement(name = "d_nom1")
	public String getD_nom1() {
		return d_nom1;
	}

	public void setD_nom1(String d_nom1) {
		this.d_nom1 = d_nom1;
	}

	@XmlElement(name = "d_tfno")
	public int getD_tfno() {
		return d_tfno;
	}

	public void setD_tfno(int d_tfno) {
		this.d_tfno = d_tfno;
	}

	@XmlElement(name = "d_nom")
	public String getD_nom() {
		return d_nom;
	}

	public void setD_nom(String d_nom) {
		this.d_nom = d_nom;
	}

	@Override
	public String toString() {
		return "Taller [d_cod=" + d_cod + ", d_nom1=" + d_nom1 + ", d_tfno=" + d_tfno + ", d_nom=" + d_nom + "]";
	}

}
