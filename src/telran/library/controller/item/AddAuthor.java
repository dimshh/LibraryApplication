package telran.library.controller.item;

import telran.input.InputOutputData;
import telran.library.controller.AbstractLibraryItem;
import telran.library.model.interfaces.LibraryModel;

public class AddAuthor extends AbstractLibraryItem {
    @Override
    public String prompt() {
        return "Add author";
    }

    @Override
    protected void performItem(InputOutputData inOut, LibraryModel library) {
        String s = library.addAuthor(askAuthorName(),
            inOut.getString("Enter country"))
            ? "Author added" : "Author not added";
        
        display(s);
    }
}
