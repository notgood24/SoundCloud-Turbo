package com.evlerr.soundcloudturbo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SoundCloudTurbo {

    private File file = new File("./src/names.txt").getAbsoluteFile();
    private List<String> names = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        new SoundCloudTurbo();
    }

    private SoundCloudTurbo() throws IOException, InterruptedException {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                if (line.length() < 3) {
                    return;
                }
                String finalLine = line;
                if (isAvailable(finalLine)) {
                    System.out.println(finalLine + " is available!");
                }
                Thread.sleep(1000);            }
        }
    }

    private boolean isAvailable(String name) {
        try {
            URL url = new URL("https://soundcloud.com/" + name);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() != 200) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
