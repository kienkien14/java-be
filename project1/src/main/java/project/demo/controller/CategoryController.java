package project.demo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.demo.client.dto.CategoryDTO;
import project.demo.client.service.WSCategoryService;
import project.demo.client.service.WSLoginService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	WSCategoryService categoryService;
	
	@Autowired
	WSLoginService wsLoginService;

	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("category", new CategoryDTO());
		return "new-category.html";
	}

	@PostMapping("/new")
	// @ModelAttribute map tat cac thuoc tinh input vao userDTO
	public String create(@ModelAttribute("category") @Valid CategoryDTO categoryDTO,
			BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "new-category.html"; // khi co loi xay ra, return views
		}
		String token = (String) session.getAttribute("token");
		categoryService.createCategory(categoryDTO, token);
//		wsLoginService.createCategory(categoryDTO, "Bearer " + token);
		return "redirect:/category/new";
	}
	
}
