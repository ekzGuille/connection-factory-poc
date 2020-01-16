package model.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "revisar")
public class Revisar {
	private int d_cod;
	private String d_mat;
	private String d_fecha;

	public Revisar() {
	}

	public Revisar(int d_cod, String d_mat, String d_fecha) {
		this.d_cod = d_cod;
		this.d_mat = d_mat;
		this.d_fecha = d_fecha;
	}

	@XmlElement(name = "d_cod")
	public int getD_cod() {
		return d_cod;
	}

	public void setD_cod(int d_cod) {
		this.d_cod = d_cod;
	}

	@XmlAttribute(name = "d_mat")
	public String getD_mat() {
		return d_mat;
	}

	public void setD_mat(String d_mat) {
		this.d_mat = d_mat;
	}

	@XmlAttribute(name = "d_fecha")
	public String getD_fecha() {
		return d_fecha;
	}

	public void setD_fecha(String d_fecha) {
		this.d_fecha = d_fecha;
	}

	@Override
	public String toString() {
		return "Revisar [d_cod=" + d_cod + ", d_mat=" + d_mat + ", d_fecha=" + d_fecha + "]";
	}

}
