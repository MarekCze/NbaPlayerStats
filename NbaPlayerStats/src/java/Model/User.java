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
    @SerializedName(value= "name")
    private String username;
    private String description;
    
    public User(){}
    
    public User(int id, String username, String description){
        this.id = id;
        this.username = username;
        this.description = description;
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
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString(){
        return "\nUser ID: " + this.getId() + "\nUsername: " + this.getUsername() + "\nUser description: " + this.getDescription();
    }
}
