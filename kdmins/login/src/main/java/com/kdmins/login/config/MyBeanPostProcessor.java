/*
package com.kdmins.login.config;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import java.lang.annotation.Annotation;
@Configuration
public class MyBeanPostProcessor implements BeanPostProcessor {
    */
/**
     * bean是容器调用构造器创建的实例
     * beanName是实例的id
     * 在所有初始化方法之前调用
     *//*

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        ReflectionUtils.doWithLocalFields(bean.getClass(), field -> {
            Annotation[] Annotations = field.getAnnotations();
            for(Annotation Annotation:Annotations){
                System.out.println(Annotation);
            }


           */
/* MergedAnnotation<?> ann = findAutowiredAnnotation(field);
            if (ann != null) {
                if (Modifier.isStatic(field.getModifiers())) {

                    return;
                }
                boolean required = determineRequiredStatus(ann);
                currElements.add(new AutowiredAnnotationBeanPostProcessor.AutowiredFieldElement(field, required));
            }*//*

        });


        return bean;
    }
    */
/*private MergedAnnotation<?> findAutowiredAnnotation(AccessibleObject ao) {
        MergedAnnotations annotations = MergedAnnotations.from(ao);
        for (Class<? extends Annotation> type : this.autowiredAnnotationTypes) {
            MergedAnnotation<?> annotation = annotations.get(type);
            if (annotation.isPresent()) {
                return annotation;
            }
        }
        return null;
    }*//*


    */
/**
     * bean是容器调用构造器创建的实例
     * beanName是实例的id
     * 在所有初始化方法之后调用
     *//*

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
*/
/*
        System.out.println(beanName);
*//*

        return bean;
    }
}*/
