package co.edu.eci.parcial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.*;

public class MathServices {
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

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }

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
    private static int binarysearch(List<Integer> list, int num){
        System.out.println(list.toString());
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

    private static List<Integer> stringToList(String lista){
        String[] list = lista.split(",");
        List<Integer> newList = new ArrayList<>();
        for(String s : list){
            newList.add(Integer.parseInt(s));
        }
        return newList;
    }

    private static String builtResponse(List<Integer> list, int value, int pos, String op){
        return String.format("{\"operation\": \"%s\",  \"inputlist\": \"%s\", \"value\": \"%s\",\"output\": \"%s\"}", op, lindaLista(list), value, pos);
    }

    private static String lindaLista(List<Integer> list){
        StringBuilder listL = new StringBuilder();
        for (int i = 0; i < list.size()-1 ; i++){
            listL.append(list.get(i) + ",");
        }
        listL.append(list.get(list.size()-1));
        return listL.toString();
    }
}
