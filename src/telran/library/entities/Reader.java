package telran.library.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;
@Entity
public class Reader {
	@Id
	int id;
	String name;
	LocalDate birthDate;
	String phone;

	@OneToMany(mappedBy = "reader")
	Set<Record> records;
	
	public Reader(int id, String name, LocalDate birthDate, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.phone = phone;
	}
	public Reader(){}
	
	public Set<Record> getRecords() {
	    return records;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Reader other = (Reader) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reader [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", phone=" + phone + "]";
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
}
