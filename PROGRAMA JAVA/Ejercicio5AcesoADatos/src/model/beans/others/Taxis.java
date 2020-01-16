package model.beans.others;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.beans.Taxi;

@XmlRootElement(name = "taxis")
public class Taxis {

	private List<Taxi> taxi;

	public Taxis() {
	}

	public Taxis(List<Taxi> taxi) {
		this.taxi = taxi;
	}

	@XmlElement(name = "taxi")
	public List<Taxi> getTaxis() {
		return taxi;
	}

	public void setTaxis(List<Taxi> taxi) {
		this.taxi = taxi;
	}

	@Override
	public String toString() {
		return "Taxis [taxi=" + taxi + "]";
	}

}
