package com.revpages.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.revpages.dto.Complex;

@RepositoryRestResource
public interface ComplexRepo extends JpaRepository<Complex, Integer> {
	
	public Complex findByComplexName(String complexName);
}
