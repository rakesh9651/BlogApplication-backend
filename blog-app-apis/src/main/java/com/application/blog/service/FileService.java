package com.application.blog.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.application.blog.exceptions.ResourceNotFoundException;

public interface FileService {
      
	String uploadImage(String path, MultipartFile file) throws IOException;
	
	InputStream getResource(String path, String fileName)throws FileNotFoundException;
}
