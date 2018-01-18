package com.zephyrusapps.edu.emat.service.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;

import java.io.Serializable;
import java.util.Set;

public class ConnectionFactoryLocatorPseudoProxy implements ConnectionFactoryLocator, Serializable, BeanFactoryAware {
    private BeanFactory beanFactory;
    private final String targetBeanName;

    public ConnectionFactoryLocatorPseudoProxy(String targetBeanName) {
        this.targetBeanName = targetBeanName;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public <A> ConnectionFactory<A> getConnectionFactory(Class<A> apiType) {
        return getTargetBean().getConnectionFactory(apiType);
    }

    @Override
    public ConnectionFactory<?> getConnectionFactory(String providerId) {
        return getTargetBean().getConnectionFactory(providerId);
    }

    @Override
    public Set<String> registeredProviderIds() {
        return getTargetBean().registeredProviderIds();
    }

    private ConnectionFactoryLocator getTargetBean() {
        return (ConnectionFactoryLocator) beanFactory.getBean(targetBeanName);
    }
}