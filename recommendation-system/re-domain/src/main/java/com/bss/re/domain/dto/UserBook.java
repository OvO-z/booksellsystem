package com.bss.re.domain.dto;

import com.bss.re.domain.model.Re;
import com.bss.re.domain.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by QAQ on 2019/5/30
 */
@Getter
@Setter
public class UserBook  extends User{
    private List<Re> books;
}
