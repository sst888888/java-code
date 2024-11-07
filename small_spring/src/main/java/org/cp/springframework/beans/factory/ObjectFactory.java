package org.cp.springframework.beans.factory;

import org.cp.springframework.beans.BeansException;

public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}