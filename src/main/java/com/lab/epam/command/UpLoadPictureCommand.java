package com.lab.epam.command;

import com.lab.epam.helper.ClassName;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Vasyl on 13.06.2015.
 */
public class UpLoadPictureCommand implements Command{
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private Random random = new Random();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command UpLoadPictureCommand.");
        try {
            //������ ����������� ������
            List files = new ArrayList();
            //������ ������� ���������� �� HTML-�����
            Map params = new HashMap();
            //�������������� ��������� files � params
            init(request, params, files);
            //��������� ���� �� �������
            save(files, params);
            loger.info("File is successfully uploaded");

            response.setContentType("text/html; charset=windows-1251");
            final PrintWriter writer = response.getWriter();
            writer.println("���� ������� ��������<br>");
            writer.println("<a href='" + request.getContextPath() + "/uploadform.htm'>U >></a>");
            writer.close();

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
    private void save(List files, Map params) throws IOException {
        try {
            for (Iterator i = files.iterator(); i.hasNext();) {
                FileItem item = (FileItem) i.next();
                String imageName = item.getName();

                //������ � ���� ����� ������ �� �����������
//                int usedID = (int) params.get("usedID");
//                UserImageService userImageService = new UserImageService();
//                UserImage userImage = new UserImage(usedID,imageName);
//                userImageService.create(userImage);
//                loger.info("File is successfully uploaded in database");

                //����, � ������� ����� ���������� ������
                final File file = new File("D:\\Eclipse_Luna\\gitProject\\LvivPortal\\LvivPortal\\src\\main\\webapp\\upload\\photo" + File.separator + imageName);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(item.get());
                fos.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            loger.error(e.getMessage());
            throw e;
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
        upload.setSizeMax(1024000);
        List items = upload.parseRequest(request);
        for (Iterator i = items.iterator(); i.hasNext();) {
            FileItem item = (FileItem) i.next();
            //���������, �������� �� �������� ������� ����� �� HTML-�����,
            //���� ��, �� �������� � Map ���� name=value...
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