package com.tan.jooq.rest;

import com.tan.jooq.h2.tables.records.PoetRecord;
import com.tan.jooq.service.PoetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tanjezh
 * @create 2024-09-02 23:57
 */
@RestController
public class PoetRest {

    @Autowired
    private PoetService poetService;

    @RequestMapping(path = "add")
    public int add(Integer id, String name) {
        return poetService.create(id, name);
    }

    @GetMapping(path = "get")
    public String get(Integer id) {
        PoetRecord record = poetService.get(id);
        return r2str(record);
    }

    @GetMapping(path = "list")
    public List<String> list() {
        List<PoetRecord> list = poetService.getAll();
        return list.stream().map(this::r2str).collect(Collectors.toList());
    }


    @GetMapping(path = "update")
    public int update(int id, String author) {
        return poetService.update(id, author);
    }

    @GetMapping(path = "del")
    public int delete(int id) {
        return poetService.delete(id);
    }


    private String r2str(PoetRecord record) {
        return record.getId() + " # " + record.getName();
    }

}
