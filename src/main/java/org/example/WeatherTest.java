package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.example.controllers.WeatherController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherTest {

    private String city;

    ////////////Блок кода для подключения к API погоды
    public int apiConnection(String city) {
        this.city = city;


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
                System.out.println("City: " + jsonNode.get("location").get("name"));
                System.out.println("Temperature (C): " + jsonNode.get("current").get("temp_c"));

                int temperature = jsonNode.get("current").get("temp_c").asInt();
                return temperature;

            }

        } catch (Throwable e) {
            System.out.println("City was not found");
        }
        return 0;
    }

}
