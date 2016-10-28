package com.revpages.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.revpages.dto.ForumPost;
import com.revpages.dto.Person;

@RepositoryRestResource
public interface PostRepo extends JpaRepository<ForumPost, Integer> {
	
	public List<ForumPost> findByAuthorOrderByTimestampDesc(Person author);
	
	public List<ForumPost> findAllByOrderByTimestampDesc();
}
