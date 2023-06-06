package vn.edu.hcmuaf.freshshop.modelmapping;

import vn.edu.hcmuaf.freshshop.model.Product;

import java.util.List;

public class ProductMapping {
//public static Product toProduct(ProductModel productModel){
//    Product product = new Product();
//
//    product.setName(productModel.getName());
//    product.setPrice(productModel.getPrice());
//    product.setDiscountedPrice(productModel.getDiscountedPrice());
//    product.setCategoryId(productModel.getCategoryId());
//    product.setDescription(productModel.getDescription());
//    product.setStatus(productModel.getStatus());
//    product.setQuantity(productModel.getQuantity());
//    return product;
//}
public static ProductModel toProductModel(Product product){
    ProductModel productModel = new ProductModel();
    productModel.setId(product.getId());
    productModel.setName(product.getName());
    productModel.setPrice(product.getPrice());
    productModel.setDiscountedPrice(product.getDiscountedPrice());
    productModel.setCategoryId(product.getCategory().getId());
    productModel.setDescription(product.getDescription());
    productModel.setStatus(product.getStatus());
    productModel.setQuantity(product.getQuantity());
    productModel.setProductImageUrls(product.getImages().stream().map(x -> x.getUrl()).toList());
    return productModel;
}
    public static List<ProductModel> toListProductModel(List<Product> models) {
        return models.stream().map(ProductMapping::toProductModel).toList();
    }
}
