package co.edu.eci.parcial.mathService;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

/**
 * Clase encargada de proveer las operaciones requeridas en los endpoints /linearsearch y /binarysearch
 */
public class MathServices {
    /**
     * Método principal de la clase MathServices
     * @param args argumentos necesarios para su correcto funcionamiento
     */
    public static void main(String[] args) {
        port(getPort());
        get("/linearsearch", ((req, res) -> {
            List<Integer> list = stringToList(req.queryParams("list"));
            int num = Integer.parseInt(req.queryParams("value"));
            int pos = linearSearch(list, num);

            return builtResponse(list, num, pos, "linearSearch");
        }));
        get("/binarysearch", ((req, res) -> {
            List<Integer> list = stringToList(req.queryParams("list"));
            int num = Integer.parseInt(req.queryParams("value"));
            int pos = binarysearch(list, num);

            return builtResponse(list, num, pos, "binarySearch");
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
        return 8080;
    }

    /**
     * Método que realiza la búsqueda de manera lineal en una lista
     * @param list Lista donde se va a buscar un valor
     * @param num Valor que se desea buscar
     * @return Posición de donde se encuentra el número que se estaba buscando, en caso de no encontrarlo se retorna -1
     */
    private static int linearSearch(List<Integer> list, int num){
        int pos = -1;
        for (int i = 0; i < list.size(); i ++){
            if(list.get(i) == num){
                pos = i;
                break;
            }
        }
        return pos;
    }

    /**
     * Método que realiza la búsqueda de manera binaria en una lista
     * @param list Lista donde se va a buscar un valor
     * @param num Valor que se desea buscar
     * @return Posición de donde se encuentra el número que se estaba buscando, en caso de no encontrarlo se retorna -1
     */
    private static int binarysearch(List<Integer> list, int num){
        if(list.size() > 1){
            int indexM = list.size()/2;
            if(list.get(indexM) == num){
                return indexM;
            } else if (list.get(indexM) > num) {
                return binarysearch(list.subList(0, indexM), num);
            } else if (list.get(indexM) < num) {
                int ans = binarysearch(list.subList(indexM, list.size()), num);
                if (ans != -1){
                    return indexM + ans;
                }else {
                    return -1;
                }
            }
        } else if (list.get(0) == num) {
            return 0;
        }
        return -1;
    }

    /**
     * Método que convierte una lista que llega como una cadena en un objeto tipo lista de java
     * @param lista lista inicial como cadena de caracteres
     * @return Lista con los valores que venían en la cadena de valores
     */
    private static List<Integer> stringToList(String lista){
        String[] list = lista.split(",");
        List<Integer> newList = new ArrayList<>();
        for(String s : list){
            newList.add(Integer.parseInt(s));
        }
        return newList;
    }

    /**
     * Método que construye la respuesta en formato json
     * @param list Lista donde se buscaron los valores
     * @param value Número que se buscó en la lista
     * @param pos Posición del número en la lista
     * @param op Método que se utilizó para buscar el número en la lista
     * @return Cadena de caracteres con toda la información
     */
    private static String builtResponse(List<Integer> list, int value, int pos, String op){
        return String.format("{\"operation\": \"%s\",  \"inputlist\": \"%s\", \"value\": \"%s\",\"output\": \"%s\"}", op, lindaLista(list), value, pos);
    }

    /**
     * Método que convierte un objeto lista en un string
     * @param list lista que se desea convertir
     * @return la lista vista como una cadena de caracteres
     */
    private static String lindaLista(List<Integer> list){
        StringBuilder listL = new StringBuilder();
        for (int i = 0; i < list.size()-1 ; i++){
            listL.append(list.get(i) + ",");
        }
        listL.append(list.get(list.size()-1));
        return listL.toString();
    }
}
