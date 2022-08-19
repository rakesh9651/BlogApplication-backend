package com.application.blog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.blog.payloads.ApiResponse;
import com.application.blog.payloads.PostDto;
import com.application.blog.payloads.PostResponse;
import com.application.blog.service.FileService;
import com.application.blog.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createPost = this.postService.createPost(postDto, categoryId, userId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	// get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get all post
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "pageId", required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {
		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDirection);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePostById(@PathVariable Integer postId) {
		postService.deletePost(postId);
		return new ApiResponse("post deleted successfully", true);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId, @RequestBody PostDto postDto) {
		PostDto post = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.ACCEPTED);
	}
	
	//search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords){
		List<PostDto> result = this.postService.serchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
	}

	//post image upload
	@PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image,
    		@PathVariable Integer postId) throws IOException{
    	
		String fileName = this.fileService.uploadImage(path, image);
       PostDto postDto = this.postService.getPostById(postId);
	   postDto.setImageName(fileName);
	   PostDto updatePost = this.postService.updatePost(postDto, postId);
	   return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
}





