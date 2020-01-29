package com.bss.re.domain.dao;

import com.bss.re.domain.dto.UserBook;

import java.util.List;

/**
 * Created by QAQ on 2019/5/30
 */
public interface ReDao {
    List<UserBook> listWithUsers(String userId);
}
