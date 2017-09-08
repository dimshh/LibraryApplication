package telran.library.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import telran.library.model.interfaces.LibraryModel;

public class LibraryTestAppl {
	public static void main(String[] args) {
		AbstractApplicationContext ctx=
				new FileSystemXmlApplicationContext("beans.xml");
		LibraryModel library=ctx.getBean(LibraryModel.class);
		library.addAuthor("Tolstoy", "Russia");
		String[] authors={"Tolstoy"};
		library.addBookItem(123, authors, "Anna", 20, 30);
		System.out.println(library.getBook(123));
		ctx.close();
	}


}
