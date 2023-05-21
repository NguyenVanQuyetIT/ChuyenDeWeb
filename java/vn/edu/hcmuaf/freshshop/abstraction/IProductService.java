package vn.edu.hcmuaf.freshshop.abstraction;



import vn.edu.hcmuaf.freshshop.modelmapping.ProductModel;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    ProductModel getProductByName(String name);
    ProductModel getProductById(UUID id);
    List<ProductModel> getAllProducts();



}
