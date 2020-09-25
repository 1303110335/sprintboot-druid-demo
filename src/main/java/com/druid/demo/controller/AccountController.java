/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.druid.demo.controller;

import com.druid.demo.dao.IAccountDAO;
import com.druid.demo.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author xuleyan
 * @version AccountController.java, v 0.1 2020-09-25 10:51 上午
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final IAccountDAO accountDAO;

    public AccountController(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @PostMapping("/add")
    public boolean testSave(@RequestBody Account account) {
        // new Account(null, "吴洋", 25, "1412341234", "江西")
        return accountDAO.save(account);
    }

    @PostMapping("/del/{id}")
    public boolean testDelete(@PathVariable Integer id) {
        return accountDAO.delete(id);
    }

    @PostMapping("/update")
    public boolean testUpdate(@RequestBody Account account) {
        // new Account(1, "李四", 27, "18819412345", "广州")
        return accountDAO.update(account);
    }

    @GetMapping("/find/{id}")
    public Account testGet(@PathVariable Integer id) {
        return accountDAO.get(id);
    }

    @GetMapping("/list")
    public List<Account> testList() {
        return accountDAO.list();
    }
}