package com.bss.book.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by QAQ on 2019/4/28
 */

@Getter
@Setter
public class OssCallbackParam {
    private String callbackUrl;
    private String callbackBody;
    private String callbackBodyType;
}
