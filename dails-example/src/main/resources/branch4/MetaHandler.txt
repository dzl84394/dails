package cn.dails.conf;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MetaHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"createDate", Date.class,new Date());
        this.strictInsertFill(metaObject,"updateDate", Date.class,new Date());
        this.strictInsertFill(metaObject,"deleted", Integer.class,0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"updateDate", Date.class,new Date());
    }
}
