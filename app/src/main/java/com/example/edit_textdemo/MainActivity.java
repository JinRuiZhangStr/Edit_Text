package com.example.edit_textdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edit_textdemo.util.SharedUtils;

public class MainActivity extends AppCompatActivity {

    private EditText edt_name;
    private EditText edt_phone;
    private EditText edt_address;
    private SharedUtils shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        if (null!=shared.getShared("status",this)){
            if ("1".equals(shared.getShared("status",this))){
                String name= shared.getShared("name",this);
                String phone=shared.getShared("phone",this);
                String address=shared.getShared("address",this);

                substring(name,phone,address);

            }
        }


    }

    private void initView() {
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_address = (EditText) findViewById(R.id.edt_address);

        shared = new SharedUtils();
    }

    public void commit(View view){
        if (TextUtils.isEmpty(edt_name.getText().toString())||
                TextUtils.isEmpty(edt_phone.getText().toString())||
                TextUtils.isEmpty(edt_address.getText().toString()))
            Toast.makeText(this, "三项不能为空", Toast.LENGTH_SHORT).show();
        else if (11!=edt_phone.length()){
            Toast.makeText(this, "手机号不得小于11位", Toast.LENGTH_SHORT).show();
        }else {
            shared.saveShared("name",edt_name.getText().toString(),this);
            shared.saveShared("phone",edt_phone.getText().toString(),this);
            shared.saveShared("address",edt_address.getText().toString(),this);
            shared.saveShared("status","1",this);
            Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        }

    }

    private void substring(String name,String phone,String address){
        if (name.length()==1){
            edt_name.setText(name+"**");
        }else {
            String oldName = name.substring(1, name.length());
            Log.e("oldName", "substring: "+oldName );
            String charname=name.substring(0,1);
            Log.e("charname", "substring: "+charname );
            String newName = name.replace(oldName, "**");
            Log.e("newname", "substring: "+newName );
            edt_name.setText(newName);

            String newPhone=phone.replace(phone.substring(3,phone.length()),"********");
            edt_phone.setText(newPhone);

        }
        


    }
}
