//package com.eatfit.view.addFood;
//
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//public class SearchSQLiteActivity extends AppCompatActivity {
//
//    ListView listView;
//    ArrayList<Student> StudentList = new ArrayList<Student>();
//    ListAdapter listAdapter;
//    SQLiteHelper sqLiteHelper;
//    EditText editText;
//    SQLiteDatabase sqLiteDatabase;
//    Cursor cursor;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_sqlite);
//
//        listView = (ListView) findViewById(R.id.listView1);
//
//        editText = (EditText) findViewById(R.id.edittext1);
//
//        listView.setTextFilterEnabled(true);
//
//        sqLiteHelper = new SQLiteHelper(this);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                // Getting Search ListView clicked item.
//                Student ListViewClickData = (Student) parent.getItemAtPosition(position);
//
//                // printing clicked item on screen using Toast message.
//                Toast.makeText(SearchSQLiteActivity.this, ListViewClickData.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        editText.addTextChangedListener(new TextWatcher() {
//
//            public void afterTextChanged(Editable s) {
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            public void onTextChanged(CharSequence stringVar, int start, int before, int count) {
//
//                listAdapter.getFilter().filter(stringVar.toString());
//            }
//        });
//
//    }
//
//    public void DisplayDataInToListView() {
//
//        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
//
//        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);
//
//        Student student;
//        StudentList = new ArrayList<Student>();
//
//        if (cursor.moveToFirst()) {
//            do {
//
//                String tempName = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name));
//
//                String tempNumber= cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_PhoneNumber));
//
//                student = new Student(tempName, tempNumber);
//
//                StudentList.add(student);
//
//            } while (cursor.moveToNext());
//        }
//
//        listAdapter = new ListAdapter(SearchSQLiteActivity.this, R.layout.custom_layout, StudentList);
//
//        listView.setAdapter(listAdapter);
//
//        cursor.close();
//    }
//
//    @Override
//    protected void onResume() {
//
//        DisplayDataInToListView() ;
//
//        super.onResume();
//    }
//
//}
