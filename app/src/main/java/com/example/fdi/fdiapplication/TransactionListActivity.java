package com.example.fdi.fdiapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;


public class TransactionListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.y = 100;
        getWindow().setAttributes(lp);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, 700);

    }
}
