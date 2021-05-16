package com.controller;

import com.util.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class getHuNanController {
    @RequestMapping("/hunan_data")

    public String getTotalData() {

        return new FileUtils().getFileContent("province_data","hunan.json");
    }
}
