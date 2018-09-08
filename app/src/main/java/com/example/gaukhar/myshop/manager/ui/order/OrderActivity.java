package com.example.gaukhar.myshop.manager.ui.order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.gaukhar.myshop.R;
import com.example.gaukhar.myshop.api.db.models.OrderItems;
import com.example.gaukhar.myshop.base.activity.BaseActivity;
import com.example.gaukhar.myshop.manager.mvp.order.OrderPresenter;
import com.example.gaukhar.myshop.manager.mvp.order.OrderView;

import java.util.List;

public class OrderActivity extends BaseActivity implements OrderView {

    @InjectPresenter
    OrderPresenter presenter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_order;
    }

    //---------------------- OrderView ------------------------//
    @Override
    public void refreshAfterAddItemToOrder(List<OrderItems> orderItems) {

    }

    @Override
    public void successCheckOut() {

    }
}
