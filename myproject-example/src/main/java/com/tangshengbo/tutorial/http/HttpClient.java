package com.tangshengbo.tutorial.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tang on 2017/7/13.
 */
public class HttpClient {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8090/user/pagehelper/50000/10000");
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
