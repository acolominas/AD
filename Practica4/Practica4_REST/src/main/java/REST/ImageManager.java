/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import REST.Security.RestSecurityFilter;
import App.Image;
import App.User;
import DB.DB;
import DISK.ImageDisk;
import REST.Security.Secured;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author alumne
 */



@Path("rest")
public class ImageManager {  
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ImageManager
     */
    public ImageManager() {
    }      
    
     /**    
    * POST method to login a user    
    * @param user    
    * @return    
    */
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user)
    {
        ResponseJSON resp;
        String ret;
        
        if(DB.CheckPassword(user.getUsername(), user.getPassword())) {
            String token = issueToken(user.getUsername());
            resp = new ResponseJSON("OK","Login Correct");
            resp.body = "Bearer " + token;
            ret =  resp.toJSON(); 
                  
            return Response.ok(ret,MediaType.APPLICATION_JSON).build();
        }  
        resp = new ResponseJSON("ERROR","Login Incorrect");
        ret =  resp.toJSON();
        return Response.status(Response.Status.UNAUTHORIZED).build();        
    }
    
    /**    
    * POST method to login a user    
    * @param user   
    * @return    
    */
    @Path("createUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser (User user)
    {        
        ResponseJSON resp;
        String ret;        
          
        if (DB.CheckUser(user.getUsername())) {
            resp = new ResponseJSON("ERROR","Duplicated username");  
            ret = resp.toJSON();
            return Response.ok(ret,MediaType.APPLICATION_JSON).build();
            
        }
        if (DB.CreateUser(user)) {
            resp = new ResponseJSON("OK","User created");  
            ret = resp.toJSON();
            return Response.ok(ret,MediaType.APPLICATION_JSON).build();
        }
        resp = new ResponseJSON("ERROR","User not created");  
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();       
    }   
    
    
    
    /**    
    * POST method to register a new image    
    * @param image         
    * @return    
    */
    @Path("register")
    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerImage (Image image)
    {
        Format f = new SimpleDateFormat("yyyy-MM-dd");
        String storage_date = f.format(new Date()); 
        image.setId("");
        image.setStorage_date(storage_date);        
        
        ResponseJSON resp;
        String ret;
        
        if(DB.CreateImage(image)){
            resp = new ResponseJSON("OK","Image registered");  
            ret = resp.toJSON();
            return Response.ok(ret,MediaType.APPLICATION_JSON).build();  
        }
        resp = new ResponseJSON("ERROR","Image not registered");  
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    }
    
    /**    
     * POST method to modify an existing image     
     * @param image      
     * @return    
     */    
    @Path("modify")
    @Secured
    @POST    
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)   
    public Response modifyImage (Image image)
    {             
        ResponseJSON resp;
        String ret;
        
        if(DB.ModifyImage(image)) {
            resp = new ResponseJSON("OK","Image modified");  
            ret = resp.toJSON();
            return Response.ok(ret,MediaType.APPLICATION_JSON).build(); 
        }
        resp = new ResponseJSON("ERROR","Image not registered");  
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    }
    
    /**    
    * POST method to delete an existing image
    * @param query 
    * @return 
    */    
    @Path("delete")
    @Secured    
    @POST    
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)   
    public Response deleteImage (QueryJSON query)
    {    
        ResponseJSON resp = new ResponseJSON("ERROR","Image not deleted");
        String ret;
        try {
            String image_id = query.image_id;
            Image img = DB.SearchImageById(String.valueOf(image_id));            
            
            if (img != null) {                
                if(ImageDisk.RemoveFromDisk(img.getFilename())) {                    
                    if (DB.DeleteImage(img.getId())) {                                              
                        resp = new ResponseJSON("OK","Image deleted");                                      
                    }
                }             
            }              
            
        }catch (IOException e){
            ret = resp.toJSON();
            return Response.ok(ret,MediaType.APPLICATION_JSON).build();
        }
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    }
    
    /**    
    * GET method to list images
    * @return
    */
    @Path("list")
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listImages ()
    {
        ResponseJSON resp;
        String ret;        
        List<Image> images = DB.ListImages();        
        resp = new ResponseJSON("OK","Images Listed");  
        resp.body = images;      
        ret = resp.toJSON();
  
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    }
    
     /**
    * GET method to search images by id    
    * @return
    */
    //@Path("searchImages/{title}/{description}/{keywords}/{author}/{creator}/{capture_date}/{storage_date}")
    @Path("searchImages")
    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchImages (Image image)
    {
        ResponseJSON resp;
        String ret;
         //List<Image> images = DB.SearchImages("test","an image","Keyword21","Silvia","Silvia","2021","2021");
        List<Image> images = DB.SearchImages(image.getTitle(),image.getDescription(), image.getKeywords(), image.getAuthor(), image.getCreator(), image.getCapture_date(), image.getStorage_date());
        
        if (images.isEmpty()) {
            resp = new ResponseJSON("ERROR","Images not found");
        } 
        else {
            resp = new ResponseJSON("OK","Images Listed"); 
            resp.body = images;
        }
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    }
    
    /**
    * GET method to search images by id    
    * @param id
    * @return
    */
    @Path("searchID/{id}")
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByID (
            @PathParam("id") int id)
    {
               
        ResponseJSON resp;
        String ret;
        
        Image image = DB.SearchImageById(String.valueOf(id));
        if (image == null) {
            resp = new ResponseJSON("ERROR","Image not found");
        } 
        else {
            resp = new ResponseJSON("OK","Image Listed");            
        }
        List<Image> images = new ArrayList<Image>();        
        images.add(image);         
        resp.body = images;
        ret = resp.toJSON();  
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    } 
    
    /**
    * GET method to search images by title
    * @param title
    * @return
    */
    @Path("searchTitle/{title}")
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByTitle (
        @PathParam("title") String title)
    {
        ResponseJSON resp;
        String ret;
        
        List<Image> images = DB.SearchImagesByTitle(title);
        
        if (images.isEmpty()) {
            resp = new ResponseJSON("ERROR","Images not found");
        } 
        else {
            resp = new ResponseJSON("OK","Images Listed");            
        }        
        resp.body = images;
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    }
           
    /**
    * GET method to search images by creation date. Date format should be      
    * yyyy-mm-dd
    * @param date
    * @return
    */
    @Path("searchCreationDate/{date}")
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)    
    public Response searchByCreationDate (
            @PathParam("date") String date)
    {
        ResponseJSON resp;
        String ret;
        
        List<Image> images = DB.SearchImagesByCreaDate(date);
        
        if (images.isEmpty()) {
            resp = new ResponseJSON("ERROR","Images not found");
        } 
        else {
            resp = new ResponseJSON("OK","Images Listed");            
        }        
        resp.body = images;
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build(); 
    }
    
    /**    
    * GET method to search images by author    
    * @param author         
    * @return    
    */    
    @Path("searchAuthor/{author}")
    @Secured    
    @GET        
    @Produces(MediaType.APPLICATION_JSON)    
    public Response searchByAuthor (
            @PathParam("author") String author)
    {
        
        ResponseJSON resp;
        String ret;
        
        List<Image> images = DB.SearchImagesByAuthor(author);
        
        if (images.isEmpty()) {
            resp = new ResponseJSON("ERROR","Images not found");
        } 
        else {
            resp = new ResponseJSON("OK","Images Listed");            
        }        
        resp.body = images;
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build(); 
    }        
    
    /**
    * GET method to search images by keyword    
    * @param keywords        
    * @return    
    */    
    @Path("searchKeywords/{keywords}") 
    @Secured
    @GET        
    @Produces(MediaType.APPLICATION_JSON)    
    public Response searchByKeywords (
            @PathParam("keywords") String keywords)
    {
        ResponseJSON resp;
        String ret;
        
        List<Image> images = DB.SearchImagesByKeyword(keywords);
        
        if (images.isEmpty()) {
            resp = new ResponseJSON("ERROR","Images not found");
        } 
        else {
            resp = new ResponseJSON("OK","Images Listed");            
        }        
        resp.body = images;
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    } 
    
    /**
    * POST method to upload an image to disk
    * @param query       
    * @return    
    */    
    @Path("uploadImage")
    @Secured
    @POST   
    @Consumes(MediaType.APPLICATION_JSON)  
    @Produces(MediaType.APPLICATION_JSON)    
    public Response uploadImage (QueryJSON query)
    {
        ResponseJSON resp = new ResponseJSON("ERROR","Image not uploaded to disk");
        String ret;                         
        String filename = query.filename;
        String image = query.image;
        byte[] img = Base64.getDecoder().decode(image.getBytes());
        if(ImageDisk.SaveToDisk(img, filename)){
            resp = new ResponseJSON("OK","Image uploaded to disk");
        }              
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    }
    
    /**
    * POST method to remove an image from disk      
     * @param query
    * @return    
    */    
    @Path("removeImage")
    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)  
    @Produces(MediaType.APPLICATION_JSON)    
    public Response removeImage (QueryJSON query)  
    {     
        ResponseJSON resp = new ResponseJSON("ERROR","Image not removed from disk");
        String ret;    
        try {
            String id = query.image_id;
            Image image = DB.SearchImageById(id);
            if(ImageDisk.RemoveFromDisk(image.getFilename())){
                resp = new ResponseJSON("OK","Image removed from disk");
            }
                        
        } catch(IOException e){
            ret = resp.toJSON();
            return Response.ok(ret,MediaType.APPLICATION_JSON).build();
        }
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    }
    
    
    /**
    * POST method to download an image from disk 
    * @param query     
    * @return    
    */    
    @Path("downloadImage")
    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)  
    @Produces(MediaType.APPLICATION_JSON)    
    public Response downloadImage (QueryJSON query) 
    {
        ResponseJSON resp = new ResponseJSON("ERROR","Image not downloaded");
        String ret;
        
        try {               
            Image image = DB.SearchImageById(query.image_id);
            String filename = image.getFilename();
            byte[] img = ImageDisk.GetFromDisk(filename);   
            String base64String = Base64.getEncoder().encodeToString(img);                        
            resp = new ResponseJSON("OK","Image downloaded");
            resp.body = base64String;                                
        } catch(IOException e){            
            ret = resp.toJSON();              
            return Response.ok(ret,MediaType.APPLICATION_JSON).build();
        }
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    }
    
    private String issueToken(String login) {
    	//Calculamos la fecha de expiración del token (1 hora de sesion)
    	Date issueDate = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(issueDate);
    	calendar.add(Calendar.MINUTE, 60);
        Date expireDate = calendar.getTime();
        
        //Creamos el token
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer("http://localhost:8080/Practica4_REST")
                .setIssuedAt(issueDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, RestSecurityFilter.KEY)
                .compact();
        return jwtToken;
    }      
}