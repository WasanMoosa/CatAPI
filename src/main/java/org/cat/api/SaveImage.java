package org.cat.api;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.cat.api.Main.directoryName;

public class SaveImage {
    public void ImageDownloader(String urlString, String id) {
        BufferedImage image = null;
        try {
            URL url = new URL(urlString);

            // read the url
            image = ImageIO.read(url);

        // if directory can be accessed, save image
            if (mkDirectory()) {
                // Save image as jpg
                ImageIO.write(image, "jpg", new File(directoryName + "/" + id + ".jpg"));
                System.out.println("The image of id " + id + " has been saved in " + directoryName + " folder");
            }

        } catch (IOException e) {
            System.out.println("Check connection");
        }
    }

    public boolean mkDirectory() {
        File file = new File(directoryName);
        boolean check = false;
        try {
            // make directory if it does not exist
            if (!file.exists()) {
                file.mkdirs();
            }
            // To ensure that program can access directory
            check = true;
        } catch (Exception e) {
            System.out.println("Can not make directory");
        }

        return check;
    }
}
