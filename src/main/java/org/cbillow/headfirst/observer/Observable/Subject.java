package org.cbillow.headfirst.observer.observable;

import org.cbillow.headfirst.observer.observe.Observer;

/**
 * 被观察者
 *
 * 提供主题的标准接口
 */
public interface Subject {

    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
