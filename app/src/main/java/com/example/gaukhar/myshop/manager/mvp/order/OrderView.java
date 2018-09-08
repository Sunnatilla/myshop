package com.example.gaukhar.myshop.manager.mvp.order;

import com.example.gaukhar.myshop.api.db.models.OrderItems;
import com.example.gaukhar.myshop.manager.mvp.base.BaseMvpView;

import java.util.List;

public interface OrderView extends BaseMvpView {

    void refreshAfterAddItemToOrder(List<OrderItems> orderItems);

    void successCheckOut();
}
