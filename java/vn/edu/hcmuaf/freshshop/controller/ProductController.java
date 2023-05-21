package vn.edu.hcmuaf.freshshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.hcmuaf.freshshop.abstraction.IProductService;
import vn.edu.hcmuaf.freshshop.modelmapping.ProductModel;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("/")
    public String getAll(Model model) {
        List<ProductModel> productModels = productService.getAllProducts();
        model.addAttribute("productModels", productModels);
        return "index";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable UUID productId, Model model) {
        ProductModel productModel = productService.getProductById(productId);
        model.addAttribute("productModel", productModel);
        return "detail";
    }
}
