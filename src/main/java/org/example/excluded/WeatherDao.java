package org.example.excluded;

// Этот класс испльзовался, при работе с JDBC
//Класс для подключения к Postgresql
public class WeatherDao {
//    //Определяем необходимы параметры для настройки Connection
//    private static final String URL = "jdbc:postgresql://localhost:5432/weather_api_dp";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "postgres";
//    private static Connection connection;
//    static {
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    //Получаем все данных из таблицы
//    public List<Weather> readAll(){
//        List<Weather> weatherData = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM Weather";
//            ResultSet resultSet = statement.executeQuery(SQL);
//
//            //Кладем полученные данные в объекты типа Weather
//            while (resultSet.next()){
//                Weather weather = new Weather();
//                weather.setId(resultSet.getInt("id"));
//                weather.setCity(resultSet.getString("city"));
//                weather.setTemperature(resultSet.getInt("temperature"));
//
//                weatherData.add(weather);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return weatherData;
//    }

}
