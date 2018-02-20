package com.stefano.briky.service;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.controller.MonthFilter;
import com.stefano.briky.controller.exception.NotFoundException;
import com.stefano.briky.dao.ExpenseDao;
import com.stefano.briky.json.ExpenseJson;
import com.stefano.briky.json.ExpenseValue;
import com.stefano.briky.json.MonthValue;
import com.stefano.briky.json.filter.EpochFilter;
import com.stefano.briky.json.filter.SeriesFilter;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.repository.ExpencesRepository;
import com.stefano.briky.utils.ConvertUtils;
import com.stefano.briky.utils.SecurityUtils;
import com.stefano.briky.utils.tag.TagParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {

    @Autowired
    ExpencesRepository expencesRepository;

    @Autowired
    ExpenseDao expenseDao;

    @Autowired @Lazy
    TagService tagService;

    @Autowired @Lazy
    CategoryService categoryService;

    @Autowired
    ConvertUtils convertUtils;

    @Autowired
    TagParser tagParser;


    public List<Expenses> findLast(int limit) {
        LoggedUser user = SecurityUtils.getUser();

        return expencesRepository.findLast(user.getId(), PageRequest.of(0, limit));
    }

    public List<Expenses> findLastByTagId(int limit, int tagId) {
        return expencesRepository.findLastByTagId(tagId, PageRequest.of(0, limit));
    }

    public List<MonthValue> yearlySum(LoggedUser user, MonthFilter pagination) {
        return expenseDao.yearlySum(user.getId(), pagination);
    }

    public List<ExpenseValue> groupedValue(EpochFilter filter) {
        return expenseDao.groupedValue(filter);
    }


    public Map<String, List<ExpenseValue>> groupedValue(SeriesFilter filter) {
        Map<String, List<ExpenseValue>> results = new HashMap<>();

        filter.getSeries().forEach(serie -> {
            EpochFilter epochFilter = new EpochFilter(filter, serie);
            results.put(serie.getName(), groupedValue(epochFilter));
        });


        return results;
    }

    /**
     * Verifica che l'entity sia associata all'utente indicato.
     * @param id
     * @param userId
     * @throws NotFoundException
     */
    public void checkGrant(int id, int userId) throws NotFoundException {
        if(!expencesRepository.existsByIdAndUserId(id, userId)) {
            throw new NotFoundException();
        }
    }

    /**
     * Crea l'entity
     * @param expense
     * @return
     */
    public Expenses create(Expenses expense) {
        int userId = SecurityUtils.getUser().getId();

        expense = convert(expense);
        expense.setUserId(userId);
        expense.setUpdatedAt(LocalDateTime.now());

        if(null == expense.getCreatedAt()) {
            expense.setCreatedAt(LocalDateTime.now());
        }

        return expencesRepository.save(expense);
    }

    /**
     * Aggiorna l'entity verificando le grant dell'utente
     * @param id
     * @param json
     * @return
     * @throws NotFoundException
     */
    public Expenses update(int id, ExpenseJson json) throws NotFoundException {
        int userId = SecurityUtils.getUser().getId();

        checkGrant(id, userId);
        Expenses expense = convert(json);
        expense.setId(id);
        expense.setUserId(userId);
        expense.setUpdatedAt(LocalDateTime.now());

        return expencesRepository.save(expense);
    }

    /**
     * Elimina l'entity solo se creata dall'utente corrente
     * @param id
     * @throws NotFoundException
     */
    public void delete(int id) throws NotFoundException {
        int userId = SecurityUtils.getUser().getId();

        checkGrant(id, userId);
        expencesRepository.deleteById(id);
    }

    /**
     * Trasforma il json in entity e controlla la categoria ed i tags
     * @param json
     * @return
     */
    private Expenses convert(ExpenseJson json) {
        Expenses expense = convertUtils.toExpense(json);
        return convert(expense);
    }

    /**
     * Verifica la categoria ed i tag impostandoli in maniera corretta per il salvataggio
     * @param expense
     * @return
     */
    private Expenses convert(Expenses expense) {
        expense.setCategory(categoryService.getOrCreate(expense.getCategory()));
        expense.setTags(tagService.getOrCreate(
                tagParser.parse(expense.getDescription())
        ));

        return expense;
    }


}
