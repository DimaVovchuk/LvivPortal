package com.lab.epam.command.save;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Decoder;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.entity.PlaceImage;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceDescriptionService;
import com.lab.epam.service.PlaceImageService;
import com.lab.epam.service.PlaceService;
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
 * Created by Vasyl on 28.06.2015.
 */
public class SaveCustomPlaceCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private static final String CHECK_DATA = "^[^<>/{}]+$";
    private static final String CHECK_PHONE = "([0-9]{6,15})";
    private static final String CHECK_PLACE_TIME = "([0-9])";
    private static Integer lastAddedPlace = null;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command SaveCustomPlaceCommand.");
        HttpSession session = request.getSession();
        List files = new ArrayList();
        Map<String, String> params = new HashMap<String, String>();
        PlaceService placeService = new PlaceService();
        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        Boolean errorFlag = false;
        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage().toUpperCase();

        try {
            init(request, params, files);
            response.setContentType("text/html; charset=windows-1251");
        } catch (FileUploadException e) {
            loger.warn(e.getMessage());
        }

        String customPlaceName = params.get("customPlaceName");
        String customPlaceDesc = params.get("customPlaceDesc");
        String customCategoryID = params.get("newCategory");
        String customPlacePrice = params.get("customPlacePrice");
        String customPlacePhone = params.get("customPlacePhone");
        String customPlaceAdrress = params.get("customPlaceAdrressHid");
        String latitude = params.get("latitudeHid");
        String longitude = params.get("longitudeHid");
        String customPlaceTime = params.get("place_time");
        loger.info("All data waas succesful getting");

        System.out.println("customPlaceName " + customPlaceName);
        System.out.println("customPlaceDesc " + customPlaceDesc);
        System.out.println("customCategoryID " + customCategoryID);
        System.out.println("customPlacePrice " + customPlacePrice);
        System.out.println("customPlacePhone " + customPlacePhone);
        System.out.println("customPlaceAdrress " + customPlaceAdrress);
        System.out.println("latitude " + latitude);
        System.out.println("longitude " + longitude);
        System.out.println("customPlaceTime " + customPlaceTime);
        System.out.println("language " + language);

        //check input data
        if (checkData(customPlaceName, CHECK_DATA) && customPlaceName == "") {
            session.setAttribute("nameUAError", 1);
            errorFlag = true;
            loger.warn("NameUA is pattern error");
        }

        if (checkData(customPlaceDesc, CHECK_DATA) && customPlaceDesc == "") {
            session.setAttribute("nameENError", 1);
            errorFlag = true;
            loger.warn("NameEN is pattern error");
        }

        if (checkData(customPlaceAdrress, CHECK_DATA) && customPlaceAdrress == "") {
            session.setAttribute("descriptionUArror", 1);
            errorFlag = true;
            loger.warn("DescriptionUA is pattern error");
        }

        if (checkData(customPlacePrice, CHECK_DATA) && customPlacePrice == "") {
            session.setAttribute("PlacePriceUAError", 1);
            errorFlag = true;
            loger.warn("PlacePriceUA is pattern error");
        }

        if (checkData(customPlacePhone, CHECK_PHONE) && customPlacePhone == "") {
            session.setAttribute("PlacePhoneError", 1);
            errorFlag = true;
            loger.warn("PlacePhone is pattern error");
        }

        if (checkData(customPlaceTime, CHECK_PLACE_TIME)) {
            session.setAttribute("PlaceTimeError", 1);
            errorFlag = true;
            loger.warn("PlaceTime is pattern error");
        }

        if (errorFlag) {
        } else {
            Place place = new Place();
            place.setLatitude(latitude);
            place.setLongitude(longitude);
            place.setCategory_id(Integer.valueOf(customCategoryID));
            place.setRating(0);
            place.setVisible(false);
            place.setPlace_time(Integer.valueOf(customPlaceTime));
            place.setDeleted(false);
            place.setRecomended(false);
            place.setCustom(true);
            loger.info("Object place is created " + place);

            lastAddedPlace = placeService.createAndReturnIndex(place);
            loger.info("lastAddedPlace is  " + lastAddedPlace);

            if(language.equalsIgnoreCase("UA")) {
                PlaceDescription placeDescription = new PlaceDescription(new PlaceDescription.Builder(lastAddedPlace, language,
                        Decoder.decodeStringUtf8(customPlaceName), Decoder.decodeStringUtf8(customPlaceDesc),Decoder.decodeStringUtf8(customPlaceAdrress)));
                placeDescription.setPhone(customPlacePhone);
                placeDescription.setPrice(Decoder.decodeStringUtf8(customPlacePrice));
                loger.info("placeDescription is  " + placeDescription);
                placeDescriptionService.create(placeDescription);

                PlaceDescription placeDescriptionEN = new PlaceDescription(new PlaceDescription.Builder(lastAddedPlace, "EN","", "",""));
                placeDescriptionEN.setPhone(customPlacePhone);
                placeDescriptionEN.setPrice("");
                loger.info("placeDescriptionEN is  " + placeDescriptionEN);
                placeDescriptionService.create(placeDescriptionEN);
             }
            if(language.equalsIgnoreCase("EN")) {
                PlaceDescription placeDescription = new PlaceDescription(new PlaceDescription.Builder(lastAddedPlace, language,
                        Decoder.decodeStringUtf8(customPlaceName), Decoder.decodeStringUtf8(customPlaceDesc),Decoder.decodeStringUtf8(customPlaceAdrress)));
                placeDescription.setPhone(customPlacePhone);
                placeDescription.setPrice(Decoder.decodeStringUtf8(customPlacePrice));
                loger.info("placeDescription is  " + placeDescription);
                placeDescriptionService.create(placeDescription);

                PlaceDescription placeDescriptionUA = new PlaceDescription(new PlaceDescription.Builder(lastAddedPlace, "UA","", "",""));
                placeDescriptionUA.setPhone(customPlacePhone);
                placeDescriptionUA.setPrice("");
                loger.info("placeDescriptionEN is  " + placeDescriptionUA);
                placeDescriptionService.create(placeDescriptionUA);
            }

            save(request, files, params);
            loger.info("Method SaveCustomPlaceCommand.execute() ended.");
            response.sendRedirect("/portal?command=placeInformation&place_id=" + lastAddedPlace);
        }
    }

    private static void init(HttpServletRequest request, Map params, List files) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File folder = new File("c:\\tmp");
        if (folder.exists()) {
        } else {
            folder.mkdir();
            loger.info("File tmp is created");
        }
        factory.setRepository(folder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        long imageSize = 2097152;
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

    private static void save(HttpServletRequest request, List files, Map params) throws IOException {
        try {
            for (Iterator i = files.iterator(); i.hasNext(); ) {
                FileItem item = (FileItem) i.next();
                String imageName = item.getName();
                HttpSession session = request.getSession();

                Pattern p = Pattern.compile("([^\\s]+(?=\\.(jpg|JPG|gif|png))\\.\\2)");
                Matcher m = p.matcher(imageName);
                boolean matches = !m.matches();

                if (!matches && lastAddedPlace != null) {
                    loger.info("Start uplad to place gallery");
                    PlaceImageService placeImageService = new PlaceImageService();
                    PlaceImage placeImage = new PlaceImage(lastAddedPlace, imageName);
                    placeImageService.create(placeImage);
                    loger.info("File is successfully uploaded in database to place image");

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
        }
        loger.info("save method ImageUploader ended");
    }

    private static boolean checkData(String testString, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(testString);
        boolean matches = !m.matches();
        loger.info("Method checkData return " + matches);
        return matches;
    }
}
