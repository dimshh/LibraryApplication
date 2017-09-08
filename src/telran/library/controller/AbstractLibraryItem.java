package telran.library.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import telran.input.InputOutputData;
import telran.library.model.interfaces.LibraryModel;
import telran.menu.Item;

public abstract class AbstractLibraryItem implements Item {
    @Autowired
    private InputOutputData inputOutput;
    
    @Autowired
    private LibraryModel libraryModel;
    
    @Override
    public void performItem() {
        performItem(inputOutput, libraryModel);
    }
    
    protected abstract void performItem(
        InputOutputData inOut, LibraryModel library);
    
    protected void display(Object value) {
        inputOutput.putObject(value);
    }
    
    protected void displayAll(Iterable<?> values) {
        values.forEach(this::display);
    }
    
    protected String askAuthorName() {
        String result = inputOutput.getString("Enter author name");
        
        return result;
    }
    
    protected int askIsbn() {
        int result = inputOutput.getInteger("Enter ISBN");
        
        return result;
    }
    
    protected LocalDate askDate(int yearMin, int yearMax) {
        LocalDate result = inputOutput.getValue("Enter date yyyy-mm-dd",
            LocalDate::parse,
            ld -> (ld.getYear() >= yearMin) && (ld.getYear() <= yearMax));
        
        return result;
    }
}
