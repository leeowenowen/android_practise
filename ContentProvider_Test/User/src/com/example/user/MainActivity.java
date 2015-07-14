package com.example.user;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	private SimpleCursorAdapter adapter;
	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		listView = new ListView(this);
		Button btn = new Button(this);
		layout.addView(btn);
		layout.addView(listView);
		setContentView(layout);

		ContentResolver contentResolver = getContentResolver();
		Uri selectUri = Uri.parse("content://com.example.provider.personProvider/person");
		Cursor cursor = contentResolver.query(selectUri, null, null, null, null);
		adapter = new SimpleCursorAdapter(this, R.layout.item, cursor, new String[] { "_id", "name", "age" }, new int[] { R.id.id, R.id.name, R.id.age });
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ListView lView = (ListView) parent;
				Cursor data = (Cursor) lView.getItemAtPosition(position);
				int _id = data.getInt(data.getColumnIndex("_id"));
				Toast.makeText(MainActivity.this, _id + "", 1).show();
			}
		});

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ContentResolver contentResolver = getContentResolver();
				Uri insertUri = Uri.parse("content://com.example.provider.personProvider/person");
				ContentValues values = new ContentValues();
				values.put("name", "xx");
				values.put("age", 988);
				Uri uri = contentResolver.insert(insertUri, values);
				Toast.makeText(MainActivity.this, "添加完成", 1).show();
			}
		});
	}
}
