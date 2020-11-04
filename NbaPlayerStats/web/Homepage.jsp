<%-- 
    Document   : Homepage
    Created on : 31-Oct-2020, 19:40:47
    Author     : marek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Wordpress API Showcase</h1>
        <div class="getPostsDiv">
            <h2>Get All Posts</h2>
            <form method="get" action="WordpressController">
                <input type="button" value="getPosts" id="getPosts">
            </form>
        </div>
        <div class="showPosts">
            <p id="allPosts"></p>
        </div>
        
        <div class="getUsersDiv">
            <h2>Get All Users</h2>
            <form method="get" action="WordpressController">
                <input type="button" value="getUsers" id="getUsers">
            </form>
        </div>
        <div class="showUsers">
            <p id="allUsers"></p>
        </div>
        
        <div class="createPostDiv">
            <h2>Create Post</h2>
            <button class="createPostBtn" value="show">Show</button>
            <button class="createPostBtn" value="hide">Hide</button>
            <form id="createPlayerForm" method="post" action="WordpressController">
                <label for="playerName">Player name:
                    <br>
                    <input type="text" name="playerName" id="playerName">
                    <br>
                </label>
                <label for="playerDescription">Player description:
                    <br>
                    <input type="text" name="playerDescription" id="playerDescription">
                    <br>
                </label>
                <input type="button" value="createPost" id="createPost">
            </form>
        </div>
        
        <div class="updatePostDiv">
            <h2>Update Post</h2>
            <button class="updatePostBtn" value="show">Show</button>
            <button class="updatePostBtn" value="hide">Hide</button>
            <form id="updatePlayerForm" method="put" action="WordpressController">
                <label for="playerId">Player id:
                    <br>
                    <input type="text" name="playerId" id="playerIdUpdate">
                    <br>
                </label>
                <label for="playerName">Player name:
                    <br>
                    <input type="text" name="playerName" id="playerName">
                    <br>
                </label>
                <label for="playerDescription">Player description:
                    <br>
                    <input type="text" name="playerDescription" id="playerDescription">
                    <br>
                </label>
                <input type="submit" name="menu" value="updatePost" id="updatePost">
            </form>
        </div>
        
        <div class="deletePostDiv">
            <h2>Delete Post</h2>
            <button class="deletePostBtn" value="show">Show</button>
            <button class="deletePostBtn" value="hide">Hide</button>
            <form id="deletePlayerForm" method="delete" action="WordpressController">
                <label for="playerId">Player id:
                    <br>
                    <input type="text" name="playerId" id="playerIdDelete">
                    <br>
                </label>
                <input type="submit" name="menu" value="deletePost" id="deletePost">
            </form>
        </div>
        
        
        <div class="createUserDiv">
            <h2>Create User</h2>
            <button class="createUserBtn" value="show">Show</button>
            <button class="createUserBtn" value="hide">Hide</button>
            <form id="createUserForm" method="post" action="WordpressController">
                <label for="username"> Username:
                    <br>
                    <input type="text" name="username" id="username">
                    <br>
                </label>
                <label for="password"> Password:
                    <br>
                    <input type="text" name="password" id="password">
                    <br>
                </label>
                <label for="email">User email:
                    <br>
                    <input type="text" name="email" id="email">
                    <br>
                </label>
                <input type="button" value="createUser" id="createUser">
            </form>
        </div>
        
        <script>
            $(document).ready(function(){            
              // Get all posts  
              $('#getPosts').click(function(){
                  $('#allPosts').empty();
                  const menu = $('#getPosts').val();
                  $.ajax({
                      type:'GET',
                      data:{menu: menu},
                      url:'WordpressController',
                      success: function(result){
                          const resObj = JSON.parse(result);
                          //console.log(resObj);
                          $('.showPosts').show();
                          
                          resObj.forEach(function(player){
                              var table = $("<table></table>");
                                table.append('<th>Game ID</th>');
                                table.append('<th>Points</th>');
                                table.append('<th>Minutes played</th>');
                                table.append('<th>Field Goals Made</th>');
                                table.append('<th>Field Goal Attempts</th>');
                                table.append('<th>Field Goal Percentage</th>');
                                table.append('<th>Free Throws Made</th>');
                                table.append('<th>Free Throw Attempts</th>');
                                table.append('<th>Free Throw Percentage</th>');
                                table.append('<th>Offensive Rebounds</th>');
                                table.append('<th>Defensive Rebounds</th>');
                                table.append('<th>Total Rebounds</th>');
                                table.append('<th>Assists</th>');
                                table.append('<th>Fouls</th>');
                                table.append('<th>Steals</th>');
                                table.append('<th>Blocks</th>');
                                table.append('<th>Plus Minus</th>');
                                
                              const content = '<h2>' + player.title + '</h2>' + player.content;
                              const heading = '<h2>Player stats from the last 5 games (From external API)';
                              player.lastFiveGames.forEach(function(game){
                                  console.log('inside player stats for each')
                                  $('<tr>').append(
                                    $('<td>').text(game.gameId),
                                    $('<td>').text(game.points),
                                    $('<td>').text(game.min),
                                    $('<td>').text(game.fgm),
                                    $('<td>').text(game.fga),
                                    $('<td>').text(game.fgp),
                                    $('<td>').text(game.ftm),
                                    $('<td>').text(game.fta),
                                    $('<td>').text(game.ftp),
                                    $('<td>').text(game.offRed),
                                    $('<td>').text(game.defReb),
                                    $('<td>').text(game.totReb),
                                    $('<td>').text(game.assists),
                                    $('<td>').text(game.pFouls),
                                    $('<td>').text(game.steals),
                                    $('<td>').text(game.blocks),
                                    $('<td>').text(game.plusMinus),
                                  ).appendTo(table);
                              });
                              
                              $('#allPosts').append(content);
                              $('#allPosts').append(heading);
                              $('#allPosts').append(table);
                          });
                      }
                  });
              });
              // Get all users
              $('#getUsers').click(function(){
                  $('#allUsers').empty();
                  const menu = $('#getUsers').val();
                  $.ajax({
                      type:'GET',
                      data:{menu: menu},
                      url:'WordpressController',
                      success: function(result){
                          const resObj = JSON.parse(result);
                          //console.log(resObj);
                          $('.showUsers').show();
                          resObj.forEach(function(user){
                              let content = '<p>User id: ' + user.id + '</p>' + '<p>Username: ' + user.username + '</p>'
                                    + '<p>User description: ' + user.content + '</p>';
                              
                              $('#allUsers').append(content);
                          });
                      }
                  });
              });
              // Create post
              $('#createPost').click(function(){
                  const menu = $('#createPost').val();
                  const name = $('#playerName').val();
                  const description = $('#playerDescription').val();
                  $.ajax({
                      type:'POST',
                      data:{
                          menu: menu,
                          name: name,
                          description: description
                      },
                      url:'WordpressController',
                      success: function(result){
                          console.log(result);
                          // message indicating success/failure of POST request
                          alert(result);
                      }
                  });
              });
              
              // Create user
              $('#createUser').click(function(){
                  const menu = $('#createUser').val();
                  const name = $('#username').val();
                  const password = $('#password').val();
                  const email = $('#email').val();
                  $.ajax({
                      type:'POST',
                      data:{
                          menu: menu,
                          username: name,
                          password: password,
                          email: email
                      },
                      url:'WordpressController',
                      success: function(result){
                          // message indicating success/failure of POST request
                          alert(result);
                      }
                  });
              });
              // show/hide forms
              $(document).on("click", function(event){
                  console.log(event.target.className)
                  const btnClass = event.target.className;
                  switch(btnClass){
                      
                      case 'createPostBtn':
                          if(event.target.value === 'show'){
                              $('#createPlayerForm').show();
                          } else {
                              $('#createPlayerForm').hide();
                          }
                          break;
                      case 'updatePostBtn':
                          if(event.target.value === 'show'){
                              $('#updatePlayerForm').show();
                          } else {
                              $('#updatePlayerForm').hide();
                          }
                          break;
                      case 'deletePostBtn':
                          if(event.target.value === 'show'){
                              $('#deletePlayerForm').show();
                          } else {
                              $('#deletePlayerForm').hide();
                          }
                          break;
                      case 'createUserBtn':
                          if(event.target.value === 'show'){
                              $('#createUserForm').show();
                          } else {
                              $('#createUserForm').hide();
                          }
                          break;
                  }
              })
            });
        </script>
    </body>
</html>
