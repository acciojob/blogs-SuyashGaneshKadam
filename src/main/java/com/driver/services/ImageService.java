package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image(description, dimensions);
        Blog blog = blogRepository2.findById(blogId).get();

        image.setBlog(blog);
        blog.getImageList().add(image);

        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String dimensions = image.getDimensions();
        int xi = 0, yi = 0, xs = 0, ys = 0, num = 0;
        for(int i=0 ; i<dimensions.length() ; i++){
            if(dimensions.charAt(i) == 'X'){
                xi = num;
                num = 0;
                continue;
            }
            num *= 10;
            num += (dimensions.charAt(i) - '0');
        }
        yi = num;
        num = 0;
        for(int i=0 ; i<dimensions.length() ; i++){
            if(dimensions.charAt(i) == 'X'){
                xs = num;
                num = 0;
                continue;
            }
            num *= 10;
            num += (dimensions.charAt(i) - '0');
        }
        ys = num;
        double x = xs;
        double y = ys;
        double x1 = xi;
        double y1 = yi;
        int ans = (int) (Math.floor((x)/(x1)) * Math.floor((y)/(y1)));
        return ans;
    }
}
