/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creditcardfx;

/**
 *
 * @author user
 */
public class Admin {
    String name,username,password;

    public Admin() {
    }

    public Admin(String name, String username, String password) {
        setName(name);
        setUsername(username);
        setPassword(password);
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
