package vn.edu.hcmuaf.freshshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.freshshop.db.DBConnect;
import vn.edu.hcmuaf.freshshop.model.Category;
import vn.edu.hcmuaf.freshshop.model.Product;
import vn.edu.hcmuaf.freshshop.repository.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

//    public List<Product> getProductsById(int category_id) {
//        return categoryRepository.findProductsById(category_id);
//    }

    public int getAmountProductById(int id) {
        return categoryRepository.countProductsById(id);
    }
}