package org.demo.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

/**
 * @author hezhiyu on 14/11/6.
 */
@Path("/")
public class DemoController {

    @Get("hi")
    public String sayHi() {
        return "@" + "Hello World!";
    }
}