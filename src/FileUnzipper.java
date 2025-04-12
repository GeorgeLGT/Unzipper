import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUnzipper {

    public static void openZip(String zipeFilePath, String destDir) {
        File dir = new File(destDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipeFilePath))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                File newFile = new File(destDir, zipEntry.getName());

                if (zipEntry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile))) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = zis.read(buffer)) >= 0) {
                            bos.write(buffer, 0, length);
                        }
                    }
                }
                zis.closeEntry();
                System.out.println("Распакован файл: " + newFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при распаковке архива: " + e.getMessage());
        }
    }
}