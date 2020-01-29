package com.bss.book.restapi.service;

import com.bss.book.domain.dto.OssCallbackResult;
import com.bss.book.domain.dto.OssFileParam;
import com.bss.book.domain.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by QAQ on 2019/4/26
 */
public interface OssService {
    OssPolicyResult policy();
    OssCallbackResult callback(HttpServletRequest request);
    OssFileParam delete(OssFileParam param);
}
