package org.example.Utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageUtils {
    public static String imageToBase64String(String path, String type) {
        File file = new File(path);
        String base64 = null;
        try {
            BufferedImage image = ImageIO.read(file);
            Integer width = image.getWidth();
            Integer height = image.getHeight();
            if (image.getTransparency() != BufferedImage.OPAQUE) {
                int w = image.getWidth();
                int h = image.getHeight();
                int[] pixels = new int[w * h];
                image.getRGB(0, 0, w, h, pixels, 0, w);
                BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                bi2.setRGB(0, 0, w, h, pixels, 0, w);
                image = bi2;
            }
            //输出流
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(image, type, stream);
            base64 = Base64.encode(stream.toByteArray());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return base64;
    }
}
