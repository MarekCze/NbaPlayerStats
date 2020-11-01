/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author marek
 */
public class User {   
    private Integer id;
    @SerializedName(value= "username",alternate="name")
    private String username;
    private String password;
    private String email;
    
    public User(){}
    
    public User(int id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
    }
    
    public User(String username, String password, String email){       
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the description
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param description the description to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString(){
        return "\nUser ID: " + this.getId() + "\nUsername: " + this.getUsername() + "\nPassword: " + this.getPassword() + "\nUser email: " + this.getEmail();
    }
}
