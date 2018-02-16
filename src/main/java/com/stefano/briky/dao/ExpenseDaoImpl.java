package com.stefano.briky.dao;

import com.stefano.briky.controller.MonthFilter;
import com.stefano.briky.dao.criteria.EpochCriteriaBuilder;
import com.stefano.briky.json.ExpenseValue;
import com.stefano.briky.json.MonthValue;
import com.stefano.briky.json.filter.EpochFilter;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.model.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {

    @Autowired
    EntityManager entityManager;


    @Override
    public List<ExpenseValue> groupedValue(EpochFilter filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Expenses> from = query.from(Expenses.class);
        List<Predicate> where = new ArrayList<>();


        EpochCriteriaBuilder ecb = new EpochCriteriaBuilder(builder, filter.getGroup());
        Path<LocalDateTime> createdAt = from.get("createdAt");

        if (filter.getFrom() != null) {
            where.add(builder.greaterThanOrEqualTo(
                    createdAt,
                    ecb.toLocalDate(filter.getGroup(), filter.getFrom()))
            );
        }

        if (filter.getTo() != null) {
            where.add(builder.lessThanOrEqualTo(
                    createdAt,
                    ecb.toLocalDate(filter.getGroup(), filter.getTo()))
            );
        }

        if(filter.getTags() != null && !filter.getTags().isEmpty()) {
            Join<Tags, Object> tags = from.join("tags", JoinType.LEFT);
            where.add(tags.get("id").in(filter.getTags()));
        }

        List<Selection<?>> selectionList = ecb.sumSelect(createdAt);
        selectionList.add(builder.sum(from.get("value")).alias("sum"));


        query.multiselect(selectionList);
        query.groupBy(ecb.groupBy(createdAt));
        query.orderBy(ecb.orderBy(createdAt));
        query.where(where.toArray(new Predicate[] {}));

        return entityManager.createQuery(query)
                .getResultList()
                .stream()
                .map(row -> new ExpenseValue(filter.getGroup(), row))
                .collect(Collectors.toList());
    }

    @Deprecated
    @Override
    public List<MonthValue> yearlySum(Integer userId, MonthFilter filter) {
        String qlString = "select year(createdAt), month(createdAt), sum(value) " +
                "from Expenses where userId=:userId and year(createdAt)=:year ";

        if (filter.isValidMonth()) {
            qlString += " and month(createdAt)=:month ";
        }

        qlString += "group by year(createdAt), month(createdAt)";

        TypedQuery<Object[]> query = entityManager.createQuery(qlString, Object[].class);
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
