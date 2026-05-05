package utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileUtils {
    public static boolean saveImage(File source, String destinationPath) {
        try {
            File dest = new File(destinationPath);
            Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
