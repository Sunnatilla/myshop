package com.example.gaukhar.myshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gaukhar.myshop.admin.ui.item.ItemActivity;
import com.example.gaukhar.myshop.manager.ui.order.OrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.btnItems) Button btnItems;
    @BindView(R.id.btnOrders) Button btnOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnItems.setOnClickListener(this);
        btnOrders.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if(v.getId() == R.id.btnItems){
            intent = new Intent(this, ItemActivity.class);
        }
        else if(v.getId() == R.id.btnOrders){
            intent = new Intent(this, OrderActivity.class);
        }
        startActivity(intent);
    }
}
