package cn.dails;

import cn.dails.http.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MainTest {

    public static void testGet() {
        //get
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        try {
            String response = HttpUtil.get(url, headers);
            System.out.println("GET Response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void post() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        String body = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

        try {
            String response = HttpUtil.post(url, headers, body);
            System.out.println("POST Response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void put() {
        // put
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        String body = "{\"id\": 1, \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

        try {
            String response = HttpUtil.put(url, headers, body);
            System.out.println("PUT Response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void delete() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        try {
            String response = HttpUtil.delete(url, headers);
            System.out.println("DELETE Response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}