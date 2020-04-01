package com.caloriecap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caloriecap.models.MealDAO;

@Repository
public interface MealRepository extends JpaRepository<MealDAO, Long>{

}
