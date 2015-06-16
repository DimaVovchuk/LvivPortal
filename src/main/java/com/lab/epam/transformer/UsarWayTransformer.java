package com.lab.epam.transformer;

import com.lab.epam.entity.UserWay;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Admin on 16.06.2015.
 */
public class UsarWayTransformer {

    public UserWay fromDBtoObjectID(ResultSet rs){
        UserWay result = null;
        try {
            if(rs.next()){
                Integer wayId = rs.getInt("W.id");
                String wayName = rs.getString("W.name");
                Integer wayDays = rs.getInt("W.way_days");
                Integer wayTime = rs.getInt("W.way_time");
                String address = rs.getString("P.adress");
                String latitude = rs.getString("P.latitude");
                String longitude = rs.getString("P.longitude");
                Integer categoryId = rs.getInt("P.category_id");
                Integer rating = rs.getInt("P.rating");
                Integer placeTime = rs.getInt("P.place_time");
                String reference = rs.getString("PI.reference");
                String description = rs.getString("PD.description");
                String placeName = rs.getString("PD.name");
                result = new UserWay(wayId, wayName, wayDays, wayTime,
                        address, latitude, longitude, categoryId, rating,
                        placeTime, reference, description, placeName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<UserWay> fromDBtoObjectList(ResultSet rs){
        ArrayList<UserWay> result = new ArrayList<>();
        try {
            while(rs.next()){
                Integer wayId = rs.getInt("W.id");
                String wayName = rs.getString("W.name");
                Integer wayDays = rs.getInt("W.way_days");
                Integer wayTime = rs.getInt("W.way_time");
                String address = rs.getString("P.adress");
                String latitude = rs.getString("P.latitude");
                String longitude = rs.getString("P.longitude");
                Integer categoryId = rs.getInt("P.category_id");
                Integer rating = rs.getInt("P.rating");
                Integer placeTime = rs.getInt("P.place_time");
                String reference = rs.getString("PI.reference");
                String description = rs.getString("PD.description");
                String placeName = rs.getString("PD.name");
                result.add(new UserWay(wayId, wayName, wayDays, wayTime, address, latitude, longitude, categoryId, rating, placeTime, reference, description, placeName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
