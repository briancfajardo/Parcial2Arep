package co.edu.eci.parcial.proxyService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Clase encargada de realizar las peticiones a los math service usando un balanceador de carga Round Robin
 */
public class HttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static int CURRENT_SERVER = 0;
    private static List<String>servers = Arrays.asList("http://ec2-54-209-44-216.compute-1.amazonaws.com:8080",
            "http://ec2-34-227-10-212.compute-1.amazonaws.com:8080");

    /**
     * Método que realiza la conexión con el servidor correspondiente, recibe su respuesta y la retorna.
     * @param op Método de búsqueda que se desea emplear para buscar un valor en un arreglo junto con los valores
     * @return Respuesta del servidor
     * @throws IOException Se lanza cuando hay un problema en la conexión
     */
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

    /**
     * Método que realiza la asignación de servidores según corresponda usando el algoritmo de balanceo de carga round robin
     * @return dirección del servidor asignado
     */
    private static String RRalgoritm(){
        String server;
        if(CURRENT_SERVER == 0){
            CURRENT_SERVER = 1;
            server = servers.get(CURRENT_SERVER);
        }else {
            CURRENT_SERVER = 0;
            server = servers.get(CURRENT_SERVER);
        }
        return server;
    }

}