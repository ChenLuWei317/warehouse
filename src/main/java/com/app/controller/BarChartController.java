package com.app.controller;

import com.app.provider.SceneProvider;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;

@Component
public class BarChartController extends BaseController{

    @FXML
    private BarChart<String, Number> barChart;


    @FXML
    private void initialize() {
        // 设置 x 轴（类别轴）和 y 轴（数字轴）
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        barChart.setTitle("Sample Bar Chart");
        xAxis.setLabel("Category");
        yAxis.setLabel("Value");

        // 创建数据系列
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Data Series 1");

        // 添加数据到数据系列
        series.getData().add(new XYChart.Data<>("Category 1", 25));
        series.getData().add(new XYChart.Data<>("Category 2", 40));
        series.getData().add(new XYChart.Data<>("Category 3", 60));

        // 将数据系列添加到条形图
        barChart.getData().add(series);
    }

    @FXML
    HBox menu;

    @FXML
    public void goToMain() {
        SceneProvider.switchScene(menu,"/fxml/user.fxml");
    }

}

