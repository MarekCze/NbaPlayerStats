/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Player;
import Model.PlayerStats;
import Utility.PlayerDeserializer;
import Utility.PlayerStatsDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Owner
 */
public class NbaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private final String nbaApiUrl = "https://rapidapi.p.rapidapi.com/";
    
    public List<PlayerStats> getLastFiveGames(List<PlayerStats> playerStats){
        List<PlayerStats> lastFiveGames = new ArrayList();
        
        for(int i = 1; i <=5; i++){
            lastFiveGames.add(playerStats.get(playerStats.size() - i));
        }
        
        return lastFiveGames;
    }
    
    public List<PlayerStats> getPlayerStats(String name){
        String playerId = getPlayerByLastName(name);
        System.out.println("PLAYER ID IS ::: " + playerId);
        List<PlayerStats> playerStats = getPlayerById(playerId);
        
        return playerStats;
    } 
    
    public String getPlayerByLastName(String playerName){
        Map<String, String> nameMap = splitName(playerName);
        System.out.println("PLAYER NAME ::: " + nameMap.get("lastName"));
        final String urlString = this.getNbaApiUrl() + "/players/lastName/" + nameMap.get("lastName");
        String playerId = "";
        
        try {

            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                       
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("x-rapidapi-key", "ee267c3466mshee30e9de121e722p1ea4b0jsn77436676e20f");
            urlConnection.setRequestProperty("x-rapidapi-host", "api-nba-v1.p.rapidapi.com");
            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            
            Gson gson = new Gson();
            
            JsonElement json = gson.fromJson(reader, JsonElement.class);
            JsonObject job = json.getAsJsonObject().get("api").getAsJsonObject();
            
            JsonArray ja = json.getAsJsonObject().get("api").getAsJsonObject().get("players").getAsJsonArray();
            
            for(JsonElement je : ja){
                JsonObject jo = je.getAsJsonObject();
                String name = jo.get("firstName").getAsString();
                if(nameMap.get("firstName").equalsIgnoreCase(name)){
                    playerId = jo.get("playerId").getAsString();
                }
            }
            
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());

        }
        
        return playerId;
    }
    
    public List<PlayerStats> getPlayerById(String playerId){
        List<PlayerStats> playerStats = new ArrayList();
        final String urlString = this.getNbaApiUrl() + "/statistics/players/playerId/" + playerId;
        
        try {

            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                       
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("x-rapidapi-key", "ee267c3466mshee30e9de121e722p1ea4b0jsn77436676e20f");
            urlConnection.setRequestProperty("x-rapidapi-host", "api-nba-v1.p.rapidapi.com");
            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            
            // get type of object used by deserializer
            Type type = new TypeToken<List<PlayerStats>>(){}.getType();
            // create new gsonBuilder
            GsonBuilder gsonBuilder = new GsonBuilder();
            // tell it what type adapter to use
            gsonBuilder.registerTypeAdapter(type, new PlayerStatsDeserializer());
            // create gson instance
            Gson gson = gsonBuilder.create();
            // deserialize JSON response to list of Player objects
            playerStats = gson.fromJson(reader, type);
            
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());

        }
        
        return playerStats;
    }
    
    private Map<String,String> splitName(String name){
        String[] nameArray = name.split(" ");
        Map<String, String> nameMap = new HashMap();
        
        if(nameArray.length == 2){
            nameMap.put("firstName", nameArray[0]);
            nameMap.put("lastName", nameArray[1]);
        }
       
        return nameMap;
    }
    
    public String getNbaApiUrl() {
        return nbaApiUrl;
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NbaController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NbaController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
