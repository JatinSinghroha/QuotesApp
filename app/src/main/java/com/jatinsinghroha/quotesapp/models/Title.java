
package com.jatinsinghroha.quotesapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Title implements Serializable
{

    @SerializedName("rendered")
    @Expose
    private String rendered;
    private final static long serialVersionUID = 1894060740779277308L;

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

}
