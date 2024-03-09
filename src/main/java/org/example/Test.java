package org.example;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        while (true) {

            //Вводим название города на английском
            System.out.print("Enter city please: ");
            Scanner scanner = new Scanner(System.in);
            String city = scanner.nextLine();


            try (
                    CloseableHttpClient client = HttpClients.createDefault();
                    CloseableHttpResponse response = client.execute(new HttpGet(String.format("http://api.weatherapi.com/v1/current.json?key=3c58bcab3de04398b16135017240903&q=%s&aqi=no", city))) //Здесь генерируется запрос на сервер (нужен Api Key)
            ) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String data = IOUtils.toString(entity.getContent(), "cp1251"); //Переводим полученный ответ в кодировку Windows

                    // Парсим полученный JSON с помощью Jackson
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode jsonNode = mapper.readTree(data);


                    System.out.println("Region: " + jsonNode.get("location").get("region"));
                    System.out.println("Temperature (C): " + jsonNode.get("current").get("temp_c"));
                }

            } catch (Throwable e) {
                System.out.println("City was not found");
            }
        }
    }
}
