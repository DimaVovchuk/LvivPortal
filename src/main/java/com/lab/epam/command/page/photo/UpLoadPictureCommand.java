package com.lab.epam.command.page.photo;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.UserImage;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.UserImageService;
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
 * Created by Vasyl on 13.06.2015.
 */
public class UpLoadPictureCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("UpLoadPictureCommand");
        HttpSession session = request.getSession();
        Integer userID = (Integer)session.getAttribute("userID");
        try {
            List files = new ArrayList();
            Map params = new HashMap<String, String>();
            init(request, params, files);
            save(request,files, params);
        }
        catch (FileUploadException fue) {
            fue.printStackTrace();
            loger.error(fue.getMessage());
            throw new ServletException(fue);
        }
        catch (Exception e) {
            e.printStackTrace();
            loger.error(e.getMessage());
            throw new ServletException(e);
        }
        UserImageService userImageService = new UserImageService();
        List<UserImage> userImageList = userImageService.getUserImageByUserId(userID);
        request.setAttribute("AllUserPhoto",userImageList);
        loger.info("uploadImage method ImageUploader ended");
        response.sendRedirect("/portal?command=allUserPhoto");
    }
    private static void save(HttpServletRequest request,List files, Map params) throws IOException {
        loger.info("save method ImageUploader started");
        try {
            for (Iterator i = files.iterator(); i.hasNext();) {
                loger.info("start loop for");
                FileItem item = (FileItem) i.next();
                String imageName = item.getName();
                HttpSession session = request.getSession();

                loger.info("imageName before save is "  + imageName);
                Pattern p = Pattern.compile("([^\\s]+(?=\\.(jpg|JPG|gif|png))\\.\\2)");
                Matcher m = p.matcher(imageName);
                boolean matches = !m.matches();
                if(!matches) {
                    //?????? ? ???? ????? ?????? ?? ???????????
                    Integer usedID = (Integer) session.getAttribute("userID");
                    if(usedID !=null){
                        loger.info("Start uplad to user gallery");
                        UserImageService userImageService = new UserImageService();
                        UserImage userImage = new UserImage(usedID, imageName);
                        userImageService.create(userImage);
                        loger.info("File is successfully uploaded in database to user image");
                    }

                    //????, ? ??????? ????? ?????????? ??????
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
        }
        catch (IOException e) {
            e.printStackTrace();
            loger.error(e.getMessage());
            throw e;
        }
        loger.info("save method ImageUploader ended");
    }

    private static void init(HttpServletRequest request, Map params, List files) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        loger.info("init method ImageUploader started");
        File folder = new File("c:\\tmp");
        if(folder.exists()){
        } else {
            folder.mkdir();
            loger.info("File tmp is created");
        }
        factory.setRepository(folder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        long imageSize = 2097152;
        upload.setSizeMax(imageSize);

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
        loger.info("init method ImageUploader ended");
    }
}
