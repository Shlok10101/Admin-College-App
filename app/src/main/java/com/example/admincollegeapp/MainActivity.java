package com.example.admincollegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.admincollegeapp.faculty.UpdateFaculty;
import com.example.admincollegeapp.notice.DeleteNoticeActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout uploadNotice, addGalleryImage, addEbook, faculty, deleteNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uploadNotice  = findViewById(R.id.addNotice);
        addGalleryImage = findViewById(R.id.addGalleryImage);
        addEbook = findViewById(R.id.addEbook);
        faculty = findViewById(R.id.faculty);
        deleteNotice = findViewById(R.id.deleteNotice);

        uploadNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        uploadNotice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Start new activity
                Intent intent = new Intent(MainActivity.this, com.example.admincollegeapp.notice.uploadNotice.class);
                startActivity(intent);
            }
        });

        addGalleryImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Start new activity
                Intent intent = new Intent(MainActivity.this, UploadImage .class);
                startActivity(intent);
            }
        });

        addEbook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Start new activity
                Intent intent = new Intent(MainActivity.this, uploadPdfActivity .class);
                startActivity(intent);
            }
        });

        faculty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Start new activity
                Intent intent = new Intent(MainActivity.this, UpdateFaculty.class);
                startActivity(intent);
            }
        });

        deleteNotice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Start new activity
                Intent intent = new Intent(MainActivity.this, DeleteNoticeActivity.class);
                startActivity(intent);
            }
        });

        }
    }
