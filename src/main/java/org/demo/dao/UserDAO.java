package org.demo.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

/**
 * @author hezhiyu on 14/11/7.
 */
@DAO
public interface UserDAO {

    //--------------------QUERY--------------------//
    @SQL("SELECT phone FROM white_list WHERE uid = :1")
    public String getUserName(long uid);
}
