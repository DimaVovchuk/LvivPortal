package com.lab.epam.command;

import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.UserService;
import com.lab.epam.smtp.SendEmail;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Vasyl on 11.06.2015.
 */
public class SignUpCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command SignUpCommand.");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setRating(0);
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setMail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setStatus(1);
        user.setRoleID(2);

        UserService userService = new UserService();
        boolean checkEmail = userService.checkEmail(email);
        boolean checkPhone = userService.checkEmail(phone);
        boolean checkLogin = userService.checkEmail(login);

        HttpSession session = request.getSession();

        if(!checkEmail){
            session.setAttribute("emailError", 1);
            loger.warn("Such email is exist");
        } else if(!checkPhone){
            session.setAttribute("phoneError", 1);
            loger.warn("Such phone is exist");
        } else if(!checkLogin){
            session.setAttribute("loginError", 1);
            loger.warn("Such login is exist");
        } else {
            try {
                userService.create(user);
                ResourceBundle bundle = (ResourceBundle) session.getAttribute("bundle");
                if(bundle.getLocale().toString().equalsIgnoreCase("ua")){
                    SendEmail.sender("Lviv Portal","Привіт. Вітаємо на нашому сайті. Будь ласка, підтвердіть ваш email " +
                            "Для підтвердження перейдіть по посиланню -> <a href=''></a>! ",email);
                }else{
                    SendEmail.sender("Lviv Portal","Hello. Welcome in out site. Please confirm your email!",email);
                }
                loger.info("New user was added");
                request.getRequestDispatcher("/views/pages/dashboard.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                loger.info("Adding new user was failed");
                loger.error(e.getMessage());
            }
        }
    }
}
