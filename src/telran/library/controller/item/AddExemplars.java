package telran.library.controller.item;

import telran.input.InputOutputData;
import telran.library.controller.AbstractLibraryItem;
import telran.library.model.interfaces.LibraryModel;

public class AddExemplars extends AbstractLibraryItem {
    @Override
    public String prompt() {
        return "Add exemplars";
    }

    @Override
    protected void performItem(InputOutputData inOut, LibraryModel library) {
        String s = library.addBookExemplars(askIsbn(),
            inOut.getInteger("Enter amount"))
            ? "Exemplars added" : "Book not found";
        
        display(s);
    }
}
