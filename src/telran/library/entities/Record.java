package telran.library.entities;
import java.time.LocalDate;

import javax.persistence.*;
@Entity
public class Record {
@Id
@GeneratedValue
int id;
LocalDate pickDate;
LocalDate shouldReturnDate;
LocalDate returnDate;
@ManyToOne
Book book;
@ManyToOne
Reader reader;
public Record(LocalDate pickDate, LocalDate shouldReturnDate, Book book, Reader reader) {
	super();
	this.pickDate = pickDate;
	this.shouldReturnDate = shouldReturnDate;
	this.book = book;
	this.reader = reader;
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
	Record other = (Record) obj;
	if (id != other.id)
		return false;
	return true;
}
public Record(){}
public LocalDate getReturnDate() {
	return returnDate;
}
public void setReturnDate(LocalDate returnDate) {
	this.returnDate = returnDate;
}
public int getId() {
	return id;
}
public LocalDate getPickDate() {
	return pickDate;
}
public LocalDate getShouldReturnDate() {
	return shouldReturnDate;
}
public Book getBook() {
	return book;
}
public Reader getReader() {
	return reader;
}

}
