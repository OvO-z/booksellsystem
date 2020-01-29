package com.bss.re.restapi.service;

import com.bss.re.domain.dto.CurrentUserSimilar;

/**
 * Created by QAQ on 2019/5/30
 */
public interface ReService {
        CurrentUserSimilar getSimilar(String userId);
}
