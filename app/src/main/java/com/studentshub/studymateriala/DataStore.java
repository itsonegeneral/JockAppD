package com.studentshub.studymateriala;

import com.google.firebase.database.DatabaseReference;
import com.studentshub.studymateriala.models.Jock;
import com.studentshub.studymateriala.models.JockImage;

import java.util.ArrayList;

public class DataStore {
    public static ArrayList<Jock> jocks = new ArrayList<>();
    public static ArrayList<JockImage> imageJock = new ArrayList<>();

    public static void setJocks(ArrayList<Jock> jocks) {
        DataStore.jocks = new ArrayList<>();
        for (Jock jock : jocks) {
            if (jock != null) {
                DataStore.jocks.add(jock);
            }
        }
    }

}
