package com.caloriecap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caloriecap.models.RoleDAO;

@Repository
public interface RoleRepository  extends JpaRepository<RoleDAO, Long>{

}
