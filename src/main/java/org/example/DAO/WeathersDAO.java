package org.example.DAO;


import org.example.models.WeatherEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class WeathersDAO {
    private final SessionFactory sessionFactory;
    @Autowired
    public WeathersDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }
    @Transactional
    public List<WeatherEntity> getWeatherList(){
        Session session = sessionFactory.getCurrentSession();
        List<WeatherEntity> weather_list = session.createQuery("select w from WeatherEntity w", WeatherEntity.class)
                .getResultList();
        return weather_list;
    }
    @Transactional
    public void save(WeatherEntity weatherEntity){
        Session session = sessionFactory.getCurrentSession();
        session.save(weatherEntity);
    }
}
