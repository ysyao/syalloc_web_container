package com.syalloc.main.servlet;

import com.syalloc.main.configuration.SAConstants;
import javax.servlet.Servlet;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import static sun.awt.geom.Crossings.debug;

public class SAServletProcessor {
    public void process(SARequest request, SAResponse response) throws ClassNotFoundException {
        String appName = request.getAppName();
        String servletName = "";
        if (appName.equals("syalloc_app")) {
            servletName = "com.syalloc.main.servlet.SALocalHelloServlet";
        }

        // TODO: 2019-03-24 这段代码不懂，技术债务
        URLClassLoader classLoader = null;
        try {
            URL[] urls = new URL[1];
            URLStreamHandler urlStreamHandler = null;
            File classPath = new File(SAConstants.WEB_APPS_PATH);
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, urlStreamHandler);
            classLoader = new URLClassLoader(urls);
        } catch (IOException e) {
            System.out.println(e.toString() );
        }
        Class myClass = null;

        myClass = classLoader.loadClass(servletName);

        Servlet servlet = null;
        SARequestFacade requestFacade = new SARequestFacade(request);
        SAResponseFacade responseFacade = new SAResponseFacade(response);
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service(requestFacade, responseFacade);
        } catch (Exception e) {
            System.out.println(e.toString());
        } catch (Throwable e) {
            System.out.println(e.toString());
        }
    }

    public static List getClasseNames(String jarName) {
        ArrayList classes = new ArrayList();

        if (debug)
            System.out.println("Jar " + jarName );
        try {
            JarInputStream jarFile = new JarInputStream(new FileInputStream(
                    jarName));
            JarEntry jarEntry;

            while (true) {
                jarEntry = jarFile.getNextJarEntry();
                if (jarEntry == null) {
                    break;
                }
                if (jarEntry.getName().endsWith(".class")) {
                    if (debug)
                        System.out.println("Found "
                                + jarEntry.getName().replaceAll("/", "\\."));
                    classes.add(jarEntry.getName().replaceAll("/", "\\."));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }



}
