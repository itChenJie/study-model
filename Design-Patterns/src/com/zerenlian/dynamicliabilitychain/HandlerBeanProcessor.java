package com.zerenlian.dynamicliabilitychain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2020/12/8 4:35 下午
 **/
@Component
public class HandlerBeanProcessor implements BeanPostProcessor, ApplicationContextAware {
    private ApplicationContext applicationContext;

    /**
     *  该方法会在一个bean初始化完成后调用，这里主要是在Pipeline初始化完成之后获取所有实现了
     *  Handler接口的bean，然后通过调用Pipeline.addLast()方法将其添加到pipeline中
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DefaultPipeline){
            DefaultPipeline pipeline = (DefaultPipeline) bean;
            Map<String, ? extends DefaultPipeline> beans = applicationContext.getBeansOfType(pipeline.getClass());
            beans.forEach((name,handler) -> pipeline.addHandler((Handler) handler));
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
