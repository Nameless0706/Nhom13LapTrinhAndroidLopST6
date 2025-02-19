package com.kounrew.Rest_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kounrew.Rest_api.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>{
	Optional<CategoryEntity> findByName(String name);
    Optional<CategoryEntity> findById(Integer id); 

	
}
