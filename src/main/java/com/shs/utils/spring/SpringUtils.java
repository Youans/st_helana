/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.utils.spring;

import com.shs.utils.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;

/**
 *
 * @author John
 */
public class SpringUtils {

    public static ApplicationContext ctx;

    static {
        try {
            ctx = ApplicationContext.class.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(SpringUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SpringUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
            return ApplicationContext.class.newInstance().getBean(beanName, clazz);
        } catch (InstantiationException ex) {
            Logger.getLogger(SpringUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
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
