package com.lab.epam.command;

/**
 * Created by Vasyl on 09.06.2015.
 */

import com.lab.epam.command.email.*;

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
        commands.put("sendEmail", new SendChangePassMailCommand());
        commands.put("checkTime", new CheckChangePassTimeCommand());
        commands.put("confirmReset", new ConfirmChangePassCommand());
        commands.put("resetEmail", new ResetEmail());

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
        Command commamdExecute = commands.get(command);
        commamdExecute.execute(request,response);
    }
}

