/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.List;
import javax.xml.ws.WebServiceRef;

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
}
