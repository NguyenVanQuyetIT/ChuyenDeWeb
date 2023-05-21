package vn.edu.hcmuaf.freshshop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.freshshop.abstraction.IProductService;
import vn.edu.hcmuaf.freshshop.modelmapping.ProductMapping;
import vn.edu.hcmuaf.freshshop.modelmapping.ProductModel;
import vn.edu.hcmuaf.freshshop.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImp implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductModel getProductByName(String name) {
        return ProductMapping.toProductModel(productRepository.findByName(name));
    }

    @Override
    public ProductModel getProductById(UUID id) {
        return ProductMapping.toProductModel(productRepository.findById(id).orElse(null)) ;
    }

    @Override
    public List<ProductModel> getAllProducts() {

        return ProductMapping.toListProduct(productRepository.findAll());
    }


}
