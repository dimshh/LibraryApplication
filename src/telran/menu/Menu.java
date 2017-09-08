package telran.menu;

import telran.input.InputOutputData;

public class Menu {
    private InputOutputData inputOutput;
    private Item[] items;
    
    public Menu(InputOutputData inputOutput, Item[] items) {
        this.inputOutput = inputOutput;
        this.items = items;
    }
    
    public void run() {
        int itemsNum = items.length;
        int selectedNumber = 0;
        
        while (selectedNumber <= itemsNum) {
            // print all menu items
            for (int i = 0; i < itemsNum; i++) {
                inputOutput.putObject(i + 1 + ". " + items[i].prompt());
            }
            inputOutput.putObject(itemsNum + 1 + ". Exit");
            
            // get user's selection
            selectedNumber =
                inputOutput.getInteger("Enter item number", 1, itemsNum + 1);
            
            if (selectedNumber <= itemsNum) {
                items[selectedNumber - 1].performItem();
            }
        }
    }
}
