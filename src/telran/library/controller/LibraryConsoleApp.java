package telran.library.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import telran.menu.Menu;

// 1. Add author
// 2. Add book
// 3. Display book
// 4. Add exemplars
// 5. Add reader
// 6. Pick book
// 7. Return book
// 8. Display delayers
// 9. Exit

public class LibraryConsoleApp {
    public static void main(String[] args) {
        try (AbstractApplicationContext ctx =
            new FileSystemXmlApplicationContext("beans.xml")) {
            
            ctx.getBean(Menu.class).run();
        }
    }
}
