package com.syalloc.main.core;

import com.syalloc.main.configuration.SAServerConfiguration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SAWebServer {
    private SAServerConfiguration config;
    private ExecutorService mExecutorService;
    
    public SAWebServer(SAServerConfiguration config) {
        this.config = config;
        //create a thread pool
        this.mExecutorService = Executors.newCachedThreadPool();
    }

    /*
     * @Author yishiyao
     * @Description
     *              1.实例化ServerSocket
     *              2.让ServerSocket监听在配置的接口
     *              3.当有连接触发的时候，利用线程池启动SAWebServerThread
     * @Date 23:04 2019-03-23
     * @Param []
     * @return void
    */
    public void start() {
        try {
            ServerSocket server = new ServerSocket(this.config.getPort());
            Socket socket;
            boolean flag = true;
            while (flag) {
                socket = server.accept();
                System.out.print("客户端连接成功");
                this.mExecutorService.execute(new SAWebServerThread(socket, this.config));
            }
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
