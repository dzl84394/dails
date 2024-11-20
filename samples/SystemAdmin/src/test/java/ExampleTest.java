import cn.dails.dao.entity.ConfCommEntity;
import cn.dails.dao.entity.DeviceCenterEntity;
import cn.dails.dao.entity.SubApiEntity;
import cn.dails.dao.entity.SubProjectEntity;
import cn.dails.example.Branch4;
import cn.dails.example.FactoryBranch4;

public class ExampleTest {

    public static void main(String[] args) {
        FactoryBranch4 fbranch4 = new FactoryBranch4();
        Branch4 branch1 = fbranch4.createExample();
        branch1.setRooturl("/Users/zlding/workspace/dails/samples/SystemAdmin");
        branch1.setServiceName("SystemAdmin");
        branch1.setJsUrl("/static/pms/js");
//        branch1.setRooturl("E:/work/dms3/fms");
        branch1.setUrl("cn/dails");
        branch1.initFiles();

        branch1.setEntityClass(SubProjectEntity.class);
        branch1.initJava();
//        branch1.addDao();
//        branch1.addService();
        branch1.initJsp();

    }
}
