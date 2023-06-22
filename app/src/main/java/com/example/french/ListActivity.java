package com.example.french;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = new ArrayList<>();

        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputEditText = findViewById(R.id.inputEditText);
                String newItem = inputEditText.getText().toString().trim();

                if (!newItem.isEmpty()) {
                    dataList.add(newItem);
                    adapter.notifyDataSetChanged();
                    saveDataToSharedPreferences();
                    inputEditText.setText("");
                }
            }
        });
    }

    private void saveDataToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dataList);
        editor.putString("dataList", json);
        editor.apply();
    }

    private List<String> loadDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("dataList", "");
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<String> dataList;

        public MyAdapter(List<String> dataList) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String item = dataList.get(position);
            holder.itemTextView.setText(item);
            holder.itemView.setOnClickListener(v ->
                    holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(),MainActivity.class)));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final TextView itemTextView;

            public ViewHolder(View view) {
                super(view);
                itemTextView = view.findViewById(R.id.itemTextView);
            }
        }
    }
}
