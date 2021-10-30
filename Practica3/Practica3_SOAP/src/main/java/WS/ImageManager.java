/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import App.Image;
import App.User;
import DB.DB;
import DISK.ImageDisk;
import java.io.IOException;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
//import javax.servlet.http.Part;

/**
 *
 * @author alumne
 */
@WebService(serviceName = "ImageManager")
public class ImageManager {

    /**
     * Web service operation
     * @param image
     * @return 
     */
    @WebMethod(operationName = "RegisterImage")
    public Integer RegisterImage(@WebParam(name = "image") Image image) {
        //TODO write your implementation code here:
        if(DB.CreateImage(image)) return 1;
        return 0;
    }

    /**
     * Web service operation
     * @param image
     * @return 
     */
    @WebMethod(operationName = "ModifyImage")
    public Integer ModifyImage(@WebParam(name = "image") Image image) {
        //TODO write your implementation code here:
        if (DB.ModifyImage(image)) return 1;
        return 0;
    }

    /**
     * Web service operation
     * @param id
     * @return 
     * @throws java.io.IOException 
     */
    @WebMethod(operationName = "DeleteImage")
    public Integer DeleteImage(@WebParam(name = "id") int id) throws IOException {
        //TODO write your implementation code here:
         Image img = DB.SearchImageById(String.valueOf(id));
         if (img != null) {
             System.out.println("Image existeix");
             if(ImageDisk.RemoveFromDisk(img.getFilename())) {
                 System.out.println("Image eliminada1");
                 if (DB.DeleteImage(img.getId())) {
                     System.out.println("Image eliminada");
                     return 1;
                 }
             }             
         }         
         //if (img != null && ImageDisk.RemoveFromDisk(img.getFilename()) && DB.DeleteImage(img.getId())) return 1;
         return 0;
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "ListImages")
    public List<Image> ListImages() {
        //TODO write your implementation code here:
        List<Image> images = DB.ListImages();
        return images;
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "SearchbyId")
    public Image SearchbyId(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        Image image = DB.SearchImageById(String.valueOf(id));
        return image;
    }

    /**
     * Web service operation
     * @param title
     * @return 
     */
    @WebMethod(operationName = "SearchbyTitle")
    public List<Image> SearchbyTitle(@WebParam(name = "title") String title) {
        //TODO write your implementation code here:
        List<Image> images = DB.SearchImagesByTitle(title);
        return images;
    }

    /**
     * Web service operation
     * @param creaDate
     * @return 
     */
    @WebMethod(operationName = "SearchbyCreaDate")
    public List<Image> SearchbyCreaDate(@WebParam(name = "creaDate") String creaDate) {
        List<Image> images = DB.SearchImagesByCreaDate(creaDate);
        return images;
    }

    /**
     * Web service operation
     * @param keywords
     * @return 
     */
    @WebMethod(operationName = "SearchbyKeywords")
    public List<Image> SearchbyKeywords(@WebParam(name = "keywords") String keywords) {
        //TODO write your implementation code here:
        List<Image> images = DB.SearchImagesByKeyword(keywords);
        return images;
    }

    /**
     * Web service operation
     * @param author
     * @return 
     */
    @WebMethod(operationName = "SearchbyAuthor")
    public List<Image> SearchbyAuthor(@WebParam(name = "author") String author) {
        //TODO write your implementation code here:
        List<Image> images = DB.SearchImagesByAuthor(author);
        return images;
    }

    /**
     * Web service operation
     * @param user
     * @return 
     */
    @WebMethod(operationName = "CreateUser")
    public Integer CreateUser(@WebParam(name = "user") User user) {
        //TODO write your implementation code here:
        if (DB.CheckUser(user.getId_usuario())) return 0;
        if (DB.CreateUser(user)) return 1;
        return 0;
    }

    /**
     * Web service operation
     * @param user
     * @return 
     */
    @WebMethod(operationName = "CheckPassword")
    public Boolean CheckPassword(@WebParam(name = "user") User user) {
        //TODO write your implementation code here:
        return DB.CheckPassword(user.getId_usuario(),user.getPassword());
    }

    /**
     * Web service operation
     * @param user
     * @return 
     */
    @WebMethod(operationName = "CheckUser")
    public Boolean CheckUser(@WebParam(name = "user") User user) {
        //TODO write your implementation code here:
        return !DB.CheckUser(user.getId_usuario());
    }

     /**
     * Web service operation
     * @param fileName
     * @return 
     * @throws java.io.IOException 
     */       
    @WebMethod(operationName = "DownloadImage")
    public byte[] DownloadImage(@WebParam(name = "fileName") String fileName) throws IOException {
        //TODO write your implementation code here:
        return ImageDisk.GetFromDisk(fileName);
    }

    /**
     * Web service operation
     * @param fileContent
     * @param fileName
     * @return 
     * @throws java.io.IOException 
     */
    @WebMethod(operationName = "UploadImage")
    public Integer UploadImage(@WebParam(name = "fileContent") byte[] fileContent, @WebParam(name = "fileName") String fileName) throws IOException {
        //TODO write your implementation code here:
        if(ImageDisk.SaveToDisk(fileContent, fileName)) return 1;
        return 0;
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "GetMaxId")
    public Integer GetMaxId() {
        //TODO write your implementation code here:
        return DB.GetMaxId();
    }

    /**
     * Web service operation
     * @param fileName
     * @return 
     * @throws java.io.IOException 
     */
    @WebMethod(operationName = "RemoveImage")
    public Integer RemoveImage(@WebParam(name = "fileName") String fileName) throws IOException {
        //TODO write your implementation code here:
        if(ImageDisk.RemoveFromDisk(fileName)) return 1;
        return 0;
    }
    
}
