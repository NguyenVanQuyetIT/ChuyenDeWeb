package vn.edu.hcmuaf.freshshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private int id;
    @Column(nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    //    @Column(name="name",nullable = false)
//    private String name;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String email;
    @Column
    private String address;
    @Column(name = "delivery_address")
    private String deliveryAddress;
    @Column(name = "status_id")
    private int statusId;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> user_roles;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String username, String password, String firstname, String lastname, String phone, String email, String address, String deliveryAddress, int statusId) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.deliveryAddress = deliveryAddress;
        this.statusId = statusId;
    }
}
