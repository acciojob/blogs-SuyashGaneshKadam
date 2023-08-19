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
        int midDim = dimensions.length() / 2;
        int midNewDim = screenDimensions.length() / 2;
        int dim = Integer.parseInt(dimensions.substring(0,midDim)) * Integer.parseInt(dimensions.substring(midDim + 1));
        int newDim = Integer.parseInt(screenDimensions.substring(0,midNewDim)) * Integer.parseInt(screenDimensions.substring(midNewDim+1));
        return newDim/dim;
    }
}
