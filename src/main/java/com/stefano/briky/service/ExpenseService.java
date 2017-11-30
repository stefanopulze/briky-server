package com.stefano.briky.service;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.json.ExpenceJson;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.repository.ExpencesRepository;
import com.stefano.briky.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    ExpencesRepository expencesRepository;

    public List<Expenses> findLast(int limit) {
        LoggedUser user = SecurityUtils.getUser();

        return expencesRepository.findLast(user.getId(), PageRequest.of(0, limit));

    }

    public List<Expenses> findLastByTagId(int limit, int tagId) {
        return expencesRepository.findLastByTagId(tagId, PageRequest.of(0, limit));
    }
}
