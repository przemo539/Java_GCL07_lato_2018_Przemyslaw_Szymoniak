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
import java.io.File;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/gallery")
public class MyController {
    AppProperties app;
    List<fileList> images;
    
    @Autowired
    public void setProperties(AppProperties app) {
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
   
    @RequestMapping(value = "/picture/{index}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(HttpServletResponse response, @PathVariable("index") int itemId) throws IOException {
        String ImageName = "cross";
       for (fileList file : images){
           if(file.getIndex() == itemId){
                ImageName = file.getName();
                break;
           }
       }
        
        ClassPathResource imgFile = new ClassPathResource(app.getPicturePath()+ImageName+".jpg");
        

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }
        
    @RequestMapping(value = "/pictures", method = RequestMethod.GET, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<fileList>>  listImages(){
        HashMap<String, List<fileList>> itemIlist = new HashMap<>();
        itemIlist.put("pictures", images);
        return itemIlist;
    }
    
     @RequestMapping(value = "/picture/{index}", method = RequestMethod.DELETE
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
     
     @RequestMapping(value = "/upload", method = RequestMethod.POST
             , produces=MediaType.APPLICATION_JSON_VALUE)
     public Map<String, Boolean> uploadImage(@RequestParam("file") MultipartFile file) throws IOException
     {
        HashMap<String, Boolean> result = new HashMap<>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(app.getPicturePath());
        String path = url.getPath();
        if (!file.isEmpty()) {

            String fileName = file.getOriginalFilename();
            try {
                InputStream is = file.getInputStream();            
                 Files.copy(is, Paths.get(path +fileName),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(MyController.class.getName()).log(Level.SEVERE, null, ex);
            }
            File imageFile = new File(path + fileName);
            result.put("result", true);
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

        } else 
            result.put("result", false);
            return result;
     }       
}