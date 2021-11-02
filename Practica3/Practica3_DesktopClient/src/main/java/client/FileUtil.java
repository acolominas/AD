/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

/**
 *
 * @author alumne
 */
public class FileUtil {
    private FileUtil() {
    }

    public static byte[] getFileContent(String path) {
        try {
            File fi = new File(path);
            byte[] fileContent = Files.readAllBytes(fi.toPath());
            return fileContent;
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
    public static Boolean SaveToDisk(byte[] FileContent,String Filename) {
        //Guardamos las imagenes en el directorio /tmp
        String Path = "/tmp";
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
}
