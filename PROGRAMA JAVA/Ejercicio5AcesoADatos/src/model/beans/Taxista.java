package model.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "taxista")
public class Taxista {

	private String d_dni;
	private String d_nom;
	private int d_tfno;
	private String d_dir;
	private int d_edad;

	public Taxista() {
	}

	public Taxista(String d_dni, String d_nom, int d_tfno, String d_dir, int d_edad) {
		this.d_dni = d_dni;
		this.d_nom = d_nom;
		this.d_tfno = d_tfno;
		this.d_dir = d_dir;
		this.d_edad = d_edad;
	}

	@XmlAttribute(name = "d_dni")
	public String getD_dni() {
		return d_dni;
	}

	public void setD_dni(String d_dni) {
		this.d_dni = d_dni;
	}

	@XmlElement(name = "d_nom")
	public String getD_nom() {
		return d_nom;
	}

	public void setD_nom(String d_nom) {
		this.d_nom = d_nom;
	}

	@XmlElement(name = "d_tfno")
	public int getD_tfno() {
		return d_tfno;
	}

	public void setD_tfno(int d_tfno) {
		this.d_tfno = d_tfno;
	}

	@XmlElement(name = "d_dir")
	public String getD_dir() {
		return d_dir;
	}

	public void setD_dir(String d_dir) {
		this.d_dir = d_dir;
	}

	@XmlElement(name = "d_edad")
	public int getD_edad() {
		return d_edad;
	}

	public void setD_edad(int d_edad) {
		this.d_edad = d_edad;
	}

	@Override
	public String toString() {
		return "Taxista [d_dni=" + d_dni + ", d_nom=" + d_nom + ", d_tfno=" + d_tfno + ", d_dir=" + d_dir + ", d_edad="
				+ d_edad + "]";
	}

}
