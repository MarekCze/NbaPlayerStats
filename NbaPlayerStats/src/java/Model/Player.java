/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Owner
 */
public class Player {
    private Integer id;
    @SerializedName(value= "title")
    private String name;
    @SerializedName(value= "content")
    private String description;
    private String link;
    private String status;
    
    public Player(){}
    
    public Player(String name, String description, String status){
        this.name = name;
        this.description = description;
        this.status = status;
    }
    
    public Player(int id, String name, String description, String link, String status){
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
        this.status = status;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }
    
    @Override
    public String toString(){
        return "\nPlayer id: " + this.getId() + "\nPlayer name: " + this.getName() + "\nPlayer description:\n " + this.getDescription()
                + "\nPost link: " + this.getLink() + "\nPost status: " + this.getStatus();
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
