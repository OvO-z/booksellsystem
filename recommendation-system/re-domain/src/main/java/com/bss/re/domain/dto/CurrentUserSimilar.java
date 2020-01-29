package com.bss.re.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

/**
 * Created by QAQ on 2019/5/30
 */

@Getter
@Setter
public class CurrentUserSimilar {
    private String userId;
    private List<UserSimilar> similars;
    private Set<String> books;
}
