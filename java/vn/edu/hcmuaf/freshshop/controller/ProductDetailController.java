package vn.edu.hcmuaf.freshshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.hcmuaf.freshshop.model.Product;
import vn.edu.hcmuaf.freshshop.service.ProductService;

@Controller
public class ProductDetailController {
    @Autowired
    ProductService productService;
    
    @GetMapping("/product-detail")
    protected String productDetail(Model model, @RequestParam("id") String productId) {
        int id = Integer.parseInt(productId);
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "shop-detail";
    }
}
