package com.gg.coordinatesconfigurationgui;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gary Glatfelter <garyglatfelter@gmail.com>
 */
public class ReadConfigFile {

    private final String CONFIG_FOLDER_NAME = "pft-print-tool";
    private final Path CONFIG_DIRECTORY = Paths.get(".").toAbsolutePath().getRoot().resolve(CONFIG_FOLDER_NAME);
    private final Path CONFIG_FILE_PATH = CONFIG_DIRECTORY.resolve("config-file.properties");
    private ObjectMapper objectMapper = new ObjectMapper();
    private Coordinates coordinateConfiguration = null;

    public ReadConfigFile() {
    }

    public boolean canReadFromDirectory() {

        boolean doesDirectoryExist = Files.exists(CONFIG_DIRECTORY);
        if (!doesDirectoryExist) {
            try {
                Files.createDirectories(CONFIG_DIRECTORY);
                doesDirectoryExist = true;
            } catch (IOException ex) {
                Logger.getLogger(Coordinates.class.getName()).log(Level.SEVERE, null, ex);
                doesDirectoryExist = false;
            } catch (Exception ex) {
                Logger.getLogger(Coordinates.class.getName()).log(Level.SEVERE, null, ex);
                doesDirectoryExist = false;
            }
        }

        return doesDirectoryExist;
    }

    public boolean canReadConfigurationFile() {
        boolean directoryExists = this.canReadFromDirectory();
        boolean canReadConfigFile = Files.exists(CONFIG_FILE_PATH);

        if (directoryExists && canReadConfigFile) {

            try {
                coordinateConfiguration = objectMapper.readValue(CONFIG_FILE_PATH.toFile(), Coordinates.class);
            } catch (IOException ex) {
                Logger.getLogger(ReadConfigFile.class.getName()).log(Level.SEVERE, null, ex);
                canReadConfigFile = false;
            } catch (Exception ex) {
                Logger.getLogger(ReadConfigFile.class.getName()).log(Level.SEVERE, null, ex);
                canReadConfigFile = false;
            }
        }

        return canReadConfigFile;
    }
    
    public Coordinates getCoordinates(){
        
        if(canReadConfigurationFile()){
            return coordinateConfiguration;
        }else{
            //use default values
            return new Coordinates();
        }
        
    }
    
    public Path getCONFIG_DIRECTORY() {
        return CONFIG_DIRECTORY;
    }

    public Path getCONFIG_FILE_PATH() {
        return CONFIG_FILE_PATH;
    }
    
    

    public static void main(String[] args) {

        ReadConfigFile unitTest = new ReadConfigFile();
        
        if(unitTest.canReadConfigurationFile() 
                && unitTest.coordinateConfiguration != null){
            System.out.println(unitTest.coordinateConfiguration);
        }

    }
}
