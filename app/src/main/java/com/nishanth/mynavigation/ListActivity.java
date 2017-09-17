package com.nishanth.mynavigation;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView lv;
    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lv = (ListView)findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);
       final ArrayList<String> names = new ArrayList<String>();
        Cursor data = mDatabaseHelper.getData();
        while(data.moveToNext()){
            names.add(data.getString(1));
        }
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,names);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = names.get(position);
                Cursor itemId= mDatabaseHelper.getItemId(name);
                int itemid = -1;
                while(itemId.moveToNext()){
                    itemid = itemId.getInt(0);
                }
                Intent i = new Intent(ListActivity.this,UpdateData.class);
                i.putExtra("id",itemid);
                i.putExtra("name",name);
                startActivity(i);

            }
        });

    }
}
