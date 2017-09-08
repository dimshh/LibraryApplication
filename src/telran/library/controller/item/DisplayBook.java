package telran.library.controller.item;

import telran.input.InputOutputData;
import telran.library.controller.AbstractLibraryItem;
import telran.library.model.interfaces.LibraryModel;

public class DisplayBook extends AbstractLibraryItem {
    @Override
    public String prompt() {
        return "Display book";
    }

    @Override
    protected void performItem(InputOutputData inOut, LibraryModel library) {
        Object v = library.getBook(askIsbn());
        String s = (v == null) ? "Book not found" : v.toString();
        
        display(s);
    }
}
