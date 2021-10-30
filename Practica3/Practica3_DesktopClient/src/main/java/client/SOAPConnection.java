/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.InputStream;
import java.util.List;
//import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;
import ws.IOException_Exception;
import ws.Image;

/**
 *
 * @author alumne
 */
public class SOAPConnection {
    
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Practica3_SOAP/ImageManager.wsdl")    
    
    public static Boolean checkPassword(String username, String password) {
         try { // Call Web Service Operation
                ws.ImageManager_Service service = new ws.ImageManager_Service();
                ws.ImageManager port = service.getImageManagerPort();
                // TODO initialize WS operation arguments here
                ws.User user = new ws.User();
                user.setIdUsuario(username);
                user.setPassword(password);
                // TODO process result here
                return port.checkPassword(user);                
            } catch (Exception ex) {
                return false;
            }   
    }
    
    public static List<ws.Image> searchByTitle(String title) {
        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here            
            // TODO process result here
            return port.searchbyTitle(title);            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return null;
        }
    }
    
    public static List<ws.Image> searchByAuthor(String author) {
    
        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here
            
            // TODO process result here
            return port.searchbyAuthor(author);            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return null;
        }
    }
    
    public static List<ws.Image> searchByKeywords(String keywords) {        
        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here
            
            // TODO process result here
            return port.searchbyKeywords(keywords);            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return null;
        }
    }
    
    public static List<ws.Image> searchByCreaDate(String creaDate) {        
        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here
            
            // TODO process result here
            return port.searchbyCreaDate(creaDate);            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return null;
        }
    }
    
    public static List<ws.Image> listImages() {        
        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here

            // TODO process result here
            return port.listImages();            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return null;
        }
    }
    
    public static Boolean createUser(String username, String password) {
        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here
            ws.User user = new ws.User();
            user.setIdUsuario(username);
            user.setPassword(password);
            // TODO process result here
            return (port.createUser(user) == 1); 
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            System.out.println("Ha petat");
            return false;
        }
    }
    
    public static Boolean deleteImage(Integer id) {
    
        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here 
            // TODO process result here
            return (port.deleteImage(id) == 1);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            System.out.println("Ha petat");
            return false; 
        }           
    }
    
    public static Boolean modifyImage(Image image) {

        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here 
            // TODO process result here
            return (port.modifyImage(image) == 1);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            System.out.println("Ha petat");
            return false; 
        }           
    }
    
    public static ws.Image searchById(Integer id) {
        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here            
            // TODO process result here
            return port.searchbyId(id);
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return null;
        }
    }
    
    public static Boolean registerImage(ws.Image image) {
        
        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here                   
            return (port.registerImage(image) == 1); 
                   
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return false;
        }   
    }
    /*
    public static Boolean uploadImage(Part part, String filename) {
        
        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here
            //String filename = FileUtil.getFilename(part);
            InputStream inputStream = part.getInputStream();
	    byte[] fileContent = FileUtil.getFileContent(inputStream);
            return (port.uploadImage(fileContent,filename) == 1);           
                   
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return false;
        }   
    }
    */
    public static byte[] downloadImage(String filename) {
        
        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here
            return port.downloadImage(filename);
                        
        } catch (IOException_Exception ex) {
            // TODO handle custom exceptions here
            return null;
        }   
    }
    
    public static Boolean removeImage(String filename) {
        
        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here
            return (port.removeImage(filename) == 1);            
                        
        } catch (IOException_Exception ex) {
            // TODO handle custom exceptions here
            return false;
        }   
    }
    
    public static Integer getMaxId(){
        try { // Call Web Service Operation
            ws.ImageManager_Service service = new ws.ImageManager_Service();
            ws.ImageManager port = service.getImageManagerPort();
            // TODO initialize WS operation arguments here
            return port.getMaxId();            
                   
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return -1;
        } 
    }
}