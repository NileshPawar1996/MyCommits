package com.sunbeam.controllers;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.sunbeam.services.StorageService;


@CrossOrigin
@Controller
public class FileControllerImpl {
	@Autowired
	private StorageService storageService;
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("uploadFile") MultipartFile file, Model model) {
		String fileName = storageService.store(file);
		model.addAttribute("fileName", fileName);
		return "info";
	}
	
	@RequestMapping(value="/download/{fileName}", produces = {"image/*", "audio/*"})
	public void download(@PathVariable("fileName") String fileName, HttpServletResponse resp) {
		Resource resource = storageService.load(fileName);
		if(resource != null) {
			try(InputStream in = resource.getInputStream()) {
				ServletOutputStream out = resp.getOutputStream();
				FileCopyUtils.copy(in, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

