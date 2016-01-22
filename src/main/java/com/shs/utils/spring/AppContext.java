/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.utils.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author Nabil
 */
public class AppContext implements ApplicationContextAware {

    private static ApplicationContext ctx;

    /**
     * Injected from the class "ApplicationContextProvider" which is
     * automatically loaded during Spring-Initialization.
     *
     * @param applicationContext
     */
    @Override
    public final void setApplicationContext(ApplicationContext applicationContext) {
        AppContext.ctx = applicationContext;
    }

    /**
     * Get access to the Spring ApplicationContext from everywhere in your
     * Application.
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
}
