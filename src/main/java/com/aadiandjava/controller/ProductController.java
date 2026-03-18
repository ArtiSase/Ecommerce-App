package com.aadiandjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aadiandjava.entity.Product;
import com.aadiandjava.repo.ProductRepository;
import com.aadiandjava.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductService productService;
	
	@GetMapping("/update")
	public String updateForm(@RequestParam int id,Model model)
	{
		Product product = productService.getById(id);
		model.addAttribute("product", product);
		return "update-form";
	}
	
	@GetMapping({"/","product-form"})
	public String product_form(Model model)
	{
		Product product=new Product();
		model.addAttribute("product", product);
		return "product-form";
	}
	
//	@GetMapping("/products")
//	public String allProducts(Model model)
//	{
//		List<Product> allProducts = productService.getAllProducts();
//		model.addAttribute("products", allProducts);
//		
//		return "products";
//	}
	
	@GetMapping("/products")
	public String allProducts(@RequestParam(defaultValue = "0") int page, Model model)
	{
		PageRequest of = PageRequest.of(page, 5);
		
		Page<Product> productPage = productRepository.findAll(of);
		
		model.addAttribute("products", productPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", productPage.getTotalPages());
		
		return "products";
	}
	
	@PostMapping("/save")
	public String save(@Valid Product product, BindingResult result , Model model,RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			System.err.println(result);
			return "product-form";
		}
		productService.saveProduct(product);
		attributes.addFlashAttribute("msg", "Product Saved Successfully"); //redirectionpage
		return "redirect:/products";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int id,RedirectAttributes attributes)
	{
		productService.delete(id);
		attributes.addFlashAttribute("msg", "Product Deleted Successfully"); //redirectionpage

		return "redirect:/products";
	}

}
