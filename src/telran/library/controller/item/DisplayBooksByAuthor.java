package telran.library.controller.item;

import java.util.Collection;

import telran.input.InputOutputData;
import telran.library.controller.AbstractLibraryItem;
import telran.library.entities.Book;
import telran.library.model.interfaces.LibraryModel;

public class DisplayBooksByAuthor extends AbstractLibraryItem {
    @Override
    public String prompt() {
        return "Display books by author";
    }

    @Override
    protected void performItem(InputOutputData inOut, LibraryModel library) {
        Collection<Book> books = library.getBooksByAuthor(askAuthorName());
        
        if (books == null) {
            display("No books found");
        } else {
            displayAll(books);
        }
    }
}
