package kr.aquino.aoc.mmxx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class IOUtility {
    private IOUtility() {
    }

    public static List<String> ReadUrl(String url) throws MalformedURLException, IOException {
        try (var br = new BufferedReader(new InputStreamReader((new URL(url)).openStream()))){
            
            var list = new ArrayList<String>();
            String line;
            while((line = br.readLine()) != null){
                list.add(line);
            }
            return list;
        }
    }

    public static List<String> ReadFile(String path) throws IOException {
        try (var br = Files.newBufferedReader(FileSystems.getDefault().getPath(path))){
            
            var list = new ArrayList<String>();
            String line;
            while((line = br.readLine()) != null){
                list.add(line);
            }
            return list;
        }
    }



}