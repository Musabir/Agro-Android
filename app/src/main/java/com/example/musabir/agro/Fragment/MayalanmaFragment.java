package com.example.musabir.agro.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musabir.agro.Adapter.CustomMayalanmaAdapter;
import com.example.musabir.agro.Adapter.XidmetGosterenlerAdapter;
import com.example.musabir.agro.Mapper.SuniMayalanmaMapper;
import com.example.musabir.agro.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Musabir on 11/24/2017.
 */

public class MayalanmaFragment extends PreferenceFragment
{
    View view;
    ListView listView;
    CustomMayalanmaAdapter mayalanmaAdapter;
    private HashMap<String, String> parameters;
    private String mayalanmaPostUrl = "https://sc.e-gov.az/file/477";
    private SwipeRefreshLayout swipeRefreshLayout;
    boolean f = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.mayalanma_listview, container, false);

        listView = (ListView) view.findViewById(R.id.list);
        parameters = new HashMap<>();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                swipeRefreshLayout.setRefreshing(true);
                                                f = true;

                                                SendPostRequest();
                                            }
                                        }
                );
            }
        });
        SendPostRequest();
        toolbar.setTitle("Süni Mayalandırma Mütəxəsisləri");

        return view;
    }


    public void SendPostRequest() {

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        if(!f)
        pDialog.show();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest postRequest = new StringRequest(Request.Method.POST, mayalanmaPostUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            response = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }                        Log.d("Response", response);
                        pDialog.dismiss();
                        ArrayList<SuniMayalanmaMapper> mayalanmaMappers = new ArrayList<>();

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONObject Employees = null;
                            JSONObject body = null;
                            Employees = obj.getJSONObject("Employees");
                            body = Employees.getJSONObject("body");

                                JSONArray objArray = null;
                                objArray = body.getJSONArray("Employee");

                                for (int i = 0; i < objArray.length(); i++) {
                                    JSONObject object = objArray.getJSONObject(i);
                                    Log.d("-->>>>>>","phone 1");
                                    SuniMayalanmaMapper  m = new SuniMayalanmaMapper(object.getString("fullName"),
                                            object.getString("activityname"),object.getString("addressDescription"),
                                            "");
                                    if(object.has("phone1")){
                                        m.setContact_number(object.getString("phone1"));
                                    }

                                    mayalanmaMappers.add(m);

                                }
                            f = false;
                            swipeRefreshLayout.setRefreshing(false);

                            mayalanmaAdapter = new CustomMayalanmaAdapter(getActivity(), R.layout.custom_mayalanma, mayalanmaMappers);
                            listView.setAdapter(mayalanmaAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getActivity()
                                , "Error occurs", Toast.LENGTH_SHORT).show();
                        Log.d("Error.Response", error.getMessage() + "e rror");
                        pDialog.dismiss();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {

                return parameters;
            }
        };
        queue.add(postRequest);


    }

}
