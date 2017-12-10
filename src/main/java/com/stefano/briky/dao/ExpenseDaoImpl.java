package com.stefano.briky.dao;

import com.stefano.briky.controller.MonthFilter;
import com.stefano.briky.json.MonthValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {

    @Autowired
    EntityManager entityManager;


    @Override
    public List<MonthValue> yearlySum(Integer userId, MonthFilter filter) {
        String qlString = "select year(createdAt), month(createdAt), sum(value) " +
                "from Expenses where userId=:userId and year(createdAt)=:year ";

        if(filter.isValidMonth()) {
            qlString += " and month(createdAt)=:month ";
        }

        qlString += "group by year(createdAt), month(createdAt)";

        TypedQuery<Object[]> query = entityManager.createQuery( qlString, Object[].class);
        query.setParameter("userId", userId);
        query.setParameter("year", filter.getYear());

        if (filter.isValidMonth()) {
            query.setParameter("month", filter.getMonth());
        }

        return query.getResultList()
                .stream()
                .map(MonthValue::new)
                .collect(Collectors.toList());
    }
}
