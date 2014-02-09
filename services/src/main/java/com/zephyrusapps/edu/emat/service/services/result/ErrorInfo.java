package com.zephyrusapps.edu.emat.service.services.result;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class ErrorInfo implements Serializable {
    String url;
    String errorMessage;

    public ErrorInfo() {
    }

    public ErrorInfo(String url, String errorMessage) {
        this.url = url;
        this.errorMessage = errorMessage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
