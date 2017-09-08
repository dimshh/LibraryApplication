package telran.library.model.interfaces;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Stream;

import telran.library.api.*;
import telran.library.entities.*;

public interface LibraryModel {
	BookCode addBookItem(long isbn,String[]authors,String title,int amount,int pickPeriod);
	boolean addAuthor(String name,String country);
	boolean addBookExemplars(long isbn,int amount);
	boolean addReader(int id,LocalDate dirthDate,String name,String phone);
	PickCode pickBook(long isbn,int readerId,LocalDate pickDate);
	boolean returnBook(long isbn,int readerId,LocalDate returnDate);
	Iterable<Reader> getReadersDelayed(LocalDate currentDate,int byDays);
	Book getBook(long isbn);
	Author getAuthor(String name);
	Reader getReader(int id);
	Stream<Record> getRecordsStream();
	Collection<Book> getBooksByAuthor(String authorName);
}
