package com.jatinsinghroha.quotesapp.models.type_converters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jatinsinghroha.quotesapp.models.Title;

import java.lang.reflect.Type;

import androidx.room.TypeConverter;

public class TitleTypeConverter {
    private static Gson gson = new Gson();
    private static Type type = new TypeToken<Title>(){}.getType();

    @TypeConverter
    public static Title stringToTitleData(String json) {
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public static String titleDataToString(Title titleData) {
        return gson.toJson(titleData, type);
    }
}