package co.edu.eci.parcial;

import static spark.Spark.*;
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

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
