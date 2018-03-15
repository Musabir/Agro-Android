package com.example.musabir.agro.Fragment;

import android.preference.PreferenceFragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musabir.agro.Adapter.HesablaAdapter;
import com.example.musabir.agro.Mapper.HesablaMapper;
import com.example.musabir.agro.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Musabir on 11/25/2017.
 */

public class HesablaFragment extends PreferenceFragment {

    View view;
    GridView gridView;
    ArrayAdapter<HesablaMapper> adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    //List<Mapper> list = new ArrayList<Mapper>();
    boolean f = false;
    private HashMap<String, String> parameters;
    ArrayList<HesablaMapper> mappers = new ArrayList<>();
    int icons[]={R.drawable.taxil, R.drawable.diskli_mala, R.drawable.diskli_mala,
            R.drawable.becerme, R.drawable.ciyid, R.drawable.sum,
            R.drawable.sum, R.drawable.sum, R.drawable.hamarlamaq,
            R.drawable.dermanlamaq, R.drawable.cileyici, R.drawable.qazmaq,
            R.drawable.preslemek, R.drawable.bicmek, R.drawable.bicmek,
            R.drawable.toxumsepini, R.drawable.gubre, R.drawable.pambiq,
            R.drawable.cugundur, R.drawable.kartof, R.drawable.kartofcixarmaq};


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.hesabla_listview, container, false);

        gridView = (GridView) view.findViewById(R.id.grid_view);
        parameters = new HashMap<>();
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
        getActivity().setTitle("Aqrotexniki Xidmətlər");
        return view;
    }




        private void SendPostRequest() {
            final String[] str = {""};


            final ProgressDialog pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading...");
            if(!f)
            pDialog.show();
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            StringRequest postRequest = new StringRequest(Request.Method.POST, "https://sc.e-gov.az/file/476",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                str[0] = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            pDialog.dismiss();
                            // response
                            Log.d("Response", str[0]);
                            String token = null;

                            try {
                                JSONObject obj = new JSONObject(str[0]);

                                JSONArray objArray = null;
                                JSONObject obj1=null;
                                JSONObject obj2=null;

                                obj1 = obj.getJSONObject("AGRO_SERVICES");

                                obj2=obj1.getJSONObject("body");

                                objArray=obj2.getJSONArray("AGRO_SERVICE");
                                for (int i = 0; i < objArray.length(); i++) {

                                    HesablaMapper mapper = new HesablaMapper();
                                    JSONObject object = objArray.getJSONObject(i);
                                    mapper.setXidmet(object.getJSONArray("name").getString(0));
                                    mapper.setQiymet(object.getString("salary").replace(".",","));
                                    mapper.setSize(object.getString("size"));

                                    mappers.add(mapper);

                                }

                                swipeRefreshLayout.setRefreshing(false);
                                f = false;
                                adapter=new HesablaAdapter(getActivity(),R.layout.custom_hesabla,mappers,icons);


                                gridView.setAdapter(adapter);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Toast.makeText(getActivity(), "Error occurs1", Toast.LENGTH_SHORT).show();
                            Log.d("Error.Response", error.getMessage() + "e rror");
                            if(pDialog!=null)
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
