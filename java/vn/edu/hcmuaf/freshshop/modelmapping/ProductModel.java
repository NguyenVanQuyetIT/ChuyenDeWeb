package vn.edu.hcmuaf.freshshop.modelmapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductModel {
    private int id;
    private String name;
    private int price;
    private int discountedPrice;
    private int categoryId;
    private String description;
    private String status;
    private int quantity;
    List<MultipartFile> productImages;
    List<String> productImageUrls;
}
