package org.demo.interceptor;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

/**
 * @author hezhiyu on 14/12/10.
 */
public class GlobalInterceptor extends ControllerInterceptorAdapter {

    @Override
    protected Object after(Invocation inv, Object instruction) throws Exception {
        System.out.println("GlobalInterceptor -> after");
        return super.after(inv, instruction);
    }

    @Override
    protected Object before(Invocation inv) throws Exception {
        System.out.println("GlobalInterceptor -> before");
        return super.before(inv);
    }
}
