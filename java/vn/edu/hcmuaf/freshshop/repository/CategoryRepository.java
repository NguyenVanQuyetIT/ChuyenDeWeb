package vn.edu.hcmuaf.freshshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.freshshop.model.Category;
import vn.edu.hcmuaf.freshshop.model.Product;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
//    List<Category> findAll();
    List<Product> findProductsById(int id);
//    @Query("SELECT SUM(p.quantity) FROM Product p WHERE p.category = :category")
    int countProductsById(int id);
}
