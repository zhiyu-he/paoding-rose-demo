package org.demo.interceptor;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;
import org.demo.annotation.LogAccess;

import java.lang.annotation.Annotation;

/**
 * @author hezhiyu on 14/12/10.
 */
public class LoginInterceptor extends ControllerInterceptorAdapter {

    @Override
    protected Class<? extends Annotation> getRequiredAnnotationClass() {
        return LogAccess.class;
    }

    @Override
    protected Object after(Invocation inv, Object instruction) throws Exception {
        System.out.println(String.format("LoginInterceptor after controller: %s method: %s",
                inv.getControllerClass().getName(), inv.getMethod().getName()));
        return super.after(inv, instruction);
    }

    @Override
    protected Object before(Invocation inv) throws Exception {
        System.out.println(String.format("LoginInterceptor before controller: %s method: %s",
                inv.getControllerClass().getName(), inv.getMethod().getName()));
        return super.before(inv);
    }
}