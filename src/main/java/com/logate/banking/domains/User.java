package com.logate.banking.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name",nullable = false)
    private String fullName;

    @Column(name = "JMBG",nullable = false,unique = true)
    @Size(max = 13,min = 13,message ="JMBG mora imati 13 cifara")
    @Pattern(regexp = "^[0-9]{13}$",message = "JMBG moze sadrzati iskljucivo cifre")
    private String jmbg;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String address;

    @Column(name = "birth_date")
    private Date birthDate = new Date();

    @Column
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "create_time")
    private Date createdAt = new Date();

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties("userList")
    private Role role;

    @OneToMany(mappedBy = "user", targetEntity = BankAccount.class)
    List<BankAccount> bankAccounts = new ArrayList<BankAccount>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
