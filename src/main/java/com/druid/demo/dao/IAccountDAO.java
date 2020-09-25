/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.druid.demo.dao;

import com.druid.demo.domain.Account;

import java.util.List;

/**
 *
 * @author xuleyan
 * @version IAccountDAO.java, v 0.1 2020-09-24 10:53 下午
 */
public interface IAccountDAO {

    boolean save(Account account);

    boolean delete(Integer id);

    boolean update(Account account);

    Account get(Integer id);

    List<Account> list();

}