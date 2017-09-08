package telran.library.entities;
import java.util.Set;

import javax.persistence.*;
@Entity
public class Book {
@Id
long isbn;
String title;
int amount;
int pickPeriod;
@ManyToMany(fetch=FetchType.EAGER)
Set<Author> authors;

@OneToMany(mappedBy = "book")
Set<Record> records;

public Book(long isbn, String title, int amount, int pickPeriod) {
	this.isbn = isbn;
	this.title = title;
	this.amount = amount;
	this.pickPeriod = pickPeriod;
}
public Book(){}

public Set<Record> getRecords() {
    return records;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (isbn ^ (isbn >>> 32));
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
	Book other = (Book) obj;
	if (isbn != other.isbn)
		return false;
	return true;
}
@Override
public String toString() {
	return "Book [isbn=" + isbn + ", title=" + title + ", amount=" + amount + ", pickPeriod=" + pickPeriod
			+ ", authors=" + authors + "]";
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public int getPickPeriod() {
	return pickPeriod;
}
public void setPickPeriod(int pickPeriod) {
	this.pickPeriod = pickPeriod;
}
public Set<Author> getAuthors() {
	return authors;
}
public void setAuthors(Set<Author> authors) {
	this.authors = authors;
}
public long getIsbn() {
	return isbn;
}
public String getTitle() {
	return title;
}

}
