package vn.edu.hcmuaf.freshshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Table(name = "product")
@Entity
public class Product {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;
    @Column(name = "discounted_price")
    private int discountedPrice;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column
    private String description;
    @OneToMany(mappedBy = "product")
    private List<Image> images;
    @Column
    private String status;
    @Column
    private int quantity;
}
