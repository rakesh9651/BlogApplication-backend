package com.application.blog.service;

import java.util.List;

import com.application.blog.entities.Post;
import com.application.blog.payloads.PostDto;
import com.application.blog.payloads.PostResponse;

public interface PostService {
  
	//create 
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
	
	//update
	public PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	public void deletePost(Integer postId);
	
	//get all post
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
	
	//get single post
	public PostDto getPostById(Integer postId);
	
	//get all post by category
	public List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	public List<PostDto> getPostsByUser(Integer userId);
	
	//search posts
	public List<PostDto> serchPosts(String keyword);
	
}
