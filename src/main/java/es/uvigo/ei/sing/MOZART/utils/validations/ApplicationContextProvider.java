package es.uvigo.ei.sing.MOZART.utils.validations;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    public static Object getBean(Class clazz) {
        return ApplicationContextProvider.CONTEXT.getBean(clazz);
    }

    public static Object getBean(String qualifier, Class clazz) {
        return ApplicationContextProvider.CONTEXT.getBean(qualifier, clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ApplicationContextProvider.CONTEXT = context;
    }
}