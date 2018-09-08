package com.example.gaukhar.myshop.admin.mvp.item;

import com.example.gaukhar.myshop.api.db.entity.Item;
import com.example.gaukhar.myshop.manager.mvp.base.BaseMvpView;

public interface ItemView extends BaseMvpView{

    void requestCameraPermission(int permission);

    void startBarcodeScannerActivity();

    void startCapturePhotoActivity();

    void successInserted(Item item);
}
