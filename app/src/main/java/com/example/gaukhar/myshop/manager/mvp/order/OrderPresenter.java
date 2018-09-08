package com.example.gaukhar.myshop.manager.mvp.order;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.gaukhar.myshop.api.db.App;
import com.example.gaukhar.myshop.api.db.entity.Item;
import com.example.gaukhar.myshop.api.db.entity.Order;
import com.example.gaukhar.myshop.api.db.models.OrderItems;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class OrderPresenter extends MvpPresenter<OrderView> {

    private UUID mUuid;
    private double mPrice;
    private List<Order> mOrders;
    private List<OrderItems> mOrderItems;

    public OrderPresenter(){
        mUuid = UUID.randomUUID();
        mOrders = new ArrayList<>();
        mOrderItems = new ArrayList<>();
    }

    public void addItem(Item item){
        Order order = new Order();
        order.itemId = item.idItem;
        order.uuid = mUuid.toString();
        order.orderDate = new Date();
        mPrice += item.price;
        mOrders.add(order);
        for(Order o: mOrders){
            o.orderPrice = mPrice;
        }

        OrderItems orderItems = new OrderItems();
        orderItems.item = item;
        orderItems.order = order;
        mOrderItems.add(orderItems);

        getViewState().refreshAfterAddItemToOrder(mOrderItems);
    }

    public void removeItem(Item item){

        for(int i=0; i<mOrders.size(); i++){
            if(mOrders.get(i).itemId == item.idItem){
                mOrders.remove(i);
            }
        }
        mPrice -= item.price;
        for (Order o: mOrders){
            o.orderPrice = mPrice;
        }

        for (int i=0; i<mOrderItems.size(); i++) {
            if(mOrderItems.get(i).item.idItem == item.idItem){
                mOrderItems.remove(i);
            }
        }

        getViewState().refreshAfterAddItemToOrder(mOrderItems);
    }

    public void checkOut(){

        Maybe.fromAction(() -> App.getInstance().getDatabase().orderItemsDao()
                .insertAll(mOrders))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> getViewState().showLoadingIndicator(true))
                .doOnComplete(() -> getViewState().showLoadingIndicator(true))
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
