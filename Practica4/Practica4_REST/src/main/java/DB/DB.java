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
                if (rs.next()) {
                    CloseConnection(connection);
                    return true;
                }
                CloseConnection(connection);                
                return false; 
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
                statement.setString(1,user.getUsername());
                statement.setString(2,user.getPassword());
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
        
        public static List<Image> SearchImages(String title, String  description, String  keywords, String author, String creator, String capture_date, String storage_date) { 
            String query;
            PreparedStatement statement;
            Connection connection = null;
            List<Image> images = new ArrayList<Image>();
            try {
                query = "SELECT * FROM image WHERE";
                if(!title.isEmpty()) query = query + " title LIKE  ? AND";
                if(!description.isEmpty()) query = query + " description LIKE  ? AND";
                if(!keywords.isEmpty()) query = query + " keywords LIKE  ? AND";
                if(!author.isEmpty()) query = query + " author LIKE  ? AND";
                if(!creator.isEmpty()) query = query + " creator LIKE  ? AND";
                if(!capture_date.isEmpty()) query = query + " capture_date LIKE  ? AND";
                if(!storage_date.isEmpty()) query = query + " storage_date LIKE  ? AND";
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query.substring(0,query.length()-4));
                int i = 1;
                if(!title.isEmpty()) statement.setString(i++, "%" +title+ "%");
                if(!description.isEmpty()) statement.setString(i++, "%" +description+ "%");
                if(!keywords.isEmpty()) statement.setString(i++, "%" +keywords+ "%");
                if(!author.isEmpty()) statement.setString(i++, "%" +author+ "%");
                if(!creator.isEmpty()) statement.setString(i++, "%" +creator+ "%");
                if(!capture_date.isEmpty()) statement.setString(i++, "%" +capture_date+ "%");
                if(!storage_date.isEmpty()) statement.setString(i++, "%" +storage_date+ "%");
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
        
        public static List<Image> SearchImagesByCreaDate(String creaDate) {
            String query;
            PreparedStatement statement;
            Connection connection = null;
            List<Image> images = new ArrayList<Image>();
            try {
                query = "SELECT * FROM image WHERE storage_date LIKE ?";
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                statement.setString(1, "%" +creaDate+ "%");
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
                                                      
                if(rs.next()) {
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
        
        public static Boolean ModifyImage(Image img) {
            String query;
            PreparedStatement statement;
            Connection connection = null;  
            Image image = null;
            try {
                query = "UPDATE IMAGE SET TITLE = ?,DESCRIPTION = ?, KEYWORDS = ?, AUTHOR = ?, CREATOR = ?, CAPTURE_DATE = ?, STORAGE_DATE = ?, FILENAME = ?"
                    + "WHERE id = ?";
                
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                statement.setString(1, img.getTitle());
                statement.setString(2, img.getDescription());
                statement.setString(3, img.getKeywords());
                statement.setString(4, img.getAuthor());
                statement.setString(5, img.getCreator());
                statement.setString(6, img.getCapture_date());
                statement.setString(7, img.getStorage_date());
                statement.setString(8, img.getFilename());
                statement.setString(9, img.getId());
                statement.executeUpdate();        
                CloseConnection(connection);
                return true;
                
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                CloseConnection(connection);
                return false;
            }
        }
        
        public static Boolean CreateImage(Image img) {
            String query;
            PreparedStatement statement;
            Connection connection = null;  
            Image image = null;
            try {
                
                query = "INSERT INTO IMAGE (TITLE, DESCRIPTION, KEYWORDS, AUTHOR, CREATOR, CAPTURE_DATE, STORAGE_DATE, FILENAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                
                connection = OpenConnection(connection);
                statement = connection.prepareStatement(query);
                statement.setString(1, img.getTitle());
                statement.setString(2, img.getDescription());
                statement.setString(3, img.getKeywords());
                statement.setString(4, img.getAuthor());
                statement.setString(5, img.getCreator());
                statement.setString(6, img.getCapture_date());
                statement.setString(7, img.getStorage_date());
                statement.setString(8, img.getFilename());
                statement.executeUpdate();        
                CloseConnection(connection);
                return true;
                
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                CloseConnection(connection);
                return false;
            }
        }
        
        public static Boolean DeleteImage(String id) {
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
                return true;
                
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                CloseConnection(connection);
            }
            return false;
        }
    
        
}
