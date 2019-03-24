package com.syalloc.main;

import com.syalloc.main.configuration.ConfigurationCenter;
import com.syalloc.main.configuration.SAServerConfiguration;
import com.syalloc.main.core.SAWebServer;

public class SyallocWebContainer {
    public static void main(String[] args) {
        //配置中心类生成Server相关的配置数据
        SAServerConfiguration serverConfiguration = ConfigurationCenter.getInstance().getWebConfiguration().getServer();
        //Server获取到这些配置数据，然后实例化
        SAWebServer server = new SAWebServer(serverConfiguration);
        server.start();
    }
}
