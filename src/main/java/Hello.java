import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

/**
 * Created by Dima on 05-Jun-15.
 */
public class Hello {
    public static void main(String[] args) throws Exception {
        // Replace the API key below with a valid API key.
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCL9BI_9U8ba_Zf_ldHd9KrYFtBtK7cTzI");
        GeocodingResult[] results = GeocodingApi.geocode(context,"Lviv").await();
        System.out.println(results[0].formattedAddress);
    }
}
