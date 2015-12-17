package org.cbillow.headfirst.observer;

import org.cbillow.headfirst.observer.observable.WeatherData;
import org.cbillow.headfirst.observer.observe.CurrentConditionDisplay;
import org.cbillow.headfirst.observer.observe.ForecastDisplay;
import org.cbillow.headfirst.observer.observe.StatisticsDisplay;

/**
 * Created by Cbillow on 15/12/15.
 */
public class WeatherStation {

    public static void main(String[] args) {
        /**
         * 主题
         */
        WeatherData weatherData = new WeatherData();
        /**
         * 开始订阅
         */
        CurrentConditionDisplay conditionDisplay = new CurrentConditionDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        /**
         * 测试
         */
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
