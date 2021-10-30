/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageManager;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import client.SOAPConnection;
import java.util.ArrayList;

/**
 *
 * @author alumne
 */
public class ImageManagerClient {
    
    private static String user = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Boolean login_correct = false;               
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Image Manager - Login Page");
            System.out.println("Introduce usuario: ");
            String username = sc.nextLine();
            System.out.println("Introduce password: ");        
            String password = sc.nextLine();
            login_correct = SOAPConnection.checkPassword(username,password); 
            if (login_correct) { 
                System.out.println("Login Correcto!");
                user = username;
            }
            else System.out.println("Login incorrecto, vuelva a probar");             
        } while (!login_correct);
        
        if (login_correct) { 
            menuMain();           
        }            
    }
    
    private static void menuMain(){
         Boolean exit = false; 
         while(!exit) {
                System.out.println("=======  MENU =======");
                System.out.println("== Bienvenido "+user+ " ==");
                System.out.println("1 - Registrar Imagen");
                System.out.println("2 - Modificar Imagen");
                System.out.println("3 - Listar Imagenes");
                System.out.println("4 - Buscar Imagen");
                System.out.println("5 - Salir");
                Scanner sc = new Scanner(System.in);
                Integer option = sc.nextInt();                
                switch(option) {
                    case 1:
                        menuRegistrarImagen();            
                        break;
                    case 2:
                        menuModificarImagen();
                        break;                      
                    case 3:
                        menuListarImagenes();
                        break;
                    case 4:
                        menuBuscarImagen();
                        break;
                    case 5:
                    default:
                        exit = true;
                        break;
                }                
            }
    }
    
    private static void menuRegistrarImagen(){
        ws.Image image = new ws.Image();
        image.setTitle("test");
        image.setDescription("test1");
        image.setAuthor("Arnau");
        image.setCreator(user);
        image.setKeywords("test1,test1");
        image.setFilename("1.jpg");
        image.setCaptureDate("hoy");
        image.setStorageDate("hoy");
        SOAPConnection.registerImage(image);        
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
    
    private static void menuModificarImagen() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce Id");
        Integer id = sc.nextInt();
        ws.Image image = SOAPConnection.searchById(id);
        if(image.getCreator().equals(user)) {
        } else {
            System.out.println("No tienes permisos");
        }
    }
    
    private static void menuEliminar() {
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
            System.out.println("1 - Modificar una imagen");
            System.out.println("2 - Eliminar una imagen");
            System.out.println("3 - Volver al menu");
            Integer option = sc.nextInt();
            
            switch(option) {
                case 1:
                    menuModificarImagen();
                    break;
                case 2:
                    menuEliminar();
                    break;                
                case 3:
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