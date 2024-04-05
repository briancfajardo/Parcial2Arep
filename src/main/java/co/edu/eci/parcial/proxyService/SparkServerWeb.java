package co.edu.eci.parcial.proxyService;

import static spark.Spark.*;

/**
 * Clase encargada de prestarlos servicios básicos al cliente, además de realizar las peticiones a los math services por medio
 * del proxy
 */
public class SparkServerWeb
{
    public static void main( String[] args )
    {
        staticFiles.location("/public");
        port(getPort());
        get("/linearsearch", ((req, res) -> {
            return HttpConnection.RRInvoker("/linearsearch?list="+req.queryParams("list")+"&value="+req.queryParams("value"));
        }));
        get("/binarysearch", ((req, res) -> {
            return HttpConnection.RRInvoker("/binarysearch?list="+req.queryParams("list")+"&value="+req.queryParams("value"));
        }));
    }
    /**
     * Método que busca el puerto por el cual va a correr el servicio en las variables del sistema
     * @return puerto asignado
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
