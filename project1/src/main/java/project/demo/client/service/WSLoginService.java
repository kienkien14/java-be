package project.demo.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import project.demo.client.dto.BillDTO;
import project.demo.client.dto.CategoryDTO;
import project.demo.client.dto.ResponseDTO;


@FeignClient(value = "loginservice", url = "http://localhost:8080")
public interface WSLoginService {

	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password);

	@PostMapping("/admin/category/") 
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO,
			@RequestHeader("Authorization") String bearerToken);
	
	@PostMapping("/admin/bill/") 
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO<CategoryDTO> createBill(@RequestBody BillDTO billDTO,
			@RequestHeader("Authorization") String bearerToken);
}
