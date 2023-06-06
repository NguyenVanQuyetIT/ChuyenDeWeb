package vn.edu.hcmuaf.freshshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.hcmuaf.freshshop.modelmapping.ProductMapping;
import vn.edu.hcmuaf.freshshop.modelmapping.ProductModel;
import vn.edu.hcmuaf.freshshop.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String getAll(Model model) {
        List<ProductModel> productModels = ProductMapping.toListProductModel(productService.getAllProduct());
        model.addAttribute("productModels", productModels);
        return "index";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id, Model model) {
        ProductModel productModel = ProductMapping.toProductModel(productService.getProductById(id));
        model.addAttribute("productModel", productModel);
        return "detail";
    }
}
