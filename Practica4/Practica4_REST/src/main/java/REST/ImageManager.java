/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import App.Image;
import App.User;
import DB.DB;
import DISK.ImageDisk;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 * https://compuarticulos.blogspot.com/2018/08/crear-un-simple-rest-api-con-netbeans-y.html
 * https://oracle-max.com/creando-un-api-rest-con-java-y-netbeans-que-devuelva-un-json-en-su-request/
 * http://joseltoro.blogspot.com/2020/05/crear-un-crud-web-service-rest-json-en.html
 * https://stackoverflow.com/questions/21643724/amdatu-multi-part-form-formparam-is-always-null
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
    * @param username    
    * @param password     
    * @return    
    */
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response login (
            @FormParam("username") String username,
            @FormParam("password") String password)
    {
        Gson gson = new Gson();
        ResponseJSON resp;
        String ret;
        
        if(DB.CheckPassword(username, password)) {
            resp = new ResponseJSON("OK","Login Correct");
            ret =  gson.toJson(resp);            
            return Response.ok(ret,MediaType.APPLICATION_JSON).build();
        }  
        resp = new ResponseJSON("ERROR","Login Incorrect");
        ret =  gson.toJson(resp); 
        return Response.ok(ret,MediaType.APPLICATION_JSON).build(); 
    }
    
    /**    
    * POST method to login a user    
    * @param username    
    * @param password     
    * @return    
    */
    @Path("createUser")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response createUser (
            @FormParam("username") String username,
            @FormParam("password") String password)
    {        
        ResponseJSON resp;
        String ret;
        
        User user = new User(username,password);
        
        if (DB.CheckUser(username)) {
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
    * @param title    
    * @param description     
    * @param keywords         
    * @param author    
    * @param creator    
    * @param capture_date         
    * @return    
    */
    //https://stackoverflow.com/questions/21643724/amdatu-multi-part-form-formparam-is-always-null
    //https://openliberty.io/docs/21.0.0.6/send-receive-multipart-jaxrs.html
    @Path("register")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response registerImage (
            @FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("keywords") String keywords,
            @FormParam("author") String author,
            @FormParam("creator") String creator,
            @FormParam("capture") String capture_date)
    {
        Format f = new SimpleDateFormat("yyyy-MM-dd");
        String storage_date = f.format(new Date()); 
        String filename= "";
        Image image = new Image(
                "",
                title,
                description,
                keywords,
                author,
                creator,
                capture_date,
                storage_date,
                filename
        );
        
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
     * @param id
     * @param title    
     * @param description     
     * @param keywords         
     * @param author    
     * @param creator        
     * @param capture_date        
     * @param storage_date        
     * @return    
     */    
    @Path("modify")    
    @POST    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
    @Produces(MediaType.TEXT_HTML)    
    public Response modifyImage (
            @FormParam("id") String id,
            @FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("keywords") String keywords,
            @FormParam("author") String author,
            @FormParam("creator") String creator,            
            @FormParam("capture") String capture_date,
            @FormParam("storage") String storage_date)
    {
        String filename = "";
        Image image = new Image(
                id,
                title,
                description,
                keywords,
                author,
                creator,
                capture_date,
                storage_date,
                filename
        );
        
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
    * @param id
    * @return 
    */    
    @Path("delete")    
    @POST    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
    @Produces(MediaType.TEXT_HTML)    
    public Response deleteImage (
            @FormParam("id") String id) throws IOException
    {     
        Image img = DB.SearchImageById(String.valueOf(id));
        
        ResponseJSON resp;
        String ret;
        
        if (img != null) {
            System.out.println("Image existeix");
            if(ImageDisk.RemoveFromDisk(img.getFilename())) {
                System.out.println("Image eliminada1");
                if (DB.DeleteImage(img.getId())) {
                    System.out.println("Image eliminada");                       
                    resp = new ResponseJSON("OK","Image deleted");  
                    ret = resp.toJSON();
                    return Response.ok(ret,MediaType.APPLICATION_JSON).build();                                           
                }
            }             
        }
        resp = new ResponseJSON("ERROR","Image not modified");  
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();          
    }
    
    /**    
    * GET method to list images
    * @return
    */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listImages ()
    {
        ResponseJSON resp;
        String ret;        
        List<Image> images = DB.ListImages();        
        resp = new ResponseJSON("OK","Images Listed");  
        resp.setBody(images);
        ret = resp.toJSON();
  
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    }
    
    /**
    * GET method to search images by id    
    * @param id
    * @return
    */
    @Path("searchID/{id}")
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
        resp.setBody(images);
        ret = resp.toJSON();  
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    } 
    
    /**
    * GET method to search images by title
    * @param title
    * @return
    */
    @Path("searchTitle/{title}")
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
        resp.setBody(images);
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
        resp.setBody(images);
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build(); 
    }
    
    /**    
    * GET method to search images by author    
    * @param author         
    * @return    
    */    
    @Path("searchAuthor/{author}")    
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
        resp.setBody(images);
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build(); 
    }        
    
    /**
    * GET method to search images by keyword    
    * @param keywords        
    * @return    
    */    
    @Path("searchKeywords/{keywords}")    
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
        resp.setBody(images);
        ret = resp.toJSON();
        return Response.ok(ret,MediaType.APPLICATION_JSON).build();
    }       
}