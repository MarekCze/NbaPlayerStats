/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Model.Player;
import Model.PlayerStats;
import com.google.gson.Gson;
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

// Custom GSON deserializer that generates PlayerStats objects from JSON tree
public class PlayerStatsDeserializer implements JsonDeserializer<List<PlayerStats>>{

    @Override
    public List<PlayerStats> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject().get("api").getAsJsonObject();
        JsonArray ja = jo.get("statistics").getAsJsonArray();
        
        List<PlayerStats> playerStatsList = new ArrayList();
        
        Gson gson = new Gson();        
        playerStatsList = gson.fromJson(ja, typeOfT);
        
        return playerStatsList;
    }
    
}
