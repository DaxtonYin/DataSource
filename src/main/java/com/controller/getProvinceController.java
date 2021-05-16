package com.controller;


import com.util.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class getProvinceController {
    @RequestMapping("/province_data")
    public String getTotalData() {

        return new FileUtils().getFileContent("province.json");
    }

}
