package com.bss.re.restapi.service.impl;

import com.bss.re.domain.dao.ReDao;
import com.bss.re.domain.dto.CurrentUserSimilar;
import com.bss.re.domain.dto.UserBook;
import com.bss.re.domain.dto.UserSimilar;
import com.bss.re.domain.mapper.ReMapper;
import com.bss.re.domain.model.Re;
import com.bss.re.domain.model.ReExample;
import com.bss.re.restapi.components.RecommendationUtils;
import com.bss.re.restapi.service.ReService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by QAQ on 2019/5/30
 */

@Service
public class ReServiceImpl implements ReService {

    @Autowired
    ReMapper mapper;

    @Autowired
    ReDao dao;

    public UserBook getCurUser(String currentUserId){
        ReExample example = new ReExample();
        example.createCriteria().andUseridEqualTo(currentUserId);
        List<Re> res = mapper.selectByExample(example);
        UserBook userBook = new UserBook();
        userBook.setId(currentUserId);
        userBook.setBooks(res);
        return userBook;
    }

    public List<UserBook> exceptCurUser(String currentUserId){
        return dao.listWithUsers(currentUserId);
    }

    @Override
    public CurrentUserSimilar getSimilar(String userId){
        UserBook currentUser = getCurUser(userId);
        CurrentUserSimilar similar = RecommendationUtils.getSimilar(currentUser,exceptCurUser(userId));
        Set<String> recommendBook = new HashSet<>();
        for (UserSimilar s:similar.getSimilars()){
            List<Re> books = getCurUser(s.getUserId()).getBooks();
            for (Re book: books){
                recommendBook.add(book.getIsbn());
            }
        }

        for (Re r: currentUser.getBooks()){
            recommendBook.remove(r.getIsbn());
        }

        similar.setBooks(recommendBook);
        return similar;
    }
}
