package telran.library.controller.item;

import telran.input.InputOutputData;
import telran.library.api.BookCode;
import telran.library.controller.AbstractLibraryItem;
import telran.library.model.interfaces.LibraryModel;

public class AddBook extends AbstractLibraryItem {
    @Override
    public String prompt() {
        return "Add book";
    }

    @Override
    protected void performItem(InputOutputData inOut, LibraryModel library) {
        long isbn = askIsbn();
        
        String[] authors =
            inOut.getString("Enter authors separated by #").split("#");
        
        String title = inOut.getString("Enter title");
        int amount = inOut.getInteger("Enter amount");
        int pickPeriod = inOut.getInteger("Enter pick period");
        
        display(library.addBookItem(isbn, authors, title, amount, pickPeriod));
    }
}
