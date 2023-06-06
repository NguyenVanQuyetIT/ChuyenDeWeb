package vn.edu.hcmuaf.freshshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.hcmuaf.freshshop.model.Product;
import vn.edu.hcmuaf.freshshop.service.CategoryService;
import vn.edu.hcmuaf.freshshop.service.ProductService;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/category")
    protected String category(Model model, @RequestParam(name = "id") String categoryId) {
        int id = Integer.parseInt(categoryId);
        List<Product> products = productService.getProductsByCategoryId(id);
        model.addAttribute("category_id", id);
        model.addAttribute("productsOfCategory", products);
        return "redirect:/shop";
    }
}
