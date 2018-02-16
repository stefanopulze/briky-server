package com.stefano.briky.dao.criteria;

import com.stefano.briky.json.EpochGroup;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EpochCriteriaBuilder {

    private CriteriaBuilder builder;
    private EpochGroup group;


    public EpochCriteriaBuilder(CriteriaBuilder builder, EpochGroup group) {
        this.builder = builder;
        this.group = group;
    }

    private Expression<Integer> buildFunction(EpochGroup group, Path<?> column) {
        return builder.function(
                group.name().toLowerCase(),
                Integer.class,
                column);
    }

    private int extractDateInfo(LocalDate date, EpochGroup group) {
        if (group == EpochGroup.YEAR) {
            return date.getYear();
        } else if (group == EpochGroup.MONTH) {
            return date.getMonthValue();
        } else {
            return date.getDayOfMonth();
        }
    }

    public List<Expression<?>> groupBy(Path<?> column) {
        List<Expression<?>> select = new ArrayList<>();

        if (group == EpochGroup.YEAR || group == EpochGroup.MONTH) {
            select.add(
                    buildFunction(EpochGroup.YEAR, column)
            );
        }

        if (group == EpochGroup.MONTH) {
            select.add(
                    buildFunction(EpochGroup.MONTH, column)
            );
        }

        return select;
    }


    public List<Selection<?>> sumSelect(Path<?> column) {
        List<Selection<?>> select = new ArrayList<>();

        if (group == EpochGroup.YEAR || group == EpochGroup.MONTH) {
            select.add(
                    buildFunction(EpochGroup.YEAR, column)
            );
        }

        if (group == EpochGroup.MONTH) {
            select.add(
                    buildFunction(EpochGroup.MONTH, column)
            );
        }

        return select;
    }

    public List<Order> orderBy(Path<?> column) {
        List<Order> select = new ArrayList<>();

        if (group == EpochGroup.YEAR || group == EpochGroup.MONTH) {
            select.add(builder.asc(buildFunction(EpochGroup.YEAR, column)));
        }

        if (group == EpochGroup.MONTH) {
            select.add(builder.asc(buildFunction(EpochGroup.MONTH, column)));
        }

        return select;
    }

    public LocalDateTime toLocalDate(EpochGroup group, LocalDateTime date) {
        if(group == EpochGroup.YEAR) {
            return date
                    .with(ChronoField.MONTH_OF_YEAR, 1)
                    .with(ChronoField.DAY_OF_MONTH, 1);
        } else if(group == EpochGroup.MONTH) {
            return date.with(ChronoField.DAY_OF_MONTH, 1);
        } else {
            return date;
        }
    }
}
