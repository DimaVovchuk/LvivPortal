package com.lab.epam.transformer;

import com.lab.epam.entity.PlaceMarkerWithPhoto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Admin on 16.06.2015.
 */
public class PlaceMarkerWithPhotoTransformer {

    public PlaceMarkerWithPhoto fromDBtoObjectID(ResultSet rs){
        PlaceMarkerWithPhoto result = null;
        try {
            if(rs.next()){
                String name = rs.getString("name");
                String latitude = rs.getString("latitude");
                String longitude = rs.getString("longitude");
                String imageReference = rs.getString("imageReference");
                result = new PlaceMarkerWithPhoto(name, latitude, longitude, imageReference);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<PlaceMarkerWithPhoto> fromDBtoObjectList(ResultSet rs){
        ArrayList<PlaceMarkerWithPhoto> result = new ArrayList<>();
        try {
            while(rs.next()){
                String name = rs.getString("name");
                String latitude = rs.getString("latitude");
                String longitude = rs.getString("longitude");
                String imageReference = rs.getString("imageReference");
                result.add(new PlaceMarkerWithPhoto(name, latitude, longitude, imageReference));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Object[][] fromObjectArrayToArray(PlaceMarkerWithPhoto... sub){
        Object[][] result = new Object[sub.length][4];
        for (int i = 0; i < sub.length; i++){
            result[i][0] = sub[i].getName();
            result[i][1] = sub[i].getLatitude();
            result[i][2] = sub[i].getLongitude();
            result[i][3] = sub[i].getImageReference();
        }
        return result;
    }

    public Object[] fromObjectToArrayID(PlaceMarkerWithPhoto sub){
        Object[] result = new Object[4];
        result[0] = sub.getName();
        result[1] = sub.getLatitude();
        result[2] = sub.getLongitude();
        result[3] = sub.getImageReference();
        return result;
    }

}
