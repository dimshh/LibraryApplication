package telran.library.entities;
import java.util.Set;

import javax.persistence.*;
@Entity
public class Author {
	@Id
	String name;
	String country;
	
	@ManyToMany(mappedBy = "authors")
	Set<Book> books;
	
	public Author(){}
	
	public Set<Book> getBooks() {
	    return books;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Author other = (Author) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Author [name=" + name + ", country=" + country + "]";
	}
	public Author(String name, String country) {
		super();
		this.name = name;
		this.country = country;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	

}
