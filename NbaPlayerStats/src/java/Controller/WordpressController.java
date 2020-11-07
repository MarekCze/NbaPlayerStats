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
import javax.servlet.RequestDispatcher;
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
    
    private final String wpUrl = "http://localhost:8082/NBAPlayerStats/wp-json/wp/v2/";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String menu = request.getParameter("menu");
        int statusCode = 0;
        
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
                statusCode = createPost(session, request, response);
                System.out.println("STATUS CODE IS " + statusCode);
                if(statusCode == 201){
                    String message = "New post created successfully";
                    PrintWriter writer = response.getWriter();
                    writer.write(message);
                    writer.flush();
                    writer.close();
                } else {
                    String message = "Something went wrong";
                    PrintWriter writer = response.getWriter();
                    writer.write(message);
                    writer.flush();
                    writer.close();
                }
                
                break;               
            case "updatePost":
                statusCode = updatePost(session, request, response);
                System.out.println("STATUS CODE IS " + statusCode);
                gotoPage("/Homepage.jsp", request, response);          
                break;
            case "deletePost":
                System.out.println("INSIDE DELETE POST CASE");
                statusCode = deletePost(session, request, response);
                System.out.println("STATUS CODE IS " + statusCode);
                gotoPage("/Homepage.jsp", request, response);         
                break;
            case "createUser":
                statusCode = createUser(session, request, response);
                System.out.println("STATUS CODE IS " + statusCode);
                if(statusCode == 201){
                    String message = "New user created successfully";
                    PrintWriter writer = response.getWriter();
                    writer.write(message);
                    writer.flush();
                    writer.close();
                } else {
                    String message = "Something went wrong";
                    PrintWriter writer = response.getWriter();
                    writer.write(message);
                    writer.flush();
                    writer.close();
                }
                break;
        }
        
    }
    
    // SEND JSON RESPONSE BACK TO AJAX CALLBACK
    private <T> void sendJsonToClient(HttpServletResponse response, List<T> list) throws IOException{
        Gson gson = new Gson();
        // get type of object to convert to JSON
        Type type = new TypeToken<List<T>>(){}.getType();
        // convert to JSON
        String json = gson.toJson(list, type);
        
        // send to AJAX
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }
    
    // GET ALL PLAYERS
    private List<Player> getAllPlayers(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final String urlString = this.getWpUrl() + "posts";
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
            // deserialize JSON response to list of Player objects
            players = gson.fromJson(reader, type);
            
            reader.close();
            

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());

        }
        
        return players;
    }
    
    // GET ALL USERS
    private List<User> getAllUsers(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final String urlString = this.getWpUrl() + "users";
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
            // deserialize JSON response to list of Player objects
            users = gson.fromJson(reader, type);

            System.out.println("*****   USER DATA   *****");
            for(User u : users){
                System.out.println(u.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());

        }
        return users;
    }
    
    // CREATE POST
    private int createPost(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final String urlString = this.getWpUrl() + "posts";
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

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());

        }
        
        return statusCode;
    }
    
    // UPDATE POST
    private int updatePost(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final String urlString = this.getWpUrl() + "posts/" + request.getParameter("playerId");
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
            urlConnection.setRequestMethod("PUT");

            // get request parameters
            String name = request.getParameter("playerName");
            String description = request.getParameter("playerDescription");
            
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

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());

        }
        
        return statusCode;
    }
    
    // DELETE POST
    private int deletePost(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final String urlString = this.getWpUrl() + "posts/" + request.getParameter("playerId");
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
            urlConnection.setRequestMethod("DELETE");
            
            statusCode = urlConnection.getResponseCode();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());

        }
        
        return statusCode;
    }
    
    // CREATE USER
    private int createUser(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final String urlString = this.getWpUrl() + "users";
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
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            // create Player object which will be converted to json
            User u = new User(username,password,email);
            // convert Player object to json
            Gson gson = new Gson();
            String data = gson.toJson(u);
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

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());

        }
        return statusCode;
    }
    
    private String encodeCredentials(String username, String password){
        String s =  username + ":" + password;
        
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

    /**
     * @return the wpUrl
     */
    public String getWpUrl() {
        return wpUrl;
    }
    
    private void gotoPage(String url,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}
