package com.syalloc.main.configuration;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/*
* 配置中心，通过读取configuration.yaml中的内容创建相应对象
*
* */
public class ConfigurationCenter {
    private static final String WEB_CONFIG_PATH = "configuration.yaml";
    private WebConfiguration webConfiguration;
    private static ConfigurationCenter sInstance;


    public static ConfigurationCenter getInstance() {
        if (sInstance == null) {
            sInstance = new ConfigurationCenter();
        }
        return sInstance;
    }

    private ConfigurationCenter() {
        Yaml yaml = new Yaml();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(WEB_CONFIG_PATH);
        this.webConfiguration = yaml.loadAs(is, WebConfiguration.class);
    }

    public WebConfiguration getWebConfiguration() {
        return webConfiguration;
    }

    public void setWebConfiguration(WebConfiguration webConfiguration) {
        this.webConfiguration = webConfiguration;
    }
}
