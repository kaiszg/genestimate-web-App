package com.genestimate.webapp.model;

import javax.persistence.*;

/**
 * Created by Kais on 04.02.2017.
 */

@Entity
@Table(name = "USER", schema = "PUBLIC")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "AUTH_TOKEN")
    private String authToken;

    @Column(name = "AUTH_ROLE")
    private String authRole;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT", referencedColumnName = "ID")
    private Client client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthRole() {
        return authRole;
    }

    public void setAuthRole(String authRole) {
        this.authRole = authRole;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
