package com.lab.epam.command.save;

import com.lab.epam.command.controller.Command;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Decoder;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vasyl on 25.06.2015.
 */
public class SaveProfileCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private static final String CHECK_NAME = "^[^<>/{}\\s?!;]+$";
    private static final String CHECK_SURNAME = "^[^<>/{}\\s?!;]+$";
    private static final String CHECK_PHONE = "([0-9]{6,15})";
    private static final String CHECK_ABOUT = "^[^<>/{}]+$";
    private static final String CHECK_AGENCY_NAME = "^[^<>/{}]+$";
    private UserImageService userImageService = new UserImageService();
    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command Update Profile.");
        HttpSession session = request.getSession();
        List files = new ArrayList();
        Map<String, String> params = new HashMap<String, String>();
        UserService userService = new UserService();
        String userLogin = (String) session.getAttribute("login");
        User user = null;
        Integer userRole = null;
        Boolean errorFlag = false;

        try {
            init(request, params, files);
            save(request, files);
            response.setContentType("text/html; charset=windows-1251");
        } catch (FileUploadException e) {
            loger.warn(e.getMessage());
        }
        String name = params.get("NewName");
        String surname = params.get("surname");
        String phone = params.get("phone");
        String about = params.get("about");
        String companyName = params.get("companyName");

        //check input data
        if (checkData(name, CHECK_NAME)) {
            session.setAttribute("NameError", 1);
            errorFlag = true;
            loger.warn("Name is pattern error");
        }

        if (checkData(surname, CHECK_SURNAME)) {
            session.setAttribute("SurnameError", 1);
            errorFlag = true;
            loger.warn("Surname is pattern error");
        }

        if (checkData(phone, CHECK_PHONE)) {
            session.setAttribute("PhoneError", 1);
            errorFlag = true;
            loger.warn("Phone is pattern error");
        }
        if (checkData(about, CHECK_ABOUT) && about == "") {
            session.setAttribute("aboutError", 1);
            errorFlag = true;
            loger.warn("about is pattern error");
        }

        if (userLogin != null) {
            user = userService.getUserByLogin(userLogin);
            userRole = user.getRole_id();
            loger.info("Command Update user " + user);
        }
        if (phone == "") {
            session.setAttribute("phoneError", 1);
            errorFlag = true;
            loger.warn("Phone is empty");
        }

        if (userRole == 3 || userRole == 4) {
            if (checkData(companyName, CHECK_AGENCY_NAME)) {
                session.setAttribute("companyNameError", 1);
                errorFlag = true;
                loger.warn("Company name is pattern error");
            }
            if (companyName == "") {
                session.setAttribute("companyNameError", 1);
                errorFlag = true;
                loger.warn("Company name is empty");
            }

        }
        User forChecPhone = userService.getUserByPhone(phone);
        if (user.getId() != forChecPhone.getId()) {
            session.setAttribute("phoneError", 1);
            errorFlag = true;
            loger.warn("Such phone is exist");
        }

        if (errorFlag) {
        } else {

            user.setName(Decoder.decodeStringUtf8(name));
            user.setSurname(Decoder.decodeStringUtf8(surname));
            user.setCompanyName(Decoder.decodeStringUtf8(companyName));
            user.setPhone(phone);
            user.setAbout(Decoder.decodeStringUtf8(about));
            loger.info("CUser after change " + user);

            try {
                userService.update(user);
                Integer avatarID = user.getAvatar();
                if(avatarID!=null) {
                    UserImage avatar = userImageService.getByPK(avatarID);
                    String avatarReference = avatar.getReference();
                    session.setAttribute("avatarReference", avatarReference);
                }
            } catch (PersistException e) {
                loger.info("User is not update");
            }

            response.sendRedirect("/portal?command=edit");
        }
    }

    private void init(HttpServletRequest request, Map params, List files) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File folder = new File("c:\\tmp");
        if (folder.exists()) {
        } else {
            folder.mkdir();
            loger.info("File tmp is created");
        }
        factory.setRepository(folder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        long imageSize = 1024000;
        upload.setSizeMax(imageSize);

        List items = upload.parseRequest(request);
        for (Iterator i = items.iterator(); i.hasNext(); ) {
            FileItem item = (FileItem) i.next();
            if (item.isFormField()) {
                params.put(item.getFieldName(), item.getString());
            } else {
                if (item.getSize() <= 0) continue;
                files.add(item);
            }
        }
    }

    private void save(HttpServletRequest request, List files) throws IOException {
        try {
            for (Iterator i = files.iterator(); i.hasNext(); ) {
                FileItem item = (FileItem) i.next();
                String imageName = item.getName();
                HttpSession session = request.getSession();

                Pattern p = Pattern.compile("([^\\s]+(?=\\.(jpg|JPG|gif|png))\\.\\2)");
                Matcher m = p.matcher(imageName);
                boolean matches = !m.matches();
                if (!matches) {
                    if(imageName != null) {
                        Integer userID = (Integer) session.getAttribute("userID");
                        UserService userService = new UserService();
                        UserImage userImage = new UserImage(userID, imageName);
                        userImageService.create(userImage);

                        List<UserImage> allImageList = userImageService.getUserImageByUserId(userID);
                        if (allImageList.size() >= 1) {
                            UserImage lastUploadHpoto = allImageList.get(allImageList.size() - 1);
                            Integer lastImageIndex = lastUploadHpoto.getId();
                            User user = userService.getByPK(userID);
                            user.setAvatar(lastImageIndex);
                            userService.update(user);
                        }
                        loger.info("File is successfully uploaded in database to Avatar image");
                    }
                    String realPath = request.getRealPath("/upload/photo/" + File.separator + imageName);
                    final File file = new File(realPath);
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(item.get());
                    fos.close();
                } else {
                    session.setAttribute("imageError", 1);
                    loger.warn("Name is pattern error " + imageName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            loger.error(e.getMessage());
            throw e;
        } catch (PersistException e) {
            e.printStackTrace();
            loger.error(e.getMessage());
        }
        loger.info("save method ImageUploader ended");
    }

    private static boolean checkData(String testString, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(testString);
        boolean matches = !m.matches();
        return matches;
    }
}
