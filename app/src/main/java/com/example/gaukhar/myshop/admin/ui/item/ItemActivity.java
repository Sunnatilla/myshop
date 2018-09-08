package com.example.gaukhar.myshop.admin.ui.item;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.gaukhar.myshop.R;
import com.example.gaukhar.myshop.admin.mvp.item.ItemPresenter;
import com.example.gaukhar.myshop.admin.mvp.item.ItemView;
import com.example.gaukhar.myshop.api.db.entity.Item;
import com.example.gaukhar.myshop.base.activity.BaseActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemActivity extends BaseActivity implements ItemView, View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback {

    @InjectPresenter
    ItemPresenter presenter;

    @BindView(R.id.tvBarCode) TextView tvBarCode;
    @BindView(R.id.imgView) ImageView imgView;

    private File directory;
    private Uri imageUri;

    private static final int REQUEST_BAR_CODE = 1;
    private static final int REQUEST_CAPTURE_PHOTO_CODE = 2;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_item;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        createDirectory();

        tvBarCode.setOnClickListener(this);
        imgView.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_BAR_CODE){
                tvBarCode.setText(data.getStringExtra("code"));
            }
            if(requestCode == REQUEST_CAPTURE_PHOTO_CODE){
                Glide.with(this).load(imageUri).apply(new RequestOptions().centerCrop()).into(imgView);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tvBarCode){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                presenter.startBarcodeScannerActivity();
            }
            else{
                presenter.requestCameraPermission(REQUEST_BAR_CODE);
            }
        }
        else if(v.getId() == R.id.imgView){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
               ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                presenter.startCapturePhotoActivity();
            }
            else{
                presenter.requestCameraPermission(REQUEST_CAPTURE_PHOTO_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_BAR_CODE){
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                presenter.startBarcodeScannerActivity();
            }
        }
        else if(requestCode == REQUEST_CAPTURE_PHOTO_CODE){
            if(grantResults.length == 2 &&
               grantResults[0] == PackageManager.PERMISSION_GRANTED &&
               grantResults[1] == PackageManager.PERMISSION_GRANTED){
                presenter.startCapturePhotoActivity();
            }
            else if(grantResults.length == 3 &&
                    (grantResults[0] == PackageManager.PERMISSION_GRANTED ||
                     grantResults[1] == PackageManager.PERMISSION_GRANTED ||
                     grantResults[2] == PackageManager.PERMISSION_GRANTED)){
                presenter.startCapturePhotoActivity();
            }
        }
    }

    private void createDirectory() {
        directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"MyShopItemPictures");
        if (!directory.exists())
            directory.mkdirs();
    }

    private Uri generateFileUri(){
        File file = new File(directory.getPath() + "/" + "photo_" + System.currentTimeMillis() + ".jpg");
        return Uri.fromFile(file);
    }

    //------------------- ItemView implementation -----------------//

    @Override
    public void requestCameraPermission(int permission) {

        if(permission == REQUEST_BAR_CODE){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, permission);
        }
        else if(permission == REQUEST_CAPTURE_PHOTO_CODE){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, permission);
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, permission);
            }
        }
    }

    @Override
    public void startBarcodeScannerActivity() {
        Intent intent = new Intent(this, BarcodeScannerActivity.class);
        startActivityForResult(intent, REQUEST_BAR_CODE);
    }

    @Override
    public void startCapturePhotoActivity() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        imageUri = FileProvider
                            .getUriForFile(this,
                                            "com.example.gaukhar.myshop.fileprovider",
                                            new File(directory.getPath() + "/" + "photo_" + System.currentTimeMillis() + ".jpg"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, REQUEST_CAPTURE_PHOTO_CODE);
    }

    @Override
    public void successInserted(Item item) {

    }
}
