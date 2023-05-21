package vn.edu.hcmuaf.freshshop.modelmapping;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
@Setter


public class ProductModel {
    private UUID id;
    private  String name;
    private int price;
    private int discountedPrice;
    private int categoryId;
    private  String description;
    private String status;
    private int quantity;
    List<MultipartFile> productImages;
    List<String> productImagesUrl;


}
