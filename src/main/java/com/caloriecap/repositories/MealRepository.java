package com.caloriecap.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.caloriecap.models.MealDAO;
import com.caloriecap.models.UserDAO;

@Repository
public interface MealRepository extends JpaRepository<MealDAO, Long>{

	@Query("select m from MealDAO m where m.date>=?1 and m.date<=?2 and m.user=?3 order by m.date desc")
	public List<MealDAO> findMealsBetweenDates(Date from, Date to, UserDAO user);
	
	@Query("select m from MealDAO m where m.date>=?1 and m.date<=?2")
	public List<MealDAO> findMealsBetweenDatesForAllUsers(Date from, Date to);
	
}
