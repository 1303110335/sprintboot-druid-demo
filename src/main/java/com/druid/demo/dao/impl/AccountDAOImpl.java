/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.druid.demo.dao.impl;

import com.druid.demo.dao.IAccountDAO;
import com.druid.demo.domain.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AccountDAOImpl implements IAccountDAO {

    private final JdbcTemplate jdbcTemplate;

    public AccountDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(Account account) {
        String sql = "INSERT INTO t_account(name, age, phone, address) VALUES(?, ?, ?, ?)";
        Object[] arguments = new Object[] { account.getName(), account.getAge(), account.getPhone(), account.getAddress() };
        return jdbcTemplate.update(sql, arguments) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM t_account WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public boolean update(Account account) {
        String sql = "UPDATE t_account SET name = ?, age = ?, phone = ?, address = ? WHERE id = ?";
        Object[] arguments = new Object[] { account.getName(), account.getAge(), account.getPhone(), account.getAddress(), account.getId() };
        return jdbcTemplate.update(sql, arguments) > 0;
    }

    @Override
    public Account get(Integer id) {
        String sql = "SELECT id, name, age, phone, address FROM t_account WHERE id = ?";
        List<Account> accountList = jdbcTemplate.query(sql, getRowMapper(), id);
        if (CollectionUtils.isEmpty(accountList)) {
            return new Account();
        }
        return accountList.get(0);
    }

    @Override
    public List<Account> list() {
        String sql = "SELECT id, name, age, phone, address FROM t_account";

        return jdbcTemplate.query(sql, getRowMapper());
    }

    private RowMapper<Account> getRowMapper() {
        return (resultSet, i) -> {
            Account account = new Account();
            account.setAddress(resultSet.getString("address"));
            account.setAge(resultSet.getInt("age"));
            account.setId(resultSet.getInt("id"));
            account.setName(resultSet.getString("name"));
            account.setPhone(resultSet.getString("phone"));
            return account;
        };
    }

}