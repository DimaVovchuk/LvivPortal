package com.lab.epam.command.save;

import com.lab.epam.command.controller.Command;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.UserImageService;
import com.lab.epam.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Oleguk on 18.06.2015.
 */
public class SaveProfileCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command Update Profile.");
        HttpSession session = request.getSession();
        List files = new ArrayList();
        Map<String, String> params = new HashMap<String, String>();
        try {
            init(request,params,files);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        for(Map.Entry<String, String> entry: params.entrySet()){
            System.out.println(entry.getValue() + "-- "+ entry.getKey());
        }

        UserService userService = new UserService();
        UserImageService userImageService = new UserImageService();
        String userLogin = (String) session.getAttribute("login");

        User user = null;

        String name = (String) session.getAttribute("NewName");
        loger.info("NewName " + name);
        String name1 = (String) request.getAttribute("NewName");
        loger.info("NewName1 " + name1);
        String surname = (String) session.getAttribute("surname");
        String login = (String) session.getAttribute("login");
        String mail = (String) session.getAttribute("mail");
        String phone = (String) session.getAttribute("phone");
        String about = (String) session.getAttribute("about");
        String typePhoto = (String) session.getAttribute("typePhoto");


        String errorMsg = null;

        if (userLogin != null) {
            user = userService.getUserByLogin(userLogin);
            loger.info("Command Update user " + user);
        }

        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setMail(mail);
        user.setPhone(phone);
        user.setAbout(about);
        loger.info("CUser after change " + user);
        try {
            userService.update(user);
        } catch (PersistException e) {
            loger.info("User is not update");
        }
        response.sendRedirect("/portal?command=edit");
    }
    private static void init(HttpServletRequest request, Map params, List files) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        List items = upload.parseRequest(request);
        for (Iterator i = items.iterator(); i.hasNext();) {
            FileItem item = (FileItem) i.next();
            if (item.isFormField()) {
                params.put(item.getFieldName(), item.getString());
            }
            else {
                if (item.getSize() <= 0) continue;
                files.add(item);
            }
        }
    }
}
