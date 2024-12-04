package com.app.controller;

import com.app.entity.EntryExit;
import com.app.entity.Goods;
import com.app.entity.LoginUser;
import com.app.entity.User;
import com.app.service.EntryExitService;
import com.app.service.GoodsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class StorageController {

    @Resource
    GoodsService goodsService; // 注入 UnitService
    @Autowired
    private EntryExitService entryExitService; // 注入 EntryExitService

    @FXML
    private TextField warehouseNumberField;

    @FXML
    private DatePicker entryTimePicker; // 更新为 DatePicker

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productQuantityField;

    @FXML
    private ComboBox<Goods> goodsComboBox; // 添加 ComboBox 控件

    @FXML
    private TextField handlerField;

    @FXML
    private TextField remarksField;
    @FXML
    private TableView<EntryExit> tableView; // 表格
    @FXML
    private TableColumn<EntryExit, String> goodsNameColumn;
    @FXML
    private TableColumn<EntryExit, String> goodNameColumn;
    @FXML
    private TableColumn<EntryExit, String> goodsTypeColumn;
    @FXML
    private TableColumn<EntryExit, String> goodsTimeColumn;
    @FXML
    private TableColumn<EntryExit, Integer> goodsNumberColumn;
    @FXML
    private TableColumn<EntryExit, String> goodsMessageColumn;
    @FXML
    private TableColumn<EntryExit, Void> actionsColumn; // 定义操作列
    private ObservableList<EntryExit> goodsList = FXCollections.observableArrayList();
    private ObservableList<Goods> goodList = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

// 设置日期列

        bindUnitsToComboBox();
        goodsNameColumn.setCellValueFactory(new PropertyValueFactory<>("物料代码"));
        goodsTypeColumn.setCellValueFactory(new PropertyValueFactory<>("类型"));
        goodsTimeColumn.setCellValueFactory(new PropertyValueFactory<>("日期"));
        goodsNumberColumn.setCellValueFactory(new PropertyValueFactory<>("数量"));

        goodsMessageColumn.setCellValueFactory(new PropertyValueFactory<>("备注"));
        goodNameColumn.setCellValueFactory(cellData -> {
            EntryExit entryExit = cellData.getValue();
            // 使用物料代码从 goodList 中找到 Goods 对象
            Goods goods = goodList.stream()
                    .filter(g -> g.get物料代码().equals(entryExit.get物料代码()))
                    .findFirst()
                    .orElse(null);
            // 返回物品名称
            return new SimpleStringProperty(goods != null ? goods.get物料名称() : "未找到");
        });
        initializeActionColumn();
        // 绑定数据到表格
        tableView.setItems(goodsList);




    }
    private void initializeActionColumn() {
        actionsColumn.setCellFactory(col -> new TableCell<EntryExit, Void>() {
            private final Button editButton = new Button("编辑");
            private final Button deleteButton = new Button("删除");
            private final HBox pane = new HBox(editButton, deleteButton);

            {
                pane.setAlignment(Pos.CENTER);
                pane.setSpacing(10);

                editButton.setOnAction(event -> {
                    EntryExit entryExit = getTableView().getItems().get(getIndex());
                    if (entryExit != null) {
                        showEditDialog(entryExit); // 显示编辑对话框
                    }
                });

                deleteButton.setOnAction(event -> {
                    EntryExit entryExit = getTableView().getItems().get(getIndex());
                    if (entryExit != null) {
                        handleDelete(entryExit); // 删除
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableView() == null) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });
    }

    private void showEditDialog(EntryExit entryExit) {
        Dialog<EntryExit> dialog = new Dialog<>();
        dialog.setTitle("编辑入库信息");
        dialog.setHeaderText("请修改入库信息");

        // 创建输入字段
        TextField productNameField = new TextField(String.valueOf(entryExit.get物料代码()));
        TextField productQuantityField = new TextField(String.valueOf(entryExit.get数量()));
        TextArea remarksField = new TextArea(entryExit.get备注());

        // 创建布局
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("物料代码:"), 0, 0);
        grid.add(productNameField, 1, 0);
        grid.add(new Label("数量:"), 0, 1);
        grid.add(productQuantityField, 1, 1);
        grid.add(new Label("备注:"), 0, 2);
        grid.add(remarksField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        ButtonType saveButtonType = new ButtonType("保存", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 处理保存按钮点击事件
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                entryExit.set数量(Integer.valueOf(productQuantityField.getText()));
                entryExit.set备注(remarksField.getText());
                // 更新表格数据
                tableView.refresh();
                return entryExit;
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void handleDelete(EntryExit entryExit) {
        goodsList.remove(entryExit);
        tableView.refresh();  // 刷新表格以反映更改
    }
    private void bindUnitsToComboBox() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        List<Goods> goodsData = goodsService.list(queryWrapper); // 更改变量名为 goodsData

        // 将查询结果添加到 goodList 中
        goodList.setAll(goodsData);

        // 使用 FilteredList 实现模糊搜索
        FilteredList<Goods> filteredGoods = new FilteredList<>(goodList, p -> true);
        goodsComboBox.setItems(filteredGoods);
        goodsComboBox.setEditable(true);

        // 监听 ComboBox 输入框中的文本变化，实现模糊过滤
        goodsComboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            filteredGoods.setPredicate(goods -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return goods.get物料名称().toLowerCase().contains(lowerCaseFilter);
            });
        });

        // 自定义 StringConverter，将 Goods 对象显示为物料名称
        goodsComboBox.setConverter(new StringConverter<Goods>() {
            @Override
            public String toString(Goods goods) {
                return goods == null ? "" : goods.get物料名称();
            }

            @Override
            public Goods fromString(String name) {
                return goodsComboBox.getItems().stream()
                        .filter(goods -> goods.get物料名称().equals(name))
                        .findFirst()
                        .orElse(null);
            }
        });

        // 更新编辑框显示内容为所选项的物料名称
        goodsComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                goodsComboBox.getEditor().setText(newValue.get物料名称());
            }
        });
    }

    @FXML
    private void handleSubmit() {
//        String warehouseNumber = warehouseNumberField.getText();
//        LocalDate localDate = entryTimePicker.getValue() != null ? entryTimePicker.getValue() : null;
//        Date entryTime = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        // 如果未选择，则为 null
        // 获取日期
//        String productName = productNameField.getText();
        String quantityText = productQuantityField.getText(); // 获取输入的数量文本
        if (quantityText.isEmpty()) {
            showAlert("数量不能为空！"); // 如果输入为空，提示用户
            return;
        }

        Integer productQuantity;
        try {
             productQuantity = Integer.valueOf(quantityText); // 将输入的文本转换为 Integer
        } catch (NumberFormatException e) {
            showAlert("请输入有效的正整数！"); // 提示用户输入无效
            return;
        }

        if (productQuantity <= 0) {
            showAlert("数量必须是正整数！"); // 如果数量小于或等于0，提示用户
            return;
        }
//        Integer productQuantity = Integer.valueOf(productQuantityField.getText());
        Goods selectedUnit = goodsComboBox.getValue(); // 获取选中的计量单位
        System.out.println(selectedUnit);
        String remarks = remarksField.getText();

        // 这里可以添加逻辑来处理获取的数据
        //String goodsName = selectedUnit.get物料名称();
        System.out.println("商品数量: " + productQuantity);
        System.out.println("商品名称: " + selectedUnit); // 打印选中的计量单位
//        System.out.println("经办人: " + currentUser.get人员代码());
        System.out.println("备注: " + remarks);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        User currentUser = loginUser.getCurrentUser();

        // 创建 EntryExit 对象
        EntryExit newEntryExit = new EntryExit();
        newEntryExit.set物料代码(selectedUnit.get物料代码()); // 假设 Goods 类有 get物料代码 方法
        newEntryExit.set类型("进仓"); // 设置类型为入库
        newEntryExit.set数量(productQuantity);
        newEntryExit.set日期(new Date()); // 当前日期
        newEntryExit.set操作人员代码(currentUser.get人员代码());
        newEntryExit.set备注(remarks);

        // 将新创建的 EntryExit 对象添加到表格的数据列表中
        goodsList.add(newEntryExit);

        // 清空输入字段
        productQuantityField.clear();
        remarksField.clear();
        goodsComboBox.getSelectionModel().clearSelection(); // 清空选择的商品
        // 这里可以添加代码来将数据保存到数据库
        // 创建 EntryExit 对象并保存到数据库

    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("警告");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void handleWarehouseEntry() {
        // 使用迭代器遍历 goodsList，避免 ConcurrentModificationException
        for (int i = 0; i < goodsList.size(); i++) {
            EntryExit entryExit = goodsList.get(i);

            // 保存每个 EntryExit 对象到数据库
            boolean isSaved = entryExitService.saveEntryExit(entryExit);
            if (!isSaved) {
                System.out.println("数据未能成功保存到数据库。");
                return; // 如果任何一个数据保存失败，就终止保存过程
            }

            // 获取对应的商品对象
            Goods selectedGoods = goodsComboBox.getItems().stream()
                    .filter(goods -> goods.get物料代码().equals(entryExit.get物料代码()))
                    .findFirst()
                    .orElse(null);

            if (selectedGoods == null) {
                System.out.println("未找到对应的商品信息。");
                continue; // 如果未找到商品，跳过库存更新
            }

            // 更新商品库存
            Integer currentStock = selectedGoods.get库存数量(); // 假设 Goods 类有一个 get库存数量() 方法
            Integer newStock = entryExit.get类型().equals("进仓") ? currentStock + entryExit.get数量() : currentStock - entryExit.get数量();

            // 检查更新后的库存是否为负值
            if (newStock < 0) {
                System.out.println("库存不足，无法完成操作。");
                continue; // 或者抛出异常
            }

            // 更新商品库存
            selectedGoods.set库存数量(newStock); // 假设 Goods 类有一个 set库存数量() 方法
            goodsService.updateById(selectedGoods); // 使用 MyBatis-Plus 的更新方法

            // 成功保存后从表格数据源中移除该 EntryExit 对象
            goodsList.remove(entryExit);
            i--;  // 调整索引，因为移除了一个元素
        }

        System.out.println("所有数据成功保存到数据库，并且库存已更新。");
        tableView.refresh();  // 刷新表格以反映更改
    }

}
