package com.g4.dev.esportlancentersw.reportes.util;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReportesUtils {
    private static  BufferedImage bufferedImage;
    static {
        bufferedImage = null;
    }

    public static void eliminarPdfFile(String rutaPdf) throws IOException {
            Path path = Paths.get(rutaPdf);
            Files.delete(path);
    }

    public  static ByteArrayResource getBytesFromPdfFile(String path) throws IOException {
        byte[] data = Files.readAllBytes(Paths.get(path));
        return new ByteArrayResource(data);
    }

    public  static BufferedImage getBufferFromResourceImage(ResourceLoader charger){
        Resource r = charger.getResource("classpath:imagenes"+ File.separator+"gusesprort.jpg");

        File fil = null;
        try {
            fil = r.getFile();
            bufferedImage = ImageIO.read(fil);
        } catch (IOException e) {
            bufferedImage.flush();

            throw new RuntimeException(e);
        }
        return  bufferedImage;
    }
}
