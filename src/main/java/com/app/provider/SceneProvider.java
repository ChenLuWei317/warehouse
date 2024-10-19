package com.app.provider;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.SneakyThrows;

public class SceneProvider {
    private static double xOffset = 0;
    private static double yOffset = 0;
    @FXML
    private static javafx.scene.control.Hyperlink registerLink;
    @SneakyThrows
    public static Node switchScene(Node node, String fxmlURL) {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneProvider.class.getResource(fxmlURL));
        fxmlLoader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);


        Parent root = fxmlLoader.load();

        Window window = node.getScene().getWindow();
        if (window instanceof Stage) {
            Stage stage = (Stage) window;
            Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
            double width = screenRectangle.getWidth();
            double height = screenRectangle.getHeight();
            Scene scene = new Scene(root, (15.0/25)*width, (17.0/25)*height);
            // 处理拖动事件
            scene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            scene.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });

            stage.setScene(scene);
        }
          return node;
    }
}
