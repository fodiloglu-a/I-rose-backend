package com.task.irose.service.pullData;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class DownloadDataServices {


    public String downloadData(String url) {
        StringBuilder content = new StringBuilder();

        try {
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

            reader.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content.toString();
    }


}
