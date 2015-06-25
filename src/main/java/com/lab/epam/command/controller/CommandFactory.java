package com.lab.epam.command.controller;

/**
 * Created by Vasyl on 09.06.2015.
 */

import com.lab.epam.command.delete.DeletePlaceCommand;
import com.lab.epam.command.email.*;
import com.lab.epam.command.locale.LocaleCommand;
import com.lab.epam.command.logination.*;
import com.lab.epam.command.page.createtrip.CountDaysCommand;
import com.lab.epam.command.page.createtrip.CreateUserDataCommand;
import com.lab.epam.command.page.index.IndexCommand;
import com.lab.epam.command.page.map.RoutesCommand;
import com.lab.epam.command.page.map.ShowMapCommand;
import com.lab.epam.command.page.photo.ShowAllUserPhoto;
import com.lab.epam.command.page.photo.UpLoadPictureCommand;
import com.lab.epam.command.page.place.*;
import com.lab.epam.command.page.user.*;
import com.lab.epam.command.page.user.admin.EditPlaceCommand;
import com.lab.epam.command.page.user.admin.ShowAllUserCommand;
import com.lab.epam.command.save.SaveEditPlaceCommand;
import com.lab.epam.command.save.SavePlaceCommand;
import com.lab.epam.command.save.SaveProfileCommand;
import com.lab.epam.command.save.SaveWayCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        commands.put("showAllUser", new ShowAllUserCommand());
        commands.put("allUserPhoto", new ShowAllUserPhoto());
        commands.put("edit", new EditProfileCommand());
        commands.put("updateprofile", new SaveProfileCommand());
        commands.put("userWays", new UserWaysCommand());
        commands.put("userPlace", new UserPlaceCommand());
        commands.put("deleteUserPlace", new DeleteUserPlaceCommand());
        commands.put("deleteUserWay", new DeleteUserWaysCommand());
        commands.put("signOut", new SignOutCommand());
        commands.put("planTrip", new CountDaysCommand());
        commands.put("createUserData", new CreateUserDataCommand());
        commands.put("routes", new RoutesCommand());
        commands.put("saveWay", new SaveWayCommand());
        commands.put("editPlace", new EditPlaceCommand());
        commands.put("placeJSON", new PlaceJSONCommand());
        commands.put("savePlace", new SavePlaceCommand());
        commands.put("saveEditPlace", new SaveEditPlaceCommand());
        commands.put("signUpFormCheck", new SignUpFormCheckCommand());
        commands.put("signInFormCheck", new SignInFormCheckCommand());
        commands.put("rectRating", new RectRatingCommand());
        commands.put("deletePlace", new DeletePlaceCommand());
        commands.put("addplace", new AddPlaceUserDataTripCommand());
        commands.put("userAllWay", new UserAllWayCommand());
        commands.put("addNewPlace", new AddNewPlaceCommand());
    }

    public static void createCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String command = request.getParameter("command");

//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//        if(command==null && isMultipart){
//            command = "upLoad";
//        }

        if (request.getContentType() != null && request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1 ){
            HttpSession session = request.getSession();
            command = (String)session.getAttribute("command");
        }

        System.out.println(command);


            if (commands.containsKey(command)) {
                Command commamdExecute = commands.get(command);
                commamdExecute.execute(request, response);
            } else {
                request.getRequestDispatcher("/views/pages/404.jsp").forward(request, response);
            }

    }
}

