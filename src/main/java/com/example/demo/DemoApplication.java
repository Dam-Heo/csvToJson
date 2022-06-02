package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(DemoApplication.class, args);
        Pattern pattern = Pattern.compile(",");
        String path = "C:\\web\\csvToJson\\src\\main\\java\\com\\example\\demo\\data\\";
        try (BufferedReader in = new BufferedReader(new FileReader(path + "top20.csv"));){
            List<CsvDto> players = in
                    .lines()
                    .skip(1)
                    .map(line -> {
                        String[] x = pattern.split(line);
                        return new CsvDto(Integer.valueOf(x[0]),
                                x[1],
                                x[2],
                                x[3]);
                    })
                    .collect(Collectors.toList());
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
//            mapper.writeValue(System.out, players);
            mapper.writeValue(new File(path + "top20.json"), players);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "top20.json"));
            JSONArray jsonOrderArr = (JSONArray) JSONValue.parse(bufferedReader);
            for(int i=0;i<jsonOrderArr.size();i++) {
                JSONObject orderJsonArrComponent = (JSONObject) jsonOrderArr.get(i);
                String licenseOrgan = orderJsonArrComponent.get("licenseOrgan").toString();
                System.out.println(i+1 +" "+licenseOrgan);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
