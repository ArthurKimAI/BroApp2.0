package com.example.broapp20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private Button btnDelete;
    private EditText edtTxtName;
    private EditText edtTxtPhone;
    private EditText edtTxtTime;
    private Button btnViewAll;
    private ListView lv_customerView;

    ArrayAdapter customerArrayAdapter;

    DataBaseHelper dataBaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        edtTxtName = findViewById(R.id.edtTxtName);
        edtTxtPhone = findViewById(R.id.edtTxtPhone);
        edtTxtTime = findViewById(R.id.edtTxtTime);
        btnViewAll = findViewById(R.id.btnViewAll);
        lv_customerView = findViewById(R.id.lv_customerList);

        ShowCustomerOnListView(dataBaseHelper);

        new DataBaseHelper(MainActivity.this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomerModel customerModel;
                try {
                    customerModel = new CustomerModel(edtTxtName.getText().toString(), Double.parseDouble(edtTxtTime.getText().toString()), Double.parseDouble(edtTxtPhone.getText().toString()));
                    Toast.makeText(MainActivity.this, "Customer " + customerModel.toString() + " added", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel("Error occured",-1, -1);
                }


                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success = dataBaseHelper.addOne(customerModel);

                Toast.makeText(MainActivity.this, "Success " + success, Toast.LENGTH_SHORT).show();
                ShowCustomerOnListView(dataBaseHelper);
            }
        });
        
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                // set ArrayAdapter to be able to display information from DB onto the ListView
                new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
                lv_customerView.setAdapter(customerArrayAdapter);

//                Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //setOnItem allows to operate with items in ListView (for instance "DELETE or EDIT")
        lv_customerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long customerName) {
                CustomerModel clickedCustomer = (CustomerModel) adapterView.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedCustomer);
                ShowCustomerOnListView(dataBaseHelper);
                Toast.makeText(MainActivity.this, "Deleted" + clickedCustomer, Toast.LENGTH_SHORT).show();
            }
        });


    }



    private void ShowCustomerOnListView(DataBaseHelper dataBaseHelper2) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryone());
        lv_customerView.setAdapter(customerArrayAdapter);
    }

}