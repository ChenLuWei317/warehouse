package com.app.service;

import com.app.entity.EntryExit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ASUS
* @description 针对表【软工2202_09_05_29进出仓表】的数据库操作Service
* @createDate 2024-10-17 19:41:27
*/
public interface EntryExitService extends IService<EntryExit> {
    boolean saveEntryExit(EntryExit entryExit);
}
