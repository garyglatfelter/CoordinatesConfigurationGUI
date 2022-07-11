package com.gg.coordinatesconfigurationgui;

/**
 * This tool is intended to provide a more accessible and user-friendly way of
 * changing coordinates for the AcceptanceSheetLotPrint program.
 * @author Gary Glatfelter <garyglatfelter@gmail.com>
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * GUI for generating a configuration file for use with the AcceptanceSheetLotPrint program.
 * Goal is for end user to be able to tweak coordinates when inevitable layout changes
 * occur in the document which contains the cover sheets. Future improvements can
 * include validating the input (min & max ranges for each value, etc)
 * @author Gary Glatfelter <garyglatfelter@gmail.com>
 */
public class CoordinatesGUI extends Application {

    Coordinates.CoordConstants[] coordinateNamesArray = Coordinates.CoordConstants.values();
    int numOfCoordinateNames = coordinateNamesArray.length;
    Label[] labels = new Label[numOfCoordinateNames];
    TextField[] userInputs = new TextField[numOfCoordinateNames];
    Coordinates coordinates;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Acceptance Sheet Print Tool - Configuration Tool v1.1");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        

        
        Separator titleSeparator = new Separator();
        grid.add(titleSeparator, 0, 1, 4, 1);
        
        ReadConfigFile configuration = new ReadConfigFile();
        coordinates = configuration.getCoordinates();
        //addCoordinatePair(int row, int coordinateCode, int coordinate1Value, int coordinate2Value, GridPane grid
        int row = 2;
        addCoordinatePair(row, Coordinates.CoordConstants.SERIAL_NUM_X.coordinateCode, coordinates.getSerialNumX(), coordinates.getSerialNumY(), grid);
        row +=2;
        addCoordinatePair(row, Coordinates.CoordConstants.LOT_NUM_X.coordinateCode, coordinates.getLotNumX(), coordinates.getLotNumY(), grid);
        row +=2;
        addCoordinatePair(row, Coordinates.CoordConstants.STATION_NUM_X.coordinateCode, coordinates.getStationNumX(), coordinates.getStationNumY(), grid);
        row +=2;
        addCoordinatePair(row, Coordinates.CoordConstants.DATE_X.coordinateCode, coordinates.getDateX(), coordinates.getDateY(), grid);
        row +=2;
        addCoordinatePair(row, Coordinates.CoordConstants.CHECKMARK_EQ002002A_X.coordinateCode, coordinates.getCheckmarkEQ002002A_X(), coordinates.getCheckmarkEQ002002A_Y(), grid);
        row +=2;
        addCoordinatePair(row, Coordinates.CoordConstants.CHECKMARK_EQ002003_X.coordinateCode, coordinates.getCheckmarkEQ002003_X(), coordinates.getCheckmarkEQ002003_Y(), grid);
        row +=2;
        addCoordinatePair(row, Coordinates.CoordConstants.OPERATOR_X.coordinateCode, coordinates.getOperatorX(), coordinates.getOperatorY(), grid);
        row +=2;
        addCoordinatePair(row, Coordinates.CoordConstants.SIGN_DATE_X.coordinateCode, coordinates.getSignDateX(), coordinates.getSignDateY(), grid);
        
        row+=2;

        String defaultFailPage 
                = String.valueOf(coordinates.getFailureCoverSheetPage());

        labels[Coordinates.CoordConstants.FAILURE_COVER_SHEET_PAGE.coordinateCode]
                = new Label(Coordinates.CoordConstants.FAILURE_COVER_SHEET_PAGE.description);
        userInputs[Coordinates.CoordConstants.FAILURE_COVER_SHEET_PAGE.coordinateCode]
                = new TextField(defaultFailPage);
        userInputs[Coordinates.CoordConstants.FAILURE_COVER_SHEET_PAGE.coordinateCode]
                .setMaxWidth(75);
        grid.add(labels[Coordinates.CoordConstants.FAILURE_COVER_SHEET_PAGE.coordinateCode],
                0, row);
        grid.add(userInputs[Coordinates.CoordConstants.FAILURE_COVER_SHEET_PAGE.coordinateCode],
                1, row);

        row++;
        String defaultMainCoverPage = String.valueOf(
                coordinates.getMainCoverSheetPage());
        labels[Coordinates.CoordConstants.MAIN_COVER_SHEET_PAGE.coordinateCode]
                = new Label(Coordinates.CoordConstants.MAIN_COVER_SHEET_PAGE.description);
        userInputs[Coordinates.CoordConstants.MAIN_COVER_SHEET_PAGE.coordinateCode]
                = new TextField(defaultMainCoverPage);
        userInputs[Coordinates.CoordConstants.MAIN_COVER_SHEET_PAGE.coordinateCode].setMaxWidth(75);
        grid.add(labels[Coordinates.CoordConstants.MAIN_COVER_SHEET_PAGE.coordinateCode],
                0, row);
        grid.add(userInputs[Coordinates.CoordConstants.MAIN_COVER_SHEET_PAGE.coordinateCode],
                1, row);

        row++;
        //int offsetForFileButtons = Coordinates.CoordConstants.MAIN_COVER_SHEET_PAGE.coordinateCode + offset + 1;

        Separator fileInputSectionSeparator = new Separator();
        grid.add(fileInputSectionSeparator, 0, row, 2, 1);
        row++;
        
        //This can be experimented with and implemeneted later
        /*
        Label fileLabel = new Label("<--- Select IQC Acceptance Sheet File");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));

        Button fileButton = new Button("Select file with Acceptance Sheet");
        fileButton.setPrefWidth(280);
        fileButton.setOnAction(e -> {
            dataSheet = fileChooser.showOpenDialog(primaryStage);
            fileLabel.setText(dataSheet.getName());
            fileLabel.setTextFill(Color.BLACK);
        });
        grid.add(fileButton, 0, row, 2, 1);
        grid.add(fileLabel, 1, row, 2, 1);

        row++;
        */
        Button printButton = new Button("Generate Coordinates File");
        printButton.setTextFill(Color.GREEN);
        grid.add(printButton, 1, row, 2, 1);

        printButton.setOnMouseClicked(click -> {
            int[] userResponses = new int[numOfCoordinateNames];
            
            for (int i = 0; i < numOfCoordinateNames; i++) {
                userResponses[i] = Integer.parseInt(userInputs[i].getText());
            }

            ObjectMapper mapper = new ObjectMapper();
            Coordinates coord = new Coordinates();

            coord.setSerialNumX(userResponses[Coordinates.CoordConstants.SERIAL_NUM_X.coordinateCode]);

            coord.setSerialNumY(userResponses[Coordinates.CoordConstants.SERIAL_NUM_Y.coordinateCode]);

            coord.setLotNumX(userResponses[Coordinates.CoordConstants.LOT_NUM_X.coordinateCode]);

            coord.setLotNumY(userResponses[Coordinates.CoordConstants.LOT_NUM_Y.coordinateCode]);

            coord.setStationNumX(userResponses[Coordinates.CoordConstants.STATION_NUM_X.coordinateCode]);

            coord.setStationNumY(userResponses[Coordinates.CoordConstants.STATION_NUM_Y.coordinateCode]);

            coord.setDateX(userResponses[Coordinates.CoordConstants.DATE_X.coordinateCode]);

            coord.setDateY(userResponses[Coordinates.CoordConstants.DATE_Y.coordinateCode]);

            coord.setOperatorX(userResponses[Coordinates.CoordConstants.OPERATOR_X.coordinateCode]);

            coord.setOperatorY(userResponses[Coordinates.CoordConstants.OPERATOR_Y.coordinateCode]);

            coord.setSignDateX(userResponses[Coordinates.CoordConstants.SIGN_DATE_X.coordinateCode]);

            coord.setSignDateY(userResponses[Coordinates.CoordConstants.SIGN_DATE_Y.coordinateCode]);

            coord.setCheckmarkEQ002002A_X(userResponses[Coordinates.CoordConstants.CHECKMARK_EQ002002A_X.coordinateCode]);

            coord.setCheckmarkEQ002002A_Y(userResponses[Coordinates.CoordConstants.CHECKMARK_EQ002002A_Y.coordinateCode]);

            coord.setCheckmarkEQ002003_X(userResponses[Coordinates.CoordConstants.CHECKMARK_EQ002003_X.coordinateCode]);

            coord.setCheckmarkEQ002003_Y(userResponses[Coordinates.CoordConstants.CHECKMARK_EQ002003_Y.coordinateCode]);

            coord.setFailureCoverSheetPage(userResponses[Coordinates.CoordConstants.FAILURE_COVER_SHEET_PAGE.coordinateCode]);

            coord.setMainCoverSheetPage(userResponses[Coordinates.CoordConstants.MAIN_COVER_SHEET_PAGE.coordinateCode]);

            try {
                mapper.writerWithDefaultPrettyPrinter()
                        .writeValue(configuration.getCONFIG_FILE_PATH().toFile(), coord);
                Desktop desktop = Desktop.getDesktop();
                desktop.open(configuration.getCONFIG_DIRECTORY().toFile());
                
            } catch (IOException ex) {
                Logger.getLogger(Coordinates.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Configuration Tool");
        primaryStage.show();
    }
    
    /**
     * This will add the X and Y paired coordinates to a single row in the GridPane.
     * Row layout: The first label will be a description of which coordinate,
     * then a TextField for the X coordinate, a label for the Y coordinate, 
     * and lastly a TextField for the Y coordinate.
     * 
     * @param row This is the row number in the GridPane grid to display this coordinate pair
     * @param coordinateCode This is an index found in Coordinates.CoordConstants.
     * @param coordinate1Value This is the integer value for the X coordinate.
     * @param coordinate2Value This is the integer value for the Y coordinate.
     * @param grid This is GridPane instance where the row is to be added.
     */
    public void addCoordinatePair(int row, int coordinateCode,
            int coordinate1Value, int coordinate2Value, GridPane grid){
            
            // Grab x and y pair and add to grid in pairs
            Coordinates.CoordConstants coordinateName1 = coordinateNamesArray[coordinateCode];
            Coordinates.CoordConstants coordinateName2 = coordinateNamesArray[coordinateCode + 1];

            String defaultValue1 = String.valueOf(coordinate1Value);
            String defaultValue2 = String.valueOf(coordinate2Value);

            labels[coordinateName1.coordinateCode]
                    = new Label(coordinateName1.description);
            labels[coordinateName2.coordinateCode]
                    = new Label(coordinateName2.description);
            userInputs[coordinateName1.coordinateCode]
                    = new TextField(defaultValue1);
            userInputs[coordinateName2.coordinateCode]
                    = new TextField(defaultValue2);
            userInputs[coordinateName1.coordinateCode].setMaxWidth(75);
            userInputs[coordinateName2.coordinateCode].setMaxWidth(75);

            grid.add(labels[coordinateName1.coordinateCode], 0, row);
            grid.add(userInputs[coordinateName1.coordinateCode], 1, row);
            grid.add(labels[coordinateName2.coordinateCode], 2, row);
            grid.add(userInputs[coordinateName2.coordinateCode], 3, row);
            
            row++;
            Separator separator = new Separator();
            grid.add(separator, 0, row, 4, 1);
    }

    /**
     * Main class. Starts the GUI.
     * @param args No arguments are utilized.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
