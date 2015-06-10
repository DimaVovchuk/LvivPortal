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
        commands.put("showMap", new ShowMap());
        commands.put("index", new Index());
        commands.put("locale", new LocaleCommand());
    }

    public static void createCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        Command commamdExecute = commands.get(command);
        commamdExecute.execute(request,response);
    }
}

