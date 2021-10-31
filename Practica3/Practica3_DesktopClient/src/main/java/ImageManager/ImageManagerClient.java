/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageManager;

import client.FileUtil;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import client.SOAPConnection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import client.DisplayImage;
import java.io.IOException;


/**
 *
 * @author alumne
 */
public class ImageManagerClient {
    
    private static String user = "";
    private static Boolean exit = false;
    private static Boolean login_correct = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        do {            
            menuLogin();
        } while (!login_correct && !exit);
        
        while(!exit) {
            menuMain();
        }            
    }
    
    
    private static void menuLogin(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Image Manager - Login Page");
        System.out.println("Introduce usuario: ");
        String username = sc.nextLine();
        System.out.println("Introduce password: ");        
        String password = sc.nextLine();
        login_correct = SOAPConnection.checkPassword(username,password);
        if (login_correct) {                 
                user = username;
        }
        else System.out.println("Login incorrecto, vuelva a probar");  
    }
    
    private static void menuMain(){            
        System.out.println("=======  MENU =======");
        System.out.println("== Bienvenido "+user+ " ==");
        System.out.println("1 - Registrar Imagen");
        System.out.println("2 - Listar Imagenes");
        System.out.println("3 - Buscar Imagen");
        System.out.println("4 - Salir");
        Scanner sc = new Scanner(System.in);
        Integer option = sc.nextInt();                
        switch(option) {
            case 1:
                menuRegistrarImagen();            
                break;                     
            case 2:
                menuListarImagenes();
                break;
            case 3:
                menuBuscarImagen();
                break;
            case 4:
            default:
                exit = true;
                break;
        }                    
    }
    
    private static void menuRegistrarImagen(){
       Scanner sc = new Scanner(System.in);
       System.out.println("Introduce Title");
       String title = sc.nextLine(); 
       
       sc = new Scanner(System.in);       
       System.out.println("Introduce Description");
       String description = sc.nextLine();
       
       sc = new Scanner(System.in);
       System.out.println("Introduce Keywords");
       String keywords = sc.nextLine();
       
       sc = new Scanner(System.in);
       System.out.println("Introduce Author");
       String author = sc.nextLine(); 
       
       sc = new Scanner(System.in);
       System.out.println("Introduce Capture Date");
       String capture_date = sc.nextLine(); 
       
       sc = new Scanner(System.in);
       System.out.println("Introduce filename (/home/alumne/Descargas/[filename])");
       String filename = sc.nextLine();
       
       if(!description.isEmpty() && !title.isEmpty() && !keywords.isEmpty() && !author.isEmpty() && !capture_date.isEmpty()) {
           
            int id = SOAPConnection.getMaxId();               

            Format f = new SimpleDateFormat("yyyy-MM-dd");
            String storage_date = f.format(new Date());                
            
            String new_filename = String.valueOf(id + 1) + "_" + filename;
            if(SOAPConnection.uploadImage(filename,new_filename));
            ws.Image image = new ws.Image();
            image.setTitle(title);
            image.setDescription(description);
            image.setAuthor(author);
            image.setCreator(user);
            image.setKeywords(keywords);
            image.setFilename(new_filename);
            image.setCaptureDate(capture_date);
            image.setStorageDate(storage_date);               
            if(SOAPConnection.registerImage(image)) System.out.println("Imagen registrada!") ;
           
       }
       else System.out.println("Existen campo vacios!");     
    }
    
    private static void menuListarImagenes(){
        List<ws.Image> images = SOAPConnection.listImages();
        showImages(images);
    }  
  
    private static void menuBuscarImagen() {
        System.out.println("1 - Buscar por Id");
        System.out.println("2 - Buscar por Title");
        System.out.println("3 - Buscar por Author");
        System.out.println("4 - Buscar por Keywords");
        System.out.println("5 - Buscar por Creation Date");
        System.out.println("6 - Cancelar");
        Scanner sc = new Scanner(System.in);
        Scanner sc_value = new Scanner(System.in);
        Integer option = sc.nextInt();
        List<ws.Image> images = new ArrayList<>();      
        switch (option) {
            case 1:
                System.out.println("Introduce Id");
                Integer id = sc_value.nextInt();
                ws.Image image = SOAPConnection.searchById(id);
                images.add(image);
                showImages(images);
                break;
            case 2:
                System.out.println("Introduce Title");
                String title = sc_value.nextLine();
                images = SOAPConnection.searchByTitle(title);
                showImages(images);
                break;
            case 3:
                System.out.println("Introduce Author");
                String author = sc_value.nextLine();
                images = SOAPConnection.searchByAuthor(author);
                showImages(images);
                break;
            case 4:
                System.out.println("Introduce Keywords");
                String keywords = sc_value.nextLine();
                images = SOAPConnection.searchByKeywords(keywords);
                showImages(images);
                break;
            case 5:
                System.out.println("Introduce Creation Date");
                String creation_date = sc_value.nextLine();
                images = SOAPConnection.searchByCreaDate(creation_date);
                showImages(images);
                break;
            case 6:
            default:
                menuMain();
                break;
        }
    }
    
    private static void menuVerImagen() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce Id: ");
        Integer id = sc.nextInt();
        ws.Image image = SOAPConnection.searchById(id);
        if (image == null) {
            System.out.println("No existe una imagen con ID "+id);
        }
        else {            
            byte[] img = SOAPConnection.downloadImage(image.getFilename());
            FileUtil.SaveToDisk(img, image.getFilename());
             DisplayImage abc = new DisplayImage("/tmp/"+image.getFilename());           
        }
    }
    private static void menuModificarImagen() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce Id: ");
        Integer id = sc.nextInt();
        ws.Image image = SOAPConnection.searchById(id);
        if (image == null) {
            System.out.println("No existe una imagen con ID "+id);
        }
        else {
            if(image.getCreator().equals(user)) {
                modificarImagen(image);
            } else {
                System.out.println("No tienes permisos");
            }
        }
    }
    
    private static void modificarImagen(ws.Image image){
       Scanner sc = new Scanner(System.in);
       System.out.println("Introduce nueva Description ["+image.getDescription()+"]");
       String description = sc.nextLine();
       if(!description.isEmpty()) image.setDescription(description);
       sc = new Scanner(System.in);
       System.out.println("Introduce nuevo Title ["+image.getTitle()+"]");
       String title = sc.nextLine();
       if(!title.isEmpty()) image.setTitle(title);
       sc = new Scanner(System.in);
       System.out.println("Introduce nuevas Keywords ["+image.getKeywords()+"]");
       String keywords = sc.nextLine();
       if(!keywords.isEmpty()) image.setKeywords(keywords);
       sc = new Scanner(System.in);
       System.out.println("Introduce nuevo Author ["+image.getAuthor()+"]");
       String author = sc.nextLine();
       if(!author.isEmpty()) image.setAuthor(author);
       sc = new Scanner(System.in);
       System.out.println("Introduce nueva Capture Date ["+image.getCaptureDate()+"]");
       String capture_date = sc.nextLine();
       if(!capture_date.isEmpty()) image.setCaptureDate(capture_date);
       
       System.out.println("Introduce nuevo Filename ["+image.getFilename()+"] (/home/alumne/Descargas/[filename]))");
       String filename = sc.nextLine();
       if(!filename.isEmpty()) {
           Format f = new SimpleDateFormat("yyyy-MM-dd");
           String storage_date = f.format(new Date());
           image.setStorageDate(storage_date);
           String new_filename = image.getId() + "_" + filename;
           image.setFilename(new_filename);
           SOAPConnection.uploadImage(filename, new_filename);
           if (!image.getFilename().equals(new_filename)) {
               SOAPConnection.removeImage(image.getFilename());
           }
       }       
       SOAPConnection.modifyImage(image);       
    }
    
    private static void menuEliminarImagen() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce Id");
        Integer id = sc.nextInt();
        ws.Image image = SOAPConnection.searchById(id);
        if(image.getCreator().equals(user)) {            
            if(SOAPConnection.deleteImage(id)) {
                System.out.println("Imagen Eliminada");
            }
            else {
                System.out.println("Ha habido un error");
            }
        } else {
            System.out.println("No tienes permisos");
        }
    }
    
    private static void showImages(List<ws.Image> images) {
        if (!images.isEmpty()) {
            ListIterator<ws.Image> listIterator = images.listIterator();
            while(listIterator.hasNext()) {
                ws.Image image = listIterator.next();
                System.out.println("Id: "+image.getId());
                System.out.println("Titulo: "+image.getTitle());
                System.out.println("Description: "+image.getDescription());
                System.out.println("Keywords: "+image.getKeywords());
                System.out.println("Author: "+image.getAuthor());
                System.out.println("Creator: "+image.getCreator());
                System.out.println("Storage Date: "+image.getStorageDate());
                System.out.println("Capture Date: "+image.getCaptureDate());
                System.out.println("Filename: "+image.getFilename());
                System.out.println("--------------------------------");
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("1 - Ver una imagen");
            System.out.println("3 - Modificar una imagen");
            System.out.println("3 - Eliminar una imagen");
            System.out.println("4 - Volver al menu");
            Integer option = sc.nextInt();
            
            switch(option) {
                case 1: 
                    menuVerImagen();
                    break;
                case 2:
                    menuModificarImagen();
                    break;
                case 3:
                    menuEliminarImagen();
                    break;                
                case 4:
                default:
                    menuMain();
                    break;
            }                    
        }
        else {
            System.out.println("No hay imagenes que mostrar");        
        }
    }           
}