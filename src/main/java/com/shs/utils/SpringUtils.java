/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.utils;

import com.shs.utils.spring.AppContext;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.context.request.RequestContextHolder;

/**
 *
 * @author John
 */
public class SpringUtils {

    public static <T> T getBean(Class<T> clazz) {
        String beanName = clazz.getSimpleName().substring(0, 1).toLowerCase(Locale.ENGLISH)
                + clazz.getSimpleName().substring(1);
        return getBeanByServletContext(beanName, clazz);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return getBeanByServletContext(beanName, clazz);
    }

    public static <T> T getBeanByServletContext(String beanName, Class<T> clazz) {
        try {
            System.out.println("AppContext.getApplicationContext() " + AppContext.getApplicationContext());
            return AppContext.getApplicationContext().getBean(beanName, clazz);
        } catch (Exception ex) {
            Logger.getLogger(SpringUtils.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public static String getSessionId() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }

    private SpringUtils() {
    }
}
