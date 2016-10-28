package com.revpages.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.revpages.dto.DisLikeableReply;

@RepositoryRestResource
public interface DislikeReplyRepo extends JpaRepository<DisLikeableReply, Integer>{
	
}
