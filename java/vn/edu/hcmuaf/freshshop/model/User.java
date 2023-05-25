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
public class User {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    @Column(name="email",nullable = false)
    private String email;
    @Column(name="password",nullable = false)
    private String password;
//    @Column(name="name",nullable = false)
//    private String name;
    @Column(name="firstname",nullable = false)
    private String firstname;
    @Column(name="lastname",nullable = false)
    private String lastname;
    @Column(name="phone",nullable = false)
    private String phone;
    @Column(name="address")
    private String address;
    @Column(name="delivery_address")
    private String deliveryAddress;
    @Column(name="status_id")
    private int statusId;
    @Column(name = "user_type", nullable = true)
    private int userType;
    private boolean isEmailVerified;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> user_roles;



    public User(String email, String password) {

        this.email = email;
        this.password = password;
    }

    public boolean isEnabled() {
        return isEmailVerified;
    }


//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
