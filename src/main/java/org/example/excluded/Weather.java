package org.example.excluded;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.example.models.WeatherEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Weather {
    private int id;
    private static String city = "Moscow";
    private int temperature;

    public static void main(String[] args) {
//        ////////////Блок кода для вывода данных из таблицы БД через JDBC
//        WeatherDao weatherDao = new WeatherDao();
//        System.out.println("id\tcity\ttemperature");
//        for (Weather weather: weatherDao.readAll()){
//            System.out.println(weather.getId() + "\t" + weather.getCity() + "\t" + weather.getTemperature());
//        }



        ////////////Блок кода для подключения к API погоды
        while (true) {

            //Вводим название города на английском
//            System.out.print("Enter city please: ");
//            Scanner scanner = new Scanner(System.in);
//            String city = scanner.nextLine();


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

                    int temp = jsonNode.get("current").get("temp_c").asInt();

                    // Hibernate пишет полученную инфу в БД
//                    Configuration configuration = new Configuration().addAnnotatedClass(WeatherEntity.class);
//                    SessionFactory sessionFactory = configuration.buildSessionFactory();
//                    Session session = sessionFactory.getCurrentSession();
//
//                    try {
//                        session.beginTransaction();
//                        WeatherEntity weatherEntity = new WeatherEntity(jsonNode.get("location").get("name").asText(), jsonNode.get("current").get("temp_c").asInt());
//                        session.save(weatherEntity);
//                        session.getTransaction().commit();
//                    } finally {
//                        sessionFactory.close();
//                    }
                }

            } catch (Throwable e) {
                System.out.println("City was not found");
            }
        }
    }

}
