package model.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "conducir")
public class Conducir {
	private String d_dni;
	private String d_mat;
	private String d_fecha;
	private String d_hora_inicio;
	private String d_hora_fin;

	public Conducir() {
	}

	public Conducir(String d_dni, String d_mat, String d_fecha, String d_hora_inicio, String d_hora_fin) {
		this.d_dni = d_dni;
		this.d_mat = d_mat;
		this.d_fecha = d_fecha;
		this.d_hora_inicio = d_hora_inicio;
		this.d_hora_fin = d_hora_fin;
	}

	@XmlAttribute(name = "d_dni")
	public String getD_dni() {
		return d_dni;
	}

	public void setD_dni(String d_dni) {
		this.d_dni = d_dni;
	}

	@XmlElement(name = "d_mat")
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

	@XmlElement(name = "d_hora_inicio")
	public String getD_hora_inicio() {
		return d_hora_inicio;
	}

	public void setD_hora_inicio(String d_hora_inicio) {
		this.d_hora_inicio = d_hora_inicio;
	}

	@XmlElement(name = "d_hora_fin")
	public String getD_hora_fin() {
		return d_hora_fin;
	}

	public void setD_hora_fin(String d_hora_fin) {
		this.d_hora_fin = d_hora_fin;
	}

	@Override
	public String toString() {
		return "Conducir [d_dni=" + d_dni + ", d_mat=" + d_mat + ", d_fecha=" + d_fecha + ", d_hora_inicio="
				+ d_hora_inicio + ", d_hora_fin=" + d_hora_fin + "]";
	}

}
