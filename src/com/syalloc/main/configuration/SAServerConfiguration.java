package com.syalloc.main.configuration;

import java.util.ArrayList;

public class SAServerConfiguration {
    private int port;
    private String index;
    private String pag_404;
    private ArrayList<SAApplicationConfiguration> applications;

    public ArrayList<SAApplicationConfiguration> getApplications() {
        return applications;
    }

    public void setApplications(ArrayList<SAApplicationConfiguration> applications) {
        this.applications = applications;
    }

    public String getPag_404() {
        return pag_404;
    }

    public void setPag_404(String pag_404) {
        this.pag_404 = pag_404;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
