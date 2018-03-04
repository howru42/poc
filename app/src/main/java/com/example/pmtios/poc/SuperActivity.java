package com.example.pmtios.poc;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by PMTIOS on 3/4/18.
 */

public class SuperActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_VIDEO_DOCUMENT = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void getGalleryItems() {

        if (ContextCompat.checkSelfPermission(SuperActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(SuperActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                ActivityCompat.requestPermissions(SuperActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PICK_IMAGE_VIDEO_DOCUMENT);
            }
        } else {
            // Permission is not granted
            if (Build.VERSION.SDK_INT <= 19) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                photoPickerIntent.setType("image/* video/*");
                startActivityForResult(photoPickerIntent, PICK_IMAGE_VIDEO_DOCUMENT);
            } else {
                Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                String[] mimeTypes = {"image/*"};
                photoPickerIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                photoPickerIntent.setType("image/* video/*");
                photoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                photoPickerIntent.setType("file/*");
                photoPickerIntent.addCategory(Intent.CATEGORY_DEFAULT);

                startActivityForResult(photoPickerIntent, PICK_IMAGE_VIDEO_DOCUMENT);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PICK_IMAGE_VIDEO_DOCUMENT:
                getGalleryItems();
                return;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                return;
        }

    }
}

