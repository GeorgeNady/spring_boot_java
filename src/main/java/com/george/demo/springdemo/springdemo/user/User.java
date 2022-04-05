package com.george.demo.springdemo.springdemo.user;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    @Id private Long id;
    private String name;
    @Column(nullable = false, unique = true) private String email;
    @Column(unique = true,updatable = true) private String phone;
    private LocalDate dob;
    @Column(name = "hash_password") private String hashPassword;
    @Transient private Integer age;


    /////////////////////////////////////////////////////////////////////////////////////////////////////// CONSTRUCTORS
    public User() {}

    // Y ******************** for create entity to the database
    public User(String name, String email, String phone, LocalDate dob, String hashPassword) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.hashPassword = hashPassword;
    }

    public User(String name, String email, String phone, LocalDate dob, String hashPassword, Integer age) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.hashPassword = hashPassword;
        this.age = age;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////// GETTER AND SETTERS
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public LocalDate getDob() {return dob;}

    public void setDob(LocalDate dob) {this.dob = dob;}

    public Integer getAge() {return Period.between(getDob() ,LocalDate.now()).getYears();}

    public void setAge(Integer age) {this.age = age;}

    public String getHashPassword() {return hashPassword;}

    public void setHashPassword(String hashPassword) {this.hashPassword = hashPassword;}

    /////////////////////////////////////////////////////////////////////////////////////////////////// HELPER FUNCTIONS


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", hashPassword='" + hashPassword + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && name.equals(user.name) && email.equals(user.email) && phone.equals(user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone);
    }

}
