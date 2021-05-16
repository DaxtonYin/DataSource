package com.controller;

import com.util.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
public class getQingHaiController {
    @RequestMapping("/qinghai_data")
    @ResponseBody
    public String getTotalData() {

        return new FileUtils().getFileContent("province_data","qinghai.json");
    }
}
