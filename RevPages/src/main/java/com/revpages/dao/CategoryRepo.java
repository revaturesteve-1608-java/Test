package com.revpages.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.revpages.dto.ForumCategory;

@RepositoryRestResource
public interface CategoryRepo extends JpaRepository<ForumCategory, Integer>{
	
	public ForumCategory findByCategoryName(String name);
}
