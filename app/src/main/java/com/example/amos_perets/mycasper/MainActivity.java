package com.example.amos_perets.mycasper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;

import com.example.amos_perets.mycasper.manager_data.ManagerData;
import com.example.amos_perets.mycasper.recycler_main_header.AdapterMainHeader;
import com.example.amos_perets.mycasper.dialog.ShowDialog;
import com.example.amos_perets.mycasper.dialog.ShowDialogResult;
import com.example.amos_perets.mycasper.utils.ApplicationUtil;
import com.example.amos_perets.mycasper.utils.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private ManagerData managerData;

    private RecyclerView mainRecyclerView;

    private LinearLayoutManager linearLayoutManager;

    private Button buttonAddHeader;

    private ShowDialog viewDialog;

    private AdapterMainHeader adapterMainHeader;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();
        initObjects();
        initRecyclerView();

        buttonAddHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.showDialog();
            }
        });
        setSupportActionBar(toolbar);

    }

    private void showDialog() {
        managerData.createHeader();
    }

    private void initObjects() {
        viewDialog = new ShowDialog(this);
        new ShowDialogResult();
        managerData = new ManagerData(this);
        adapterMainHeader = new AdapterMainHeader(managerData);
    }

    private void initRecyclerView() {
        mainRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(linearLayoutManager);
        mainRecyclerView.setAdapter(adapterMainHeader);
    }

    private void findViews() {
        toolbar = findViewById(R.id.toolbar);
        buttonAddHeader = toolbar.findViewById(R.id.button_add_new_header);
        mainRecyclerView = findViewById(R.id.recycler_headers_list);
    }

}
