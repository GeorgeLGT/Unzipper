import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        GameProgress playerProgress1 = new GameProgress(100, 50, 80, 100);
        GameProgress playerProgress2 = new GameProgress(100, 11, 55, 1000);
        GameProgress playerProgress3 = new GameProgress(100, 12, 55, 1000);
        String saveDir = "C:\\Games\\savegames\\";
        String saveFile1 = saveDir + "Save1.dat";
        String saveFile2 = saveDir + "Save2.dat";
        String saveFile3 = saveDir + "Save3.dat";
        GameSaver.saveGame(saveFile1, playerProgress1);
        GameSaver.saveGame(saveFile2, playerProgress2);
        GameSaver.saveGame(saveFile3, playerProgress3);

        String zipFilePath = saveDir + "games.zip";
        List<String> filesToZip = Arrays.asList(saveFile1, saveFile2, saveFile3);
        FileZipper.zipFiles(zipFilePath, filesToZip);

        FileZipper.deleteFiles(filesToZip);

        String zipeFilePath = "C:\\Games\\savegames\\games.zip";
        String destDir = "savegames\\";
        FileUnzipper.openZip(zipFilePath, destDir);
        String saveFilePath = destDir + "Save1.dat";
        GameProgress loadedGame = GameLoader.openProgress(saveFilePath);

        if (loadedGame != null) {
            System.out.println(loadedGame);
        }
    }
}
