package com.stefano.briky.repository;

import com.stefano.briky.model.Expenses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ExpencesRepository extends JpaRepository<Expenses, Integer> {

    Expenses findByIdAndUserId(Integer id, Integer userId);

    @Query("select sum(value) from Expenses where userId=:userId and createdAt>=:startDate and createdAt<=:endDate")
    Double monthlySum(
            @Param("userId") Integer userId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );


    @Query("select e from Expenses e where e.userId=:userId order by e.createdAt")
    List<Expenses> findLast( @Param("userId") int userId, Pageable page);
}