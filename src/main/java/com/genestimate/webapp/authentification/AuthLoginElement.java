package com.genestimate.webapp.authentification;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Kais on 04.02.2017.
 */
@XmlRootElement
public class AuthLoginElement implements Serializable {

    private String username;
    private String password;

    public AuthLoginElement() {
    }

    public AuthLoginElement(String username, String password) {
        this.username = username;
        this.password = password;
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
}