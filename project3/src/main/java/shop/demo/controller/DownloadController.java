package shop.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {
	final String UPLOAD_FOLDER = "D:\\BackEnd\\image\\";
	
	/// /user/download/abc.jpg 
	@GetMapping("user/download/{filename}")
	public void downloadUser(@PathVariable("filename") String filename, HttpServletResponse response) throws IOException {
		File file = new File(UPLOAD_FOLDER + filename);
		Files.copy(file.toPath(), response.getOutputStream());
	}
	
	@GetMapping("product/download/{filename}")
	public void downloadProduct(@PathVariable("filename") String filename, HttpServletResponse response) throws IOException {
		File file = new File(UPLOAD_FOLDER + filename);
		Files.copy(file.toPath(), response.getOutputStream());
	}

}
