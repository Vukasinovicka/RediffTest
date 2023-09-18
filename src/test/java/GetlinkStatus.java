import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetlinkStatus {

     static int invaldlink;

    public static void verifyLink(String linkString) {

        try {
            URL url = new URL(linkString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setConnectTimeout(50000);

            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {

            }else {
                System.out.println(linkString + " : " + urlConnection.getResponseCode() + " : " + urlConnection.HTTP_NOT_FOUND);
                invaldlink++;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getInvalidLink() {
        System.out.println("Total invald links : " + invaldlink);
    }
}
