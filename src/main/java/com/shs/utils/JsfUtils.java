package com.shs.utils;

//~--- non-JDK imports --------------------------------------------------------
//~--- JDK imports ------------------------------------------------------------
import java.io.IOException;
import java.util.Iterator;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewParameter;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewMetadata;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Romany
 */
public class JsfUtils {

    public JsfUtils() {
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    public static String getCurrentViewId() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();
    }

    public static void navigateToCase(String navigationCase) {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, navigationCase);
    }

    public static void setRequestParameter(String parameterName, String paramValue) {
        getRequest().getParameterMap().put(parameterName, new String[]{paramValue});
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public static Object getSessionParameter(String parameterName) {
        return getSession().getAttribute(parameterName);
    }

    public static void setSessionParameter(String parameterName, Object object) {
        getSession().setAttribute(parameterName, object);
    }

    public static void deleteCookieParamter(String paramterName, String value) {
        Cookie cookie = new Cookie(paramterName, value);
        cookie.setMaxAge(0);
        getResponse().addCookie(cookie);
    }

    public static void setCookieParamter(String paramterName, String value) {
        Cookie cookie = new Cookie(paramterName, value);
        cookie.setMaxAge(2592000);//60*60*24*30
        cookie.setPath("/");
        getResponse().addCookie(cookie);
    }

    public static void setCookieRequestParamter(HttpServletRequest request, HttpServletResponse response, String paramterName, String value) {
        Cookie cookie = new Cookie(paramterName, value);
        cookie.setMaxAge(2592000);//60*60*24*30
        cookie.setPath("/");

        response.addCookie(cookie);
    }

    public static void setSessionCookieParamter(String paramterName, String value) {
        Cookie cookie = new Cookie(paramterName, value);
        getResponse().addCookie(cookie);
    }

    public static void setHttpOnlyCookieParamter(String paramterName, String value) {
//        Cookie cookie = new Cookie(paramterName, value);
//        cookie.setMaxAge(2592000);//60*60*24*30
//        cookie.setHttpOnly(true);
        getResponse().setHeader("SET-COOKIE", paramterName + "=" + value + "; Path=/; Max-Age=2592000; HttpOnly");
//        getResponse().addCookie(cookie);
    }

    public static String getCookieParamter(String paramterName) {
        Cookie[] cookies = getRequest().getCookies();

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (paramterName.equals(cookie.getName())) {
                    return (cookie.getValue());
                }
            }
        }
        return "";
    }

    public static String getCookieParamterFromRequest(HttpServletRequest request, String paramterName) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (paramterName.equals(cookie.getName())) {
                    return (cookie.getValue());
                }
            }
        }
        return "";
    }

    public static void redirect(String address) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(address);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void redirectExternal(String address) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(address);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void dispatch(String address) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().dispatch(address);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Forward inside the jsf app
     *
     * @param address a faces view id ( like /somepath/apage.jsp or
     * /somepath/apage.xhtml)
     */
    public static void forward(String address) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String currentViewId = facesContext.getViewRoot().getViewId();

        if ((address != null) && (!address.equals(currentViewId))) {
            ViewHandler viewHandler = facesContext.getApplication().getViewHandler();
            UIViewRoot viewRoot = viewHandler.createView(facesContext, address);

            facesContext.setViewRoot(viewRoot);
            facesContext.renderResponse();
        }
    }

    public static UIComponent findComponent(String componentId) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot root = context.getViewRoot();
        UIComponent c = findComponent(root, componentId);

        return c;
    }

    /**
     * Finds component with the given id
     */
    public static UIComponent findComponent(UIComponent c, String componentId) {
        if (componentId.equals(c.getId())) {
            return c;
        }

        Iterator<UIComponent> kids = c.getFacetsAndChildren();

        while (kids.hasNext()) {
            UIComponent found = findComponent(kids.next(), componentId);

            if (found != null) {
                return found;
            }
        }

        return null;
    }

    public static boolean isPostback() {
        return FacesContext.getCurrentInstance().isPostback();
    }

    public ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public static boolean isPartialRequest() {
        return FacesContext.getCurrentInstance().getPartialViewContext().isPartialRequest();
    }

    public static Object getValueObject(String expr, Class returnType) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ELContext elctx = fc.getELContext();
        ExpressionFactory elFactory = fc.getApplication().getExpressionFactory();
        ValueExpression valueExpr = elFactory.createValueExpression(elctx, expr, returnType);
        return valueExpr.getValue(elctx);
    }

    public static String getBasePhysicalPath() {
        String path = JsfUtils.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);

        path = path.substring(0, path.indexOf("WEB-INF"));

        return path;
    }

    public static String getViewParameter(String parameterName) {
        String toReturn = null;
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();

            Iterator<UIViewParameter> viewParams = ViewMetadata.getViewParameters(ctx.getViewRoot()).iterator();
            while (viewParams.hasNext()) {
                UIViewParameter parameter = viewParams.next();
                if (parameterName.equals(parameter.getName())) {
                    toReturn = parameter.getValue().toString();
                    break;
                }

            }
        } catch (Throwable ex) {
            //SystemLogger.getLogger(BaseAction.class.getName()).error( null, ex);
        }
        return toReturn;
    }

    public void updateViewParameter(String parameterName, Object parameterValue) {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();

            Iterator<UIViewParameter> viewParams = ViewMetadata.getViewParameters(ctx.getViewRoot()).iterator();
            while (viewParams.hasNext()) {
                UIViewParameter parameter = viewParams.next();

                if (parameterName.equals(parameter.getName())) {
                    parameter.setValue(parameterValue);
                    break;
                }

            }
        } catch (Exception ex) {
            //SystemLogger.getLogger(BaseAction.class.getName()).error( null, ex);
        }
    }

    public static String[] getParameterValues(String name) {
        return getRequest().getParameterValues(name);
    }

    public static String getRequestParameter(String name) {
        try {
            String object = getRequest().getParameter(name);
            if (object == null || "".equals(object)) {
                object = getViewParameter(name);
            }
            return object;
        } catch (Exception ex) {
        }
        return null;
    }

    public static long getLongParameter(String name) {
        try {
            return Long.parseLong(getRequestParameter(name));
        } catch (NumberFormatException ex) {
        }
        return 0;
    }

    public static int getIntParameter(String name) {
        try {
            return Integer.parseInt(getRequestParameter(name));
        } catch (NumberFormatException ex) {
        }
        return 0;
    }

    public static int getIntParameter(String name, int defaultValue) {
        try {
            return Integer.parseInt(getRequestParameter(name));
        } catch (NumberFormatException ex) {
        }
        return defaultValue;
    }

    public static int getIntParameter(HttpServletRequest request, String name, int defaultValue) {
        try {
            return Integer.parseInt(request.getParameter(name));
        } catch (NumberFormatException ex) {
        }
        return defaultValue;
    }

    public static double getDoubleParameter(String name) {
        try {
            return Double.parseDouble(getRequestParameter(name));
        } catch (NumberFormatException ex) {
        }
        return 0.0;
    }

    public static void addMessageToElement(String elementClientId, String msgStr) {
        FacesMessage msg = new FacesMessage(FacesMessage.FACES_MESSAGES, msgStr);
        FacesContext.getCurrentInstance().addMessage(elementClientId, msg);
    }

}
