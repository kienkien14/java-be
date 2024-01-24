package project.demo.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.demo.client.dto.BillDTO;
import project.demo.client.dto.CategoryDTO;
import project.demo.client.dto.ResponseDTO;
import project.demo.client.service.WSBillService;
import project.demo.client.service.WSCategoryService;
import project.demo.client.service.WSLoginService;

@Controller
@RequestMapping("/bill")
public class BillController {
	
	@Autowired
	WSLoginService wsLoginService;

	@Autowired
	WSCategoryService categoryService;
	
	@Autowired
	WSBillService wsBillService;
	
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("bill", new BillDTO());
		return "new-bill.html";
	}
	
	@PostMapping("/new")
	// @ModelAttribute map tat cac thuoc tinh input vao userDTO
	public String create(@ModelAttribute("bill") @Valid BillDTO billDTO,
			BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "new-bill.html"; // khi co loi xay ra, return views
		}
		String token = (String) session.getAttribute("token");
		//wsBillService.createBill(billDTO, token);
		wsLoginService.createBill(billDTO, "Bearer " + token);
		return "redirect:/bill/new";
	}	

}