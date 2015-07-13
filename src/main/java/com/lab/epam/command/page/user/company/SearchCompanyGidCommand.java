package com.lab.epam.command.page.user.company;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Decoder;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.UserImageService;
import com.lab.epam.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Admin on 12.07.2015.
 */
public class SearchCompanyGidCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = new UserService();
        UserImageService userImageService = new UserImageService();

        HttpSession session = request.getSession();

        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage();
        String searchResult = "";

        String searchString = request.getParameter("search");
        searchString = Decoder.decodeStringUtf8(searchString);
        List<User> users = new ArrayList<>();
        Map<Integer, String> results = new HashMap<>();
        Integer countPlace = 0;

        if (searchString != null && !searchString.equals("")) {
            searchString = searchString.toLowerCase();
            String[] searchParth = searchString.split(" ");
            users = userService.getUserByRole(3);
            if (users != null){
                users.addAll(userService.getUserByRole(4));
            } else {
                users = userService.getUserByRole(4);
            }

            if (users != null && !users.isEmpty()) {
                for (User user : users) {
                    String name = user.getCompanyName().replaceAll("\"", "");
                    name = name.toLowerCase();
                    String[] nameParth = name.split(" ");
                    Integer count = 0;
                    for (String parth: nameParth) {
                        for (String res: searchParth) {
                            if (parth.contains(res)) {
                                count++;
                            }
                        }
                    }
                    if (count >= searchParth.length){
                        if (countPlace < 10) {
                            UserImage uIm  = null;
                            if (user.getAvatar() != null){
                                uIm = userImageService.getByPK(user.getAvatar());
                            }
                            if (uIm == null || !isInFolder(uIm.getReference(), request)){
                                uIm = new UserImage(user.getId(), "user.png");
                            }
                            searchResult += user.getCompanyName() + "*" + uIm.getReference() + "\n";
                            countPlace++;
                        }
                    }

                }
                if (searchResult.isEmpty()) {
                    Integer fail = 0;
                    for (int i = 1; i <= 3; i++) {
                        results.put(i, new String(""));
                    }
                    for (User user : users) {
                        String name = user.getCompanyName().replaceAll("\"", "");
                        name = name.toLowerCase();
                        fail = distanse(name, searchString);
                        if( fail <= 3){
                            if (results.get(fail) != null && !results.get(fail).equals("")){
                                String str = results.get(fail);
                                String[] strArray = str.split("\n");
                                if (strArray.length < 10){
                                    UserImage uIm  = null;
                                    if (user.getAvatar() != null){
                                        uIm = userImageService.getByPK(user.getAvatar());
                                    }
                                    if (uIm == null || !isInFolder(uIm.getReference(), request)){
                                        uIm = new UserImage(user.getId(), "user.png");
                                    }
                                    str += user.getCompanyName() + "*" + uIm.getReference() + "\n";
                                }
                                results.put(fail, str);
                            }else{
                                UserImage uIm  = null;
                                if (user.getAvatar() != null){
                                    uIm = userImageService.getByPK(user.getAvatar());
                                }
                                if (uIm == null || !isInFolder(uIm.getReference(), request)){
                                    uIm = new UserImage(user.getId(), "user.png");
                                }
                                String str = user.getCompanyName() + "*" + uIm.getReference() + "\n";
                                results.put(fail, str);
                            }
                        }
                    }
                    if (!results.isEmpty()){
                        for (int i = 1; i <= 15; i++){
                            String str = results.get(i);
                            if (str != null && !str.equals("")){
                                searchResult = str;
                                break;
                            }
                        }
                    }

                }

            }

        }
        loger.info("Command SearchCompanyGidCommand");

        response.setContentType("application/txt");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(searchResult);
    }

    private Integer distanse(String s1, String s2){
        Integer m = s1.length();
        Integer n = s2.length();
        char[] charS2 = s2.toCharArray();
        Integer count = 0;
        if (charS2.length >= 2){
            for (int i = 0; i < charS2.length - 1; i++){
                String s = new StringBuilder().append(charS2[i]).append(charS2[i + 1]).toString();;
                if (s1.contains(s)){
                    count++;
                }
            }
        }
        Integer distance = n - count;
        return distance;
    }

    private Boolean isInFolder(String fileName, HttpServletRequest request) {
        ClassLoader classLoader = getClass().getClassLoader();
        String realPath = request.getRealPath("/upload/photo/");
        File f = new File(realPath);
        String[] list = f.list();
        for (String file : list) {
            if (fileName.equals(file)) {
                return true;
            }
        }
        return false;
    }
}
