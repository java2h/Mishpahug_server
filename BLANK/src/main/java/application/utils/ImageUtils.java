package application.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    public static byte[] getImage(String fileName) throws IOException {
        File file =new File(fileName);
        //if(file.exists()){
            try {
                BufferedImage bufferedImage= ImageIO.read(file);
                ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", byteOutStream);
                return byteOutStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
       // }
        return null;
    }
}
