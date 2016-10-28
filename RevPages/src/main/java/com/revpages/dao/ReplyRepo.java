package com.revpages.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.revpages.dto.ForumPost;
import com.revpages.dto.PostReply;

@RepositoryRestResource
public interface ReplyRepo extends JpaRepository<PostReply, Integer> {
	
	public List<PostReply> findByPost(ForumPost post);

}
