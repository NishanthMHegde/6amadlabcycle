package com.nishanth.mynavigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {
  Button btnSave,btnDel;
    EditText newname;
    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        btnSave = (Button)findViewById(R.id.saveBtn);
        btnDel = (Button)findViewById(R.id.delBtn);
       newname = (EditText)findViewById(R.id.name);
       mDatabaseHelper = new DatabaseHelper(this);
        Intent i = getIntent();
       final String oldname = i.getStringExtra("name");
        final int id = i.getIntExtra("id",-1);
        newname.setText(oldname);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = newname.getText().toString();
                if(newUsername.length()!=0){
                    Toast.makeText(UpdateData.this," Saving Data",Toast.LENGTH_SHORT).show();
                    mDatabaseHelper.updateData(id,oldname,newUsername);

                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteData(oldname,id);
            }
        });
    }
}
