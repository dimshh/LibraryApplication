package telran.library.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import telran.library.api.BookCode;
import telran.library.api.PickCode;
import telran.library.entities.Author;
import telran.library.entities.Book;
import telran.library.entities.Reader;
import telran.library.entities.Record;
import telran.library.model.interfaces.LibraryModel;
import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;
public class LibraryOrm implements LibraryModel {
@PersistenceContext(type = PersistenceContextType.EXTENDED)
EntityManager em;
	@Override
	@Transactional
	public BookCode addBookItem(long isbn, String[] authors,
			String title, int amount, int pickPeriod) {
		if(em.find(Book.class, isbn)!=null)
			return BookCode.ALREADY_EXISTS;
		Set<Author> setAuthors=getAuthors(authors);
		if(setAuthors==null)
			return BookCode.NO_AUTHOR;
		Book book=new Book(isbn, title, amount, pickPeriod);
		book.setAuthors(setAuthors);
		em.persist(book);
		return BookCode.OK;
	}

	private Set<Author> getAuthors(String[] authors) {
		Set<Author> res=new HashSet<>();
		for(String authorName:authors){
			Author author=em.find(Author.class, authorName);
			if(author==null)
				return null;
			res.add(author);
		}
		return res;
	}

	@Override
	@Transactional
	public boolean addAuthor(String name, String country) {
		if(em.find(Author.class, name)!=null)
			return false;
		em.persist(new Author(name, country));
		return true;
	}

	@Override
	@Transactional
	public boolean addBookExemplars(long isbn, int amount) {
		Book book=em.find(Book.class, isbn);
		if(book==null)
			return false;
		book.setAmount(book.getAmount()+amount);
		return true;
	}

	@Override
	@Transactional
	public boolean addReader(int id, LocalDate birthDate, String name, String phone) {
		if(em.find(Reader.class, id)!=null)
			return false;
		em.persist(new Reader(id, name, birthDate, phone));
		return true;
	}

	@Override
	@Transactional
	public PickCode pickBook(long isbn, int readerId, LocalDate pickDate) {
		Book  book=em.find(Book.class,isbn);
		if(book==null)
			return PickCode.NO_BOOK;
		int amount=book.getAmount();
		if(amount<=0)
			return PickCode.NO_EXEMPLAR;
		Reader reader=em.find(Reader.class, readerId);
		if(reader==null)
			return PickCode.NO_READER;
		List<Record> records = recordQuery(isbn, readerId);
		if(records.stream().anyMatch(x->x.getReturnDate()==null))
			return PickCode.ALREADY_PICKED_BY_READER;
		book.setAmount(amount-1);
		em.persist(new Record(pickDate,
				pickDate.plusDays(book.getPickPeriod()), book, reader));
		return PickCode.OK;
	}

	private List<Record> recordQuery(long isbn, int readerId) {
		String jpql=String.format("select r from Record r where "
				+ "r.book.isbn=%d and r.reader.id=%d", isbn,readerId);
		List<Record> records=em.createQuery(jpql,Record.class)
				.getResultList();
		return records;
	}

	@Override
	@Transactional
	public boolean returnBook(long isbn, int readerId,
			LocalDate returnDate) {
		List<Record> records=recordQuery(isbn,readerId);
		if(records.isEmpty())
			return false;
		Record recordForReturn=getRecordForReturn(records);
		if(recordForReturn==null)
			return false;
		recordForReturn.setReturnDate(returnDate);
		Book book=em.find(Book.class, isbn);
		book.setAmount(book.getAmount()+1);
		return true;
	}

	private Record getRecordForReturn(List<Record> records) {
		for(Record record:records){
			if(record.getReturnDate()==null)
				return record;
		}
		return null;
	}

	@Override
	public Iterable<Reader> getReadersDelayed(LocalDate currentDate, int byDays) {
		String jpql="select r from Record r where returnDate is null";
		List<Record> records=em.createQuery(jpql,Record.class)
				.getResultList();
		
		return records.stream().filter(x->x.getReturnDate()==null && 
				ChronoUnit.DAYS.between(x.getShouldReturnDate(),
						currentDate)>=byDays)
				.map(x->x.getReader()).distinct()
				.collect(Collectors.toList());
	}

	@Override
	public Book getBook(long isbn) {
		Book book= em.find(Book.class, isbn);
		
		return book;
	}

	@Override
	public Author getAuthor(String name) {
		return em.find(Author.class, name);
	}

	@Override
	public Reader getReader(int id) {
		return em.find(Reader.class, id);
	}

	@Override
	public Stream<Record> getRecordsStream() {
		
		return em.createQuery("select r from Record r",Record.class)
				.getResultList().stream();
	}

	@Override
    public Collection<Book> getBooksByAuthor(String authorName) {
	    Collection<Book> result = null;
	    Author author = em.find(Author.class, authorName);
        
	    if (author != null) {
	        em.refresh(author);
	        result = author.getBooks();
	    }
        return result;
    }
}
