package com.controller;

import com.util.FileUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class getAddOverseasController {
    @RequestMapping("/overseas_data")
    public String getTotalData() {
        return new FileUtils().getFileContent("overseas.json");
    }

}
