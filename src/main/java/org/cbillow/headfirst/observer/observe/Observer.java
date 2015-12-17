package org.cbillow.headfirst.observer.observe;

/**
 * 观察者接口
 *
 * 所有观察者必须实现这个接口
 */
public interface Observer {

    void update(float temp, float humidity, float pressure);
}
