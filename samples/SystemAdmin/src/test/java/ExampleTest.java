import cn.dails.dao.entity.DeviceEntity;
import cn.dails.dao.entity.SubProjectEntity;
import cn.dails.dao.entity.SubServiceEntity;
import cn.dails.example.Branch3;
import cn.dails.example.FactoryBranch3;

public class ExampleTest {

    public static void main(String[] args) {
        FactoryBranch3 fbranch3 = new FactoryBranch3();
        Branch3 branch1 = fbranch3.createExample();
        branch1.setRooturl("/Users/zlding/workspace/dails/samples/SystemAdmin");
        branch1.setServiceName("SystemAdmin");
        branch1.setJsUrl("/static/pms/js");
//        branch1.setRooturl("E:/work/dms3/fms");
        branch1.setUrl("cn/dails");
        branch1.initFiles();

        branch1.setEntityClass(DeviceEntity.class);
        branch1.initJava();
//        branch1.addDao();
//        branch1.addService();
        branch1.initJsp();

    }
}
