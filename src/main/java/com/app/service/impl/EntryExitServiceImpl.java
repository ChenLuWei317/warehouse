package com.app.service.impl;

import com.app.entity.EntryExit;
import com.app.mapper.EntryExitMapper;
import com.app.service.EntryExitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【软工2202_09_05_29进出仓表】的数据库操作Service实现
* @createDate 2024-10-17 19:41:27
*/
@Service
public class EntryExitServiceImpl extends ServiceImpl<EntryExitMapper, EntryExit>
    implements EntryExitService {
    public boolean saveEntryExit(EntryExit entryExit) {
        return this.save(entryExit); // 使用 MyBatis-Plus 的 save 方法
    }
}




