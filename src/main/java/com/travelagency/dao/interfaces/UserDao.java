package com.travelagency.dao.interfaces;

import com.travelagency.entity.User;

public interface UserDao<T extends User>
        extends GenericDao<T> {

}
