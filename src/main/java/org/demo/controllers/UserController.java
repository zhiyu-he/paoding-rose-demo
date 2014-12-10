package org.demo.controllers;

import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import org.demo.annotation.LogAccess;

/**
 * @author hezhiyu on 14/12/10.
 */
@Path("user")
public class UserController {


    @Post("login")
    public String login(@Param("name") String name,
                        @Param("pwd")  String pwd) {

        return "@" + String.format("name: %s, pwd: %s", name, pwd);
    }

    @LogAccess
    @Get("profile")
    public String getProfile(@Param("uid") Long uid) {
        return "@" + uid;
    }
}
