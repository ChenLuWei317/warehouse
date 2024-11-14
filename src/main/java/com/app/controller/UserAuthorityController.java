package com.app.controller;

import com.app.entity.Authority;
import com.app.entity.AuthorityManage;
import com.app.service.AuthorityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserAuthorityController {
    @FXML
    private TableView<AuthorityManage> tableView;

    @FXML
    private TableColumn<AuthorityManage, Integer> authorityIdColumn;
    @FXML
    private TableColumn<AuthorityManage, String> userIdColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private Pagination pagination;

    private ObservableList<AuthorityManage> data;

    @Autowired
    private AuthorityService authorityService;


    @FXML
    private void initialize() {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("人员代码"));
        authorityIdColumn.setCellValueFactory(new PropertyValueFactory<>("权限代码"));


        pagination.setPageFactory(this::createPage);
    }

    private TableView<AuthorityManage> createPage(int currentPage) {
        Page<AuthorityManage> page = new Page<>(currentPage, 10);
        IPage<AuthorityManage> iPage = authorityService.page(currentPage, 10, searchField.getText());
        data = FXCollections.observableArrayList(iPage.getRecords());
        tableView.setItems(data);
        return tableView;
    }

    @FXML
    private void search() {
        createPage(0);
    }
}