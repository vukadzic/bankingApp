package com.logate.banking.domains;

import javax.persistence.*;

@Entity
@Table(name = "user_has_roles")
public class UserHasRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_id")
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public UserHasRoles(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
