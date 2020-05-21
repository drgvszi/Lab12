package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ControlPanel extends GridPane {
    final TextField textField = new TextField();
    Button save = new Button("Save");
    Button load = new Button("Load");

    ControlPanel() {
        this.setVgap(5);
        this.setHgap(5);
        textField.setPromptText("Enter the class name and press enter.");
        textField.setPrefColumnCount(20);
        textField.setText("javafx.scene.control.Button");
        GridPane.setConstraints(textField, 0, 0);
        this.getChildren().add(textField);
        GridPane.setConstraints(save, 1, 0);
        this.getChildren().add(save);
        GridPane.setConstraints(load, 2, 0);
        this.getChildren().add(load);
    }

    public TextField getTextField() {
        return textField;
    }

    public void setActions(final Pane designPanel) {
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                writeXml(designPanel);
            }
        });
        load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                designPanel.getChildren().clear();
                designPanel.getChildren().addAll(readXml().getChildren());
            }
        });
    }

    public void writeXml(Pane designPanel) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("SAVEXML.xml")))) {
            for (Node children :
                    designPanel.getChildren()) {
                encoder.writeObject(children);
            }
        } catch (Exception ignored) {
        }
    }

    public Pane readXml() {
        try (FileInputStream fis = new FileInputStream("SAVEXML.xml")) {
            XMLDecoder decoder = new XMLDecoder(fis);
            Node decodedNode;
            Pane decodedPane = new Pane();
            try {
                while ((decodedNode = (Node) decoder.readObject()) != null) {
                    decodedPane.getChildren().add(decodedNode);
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            decoder.close();
            return decodedPane;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}