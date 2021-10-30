/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DISK;

import DB.DB;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Part;

/**
 *
 * @author alumne
 */
public class ImageDisk {
    static final private String Path = "/home/alumne/Escritorio/Practica2/src/main/webapp/Images";
    
 
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
      
    public static Boolean SaveToDisk(String Filename, final Part part) throws FileNotFoundException, IOException {
        OutputStream outs = null;
        InputStream filecontent = null;
        File fileSaveDir = new File(Path);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }              
        //out.println(fileName);

        outs = new FileOutputStream(new File(Path + File.separator + Filename));
        filecontent = part.getInputStream();

        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = filecontent.read(bytes)) != -1) {
            outs.write(bytes, 0, read);
        }
        return true;
    }
    public static Boolean RemoveFromDisk(String Filename) throws IOException{
            return Files.deleteIfExists(Paths.get(Path +"/"+ Filename));  
    }
    
}
