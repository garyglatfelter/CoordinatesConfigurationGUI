package com.gg.coordinatesconfigurationgui;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
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
public class Coordinates {

    public static enum CoordConstants {
        SERIAL_NUM_X(0, "MAIN COVER SHEET: serial number line - X coordinate: ", 155),
        SERIAL_NUM_Y(1, "Y coordinate: ", 568),
        LOT_NUM_X(2, "MAIN COVER SHEET: lot number line - X coordinate: ", 300),
        LOT_NUM_Y(3, "Y coordinate: ", 568),
        STATION_NUM_X(4, "MAIN COVER SHEET: station number line - The x coordinate: ", 505),
        STATION_NUM_Y(5, "Y coordinate: ", 568),
        DATE_X(6, "MAIN COVER SHEET: date line - The x coordinate: ", 380),
        DATE_Y(7, "Y coordinate: ", 98),
        CHECKMARK_EQ002002A_X(8, "MAIN COVER SHEET: Checkbox EQ002002A - The x coordinate", 389),
        CHECKMARK_EQ002002A_Y(9, "Y coordinate: ", 607),
        CHECKMARK_EQ002003_X(10, "MAIN COVER SHEET: Checkbox EQ002003 - The x coordinate", 476),
        CHECKMARK_EQ002003_Y(11, "Y coordinate: ", 607),
        OPERATOR_X(12, "PFT REPORT: operator printed name - The x coordinate: ", 80),
        OPERATOR_Y(13, "Y coordinate: ", 670),
        SIGN_DATE_X(14, "PFT REPORT: date - The x coordinate: ", 420),
        SIGN_DATE_Y(15, "Y coordinate: ", 670),
        FAILURE_COVER_SHEET_PAGE(16, "EQ-0056-SCR: The page number of failure cover sheet", 5),
        MAIN_COVER_SHEET_PAGE(17, "EQ-0056-SCR: The page number of main cover sheet", 4),;

        public final int coordinateCode;
        public final String description;
        public final int defaultValue;

        private CoordConstants(int coordinateCode, String description, int defaultValue) {
            this.coordinateCode = coordinateCode;
            this.description = description;
            this.defaultValue = defaultValue;
        }
    }
    /**
     * This is the x coordinate for the serial number line in the template
     */
    private int serialNumX = CoordConstants.SERIAL_NUM_X.defaultValue;

    /**
     * This is the y coordinate for the serial number line in the template
     */
    private int serialNumY = CoordConstants.SERIAL_NUM_Y.defaultValue;

    /**
     * This is the x coordinate for the lot number line in the template
     */
    private int lotNumX = CoordConstants.LOT_NUM_X.defaultValue;

    /**
     * This is the y coordinate for the lot number line in the template
     */
    private int lotNumY = CoordConstants.LOT_NUM_Y.defaultValue;

    /**
     * This is the x coordinate for the station number line in the template
     */
    private int stationNumX = CoordConstants.STATION_NUM_X.defaultValue;

    /**
     * This is the y coordinate for the station number line in the template
     */
    private int stationNumY = CoordConstants.STATION_NUM_Y.defaultValue;

    /**
     * This is the x coordinate for the date line in the template
     */
    private int dateX = CoordConstants.DATE_X.defaultValue;

    /**
     * This is the y coordinate for the date line in the template
     */
    private int dateY = CoordConstants.DATE_Y.defaultValue;

    /**
     * This is the x coordinate for the operator printed name in the PFT report
     */
    private int operatorX = CoordConstants.OPERATOR_X.defaultValue;

    /**
     * This is the y coordinate for the operator printed name in the PFT report
     */
    private int operatorY = CoordConstants.OPERATOR_Y.defaultValue;

    /**
     * This is the x coordinate for the date that goes next to operator's
     * signature in the PFT report
     */
    private int signDateX = CoordConstants.SIGN_DATE_X.defaultValue;

    /**
     * This is the y coordinate for the date that goes next to operator's
     * signature in the PFT report
     */
    private int signDateY = CoordConstants.SIGN_DATE_Y.defaultValue;

    /**
     * This is the x coordinate of the checkbox for selecting part EQ002002A
     */
    private int checkmarkEQ002002A_X = CoordConstants.CHECKMARK_EQ002002A_X.defaultValue;

    /**
     * This is the y coordinate of the checkbox for selecting part EQ002002A
     */
    private int checkmarkEQ002002A_Y = CoordConstants.CHECKMARK_EQ002002A_Y.defaultValue;

    /**
     * This is the x coordinate of the checkbox for selecting part EQ002003
     */
    private int checkmarkEQ002003_X = CoordConstants.CHECKMARK_EQ002003_X.defaultValue;

    /**
     * This is the y coordinate of the checkbox for selecting part EQ002003
     */
    private int checkmarkEQ002003_Y = CoordConstants.CHECKMARK_EQ002003_Y.defaultValue;
    /**
     * This is the page number of EQ-0056 where the cover sheet for recording
     * failures found
     */
    private int failureCoverSheetPage = CoordConstants.FAILURE_COVER_SHEET_PAGE.defaultValue;

    /**
     * This is the page number of EQ-0056 where the main cover sheet for pft
     * acceptance can be found.
     */
    private int mainCoverSheetPage = CoordConstants.MAIN_COVER_SHEET_PAGE.defaultValue;

    public Coordinates() {
    }

    public int getSerialNumX() {
        return serialNumX;
    }

    public void setSerialNumX(int serialNumX) {
        this.serialNumX = serialNumX;
    }

    public int getSerialNumY() {
        return serialNumY;
    }

    public void setSerialNumY(int serialNumY) {
        this.serialNumY = serialNumY;
    }

    public int getLotNumX() {
        return lotNumX;
    }

    public void setLotNumX(int lotNumX) {
        this.lotNumX = lotNumX;
    }

    public int getLotNumY() {
        return lotNumY;
    }

    public void setLotNumY(int lotNumY) {
        this.lotNumY = lotNumY;
    }

    public int getStationNumX() {
        return stationNumX;
    }

    public void setStationNumX(int stationNumX) {
        this.stationNumX = stationNumX;
    }

    public int getStationNumY() {
        return stationNumY;
    }

    public void setStationNumY(int stationNumY) {
        this.stationNumY = stationNumY;
    }

    public int getDateX() {
        return dateX;
    }

    public void setDateX(int dateX) {
        this.dateX = dateX;
    }

    public int getDateY() {
        return dateY;
    }

    public void setDateY(int dateY) {
        this.dateY = dateY;
    }

    public int getOperatorX() {
        return operatorX;
    }

    public void setOperatorX(int operatorX) {
        this.operatorX = operatorX;
    }

    public int getOperatorY() {
        return operatorY;
    }

    public void setOperatorY(int operatorY) {
        this.operatorY = operatorY;
    }

    public int getSignDateX() {
        return signDateX;
    }

    public void setSignDateX(int signDateX) {
        this.signDateX = signDateX;
    }

    public int getSignDateY() {
        return signDateY;
    }

    public void setSignDateY(int signDateY) {
        this.signDateY = signDateY;
    }

    public int getCheckmarkEQ002002A_X() {
        return checkmarkEQ002002A_X;
    }

    public void setCheckmarkEQ002002A_X(int checkmarkEQ002002A_X) {
        this.checkmarkEQ002002A_X = checkmarkEQ002002A_X;
    }

    public int getCheckmarkEQ002002A_Y() {
        return checkmarkEQ002002A_Y;
    }

    public void setCheckmarkEQ002002A_Y(int checkmarkEQ002002A_Y) {
        this.checkmarkEQ002002A_Y = checkmarkEQ002002A_Y;
    }

    public int getCheckmarkEQ002003_X() {
        return checkmarkEQ002003_X;
    }

    public void setCheckmarkEQ002003_X(int checkmarkEQ002003_X) {
        this.checkmarkEQ002003_X = checkmarkEQ002003_X;
    }

    public int getCheckmarkEQ002003_Y() {
        return checkmarkEQ002003_Y;
    }

    public void setCheckmarkEQ002003_Y(int checkmarkEQ002003_Y) {
        this.checkmarkEQ002003_Y = checkmarkEQ002003_Y;
    }

    public int getFailureCoverSheetPage() {
        return failureCoverSheetPage;
    }

    public void setFailureCoverSheetPage(int failureCoverSheetPage) {
        this.failureCoverSheetPage = failureCoverSheetPage;
    }

    public int getMainCoverSheetPage() {
        return mainCoverSheetPage;
    }

    public void setMainCoverSheetPage(int mainCoverSheetPage) {
        this.mainCoverSheetPage = mainCoverSheetPage;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "serialNumX=" + serialNumX + ", serialNumY=" + serialNumY + ", lotNumX=" + lotNumX + ", lotNumY=" + lotNumY + ", stationNumX=" + stationNumX + ", stationNumY=" + stationNumY + ", dateX=" + dateX + ", dateY=" + dateY + ", operatorX=" + operatorX + ", operatorY=" + operatorY + ", signDateX=" + signDateX + ", signDateY=" + signDateY + ", checkmarkEQ002002A_X=" + checkmarkEQ002002A_X + ", checkmarkEQ002002A_Y=" + checkmarkEQ002002A_Y + ", checkmarkEQ002003_X=" + checkmarkEQ002003_X + ", checkmarkEQ002003_Y=" + checkmarkEQ002003_Y + ", failureCoverSheetPage=" + failureCoverSheetPage + ", mainCoverSheetPage=" + mainCoverSheetPage + '}';
    }

    public static void main(String[] args) {

        String configFolderName = "pft-print-tool";
        Path configDirectory = Paths.get(".").toAbsolutePath().getRoot().resolve(configFolderName);

        System.out.println(Files.exists(configDirectory));

        if (Files.exists(configDirectory)) {
            try {
                Files.createDirectories(configDirectory);
            } catch (IOException ex) {
                Logger.getLogger(Coordinates.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Coordinates.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        configDirectory = configDirectory.resolve("config-file.properties");
        System.out.println("Config directory: " + configDirectory);
        System.out.println(Files.exists(configDirectory));

        ObjectMapper mapper = new ObjectMapper();
        Coordinates coord = new Coordinates();

        coord.setSerialNumX(155);
        coord.setSerialNumY(568);
        coord.setLotNumX(300);
        coord.setLotNumY(568);
        coord.setStationNumX(505);
        coord.setStationNumY(568);
        coord.setDateX(380);
        coord.setDateY(98);
        coord.setOperatorX(80);
        coord.setOperatorY(80);
        coord.setSignDateX(420);
        coord.setSignDateY(80);
        coord.setCheckmarkEQ002002A_X(389);
        coord.setCheckmarkEQ002002A_Y(607);
        coord.setCheckmarkEQ002003_X(476);
        coord.setCheckmarkEQ002003_Y(607);
        coord.setFailureCoverSheetPage(5);
        coord.setMainCoverSheetPage(4);

        //update
        coord.setSerialNumX(1);
        coord.setSerialNumY(2);
        coord.setLotNumX(3);
        coord.setLotNumY(4);

        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(configDirectory.toFile(), coord);
        } catch (IOException ex) {
            Logger.getLogger(Coordinates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
