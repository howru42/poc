package com.example.pmtios.poc;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CreateNewFolderActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_VIDEO_DOCUMENT = 1;
    String imageEncoded;
    List<String> imagesEncodedList;
    ArrayList<SelectedImageItem> selectedImageItems;
    RecyclerView rv_items;
    SelectedImagesCustomAdapter selectedImagesCustomAdapter;
    EditText folderName;
    JSONArray selectedDrawables;
    String id;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_folder);
        folderName = findViewById(R.id.editText);
        selectedDrawables = new JSONArray();
        preferences = getSharedPreferences("images", MODE_PRIVATE);

        if (getIntent() != null)
            id = getIntent().getStringExtra("id");
//        getGalleryItems();
        rv_items = findViewById(R.id.rv_items);
        rv_items.setLayoutManager(new GridLayoutManager(this, 3));
        selectedImageItems = new ArrayList<>();
        selectedImagesCustomAdapter = new SelectedImagesCustomAdapter(CreateNewFolderActivity.this, selectedImageItems);
        rv_items.setAdapter(selectedImagesCustomAdapter);
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (folderName.getText().toString().trim().length() == 0) {
                    Toast.makeText(CreateNewFolderActivity.this, "Folder name mandatory", Toast.LENGTH_SHORT).show();
                } else if (selectedItemsCount() == 0) {
                    Toast.makeText(CreateNewFolderActivity.this, "Please select at least one image", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject object = new JSONObject();
                    try {
                        object.put("name", folderName.getText().toString());
                        object.put("values", selectedDrawables.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (id == null)
                        id = String.valueOf(System.currentTimeMillis() % 1000);
                    preferences.edit().putString(id, object.toString()).commit();

                    finish();
                }
            }
        });
        getItems();

    }

    private int selectedItemsCount() {
        int i = 0;
        for (SelectedImageItem imageItem : selectedImageItems) {
            if (imageItem.isSelected()) {
                i++;
                selectedDrawables.put(imageItem.getImageDrawable());
            }
        }
        return i;
    }

    private void getItems() {

        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_1).setImgName("Picture 1"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_2).setImgName("Picture 2"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_3).setImgName("Picture 3"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_4).setImgName("Picture 4"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_5).setImgName("Picture 5"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_6).setImgName("Picture 6"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_7).setImgName("Picture 7"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_8).setImgName("Picture 8"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_9).setImgName("Picture 9"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_10).setImgName("Picture 10"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_11).setImgName("Picture 11"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_12).setImgName("Picture 12"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_13).setImgName("Picture 13"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_14).setImgName("Picture 14"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_15).setImgName("Picture 15"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_16).setImgName("Picture 16"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_17).setImgName("Picture 17"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_18).setImgName("Picture 18"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_19).setImgName("Picture 19"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_20).setImgName("Picture 20"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_21).setImgName("Picture 21"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_22).setImgName("Picture 22"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_23).setImgName("Picture 23"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_24).setImgName("Picture 24"));
        selectedImageItems.add(new SelectedImageItem().setImageDrawable(R.drawable.pic_25).setImgName("Picture 25"));

        if (id != null) {

            try {
                JSONObject json = new JSONObject(preferences.getString(id, ""));
                folderName.setText(json.getString("name"));
                JSONArray items = new JSONArray(json.getString("values"));
                ArrayList<Integer> arrayList = new ArrayList();
                for (int i = 0; i < items.length(); i++) {
                    arrayList.add(Integer.parseInt(items.getString(i)));
                }
                for (int i = 0; i < selectedImageItems.size(); i++) {
                    SelectedImageItem item = selectedImageItems.get(i);
                    item.setSelected(arrayList.contains(item.getImageDrawable()));
                    selectedImageItems.set(i, item);
                }
                Log.e("Size", selectedImageItems.size() + "");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.create_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.app_bar_add:
////                getGalleryItems();
//                getItems();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }

    public void getGalleryItems() {

        if (ContextCompat.checkSelfPermission(CreateNewFolderActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(CreateNewFolderActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                ActivityCompat.requestPermissions(CreateNewFolderActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PICK_IMAGE_VIDEO_DOCUMENT);
            }
        } else {
            // Permission is not granted
            if (Build.VERSION.SDK_INT <= 19) {
//                Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                photoPickerIntent.setType("image/* video/*");
//                startActivityForResult(photoPickerIntent, PICK_IMAGE_VIDEO_DOCUMENT);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Pictures"), PICK_IMAGE_VIDEO_DOCUMENT);

            } else {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Pictures"), PICK_IMAGE_VIDEO_DOCUMENT);
//
//                Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                String[] mimeTypes = {"image/*"};
//                photoPickerIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
//                photoPickerIntent.setType("image/*");
//                photoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                photoPickerIntent.setType("file/*");
//                photoPickerIntent.addCategory(Intent.CATEGORY_DEFAULT);
//
//                startActivityForResult(photoPickerIntent, PICK_IMAGE_VIDEO_DOCUMENT);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_VIDEO_DOCUMENT && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                imagesEncodedList = new ArrayList<String>();
                if (data.getData() != null) {

                    Uri mImageUri = data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    cursor.close();

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
