package vn.edu.hcmuaf.freshshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.hcmuaf.freshshop.model.Category;
import vn.edu.hcmuaf.freshshop.model.Product;
import vn.edu.hcmuaf.freshshop.service.CategoryService;
import vn.edu.hcmuaf.freshshop.service.ProductService;

//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("currentPage", "shop");
        List<Category> categories = categoryService.getAllCategory();
        /* Set attribute để in ds phân loại cột bên phải */
        model.addAttribute("categories", categories);

        /* Lấy attribute từ Category Servlet để biết sẽ hiển thị ds sản phẩm nào */
        ArrayList<Product> productsOfCategory = (ArrayList<Product>) model.getAttribute("productsOfCategory");
        /* Lấy category_id từ Category Servlet để phục vụ cho việc lọc theo giá từ các sản phẩm trên trang shop */
        Object category_id = model.getAttribute("category_id");

        if (category_id != null)
            model.addAttribute("category_id", category_id);

        ArrayList<Product> currProductList = (ArrayList<Product>) model.getAttribute("products");
        if (currProductList != null)
            model.addAttribute("products", currProductList);
        else {
            if (productsOfCategory == null)
                currProductList = (ArrayList<Product>) productService.getAllProduct();
            else
                currProductList = productsOfCategory;
            model.addAttribute("products", currProductList);

            /* Lấy attribute từ Search Servlet để biết sẽ hiển thị ds sản phẩm nào */
            ArrayList<Product> productsSearched = (ArrayList<Product>) model.getAttribute("products-searched");
            if (productsSearched != null)
                model.addAttribute("products", productsSearched);
        }
        return "shop";
    }
}