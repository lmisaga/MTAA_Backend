package com.sclad.scladapp.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class User extends AbstractEntity {

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Transient
    private String passwordConfirm; //will not be serialized

    @Column
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_roles", joinColumns = { @JoinColumn(name="user_id") }, inverseJoinColumns = { @JoinColumn(name="role_id") })
    private Set<Role> roles; //manual table creation using liquibase was required

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
