package com.bss.book.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by QAQ on 2019/4/26
 */
@Getter
@Setter
public class OssCallbackResult {
    private String filename;
    private String size;
    private String mimeType;
    private String width;
    private String height;
}
