package com.example.assignmentandroid;

import androidx.room.TypeConverter;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataConverter implements Serializable {

    @TypeConverter
    public static List<String> gettingListFromString(String genreIds) {
        List<String> list = new ArrayList<>();

        String[] array = genreIds.split(",");

        for (String s : array) {
            if (!s.isEmpty()) {
                list.add(s);
            }
        }
        return list;
    }

    @TypeConverter
    public static String writingStringFromList(List<String> list) {
        String genreIds = "";
        for (String i : list) {
            genreIds += "," + i;
        }
        return genreIds;
    }

    @TypeConverter
    public static String stringFromListOfLanguages( List <Language> languages )
    {
        String str = "";

        for (int i =0 ; i<languages.size() ; i++)
        {
            str = str + languages.get(i).getIso639_1() + ",";
            str = str + languages.get(i).getIso639_2() + ",";
            str = str + languages.get(i).getName() + ",";
            str = str + languages.get(i).getNativeName() + "/";
        }

        return str;
    }

    public static List<Language> listOfLanguageFromString ( String str)
    {
        List<Language> languages = new ArrayList<>();

        String str2 [] = str.split("/");

        for (int i=0 ; i < str2.length && str2.length!=0 ; i++)
        {
         String[] str3 = str2[i].split(",");
         if (str3.length!=0) {
             Language language = new Language(str3[0], str3[1], str3[2], str3[3]);
             languages.add(language);
         }
        }

        return languages;
    }
}

