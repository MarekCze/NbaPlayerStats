/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Player;
import Model.User;
import Utility.PlayerDeserializer;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdk.nashorn.api.scripting.JSObject;

/**
 *
 * @author Owner
 */
@WebServlet(name = "WordpressController", urlPatterns = {"/WordpressController"})
public class WordpressController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        String menu = request.getParameter("menu");
        
        
        
        switch(menu){
            case "getPosts":
                List<Player> players = new ArrayList();
                players = getAllPlayers(session, request, response);
                sendJsonToClient(response, players);
                session.setAttribute("players", players);
                break;
            case "getUsers":
                List<User> users = new ArrayList();
                users = getAllUsers(session, request, response);
                sendJsonToClient(response, users);
                session.setAttribute("users", users);
                break;
            case "createPost":
                int statusCode = createPost(session, request, response);
                System.out.println("STATUS CODE IS " + statusCode);
                if(statusCode == 201){
                    String message = "New post created successfully";
                    PrintWriter writer = response.getWriter();
                    writer.write(message);
                    writer.flush();
                    writer.close();
                } else {
                    sendJsonToClient(response, "Something went wrong");
                }
                
                break;
            case "createUser":
                createUser(session, request, response);
                break;
        }
        
    }
    
    private <T> void sendJsonToClient(HttpServletResponse response, List<T> list) throws IOException{
        Gson gson = new Gson();
        Type type = new TypeToken<List<T>>(){}.getType();
        String json = gson.toJson(list, type);
        
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }
    
    private void sendJsonToClient(HttpServletResponse response, String message) throws IOException{
        Gson gson = new Gson();
        String json = gson.toJson(message, String.class);
        System.out.println(json);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }
    
    private List<Player> getAllPlayers(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final String urlString = "http://localhost:8082/NBAPlayerStats/wp-json/wp/v2/posts";
        List<Player> players = new ArrayList();
        
        try {

            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            
            
            urlConnection.setRequestProperty("Accept", "application/json");
            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            
            // get type of object used by deserializer
            Type type = new TypeToken<List<Player>>(){}.getType();
            // create new gsonBuilder
            GsonBuilder gsonBuilder = new GsonBuilder();
            // tell it what type adapter to use
            gsonBuilder.registerTypeAdapter(type, new PlayerDeserializer());
            // create gson instance
            Gson gson = gsonBuilder.create();
            // deserialize json response to list of Player objects
            players = gson.fromJson(reader, type);
            
            for(Player p : players){
                System.out.println(p.toString());
            }
            
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());

        }
        
        return players;
    }
    
    private List<User> getAllUsers(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final String urlString = "http://localhost:8082/NBAPlayerStats/wp-json/wp/v2/users";
        List<User> users = new ArrayList();
        
        try {

            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            
            String encodedCredentials = encodeCredentials("marek", "YROE)udD^FLO2DL9Ku");
            System.out.println(encodedCredentials);
            
            urlConnection.setRequestProperty("Authorization", encodedCredentials);
            urlConnection.setRequestProperty("Accept", "application/json");
            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            
            // get type of object used by deserializer
            Type type = new TypeToken<List<User>>(){}.getType();
            // create gson instance
            Gson gson = new Gson();
            // deserialize json response to list of Player objects
            users = gson.fromJson(reader, type);

            for(User u : users){
                System.out.println(u.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());

        }
        return users;
    }
    
    private int createPost(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final String urlString = "http://localhost:8082/NBAPlayerStats/wp-json/wp/v2/posts";
        int statusCode = 0;
        try {

            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            
            // encode basic auth credentials
            String encodedCredentials = encodeCredentials("marek", "YROE)udD^FLO2DL9Ku");
            System.out.println(encodedCredentials);
            
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Authorization", encodedCredentials);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("POST");

            // get request parameters
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            
            // create Player object which will be converted to json
            Player p = new Player(name, description, "publish");
            // convert Player object to json
            Gson gson = new Gson();
            String data = gson.toJson(p);
            System.out.println(data);
            
            // create writer & reader
            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            //OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
            
            // write data to API
            wr.write(data);
            wr.flush();
            wr.close();
            
            statusCode = urlConnection.getResponseCode();
            //System.out.println("RESPONSE CODE:: " + statusCode);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());

        }
        
        return statusCode;
    }
    
        private void createUser(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final String urlString = "http://localhost:8082/NBAPlayerStats/wp-json/wp/v2/posts";
        
        try {

            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            
            // encode basic auth credentials
            String encodedCredentials = encodeCredentials("marek", "YROE)udD^FLO2DL9Ku");
            System.out.println(encodedCredentials);
            
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Authorization", encodedCredentials);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("POST");

            // create Player object which will be converted to json
            Player p = new Player("Alex Carushow","sample description description","publish");
            // convert Player object to json
            Gson gson = new Gson();
            String data = gson.toJson(p);
            System.out.println(data);
            
            // create writer & reader
            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            //OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
            
            // write data to API
            wr.write(data);
            wr.flush();
            wr.close();
            
            int status = urlConnection.getResponseCode();
            System.out.println("RESPONSE CODE:: " + status);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());

        }
    }
    
    private String encodeCredentials(String username, String password){
        String s = username + ":" + password;
        
        return "Basic " + Base64.getEncoder().encodeToString(s.getBytes());
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
