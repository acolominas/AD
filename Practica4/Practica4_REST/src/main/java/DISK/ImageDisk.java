/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DISK;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.http.Part;

/**
 *
 * @author alumne
 */
public class ImageDisk {
    static final private String Path = "/home/alumne/Escritorio/AD/Practica4/Practica4_REST/src/main/webapp/Images";
    
 
    public static String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    //LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
    }      

    public static Boolean SaveToDisk(byte[] FileContent,String Filename) {
        OutputStream outs = null;        
        File fileSaveDir = new File(Path);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }   
        
        try {
        
            outs = new FileOutputStream(new File(Path + File.separator + Filename));    
            outs.write(FileContent);        
            return true;
        } catch (IOException ex) {
            // TODO handle custom exceptions here
            return false;
        }  
    }
    
    public static byte[] GetFromDisk (String Filename) throws IOException {   
        File imgPath = new File(Path + File.separator + Filename);        
        return Files.readAllBytes(imgPath.toPath());
    }
    
    public static Boolean RemoveFromDisk(String Filename) throws IOException{
        return Files.deleteIfExists(Paths.get(Path +"/"+ Filename));  
    }
    
}

