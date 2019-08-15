package com.liba.controller.command;

import com.liba.model.sevice.AuthorService;
import com.liba.model.sevice.BookService;
import com.liba.model.sevice.TakenBookService;
import com.liba.model.sevice.UserService;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private static CommandManager commandManager;
    private final Map<String, Command> commandMap = new HashMap<>();


    private CommandManager() {

        commandMap.put("/login", new LoginCommand(new UserService()));
        commandMap.put("/registration", new RegistrationCommand(new UserService()));
        commandMap.put("/logout", new LogoutCommand());
        commandMap.put("/index", new IndexCommand(new BookService()));
        commandMap.put("/authors", new AuthorCommand(new AuthorService()));
        commandMap.put("/books", new BookCommand(new BookService()));
        commandMap.put("/create_book", new AddNewBookCommand(new AuthorService(), new BookService()));
        commandMap.put("/create_author", new AddNewAuthorCommand(new AuthorService()));
        commandMap.put("/delete_author", new DeleteAuthorCommand(new AuthorService()));
        commandMap.put("/delete_book", new DeleteBookCommand(new BookService()));
        commandMap.put("/taken_books", new TakenBooksCommand(new TakenBookService()));
        commandMap.put("/take_book", new TakeBookCommand(new TakenBookService()));
        commandMap.put("/profile", new ProfileCommand(new TakenBookService()));

    }

    public static CommandManager getInstance() {
        if (commandManager == null) {
            synchronized (CommandManager.class) {
                if (commandManager == null) {
                    commandManager = new CommandManager();
                }
            }
        }
        return commandManager;
    }


    public Command getCommand(String commandName) {
        for(String key : commandMap.keySet()){
            if(commandName.contains(key)) return commandMap.get(key);
        }

        return commandMap.get("/index");
    }
}
