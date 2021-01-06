package pkg_4;

import java.time.LocalDate;



public class Ding implements Comparable <Ding>{
	static int counter;
	int id;
	long meter;
	String name;
	String color;
	double temperature;
	LocalDate birthdate;
	KindDing kiddi;
	
	public static Ding makeDing() {
		return new Ding( ++counter,  42l, ("DingNr: " + counter), "green", 36.7, LocalDate.of(2000, 11, 12) );
	}

	
	
	
	public Ding(int id, long meter, String name, String color, double temperature, LocalDate birthdate) {
		super();
		this.id = id;
		this.meter = meter;
		this.name = name;
		this.color = color;
		this.temperature = temperature;
		this.birthdate = birthdate;
	}


	public Ding(int id, long meter, String name, String color, double temperature, LocalDate birthdate,
			KindDing kiddi) {
		super();
		this.id = id;
		this.meter = meter;
		this.name = name;
		this.color = color;
		this.temperature = temperature;
		this.birthdate = birthdate;
		this.kiddi = kiddi;
	}

	
	
	

	int getId() {
		return id;
	}


	long getMeter() {
		return meter;
	}


	String getName() {
		return name;
	}


	String getColor() {
		return color;
	}


	double getTemperature() {
		return temperature;
	}


	LocalDate getBirthdate() {
		return birthdate;
	}


	KindDing getKiddi() {
		return kiddi;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + id;
		result = prime * result + ((kiddi == null) ? 0 : kiddi.hashCode());
		result = prime * result + (int) (meter ^ (meter >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(temperature);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ding other = (Ding) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (id != other.id)
			return false;
		if (kiddi == null) {
			if (other.kiddi != null)
				return false;
		} else if (!kiddi.equals(other.kiddi))
			return false;
		if (meter != other.meter)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(temperature) != Double.doubleToLongBits(other.temperature))
			return false;
		return true;
	}


	@Override
	public int compareTo(Ding o) {		
		return this.id - o.id;
	}
	
	public String toString() {
		
		return "id= " + this.id + " name= " + this.name;
	}
	
	
	
	
	
}	


class KindDing{
	int id;
	String name;
	
	public KindDing(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	int getId() {
		return id;
	}
	void setId(int id) {
		this.id = id;
	}
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KindDing other = (KindDing) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
}