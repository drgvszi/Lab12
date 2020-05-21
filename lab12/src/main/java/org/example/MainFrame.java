package org.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.lang.reflect.Method;

public class MainFrame extends Application {

    public static int widthPx =800;
    public static int heightPx =600;

    public void customInit(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JAVAFX APP: Lab12");
        final BorderPane mainFrame = new BorderPane();
        final ControlPanel controlPanel = new ControlPanel();
        mainFrame.setTop(controlPanel);
        final Pane designPanel = new Pane();
        controlPanel.setActions(designPanel);
        mainFrame.setCenter(designPanel);
        mainFrame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                generateNewNode(controlPanel.getTextField().getText(), designPanel, event.getSceneX(), event.getSceneY() - controlPanel.getTextField().getHeight());
            }
        });
        primaryStage.setScene(new Scene(mainFrame, widthPx, heightPx));
        primaryStage.show();
    }
    public void generateNewNode(String inputClassName,Pane designPanel,Double x,Double y){
        try {
            CustomLoader customLoader = new CustomLoader();
            Class clazz= customLoader.loadClass(inputClassName);
            Node node = (Node) clazz.getConstructor().newInstance();
            node.setLayoutX(x);
            node.setLayoutY(y);
            Method methodToFind = clazz.getMethod("setText", new Class[] { String.class });
            if(methodToFind != null) {
                //methodToFind.invoke(node,"");
            }
            designPanel.getChildren().add(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
