package com.revature.ExpenseReport.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appUsers")
@Data
@NoArgsConstructor
public class AppUser {
    //fields
    @Id
    @GeneratedValue
    private Long userid;

    @Column(unique=true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "user_role")
    private String userRole;

    //constructors
    public AppUser(String username, String password, String userRole){
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    //methods

}
