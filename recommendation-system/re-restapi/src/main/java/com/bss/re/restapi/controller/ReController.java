package com.bss.re.restapi.controller;

import com.bss.re.domain.dto.CommonResult;
import com.bss.re.domain.dto.CurrentUserSimilar;
import com.bss.re.restapi.service.ReService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by QAQ on 2019/5/30
 */
@RestController
public class ReController {

    @Autowired
    ReService reService;

    @GetMapping
    public Object getSimilar(@RequestParam("userId") String id){
        CurrentUserSimilar similar = reService.getSimilar(id);
        return new CommonResult().success(similar);
    }
}
