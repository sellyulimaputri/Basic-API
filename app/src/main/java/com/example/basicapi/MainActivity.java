package com.example.basicapi;

import static androidx.core.content.ContentProviderCompat.requireContext;
import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.basicapi.adapter.StasuinAdapter;
import com.example.basicapi.data.StasiunData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StasuinAdapter stasuinAdapter;
    private List<StasiunData> stasiunData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        stasiunData = new ArrayList<>();
        stasuinAdapter = new StasuinAdapter(stasiunData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(stasuinAdapter);
        fetchDataFromApi();
    }
    private void fetchDataFromApi() {


        String apiUrl = "https://booking.kai.id/api/stations2";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                apiUrl,
                null,
                response -> {
                    String code = null;
                    String name = null;
                    String city = null;
                    String cityname = null;
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject placeJson = response.getJSONObject(i);
                            try {
                                code = placeJson.getString("code");
                                name = placeJson.getString("name");
                                city = placeJson.getString("city");
                                cityname = placeJson.getString("cityname");
                                StasiunData stasiunData1 = new StasiunData();
                                stasiunData1.code = code;
                                stasiunData1.name = name;
                                stasiunData1.city = city;
                                stasiunData1.cityName = cityname;
                                stasiunData.add(stasiunData1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        stasuinAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
        );
        Volley.newRequestQueue(this).add(request);
    }
}