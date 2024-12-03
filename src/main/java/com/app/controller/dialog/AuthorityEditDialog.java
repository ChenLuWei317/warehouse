package com.app.controller.dialog;

import com.app.entity.Authority;
import com.app.entity.User;
import com.app.service.AuthorityManageService;
import com.app.service.AuthorityService;
import com.app.service.UserService;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorityEditDialog extends Stage {

    public AuthorityEditDialog(User user, AuthorityService permissionService, AuthorityManageService authorityManageService) {
        setTitle("编辑权限 - " + user.get姓名());
        initModality(Modality.APPLICATION_MODAL);

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        // 获取所有权限
        List<Authority> allPermissions = permissionService.list();
        // 获取用户当前拥有的权限ID
        List<Integer> userPermissionIds = permissionService.getPermissionsByUserId(user.get人员代码())
                .stream()
                .map(Authority::get权限代码)
                .collect(Collectors.toList());

        // 创建复选框列表
        List<CheckBox> checkBoxes = allPermissions.stream().map(permission -> {
            CheckBox cb = new CheckBox(permission.get权限名称());
            if (userPermissionIds.contains(permission.get权限代码())) {
                cb.setSelected(true);
            }
            return cb;
        }).collect(Collectors.toList());

        vbox.getChildren().addAll(checkBoxes);

        // 保存按钮
        Button saveButton = new Button("保存");
        saveButton.setOnAction(e -> {
            // 收集选中的权限ID
            List<Integer> selectedPermissionIds = checkBoxes.stream()
                    .filter(CheckBox::isSelected)
                    .map(cb -> {
                        String name = cb.getText();
                        return allPermissions.stream()
                                .filter(p -> p.get权限名称().equals(name))
                                .findFirst()
                                .map(Authority::get权限代码)
                                .orElse(null);
                    })
                    .filter(id -> id != null)
                    .collect(Collectors.toList());

            // 更新用户权限
            authorityManageService.updateAuthorityManage(user.get人员代码(), selectedPermissionIds);

            // 关闭对话框
            close();
        });

        vbox.getChildren().add(saveButton);

        Scene scene = new Scene(vbox, 300, 400);
        setScene(scene);
    }
}
