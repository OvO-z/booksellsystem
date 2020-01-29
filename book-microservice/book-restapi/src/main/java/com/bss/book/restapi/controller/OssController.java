package com.bss.book.restapi.controller;

import com.bss.book.domain.dto.CommonResult;
import com.bss.book.domain.dto.OssCallbackResult;
import com.bss.book.domain.dto.OssFileParam;
import com.bss.book.domain.dto.OssPolicyResult;
import com.bss.book.restapi.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

/**
 * Created by QAQ on 2019/4/26
 */
@RestController
@RequestMapping("/aliyun/oss")
public class OssController {
    @Autowired
    private OssService ossService;

    @GetMapping("/policy")
    @ResponseBody
    public Object policy() {
        OssPolicyResult result = ossService.policy();
        return new CommonResult().success(result);
    }

    @PostMapping("/callback")
    @ResponseBody
    public Object callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return new CommonResult().success(ossCallbackResult);
    }

    @DeleteMapping
    public CompletableFuture<String> delete(@RequestBody OssFileParam param){
        return CompletableFuture.supplyAsync(() -> new CommonResult().success(ossService.delete(param)));
    }
}
