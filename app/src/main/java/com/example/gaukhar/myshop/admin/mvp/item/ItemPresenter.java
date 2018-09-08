package com.example.gaukhar.myshop.admin.mvp.item;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.gaukhar.myshop.api.db.App;
import com.example.gaukhar.myshop.api.db.entity.Item;

import java.io.File;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ItemPresenter extends MvpPresenter<ItemView> {

    public void requestCameraPermission(int permission){
        getViewState().requestCameraPermission(permission);
    }

    public void startBarcodeScannerActivity(){
        getViewState().startBarcodeScannerActivity();
    }

    public void startCapturePhotoActivity(){
        getViewState().startCapturePhotoActivity();
    }

    public void getItemAll(){
        App.getInstance().getDatabase().itemDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((onSubscribe) -> getViewState().showLoadingIndicator(true))
                .doOnComplete(() -> getViewState().showLoadingIndicator(false))
                .doOnSuccess(new Consumer<List<Item>>() {
                    @Override
                    public void accept(List<Item> items) throws Exception {

                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void setItem(Item item){
        Maybe.fromAction(() -> App.getInstance().getDatabase().itemDao()
                .insert(item))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v)-> getViewState().showLoadingIndicator(true))
                .doOnComplete(() -> getViewState().showLoadingIndicator(false))
                .doOnSuccess(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
