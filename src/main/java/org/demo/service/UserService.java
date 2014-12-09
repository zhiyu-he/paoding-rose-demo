package org.demo.service;

import org.demo.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hezhiyu on 14/11/7.
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public String getUserNameById(long uid) {
        return userDAO.getUserName(uid);
    }
}
