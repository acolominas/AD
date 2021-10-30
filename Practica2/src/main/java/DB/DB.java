/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import App.Image;
import App.User;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alumne
 */
public class DB {        
        
        static final String dbUrl = "jdbc:derby://localhost:1527/pr2";
        static final String dbUser = "pr2";
        static final String dbPassword = "pr2";
        
        private static void CloseConnection (Connection connection) {
            try {
                if (connection != null) {
                    connection.close();                    
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
       }
            
        private static Connection OpenConnection (Connection connection) { 
            try { 
                if (connection == null) {
                    //Class.forName("org.apache.derby.jdbc.ClientDriver");
                    connection = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
            return connection;
        }
        
        public static Boolean CheckUser (String username) {
            String query;
            PreparedStatement statement;
            Connection connection = null;
            try { 
                query = "SELECT * FROM usuarios WHERE id_usuario = ?";
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                statement.setString(1, username);
                ResultSet rs = statement.executeQuery();
                CloseConnection(connection);
                return rs.next(); 
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                return false;
            }
        }
        
        public static Boolean CheckPassword (String username, String password) {
            String query;
            PreparedStatement statement;
            Connection connection = null;
            try { 
                query = "SELECT * FROM usuarios WHERE id_usuario = ?";
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                statement.setString(1, username);
                ResultSet rs = statement.executeQuery();
                    
                if (rs.next()) {
                    String password1 = rs.getString("password");
                    CloseConnection(connection);
                    return password1.equals(password);
                }
                else {
                    CloseConnection(connection);
                    return false;
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                return false;
            }
        }
        
        public static int GetMaxId(){
            String query;
            PreparedStatement statement;
            Connection connection = null;
            Integer id = 1;
            try { 
                query = "SELECT MAX(id) FROM image";
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();
                    
                if (rs.next()) {    
                    id = rs.getInt(1);
                    CloseConnection(connection);                    
                }
                else {  
                    CloseConnection(connection);
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                return 0;
            }
            return id;        
        } 
        
        public static Boolean CreateUser(User user) {
            String query;
            PreparedStatement statement;
            Connection connection = null;
            try {
                query = "INSERT INTO usuarios VALUES(?,?)";
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);    
                statement.setString(1,user.id_usuario);
                statement.setString(2,user.password);
                statement.executeUpdate();                      
                CloseConnection(connection);              
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                CloseConnection(connection);
                return false;
            }
            return true;
        }
        
        public static List<Image> ListImages() {
            String query;
            PreparedStatement statement;
            Connection connection = null;
            List<Image> images = new ArrayList<Image>();
            try {
                query = "SELECT * FROM image";
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();             
                           
                while(rs.next()) {
                      Image img = new Image(
                              rs.getString("id"),
                              rs.getString("title"),
                              rs.getString("description"),
                              rs.getString("keywords"),
                              rs.getString("author"),
                              rs.getString("creator"),
                              rs.getString("capture_date"),
                              rs.getString("storage_date"),
                              rs.getString("filename")
                      );                      
                      images.add(img);
                }                
                CloseConnection(connection);  
                return images;
                
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                CloseConnection(connection);
            }
            return null;
        }
        
        public static List<Image> SearchImagesByTitle(String title) {
            String query;
            PreparedStatement statement;
            Connection connection = null;
            List<Image> images = new ArrayList<Image>();
            try {
                query = "SELECT * FROM image WHERE title LIKE ?";
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                statement.setString(1, "%" +title+ "%");
                ResultSet rs = statement.executeQuery();      
                           
                while(rs.next()) {
                    Image img = new Image(
                            rs.getString("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("keywords"),
                            rs.getString("author"),
                            rs.getString("creator"),
                            rs.getString("capture_date"),
                            rs.getString("storage_date"),
                            rs.getString("filename")
                    );                      
                    images.add(img);
                }                
                CloseConnection(connection);  
                return images;
                
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                CloseConnection(connection);
            }
            return null;
        }
        
        public static List<Image> SearchImagesByAuthor(String author) {
            String query;
            PreparedStatement statement;
            Connection connection = null;
            List<Image> images = new ArrayList<Image>();
            try {
                query = "SELECT * FROM image WHERE author LIKE ?";
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                statement.setString(1, "%" +author+ "%");
                ResultSet rs = statement.executeQuery();      
                           
                while(rs.next()) {
                    Image img = new Image(
                            rs.getString("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("keywords"),
                            rs.getString("author"),
                            rs.getString("creator"),
                            rs.getString("capture_date"),
                            rs.getString("storage_date"),
                            rs.getString("filename")
                    );                      
                    images.add(img);
                }                
                CloseConnection(connection);  
                return images;
                
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                CloseConnection(connection);
            }
            return null;
        }
        
        public static List<Image> SearchImagesByKeyword(String keyword) {
            String query;
            PreparedStatement statement;
            Connection connection = null;
            List<Image> images = new ArrayList<Image>();
            try {
                query = "SELECT * FROM image WHERE keywords LIKE ?";
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                statement.setString(1, "%" +keyword+ "%");
                ResultSet rs = statement.executeQuery();      
                           
                while(rs.next()) {
                    Image img = new Image(
                            rs.getString("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("keywords"),
                            rs.getString("author"),
                            rs.getString("creator"),
                            rs.getString("capture_date"),
                            rs.getString("storage_date"),
                            rs.getString("filename")
                    );                      
                    images.add(img);
                }                
                CloseConnection(connection);  
                return images;
                
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                CloseConnection(connection);
            }
            return null;
        }
        
        public static Image SearchImageById(String id) {
            String query;
            PreparedStatement statement;
            Connection connection = null;  
            Image image = null;
            try {
                query = "SELECT * FROM image WHERE id = ?";
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                statement.setString(1, id);
                ResultSet rs = statement.executeQuery();             
                           
                while(rs.next()) {
                     image = new Image(
                            rs.getString("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("keywords"),
                            rs.getString("author"),
                            rs.getString("creator"),
                            rs.getString("capture_date"),
                            rs.getString("storage_date"),
                            rs.getString("filename")                                 
                    );
                }                
                CloseConnection(connection);                  
                return image;
                
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                CloseConnection(connection);
            }
            return null;
        }
        
        public static void ModifyImage(Image img) {
            String query;
            PreparedStatement statement;
            Connection connection = null;  
            Image image = null;
            try {
                query = "UPDATE IMAGE SET TITLE = ?,DESCRIPTION = ?, KEYWORDS = ?, AUTHOR = ?, CREATOR = ?, CAPTURE_DATE = ?, STORAGE_DATE = ?, FILENAME = ?"
                    + "WHERE id = ?";
                
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                statement.setString(1, img.title);
                statement.setString(2, img.description);
                statement.setString(3, img.keywords);
                statement.setString(4, img.author);
                statement.setString(5, img.creator);
                statement.setString(6, img.capture_date);
                statement.setString(7, img.storage_date);
                statement.setString(8, img.filename);
                statement.setString(9, img.id);
                statement.executeUpdate();        
                CloseConnection(connection);                  
                
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                CloseConnection(connection);
            }
        }
        
        public static void CreateImage(Image img) {
            String query;
            PreparedStatement statement;
            Connection connection = null;  
            Image image = null;
            try {
                
                query = "INSERT INTO IMAGE (TITLE, DESCRIPTION, KEYWORDS, AUTHOR, CREATOR, CAPTURE_DATE, STORAGE_DATE, FILENAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                statement.setString(1, img.title);
                statement.setString(2, img.description);
                statement.setString(3, img.keywords);
                statement.setString(4, img.author);
                statement.setString(5, img.creator);
                statement.setString(6, img.capture_date);
                statement.setString(7, img.storage_date);
                statement.setString(8, img.filename);
                statement.executeUpdate();        
                CloseConnection(connection);                  
                
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                CloseConnection(connection);
            }
        }
        
        public static void DeleteImage(String id) {
            String query;
            PreparedStatement statement;
            Connection connection = null;  
            try {
                query = "DELETE FROM image WHERE id = ?";                
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                statement.setString(1, id);
                statement.executeUpdate();        
                CloseConnection(connection);                  
                
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                CloseConnection(connection);
            }
        }
    
        
}
