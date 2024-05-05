package com.tan.spring.dynamic.example;

import com.tan.spring.dynamic.example.api.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanjezh
 * @create 2024-05-05 15:18
 */
@RestController
@RequestMapping("/mock")
public class MockRest {

    @Autowired
    private UserInterface userInterface;

    @GetMapping("id")
    public String getId(@RequestParam("name") String name){
        return userInterface.getUserId(name).toString();
    }

}
