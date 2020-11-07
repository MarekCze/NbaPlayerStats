/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Controller.NbaController;
import Model.Player;
import Model.PlayerStats;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author marek
 */

// Custom GSON deserializer that generates Player objects from JSON tree
public class PlayerDeserializer implements JsonDeserializer<List<Player>>{

    @Override
    public List<Player> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray ja = json.getAsJsonArray();
        List<Player> playerList = new ArrayList();
        
        for(JsonElement je : ja){
            JsonObject jo = je.getAsJsonObject();
            Player p = new Player(
                jo.get("id").getAsInt(),
                jo.get("title").getAsJsonObject().get("rendered").getAsString(),
                jo.get("content").getAsJsonObject().get("rendered").getAsString(),
                jo.get("link").getAsString(),
                jo.get("status").getAsString()
            );
            
            NbaController nbaController = new NbaController();
            List<PlayerStats> playerStats = nbaController.getPlayerStats(p.getName());
            
            if(playerStats != null){
                p.setPlayerStats(playerStats);
                p.setLastFiveGames(nbaController.getLastFiveGames(p.getPlayerStats()));
            }
            
            playerList.add(p);
        }
        
        return playerList;
    }
    
}
