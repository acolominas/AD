/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Base64;
import javax.servlet.http.Part;

/**
 *
 * @author alumne
 */
public class FileUtil {
    private FileUtil() {
    }
    public static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE                                                                                                                                                                                                                            // fix.
            }
        }
        return null;
    }
    
    public static String getNewFilename(Part part) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String filename = FileUtil.getFilename(part);   
        filename = String.valueOf(timestamp.getTime()+ "_"+filename); 
        return filename;
    }
    public static byte[] getFileContent(InputStream inputStream) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int reads = inputStream.read();
            while (reads != -1) {
                    baos.write(reads);
                    reads = inputStream.read();
            }
            return baos.toByteArray();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    public static String getStringBase64(Part part) {
        try {
            InputStream inputStream = part.getInputStream();
            byte[] fileContent = FileUtil.getFileContent(inputStream);
            return Base64.getEncoder().encodeToString(fileContent);
        }catch (IOException e) {
        }
        return null;
    }
}
