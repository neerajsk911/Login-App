package com.app.login;

import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.InputStream;
import java.sql.*;
import javax.sql.DataSource;
import javax.annotation.PostConstruct;


@Path("/login")
@RequestScoped
public class LoginResource{

    private DatabaseConfig dbs = new DatabaseConfig();
    private DataSource dataSource = dbs.createDataSource();

    @PostConstruct
    private void init() {
        try{
            System.out.println("DataSource URL: " + dataSource.getConnection().getMetaData().getURL());
            System.out.println("DataSource class: " + dataSource.getClass().getName());
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getIndexHtml() {
        return getClass().getResourceAsStream("/index.html");
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)   
    public Response handleLogin(JsonObject jsonRequest) {
        String username = jsonRequest.getString("username");
        String password = jsonRequest.getString("password");
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT PRIVILEGE FROM LOGIN WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                JsonObjectBuilder builder = Json.createObjectBuilder();
                builder.add("privilege",resultSet.getString("privilege"));
                builder.add("message", "Login successful!");
                JsonObject jsonObject = builder.build();
                System.out.println("Succesful Login by User:"+username);
                return Response.ok(jsonObject)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                        .header("Access-Control-Allow-Headers", "Origin, Content-Type, Authorization")
                        .build();
            } else {
                JsonObjectBuilder builder = Json.createObjectBuilder();
                builder.add("message", "Invalid username or password.");
                JsonObject jsonObject = builder.build();
                System.err.println("Failed login for User:"+username);
                return Response.status(Response.Status.UNAUTHORIZED).entity(jsonObject).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
