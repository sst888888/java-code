package org.cp.springframework.beans.factory.config;

import org.cp.springframework.beans.factory.HierarchicalBeanFactory;
import org.cp.springframework.core.convert.ConversionService;
import org.cp.springframework.util.StringValueResolver;
import org.jetbrains.annotations.Nullable;

/**
 * Configuration interface to be implemented by most bean factories. Provides
 * facilities to configure a bean factory, in addition to the bean factory
 * client methods in the {@link org.cp.springframework.beans.factory.BeanFactory}
 * interface.
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);


    /**
     * Specify a Spring 3.0 ConversionService to use for converting
     * property values, as an alternative to JavaBeans PropertyEditors.
     * @since 3.0
     */
    void setConversionService(ConversionService conversionService);

    /**
     * Return the associated ConversionService, if any.
     * @since 3.0
     */
    @Nullable
    ConversionService getConversionService();

}
