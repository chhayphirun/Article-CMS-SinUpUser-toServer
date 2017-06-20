package com.kshrd.articlecms.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.kshrd.articlecms.R;
import com.kshrd.articlecms.util.RetrofitUtil;
import com.kshrd.articlecms.webservice.ImageService;
import com.kshrd.articlecms.webservice.ServiceGenerator;

import java.io.File;
import java.security.Permission;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {


    private static final int OPEN_GALLERY = 1;
    private static final int READ_EXTERNAL_STORAGE_CODE = 1;
    private Unbinder unbinder;
    private String imagePath;
    private ImageService imageService;
    private SpotsDialog dialog;

    @BindView(R.id.ivThumbnail)
    CircleImageView ivThumbnail;

    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        unbinder = ButterKnife.bind(this);
        imageService = ServiceGenerator.createService(ImageService.class);
        dialog = new SpotsDialog(this, "Please wait...");


    }

    @OnClick(R.id.btnSubmit)
    void onSubmitClicked(){

        // Email Filed
        RequestBody email = RetrofitUtil.toRequestBody("admin@gmail.com");



        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("PHOTO", file.getName(), requestBody);


        Call<JsonObject> uploadImage = imageService.uploadSingleImage(body);

        dialog.show();
        uploadImage.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();
                    Toast.makeText(SignupActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
                Toast.makeText(SignupActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnBrowse)
    void onButtonBrowseClicked() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkedPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            if (checkedPermission == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions, READ_EXTERNAL_STORAGE_CODE);
            } else {
                showGallery();
            }
        } else {
            showGallery();
        }
    }

    void showGallery() {
        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, OPEN_GALLERY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXTERNAL_STORAGE_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showGallery();
            } else {
                Toast.makeText(this, "Please Grant Permisson", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImage = data.getData();
        String[] pathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectedImage, pathColumn, null, null, null);
        if (cursor.moveToFirst()){
            int columnIndex = cursor.getColumnIndex(pathColumn[0]);
            imagePath = cursor.getString(columnIndex);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            ivThumbnail.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
