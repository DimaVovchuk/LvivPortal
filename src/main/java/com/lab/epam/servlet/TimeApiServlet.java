package com.lab.epam.servlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Admin on 09.06.2015.
 */
public class TimeApiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String time = request.getParameter("time");
        System.out.println(time + " request.getParameter");
        String filePath = "src\\main\\java\\com\\epam\\lab\\lololo.jsp";
       /* try {

          //  FileReader reader = new FileReader(filePath);
        JSONParser jsonParser = new JSONParser();

       // JSONObject jsonObject = (JSONObject) jsonParser.parse();
            JSONArray lang= (JSONArray) jsonObject.get("time");

            for(int i=0; i<lang.size(); i++){
                System.out.println("The " + i + " element of the array: "+lang.get(i));
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }

    public void addArray(int[] args){
        System.out.println(args);
    }*/
    }
}

