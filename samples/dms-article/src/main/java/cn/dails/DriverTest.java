package cn.dails;

public class DriverTest {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("驱动加载成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动未找到！");
        }
    }
}
