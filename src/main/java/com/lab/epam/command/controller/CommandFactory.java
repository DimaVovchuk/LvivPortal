package com.lab.epam.command.controller;

/**
 * Created by Vasyl on 09.06.2015.
 */

import com.lab.epam.command.delete.DeleteImageCommand;
import com.lab.epam.command.delete.DeletePlaceCommand;
import com.lab.epam.command.email.*;
import com.lab.epam.command.locale.LocaleCommand;
import com.lab.epam.command.logination.*;
import com.lab.epam.command.logination.fb.FBAuthorizationCommand;
import com.lab.epam.command.logination.fb.FBResponseCommand;
import com.lab.epam.command.logination.vk.VKResponseCommand;
import com.lab.epam.command.logination.vk.VkAuthorizationCommand;
import com.lab.epam.command.page.about.AboutCommand;
import com.lab.epam.command.page.createtrip.AddDayCommand;
import com.lab.epam.command.page.createtrip.CountDaysCommand;
import com.lab.epam.command.page.createtrip.CreateUserDataCommand;
import com.lab.epam.command.page.createtrip.CreateUserDataFromDBCommand;
import com.lab.epam.command.page.delete.DeleteDayCommand;
import com.lab.epam.command.page.delete.DeletePlaceFromTripCommand;
import com.lab.epam.command.page.delete.DeleteWayCommand;
import com.lab.epam.command.page.index.IndexCommand;
import com.lab.epam.command.page.map.RoutesCommand;
import com.lab.epam.command.page.map.ShowMapCommand;
import com.lab.epam.command.page.photo.ShowAllUserPhoto;
import com.lab.epam.command.page.photo.UpLoadPictureCommand;
import com.lab.epam.command.page.place.*;
import com.lab.epam.command.page.user.*;
import com.lab.epam.command.page.user.admin.AdminStatisticCommand;
import com.lab.epam.command.page.user.admin.EditPlaceCommand;
import com.lab.epam.command.page.user.admin.ShowAdminCabinetCommand;
import com.lab.epam.command.page.user.admin.ShowAllUserCommand;
import com.lab.epam.command.page.user.company.OrderWaySendMail;
import com.lab.epam.command.page.user.company.RectUserRatingCommand;
import com.lab.epam.command.save.*;

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
        commands.put("saveNewPlace", new SaveNewPlaceCommand());
        commands.put("createUserDataFromDB", new CreateUserDataFromDBCommand());
        commands.put("updateWay", new UpdateWayCommand());
        commands.put("deletePlaceFromTrip", new DeletePlaceFromTripCommand());
        commands.put("deleteDay", new DeleteDayCommand());
        commands.put("addDay", new AddDayCommand());
        commands.put("companyInformation", new CompanyInformationCommand());
        commands.put("commercial", new CommercialCommand());
        commands.put("commercialJSON", new CommercialJSONCommand());
        commands.put("addCustomPlace", new AddCustomPlaceCommand());
        commands.put("saveCustomPlace", new SaveCustomPlaceCommand());
        commands.put("authorVK", new VkAuthorizationCommand());
        commands.put("authorRespVK", new VKResponseCommand());
        commands.put("deleteImageByDB", new DeleteImageCommand());
        commands.put("authorFB", new FBAuthorizationCommand());
        commands.put("authorRespFB", new FBResponseCommand());
        commands.put("recomendedPlace", new RecomendedPlaceCommand());
        commands.put("recomendedPlaceload", new RecomendedPlaceLoaderCommand());
        commands.put("showAllCustomPlace", new ShowAllCustomPlaceCommand());
        commands.put("recomendedWay", new RecomendedWayLoadCommand());
        commands.put("recomendedWayJSON", new RecomendedWayCommand());
        commands.put("userPlaceJSONCommand", new  UserPlaceJSONCommand());
        commands.put("rectRatingWay", new  RectRatingWayCommand());
        commands.put("search", new  PlaceSearchCommand());
        commands.put("signUpForm", new  SignUpForm());
        commands.put("about", new AboutCommand());
        commands.put("send", new SendContactUsMailCommand());
        commands.put("recommendPlace", new  RecommendedPlaceCommand());
        commands.put("adminStatistic", new AdminStatisticCommand());
        commands.put("recommendPlace", new  RecommendPlaceCommand());
        commands.put("recommendWay", new  RecommendWayCommand());
        commands.put("adminCabinet", new ShowAdminCabinetCommand());
        commands.put("?onfirmCustomPlace", new ConfirmCustomPlaceCommand());
        commands.put("?onfirmRecommendedPlace", new ConfirmRecommendedPlaceCommand());
        commands.put("deleteWay", new DeleteWayCommand());
        commands.put("adminConfirmCustomPlace", new ConfirmCustomPlaceCommand());
        commands.put("adminConfirmRecommendedPlace", new ConfirmRecommendedPlaceCommand());
        commands.put("editPlacesAdminPage", new EditPlacesAdminPage());
        commands.put("confirmCustomPlaceJSON", new ConfirmCustomPlaceJSON());
        commands.put("confirmRecommendedPlaceJSON", new ConfirmRecommendedPlaceJSON());
        commands.put("orderWaySendMail", new OrderWaySendMail());
        commands.put("imageResponseJSON", new CommercialImageCommentCommand());
        commands.put("addImageResponse", new AddImageResponseCommand());
        commands.put("rectRatingUser", new RectUserRatingCommand());
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

