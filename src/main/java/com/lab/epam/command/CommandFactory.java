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
    }

    public static Command createCommand(HttpServletRequest request) {
        String command = request.getParameter("command");

        System.out.println("Comand is : " + command);

        if (command.equals("showMap")) {
            return commands.get("showMap");
        }

        if (command.equals("index")) {
            return commands.get("index");
        }
        return null;
    }
}

