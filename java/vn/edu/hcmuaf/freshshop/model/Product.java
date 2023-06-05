package vn.edu.hcmuaf.freshshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@Table(name="product")
@Entity
public class Product {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    @Column(name="name",nullable = false)
    private  String name;
    @Column(name="price",nullable = false)
    private int price;
    @Column(name = "discounted_price")
    private int discountedPrice;
    @Column(name="category_id")
    private int categoryId;
    @Column(name="description")
    private  String description;
    @OneToMany(mappedBy = "product")
    private List<Image> images;

    @Column(name="status")
    private String status;
    @Column(name="quantity")
    private int quantity;







    @Override
    public String toString() {
        return "id: " + id +
                ", name: " + name +
                ", price: " + price +
                ", discounted price: " + discountedPrice +
                ", category: " + categoryId +
                ", description: " + description +
                ", images: " + images +
                ", status: " + status +
                ", quantity: " + quantity;
    }
}
