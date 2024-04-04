package co.edu.eci.parcial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class HttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static int CURRENT_SERVER = 0;
    private static List<String>servers = Arrays.asList("http://ec2-34-236-155-176.compute-1.amazonaws.com:8080", "http://ec2-52-91-100-168.compute-1.amazonaws.com:8080");

    public static String RRInvoker(String op) throws IOException {
        String server = RRalgoritm();
        URL obj = new URL(server+op);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return "Mala petición";
    }

    private static String RRalgoritm(){
        if(CURRENT_SERVER == 0){
            CURRENT_SERVER = 1;
        }else {
            CURRENT_SERVER = 0;
        }
        return servers.get(CURRENT_SERVER);
    }

}