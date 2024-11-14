package com.app.controller;

import com.app.entity.Authority;
import com.app.service.AuthorityService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.control.ListView;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserAuthorityEditController {
    @FXML
    private ListView<Authority> listViewAllAuthorities;
    @FXML
    private ListView<Authority> listViewGrantedAuthorities;

    @Resource
    private AuthorityService authorityService;

    @FXML
    private void initialize() {
        List<Authority> allAuthorities = authorityService.listAllAuthorities();
        ObservableList<Authority> grantedAuthorities = FXCollections.observableArrayList();
        listViewAllAuthorities.setItems(FXCollections.observableArrayList(allAuthorities));
        listViewGrantedAuthorities.setItems(grantedAuthorities);
    }

    @FXML
    private void handleSaveAuthorities() {
        // 实现保存用户权限的逻辑
    }
}
