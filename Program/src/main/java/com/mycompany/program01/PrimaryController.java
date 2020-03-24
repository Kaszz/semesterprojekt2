/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.program01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Kas
 */
public class PrimaryController implements Initializable {

    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtReplace;
    @FXML
    private TextArea txtFile;
    @FXML
    private Button btnOpen;
    @FXML
    private Button brnSave;
    @FXML
    private Button btnReplace;
    @FXML
    private FileChooser fc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fc = new FileChooser();
        fc.setInitialDirectory(new File("."));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fc.getExtensionFilters().add(extFilter);
    }    

    @FXML
    private void handleOpen(MouseEvent event) {
        File file = fc.showOpenDialog(null);
        Scanner scan = null;
        
        try {
            scan = new Scanner(file);
            String fileString = "";
            
            while(scan.hasNextLine()) {
                fileString += scan.nextLine() + "\n";
            }
            txtFile.setText(fileString); 
        } 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } 
        finally {
            scan.close();
        }

    }

    @FXML
    private void handleSave(MouseEvent event) {
        File file = fc.showSaveDialog(null);
        
        if (file != null) {
            saveTextToFile(txtFile.getText(), file);
        }
    }

    @FXML
    private void handleReplace(MouseEvent event) {
        String text = txtFile.getText();
        txtFile.setText(text.replaceAll(txtSearch.getText(), txtReplace.getText()));
    }
    
    private void saveTextToFile(String content, File file) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error opening the file" + file.getName());
        }
        writer.print(content);
        writer.close();
    }
}
