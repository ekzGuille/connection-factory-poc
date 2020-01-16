package model.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "taxi")
public class Taxi {

	private String d_mat;
	private int d_tfno;
	private int d_km;

	public Taxi() {
	}

	public Taxi(String d_mat, int d_tfno, int d_km) {
		this.d_mat = d_mat;
		this.d_tfno = d_tfno;
		this.d_km = d_km;
	}

	@XmlAttribute(name = "d_mat")
	public String getD_mat() {
		return d_mat;
	}

	public void setD_mat(String d_mat) {
		this.d_mat = d_mat;
	}

	@XmlElement(name = "d_tfno")
	public int getD_tfno() {
		return d_tfno;
	}

	public void setD_tfno(int d_tfno) {
		this.d_tfno = d_tfno;
	}

	@XmlElement(name = "d_km")
	public int getD_km() {
		return d_km;
	}

	public void setD_km(int d_km) {
		this.d_km = d_km;
	}

	@Override
	public String toString() {
		return "Taxi [d_mat=" + d_mat + ", d_tfno=" + d_tfno + ", d_km=" + d_km + "]";
	}

}
