package slidenerd.vivz.aademo;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_with_extra)
public class ActivityWithExtra extends AppCompatActivity {

    public static final String MY_STRING_EXTRA = "myStringExtra";
    public static final String MY_DATE_EXTRA = "myDateExtra";
    public static final String MY_INT_EXTRA = "myIntExtra";

    @ViewById
    TextView extraTextView;
    @Extra("myStringExtra")
    String myMessage;
    @Extra("myDateExtra")
    long myDate;
    @Extra("myIntExtra")
    int myInt = 0;

    @AfterViews
    public void init() {
        extraTextView.setText(myMessage + " " + myDate + " " + myInt);
    }

}