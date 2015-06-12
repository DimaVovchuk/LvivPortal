package com.lab.epam.command;

/**
 * Created by Vasyl on 09.06.2015.
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> commands = new HashMap<String, Command>();

    static {
        commands.put("showMap", new ShowMapCommand());
        commands.put("index", new IndexCommand());
        commands.put("locale", new LocaleCommand());
        commands.put("signUp", new SignUpCommand());
        commands.put("place", new PlaceCommand());
        commands.put("signIn", new SignInCommand());
    }

    public static void createCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        Command commamdExecute = commands.get(command);
        commamdExecute.execute(request,response);
    }
}

