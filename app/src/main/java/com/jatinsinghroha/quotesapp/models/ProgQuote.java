
package com.jatinsinghroha.quotesapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jatinsinghroha.quotesapp.models.type_converters.ContentTypeConverter;
import com.jatinsinghroha.quotesapp.models.type_converters.TitleTypeConverter;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "programming_quotes_table")
public class ProgQuote implements Serializable
{

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("title")
    @TypeConverters(TitleTypeConverter.class)
    @Expose
    private Title title;
    @TypeConverters(ContentTypeConverter.class)
    @SerializedName("content")
    @Expose
    private Content content;

    private Long savedAt = System.currentTimeMillis();

    public Long getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Long savedAt) {
        this.savedAt = savedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

}




