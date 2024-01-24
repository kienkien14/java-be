package project.demo.client.service;

import javax.persistence.NoResultException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import project.demo.client.dto.CategoryDTO;
import project.demo.client.dto.ResponseDTO;

@Service
public class WSCategoryService {
	public ResponseDTO<CategoryDTO> createCategory(CategoryDTO categoryDTO, String token) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<CategoryDTO> entity = new HttpEntity<>(categoryDTO, headers);

		ResponseEntity<ResponseDTO> response = restTemplate.exchange("http://localhost:8080/admin/category/",
				HttpMethod.POST, entity, ResponseDTO.class);

		if (response.getStatusCode() == HttpStatus.OK)
			return response.getBody();
		else
			throw new NoResultException();

	}
}
