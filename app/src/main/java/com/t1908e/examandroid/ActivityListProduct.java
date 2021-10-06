package com.t1908e.examandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ActivityListProduct extends AppCompatActivity {
    private DBHelper db;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private ListView lvProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        db = new DBHelper(this);
        lvProducts = findViewById(R.id.lvProducts);
        cursor = db.getAllProducts();
        adapter = new SimpleCursorAdapter(this, R.layout.item_product, cursor, new String[]{DBHelper.ID, DBHelper.NAME, DBHelper.QUANTITY},
                new int[] {R.id.tvId, R.id.tvName, R.id.tvQuantity}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lvProducts.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //reload data;
        cursor = db.getAllProducts();
        adapter.changeCursor(cursor);
        adapter.notifyDataSetChanged();
        db.close();
    }
}