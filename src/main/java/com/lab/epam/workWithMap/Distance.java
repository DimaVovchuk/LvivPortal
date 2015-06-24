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
import java.util.Map;

/**
 * Created by Dima on 23-Jun-15.
 */
public class Distance {

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

    public double getDistance(String origin, String destination) throws IOException, JSONException {
        final String baseUrl = "http://maps.googleapis.com/maps/api/directions/json";

        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");
        params.put("language", "ua");
        params.put("mode", "walking");
        params.put("origin", origin);

        params.put("destination", destination);
        final String url = baseUrl + '?' + encodeParams(params);
        final JSONObject response = JsonReader.read(url);
        JSONObject location = response.getJSONArray("routes").getJSONObject(0);
        location = location.getJSONArray("legs").getJSONObject(0);
        final String distance = location.getJSONObject("distance").getString("value");
        return Double.parseDouble(distance);
    }

    public Integer getTime(String origin, String destination) throws IOException, JSONException {
        final String baseUrl = "http://maps.googleapis.com/maps/api/directions/json";// путь к Geocoding API по
        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");
        params.put("language", "ua");
        params.put("mode", "walking");
        params.put("origin", origin);
        params.put("destination", destination);
        final String url = baseUrl + '?' + encodeParams(params);
        final JSONObject response = JsonReader.read(url);
        JSONObject location = response.getJSONArray("routes").getJSONObject(0);
        location = location.getJSONArray("legs").getJSONObject(0);
        final String duration = location.getJSONObject("duration").getString("value");
        return Integer.parseInt(duration);
    }

    public static void main(String[] args) {
        Distance distance = new Distance();
        try {
            System.out.println(distance.getDistance("49.843698 24.025574", "49.840862 24.02893") / 1000 + " km");
            System.out.println(distance.getTime("49.843698 24.025574", "49.840862 24.02893").doubleValue() / 60 + " min");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

