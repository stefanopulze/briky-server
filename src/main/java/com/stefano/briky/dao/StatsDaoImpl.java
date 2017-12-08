package com.stefano.briky.dao;

import com.stefano.briky.json.TagExpenseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StatsDaoImpl implements StatsDao {

    @Autowired
    EntityManager entityManager;


    @Override
    public List<TagExpenseValue> monthlyTagValue(int userId, int year, int month) {
        return entityManager.createQuery("select tag.id, tag.name, count(expense.id), sum(expense.value) " +
                "from Expenses expense join expense.tags tag " +
                "where expense.userId=:userId and year(expense.createdAt)=:year and month(expense.createdAt)=:month " +
                "group by tag.id, tag.name", Object[].class)
                .setParameter("userId", userId)
                .setParameter("year", year)
                .setParameter("month", month)
                .getResultList()
                .stream()
                .map(row -> {
                    TagExpenseValue tag = new TagExpenseValue();

                    tag.setId( ((Number) row[0]).intValue());
                    tag.setName((String) row[1]);
                    tag.setCount( ((Number) row[2]).intValue());
                    tag.setValue( ((Number) row[3]).doubleValue());

                    return tag;
                })
                .collect(Collectors.toList());
    }
}
