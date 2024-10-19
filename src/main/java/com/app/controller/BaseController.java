package com.app.controller;

import com.app.provider.SceneProvider;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class BaseController {
    @FXML
    HBox menu;

    @FXML
    public void goToBarChart() {
        SceneProvider.switchScene(menu,"/fxml/barchart.fxml");
    }

    @FXML
    private void handleClose() {
        Platform.exit();
    }
}
