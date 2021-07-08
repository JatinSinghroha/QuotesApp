
package com.jatinsinghroha.quotesapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quotes_table")
public class Quote implements Serializable
{

    @SerializedName("tags")
    @Expose
    private List<String> tags = null;

    @PrimaryKey
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("authorSlug")
    @Expose
    private String authorSlug;
    @SerializedName("length")
    @Expose
    private Long length;
    @SerializedName("dateAdded")
    @Expose
    private String dateAdded;
    @SerializedName("dateModified")
    @Expose
    private String dateModified;
    private long savedAt = 0;

    public Quote(List<String> tags, String id, String author, String content, String authorSlug, Long length, String dateAdded, String dateModified) {
        this.tags = tags;
        this.id = id;
        this.author = author;
        this.content = content;
        this.authorSlug = authorSlug;
        this.length = length;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorSlug() {
        return authorSlug;
    }

    public void setAuthorSlug(String authorSlug) {
        this.authorSlug = authorSlug;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

}
