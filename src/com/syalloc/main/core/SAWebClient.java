package com.syalloc.main.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SAWebClient {
    public static void main(String[] args) throws IOException {
        //键盘输入流
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        Socket socket = new Socket("127.0.0.1", 8081);
        socket.setSoTimeout(10000);

        //socket输出流
        PrintStream socketOut = new PrintStream(socket.getOutputStream());
        //socket输入流
        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        boolean flag = true;
        while(flag) {
            System.out.println("输入（\"bye\"则退出）：");
            String order = keyboardReader.readLine();
            System.out.println("输入内容：" + order);

            System.out.println("将内容写到服务端...");
            socketOut.println(order);
            if ("bye".equals(order)) {
                flag = false;
            } else {
                try {
                    String echo = socketIn.readLine();
                    System.out.println("服务端响应:"+echo);
                } catch (SocketTimeoutException e) {
                    System.out.println("Time out..");
                }
            }
        }
        socketIn.close();
        socket.close();
    }
}
