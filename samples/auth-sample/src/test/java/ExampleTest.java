import cn.dails.dao.entity.SysRolePermissionRelationEntity;
import cn.dails.dao.entity.SysUserRoleRelationEntity;
import cn.dails.example.Branch4_3_17;
import cn.dails.example.FactoryBranch4_3_17;

public class ExampleTest {

    public static void main(String[] args) {
        FactoryBranch4_3_17 fbranch4 = new FactoryBranch4_3_17();
        Branch4_3_17 branch1 = fbranch4.createExample();
        branch1.setRooturl("/Users/zlding/workspace/dails/samples/auth-sample");
        branch1.setServiceName("auth-sample");
        branch1.setJsUrl("/static/assets/js");
//        branch1.setRooturl("E:/work/dms3/fms");
        branch1.setUrl("cn/dails");
        branch1.initFiles();

        branch1.setEntityClass(SysUserRoleRelationEntity.class);
        branch1.initJava();
//        branch1.addDao();
//        branch1.addService();
//        branch1.initJsp();

    }
}
