package com.lab.epam.command.page.photo;

import com.lab.epam.command.controller.Command;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.PlaceImage;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceImageService;
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
 * Created by Vasyl on 13.06.2015.
 */
public class UpLoadPictureCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command UpLoadPictureCommand.");
        try {
            List files = new ArrayList();
            Map params = new HashMap<String, String>();
            init(request, params, files);
            save(request,files, params);

            response.setContentType("text/html; charset=windows-1251");
            response.sendRedirect("/portal?command=allUserPhoto");
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
    }

    /**
     * ��������� ����������� ����� �� ����� �������
     */
    private void save(HttpServletRequest request,List files, Map params) throws IOException {
        try {
            for (Iterator i = files.iterator(); i.hasNext();) {
                FileItem item = (FileItem) i.next();
                String imageName = item.getName();
                HttpSession session = request.getSession();

                Pattern p = Pattern.compile("([^\\s]+(?=\\.(jpg|gif|png))\\.\\2)");
                Matcher m = p.matcher(imageName);
                boolean matches = !m.matches();
                if(!matches) {
                    //������ � ���� ����� ������ �� �����������
                    Integer usedID = (Integer) session.getAttribute("usedID");
                    Integer imageID = (Integer) session.getAttribute("imageID");
                    String typeFoto = (String) session.getAttribute("typePhoto");

                    if(imageID != null && typeFoto.equalsIgnoreCase("placeFoto")) {
                        PlaceImageService placeImageService = new PlaceImageService();
                        PlaceImage placeImage = new PlaceImage(imageID, imageName);
                        placeImageService.create(placeImage);
                        loger.info("File is successfully uploaded in database to place image");
                    }

                    if(usedID !=null && typeFoto.equalsIgnoreCase("userFoto")){
                        UserImageService userImageService = new UserImageService();
                        UserImage userImage = new UserImage(usedID, imageName);
                        userImageService.create(userImage);
                        loger.info("File is successfully uploaded in database to user image");
                    }

                    if(usedID !=null && typeFoto.equalsIgnoreCase("avatarFoto")){
                        UserImageService userImageService = new UserImageService();
                        UserService userService = new UserService();
                        UserImage userImage = new UserImage(usedID, imageName);
                        userImageService.create(userImage);

                        List<UserImage> allImageList = userImageService.getUserImageByUserId(usedID);
                        UserImage lastUploadHpoto = allImageList.get(allImageList.size() - 1);
                        Integer lastImageIndex = lastUploadHpoto.getId();

                        User user = userService.getByPK(usedID);
                        user.setAvatar(lastImageIndex);
                        userService.update(user);
                        loger.info("File is successfully uploaded in database to Avatar image");
                    }
                    //����, � ������� ����� ���������� ������
                    String realPath = request.getRealPath("/upload/photo/" + File.separator + imageName);

                    final File file = new File(realPath);
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(item.get());
                    fos.close();
                } else {
                    session.setAttribute("imageError", 1);
                    loger.warn("Name is pattern error");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            loger.error(e.getMessage());
            throw e;
        } catch (PersistException e) {
            e.printStackTrace();
            loger.error(e.getMessage());
        }
    }

    /**
     * �������������� ��������� Map params ����������� �� ����� � List files �������������� �������
     * (� ����� ������ ���� ����)
     */
    private void init(HttpServletRequest request, Map params, List files) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //������������� ������� ��� ��������� ������
        File folder = new File("c:\\tmp");
        if(folder.exists()){
        } else {
            folder.mkdir();
            loger.info("File tmp is created");
        }
        factory.setRepository(folder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        //��������� ����������� �� ������ ������������ ����� � �����
        long imageSize = 1024000;
        upload.setSizeMax(imageSize);

        List items = upload.parseRequest(request);
        for (Iterator i = items.iterator(); i.hasNext();) {
            FileItem item = (FileItem) i.next();
            if (item.isFormField()) {
                params.put(item.getFieldName(), item.getString());
            }
           //... ���� ���, �� ������������ ������ AttachmentDataSource �
            //�������� ��� � ������ ������������� ������
            else {
                if (item.getSize() <= 0) continue;
                files.add(item);
            }
        }
    }
}