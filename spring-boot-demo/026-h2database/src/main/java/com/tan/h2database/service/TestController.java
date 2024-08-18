package com.tan.h2database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author tanjezh
 * @create 2024-08-18 20:09
 */
@RestController
public class TestController {

    @Autowired
    private TestRepository repository;

    @GetMapping("/save")
    public TestEntity save(Integer id, String name){
        TestEntity entity = new TestEntity();
        entity.setId(id);
        entity.setName(name);
        return repository.save(entity);
    }

    @GetMapping("/update")
    public TestEntity update(Integer id, String name){
        Optional<TestEntity> entity = repository.findById(id);
        TestEntity testEntity = entity.get();
        testEntity.setName(name);
        return repository.save(testEntity);
    }

    @GetMapping("/list")
    public Iterable list(){
        return repository.findAll();
    }

    @GetMapping("/get")
    public TestEntity getById(Integer id){
        Optional<TestEntity> record = repository.findById(id);
        return record.get();
    }

    @GetMapping("/del")
    public boolean del(Integer id){
        repository.deleteById(id);
        return true;
    }

}
