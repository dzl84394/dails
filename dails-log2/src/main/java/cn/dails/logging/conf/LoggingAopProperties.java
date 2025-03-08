package cn.dails.logging.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "logging.aop")
public class LoggingAopProperties {
    private boolean enable = true;
    private String maxParamSize = "4MB";

    // Getters and Setters
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getMaxParamSize() {
        return maxParamSize;
    }

    public void setMaxParamSize(String maxParamSize) {
        this.maxParamSize = maxParamSize;
    }
}
