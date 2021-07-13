package com.jatinsinghroha.quotesapp.models.type_converters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jatinsinghroha.quotesapp.models.Content;

import java.lang.reflect.Type;

import androidx.room.TypeConverter;

public class ContentTypeConverter {
    private static Gson gson = new Gson();
    private static Type type = new TypeToken<Content>(){}.getType();

    @TypeConverter
    public static Content stringToContentData(String json) {
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public static String contentDataToString(Content contentData) {
        return gson.toJson(contentData, type);
    }
}