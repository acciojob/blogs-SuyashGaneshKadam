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

        int length = dimensions.length();
        Double x1 = Double.parseDouble(dimensions.substring(0, length/2));
        Double y1 = Double.parseDouble(dimensions.substring(length/2 + 1));

        length = screenDimensions.length();
        Double x2 = Double.parseDouble(screenDimensions.substring(0, length/2));
        Double y2 = Double.parseDouble(screenDimensions.substring(length/2 + 1));

        int ans = (int) (Math.floor((x1)/(x2)) * Math.floor((y1)/(y2)));
        return ans;
    }
}
