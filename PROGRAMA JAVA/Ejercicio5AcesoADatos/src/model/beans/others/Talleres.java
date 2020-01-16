package model.beans.others;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.beans.Taller;

@XmlRootElement(name = "talleres")
public class Talleres {

	private List<Taller> taller;

	public Talleres() {
	}

	public Talleres(List<Taller> taller) {
		this.taller = taller;
	}

	@XmlElement(name = "taller")
	public List<Taller> getTalleres() {
		return taller;
	}

	public void setTalleres(List<Taller> taller) {
		this.taller = taller;
	}

	@Override
	public String toString() {
		return "Talleres [taller=" + taller + "]";
	}

}
