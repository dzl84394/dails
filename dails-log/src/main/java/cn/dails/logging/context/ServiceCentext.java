package cn.dails.logging.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

@Configuration
public class ServiceCentext {




    private static String clientIp;
    private static String hostName;


    @Value("${parentName:_unknown}")
    private String parentName;

    @Value("${spring.application.name}")
    private  String moduleName;

    public static String getClientIp() {
        if (clientIp != null)
            return clientIp;

        try {
            clientIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            clientIp = "_unknown";
        }
        return clientIp;
    }



    public static String getHostName() {
        if (hostName != null)
            return hostName;
        try {
//            hostName = execReadToString("hostname").trim();
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (IOException e) {
            hostName = "_unknown";
        }
        return hostName;
    }


    public String getParentName() {
        return parentName;
    }



    public String getModuleName() {
        return moduleName;
    }




    public static String execReadToString(String execCommand) throws IOException {
        Process proc = Runtime.getRuntime().exec(execCommand);
        try (InputStream stream = proc.getInputStream()) {
            try (@SuppressWarnings("resource")
                 Scanner s = new Scanner(stream).useDelimiter("\\A")) {
                return s.hasNext() ? s.next().trim() : "";
            } finally {
                stream.close();
            }
        }
    }



}
