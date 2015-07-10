package com.lab.epam.workWithMap;

import com.google.api.client.util.Joiner;
import com.google.api.client.util.Maps;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dima on 23-Jun-15.
 */
public class Distance {
//    public static int iter = 0;

    private String encodeParams(final Map<String, String> params) {
        final String paramsUrl = Joiner.on('&').join(
                Iterables.transform(params.entrySet(), new Function<Map.Entry<String, String>, String>() {

                    @Override
                    public String apply(final Map.Entry<String, String> input) {
                        try {
                            final StringBuffer buffer = new StringBuffer();
                            buffer.append(input.getKey());
                            buffer.append('=');
                            buffer.append(URLEncoder.encode(input.getValue(), "utf-8"));
                            return buffer.toString();
                        } catch (final UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
        return paramsUrl;
    }

    public Map<String,Double> getDistanceAndTime(String origin, String destination) throws IOException, JSONException {
//        iter++;
//        if(iter == 4){
//            try {
//                Thread.currentThread().sleep(1005);
//                iter=0;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        String baseUrl = "http://maps.googleapis.com/maps/api/directions/json";
        Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");
        params.put("language", "ua");
        params.put("mode", "walking");
        params.put("origin", origin);
        params.put("destination", destination);
        String url = baseUrl + '?' + encodeParams(params);
        JSONObject response = JsonReader.read(url);
        int i = 0;
        while(response.has("error_message")){
            try {
                Thread.currentThread().sleep(1000 + i);
                response = JsonReader.read(url);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i = 1000;

        }
        JSONObject location = response.getJSONArray("routes").getJSONObject(0);
        location = location.getJSONArray("legs").getJSONObject(0);
        String distance = location.getJSONObject("distance").getString("value");
        String duration = location.getJSONObject("duration").getString("value");
        Map<String,Double> map = new HashMap<>();
        map.put("time",Double.parseDouble(duration));
        map.put("distance",Double.parseDouble(distance));
        return map;
    }

}

