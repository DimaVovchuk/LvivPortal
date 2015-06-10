package com.lab.epam.command;

/**
 * Created by Vasyl on 09.06.2015.
 */
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> commands = new HashMap<String, Command>();

    static {
        commands.put("showMap", new ShowMap());
        commands.put("index", new Index());
        commands.put("locale", new LocaleCommand());
    }

    public static Command createCommand(HttpServletRequest request) {
        String command = request.getParameter("command");
        return commands.get(command);
    }

}

