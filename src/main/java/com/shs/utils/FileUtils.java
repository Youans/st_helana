/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.utils;

import com.shs.common.SystemConfiguration;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.Part;

/**
 *
 * @author Youans
 */
public class FileUtils {
    public static String uploadImage(Part part, String path) throws IOException {
        
        InputStream inputStream = part.getInputStream();
        String fullPath = SystemConfiguration.pathToRoot + path;
        File dir = new File(fullPath);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        String uploadedFilePath = getFilename(part, path);
        FileOutputStream outputStream = new FileOutputStream((SystemConfiguration.pathToRoot + uploadedFilePath));

        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        while (true) {
            bytesRead = inputStream.read(buffer);
            if (bytesRead > 0) {
                outputStream.write(buffer, 0, bytesRead);
            } else {
                break;
            }
        }
        outputStream.flush();

        outputStream.close();
        inputStream.close();
        return uploadedFilePath;

    }

    private static String getFilename(Part part, String pathToSave) {

        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return pathToSave + filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
            }
        }
        return null;
    }
}
