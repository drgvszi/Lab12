package org.example;

import javafx.scene.control.Button;


@Width(200)
@Height(50)
@Text("Hello World")
public class MyButton extends Button {

    public MyButton() {
        AnnotationController annotationController = new AnnotationController();
        try {
            annotationController.initializeButton(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setStyle(" -fx-background-color: red; -fx-color-label-visible: white");
        this.setOpacity(0.5);

    }

}