package itsm.springGreetingsServer.annotationProcessors;

import itsm.springGreetingsServer.annotations.SpecifiedResponse;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class SpecifiedResponseAnnotationBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Object> beansMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().getAnnotation(SpecifiedResponse.class) != null ||
                beanName.toLowerCase().contains("specified"))
            beansMap.put(beanName,bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beansMap.containsKey(beanName)) {
            Class<?> originalBeanClass = beansMap.get(beanName).getClass();
            Object proxyBean = Proxy.newProxyInstance(originalBeanClass.getClassLoader(), originalBeanClass.getInterfaces(), (proxy, method, args) -> {
                 if (method.getReturnType().equals(String.class)) {
                     return (method.invoke(bean, args) + "(specified response)");
                 }
                 else
                    return method.invoke(bean,args);
            });
            return proxyBean;
        }
        else
            return bean;
    }
}
