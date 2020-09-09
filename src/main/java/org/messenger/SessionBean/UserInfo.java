package org.messenger.SessionBean;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(
        scopeName = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserInfo {
    private String name;
    private int textCounter;
    private int nameCounter;



    public String getName() {
        return name;
    }

    public int getTextCounter() {
        return textCounter;
    }

    public void setTextCounter(int textCounter) {
        this.textCounter = textCounter;
    }

    public int getNameCounter() {
        return nameCounter;
    }

    public void setNameCounter(int nameCounter) {
        this.nameCounter = nameCounter;
    }

    public void setName(String name) {
        this.name = name;
    }


}
