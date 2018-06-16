/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springapp;

/**
 *
 * @author Witajcie
 */

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController


public class MyController {
    appProperty app;
    List<fileList> images;
    
    @Autowired
    public void setProperties(appProperty app) {
        this.app = app;
    }
    
    @Autowired
    private void loadPictures() throws IOException{
        images = new ArrayList<>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(app.getPicturePath());
        String path = url.getPath();
        File[] imagesFile = new File(path).listFiles();
        SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
        
        for (File imageFile : imagesFile) {
           if(imageFile.getName().equals("cross.jpg"))
                continue;
            BufferedImage bimg = ImageIO.read(imageFile);
            Path p = Paths.get(imageFile.getPath());
            BasicFileAttributes fileAttrib
                 = Files.getFileAttributeView(p, BasicFileAttributeView.class)
              .readAttributes();
            String width          = Integer.toString(bimg.getWidth());
            String height         = Integer.toString(bimg.getHeight());
            String name = imageFile.getName().replace(".jpg", "");
            images.add(new fileList(name, width+"x"+height, imageFile.getTotalSpace(), dateformat.format(fileAttrib.creationTime().toMillis())));
        }
      
    }
   
    @RequestMapping(value = "/gallery/picture/{index}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(HttpServletResponse response, @PathVariable("index") int itemId) throws IOException {
        String ImageName = "cross";
       for (fileList file : images){
           if(file.getIndex() == itemId){
                ImageName = file.getName();
                break;
           }
       }
        
        ClassPathResource imgFile = new ClassPathResource("/image/"+ImageName+".jpg");
        

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }
        
    @RequestMapping(value = "/gallery/pictures", method = RequestMethod.GET, 
            produces=MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<fileList>>  pictures(){
        HashMap<String, List<fileList>> itemIlist = new HashMap<>();
        itemIlist.put("pictures", images);
       
        return itemIlist;
    }
    
     @RequestMapping(value = "/gallery/picture/{index}", method = RequestMethod.DELETE
             , produces=MediaType.APPLICATION_JSON_VALUE)
     public Map<String, Boolean> deleteImage(@PathVariable("index") int itemId)
     {
        String ImageName = null;
        boolean request = false;
        for (int i=0; i<images.size();i++){
            fileList file = images.get(i);
            if(file.getIndex() == itemId){
                 ImageName = file.getName();
                 images.remove(file);
                 request = true;
                 break;
            }
        }
            
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            URL url = loader.getResource(app.getPicturePath());
            String path = url.getPath()+ImageName+".jpg";
            File imageFile = new File(path).getAbsoluteFile();
            imageFile.delete();
            
            HashMap<String, Boolean> result = new HashMap<>();
            result.put("result", request);
            return result;
     }
 @RequestMapping(value = "/gallery/uploadFile", method = RequestMethod.POST)
@ResponseBody
public ResponseEntity<?> uploadFile(
    @RequestParam("uploadfile") MultipartFile uploadfile) {
  
  try {
    // Get the filename and build the local file path (be sure that the 
    // application have write permissions on such directory)
    String workingDir = System.getProperty("user.dir");
      
      String filename = uploadfile.getOriginalFilename();
              Path x = Paths.get("/src/main/resources/"+app.getPicturePath());
      String directory = workingDir+x.toString();
      String filepath = Paths.get(directory, filename).toString();    
      System.out.println(workingDir);
    
    // Save the file locally
    BufferedOutputStream stream =
        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
    stream.write(uploadfile.getBytes());
    
    File imageFile = new File(filepath);
             BufferedImage bimg = ImageIO.read(imageFile);
            Path p = Paths.get(imageFile.getPath());
            BasicFileAttributes fileAttrib
                 = Files.getFileAttributeView(p, BasicFileAttributeView.class)
              .readAttributes();
            String width          = Integer.toString(bimg.getWidth());
            String height         = Integer.toString(bimg.getHeight());
            String name = imageFile.getName().replace(".jpg", "");
            SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
            images.add(new fileList(name, width+"x"+height, imageFile.getTotalSpace(), dateformat.format(fileAttrib.creationTime().toMillis())));

    stream.close();
  }
  catch (Exception e) {
    System.out.println(e.getMessage());
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
  
  return new ResponseEntity<>(HttpStatus.OK);
} // method uploadFile
     
}