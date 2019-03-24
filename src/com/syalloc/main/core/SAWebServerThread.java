package com.syalloc.main.core;

import com.syalloc.main.configuration.ConfigurationCenter;
import com.syalloc.main.configuration.SAApplicationConfiguration;
import com.syalloc.main.configuration.SAConstants;
import com.syalloc.main.configuration.SAServerConfiguration;
import com.syalloc.main.servlet.SARequest;
import com.syalloc.main.servlet.SAResponse;
import com.syalloc.main.servlet.SAServletProcessor;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import static com.syalloc.main.configuration.SAConstants.HTTP_HEADER_404;

public class SAWebServerThread implements Runnable {

    private Socket socket;
    private SAServerConfiguration config;

    /*
     * @Author yishiyao
     * @Description 服务某个连接请求的线程
     * @Date 23:07 2019-03-23
     * @Param [socket TCP套接字, config 服务配置中心关于Server配置]
     * @return
    */
    public SAWebServerThread(Socket socket, SAServerConfiguration config) {
        this.socket = socket;
        this.config = config;
    }

    @Override
    public void run() {
        System.out.println("SAWebServerThread.run()");
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true);
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            StringBuffer sb = new StringBuffer();
            String d;
            while ((d = in.readLine()) != null) {
                if (d.equals("")) {
                    break;
                }
                sb.append(d);
            }

            System.out.println("收到内容："+sb.toString());

            // 2019-03-22 获取请求头的应用名称，通名称实例化对应的servlet，然后将请求发过去.
            SARequest request = new SARequest(sb.toString());
            SAResponse response = new SAResponse(out);
            response.setRequest(request);
            String appName = request.getAppName();

            // TODO: 2019-03-24 通过读取配置文件将servlet类名和webapp名映射得到对应的servlet
            if (hasApplicationOfName(appName)) {
                SAServletProcessor processor = new SAServletProcessor();
                processor.process(request, response);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(HTTP_HEADER_404);
                Stream<String> stream = Files.lines(Paths.get(this.config.getPag_404()), StandardCharsets.UTF_8);
                stream.forEach(s -> stringBuilder.append(s).append("\n"));
                out.print(stringBuilder.toString());
                out.flush();
            }
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            pageNotFound(out);
        }
        finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /*
     * @Author yishiyao
     * @Description 通过uri中的应用名称来查询在配置中心当中是否有配置此应用的信息
     * @Date 23:12 2019-03-23
     * @Param [appName uri中的应用名称]
     * @return boolean 是否有应用的配置信息
    */
    private boolean hasApplicationOfName(String appName) {
        for (SAApplicationConfiguration application :
                this.config.getApplications()) {
            if (appName.equals(application.getAppName())) {
                return true;
            }
        }
        return false;
    }

    private void pageNotFound(PrintWriter out) {
        String pageNotFound = ConfigurationCenter.getInstance().getWebConfiguration().getServer().getPag_404();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SAConstants.HTTP_HEADER_404);
        try {

            Stream<String> stream = Files.lines(Paths.get(pageNotFound), StandardCharsets.UTF_8);
            stream.forEach(s -> stringBuilder.append(s).append("\n"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        out.print(stringBuilder.toString());
    }
}
