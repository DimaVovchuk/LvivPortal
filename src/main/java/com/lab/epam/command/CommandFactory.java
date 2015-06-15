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
        commands.put("userCabinet", new UserCabinetCommand());
        commands.put("confirm", new ConfirmEmailCommand());
        commands.put("placeInformation", new PlaceInfortmationCommand());
        commands.put("upLoad", new UpLoadPictureCommand());

    }

    public static void createCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String command = request.getParameter("command");

//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//        if(command==null && isMultipart){
//            command = "upLoad";
//        }

        if (request.getContentType() != null && request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1 ){
            command = "upLoad";
        }

        System.out.println(command);

        if(commands.containsKey(command)) {
            Command commamdExecute = commands.get(command);
            commamdExecute.execute(request, response);
        } else {
            request.getRequestDispatcher("/views/pages/404.jsp").forward(request, response);
        }
    }
}

