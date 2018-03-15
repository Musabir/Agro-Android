package com.example.musabir.agro.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musabir.agro.Activities.ToxumDetails;
import com.example.musabir.agro.Adapter.CustomSearchAdapter;
import com.example.musabir.agro.Adapter.MainListAdapter;
import com.example.musabir.agro.Mapper.ToxumMapper;
import com.example.musabir.agro.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class MainScreenFragment extends PreferenceFragment {
    View view;
    ListView listview_view_list,search_listview;
    ArrayList<ToxumMapper> toxumMappers;
    MainListAdapter listAdapter;
    private HashMap<String,ArrayList<ToxumMapper>> cityMap = new HashMap<>();
    public static ArrayList<String> cities;
    public static ArrayList<String> usedCities;
    ArrayList<ToxumMapper> searchArrayList;
    private RelativeLayout oops;
    ProgressDialog pDialog ;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static ToxumMapper toxum;
    private HashMap<String, String> parameters;
    private String toxumPostUrl = "https://sc.e-gov.az/file/484";
    boolean f = false;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.main_screen_layout, container, false);
        listview_view_list = (ListView) view.findViewById(R.id.listview_view_list);
        toxumMappers = new ArrayList<>();
        parameters = new HashMap<>();
        pDialog = new ProgressDialog(getActivity());
        SendPostRequest();
        setHasOptionsMenu(true);

        searchArrayList = new ArrayList<>();

        String[] arr = {"Ağcabədi","Ağdam","Ağdaş","Ağdərə","Ağstafa","Ağsu","Astara","Bakı","Balakən","Beyləqan","Bərdə","Biləsuvar","Cəbrayıl","Cəlilabad",
                "Culfa","Daşkəsən","Dəliməmmədli","Füzuli","Gədəbəy","Gəncə","Goranboy","Göyçay","Göygöl","Göytəpə","Hacıqabul","Horadiz","Xaçmaz"
                ,"Xankəndi","Xocalı","Xocavənd","Xırdalan","Xızı","Xudat","İmişli","İsmayıllı","Kəlbəcər","Kürdəmir","Qax"
                ,"Qazax","Qəbələ","Qobustan","Qovlar","Quba","Qubadlı","Qusar","Laçın","Lerik","Lənkəran","Liman","Masallı"
                ,"Mingəçevir","Naftalan","Naxçıvan","Neftçala","Oğuz","Ordubad","Saatlı","Sabirabad","Salyan","Samux","Siyəzən"
                ,"Sumqayıt","Şabran","Şahbuz","Şamaxı","Şəki","Şəmkir","Şərur","Şirvan","Şuşa","Tərtər","Tovuz"
                ,"Ucar","Yardımlı","Yevlax","Zaqatala","Zəngilan","Zəngilan"};
        cities = new ArrayList<>(Arrays.asList(arr));
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        listview_view_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (listview_view_list.getChildAt(0) != null) {
                    swipeRefreshLayout.setEnabled(listview_view_list.getFirstVisiblePosition() == 0 && listview_view_list.getChildAt(0).getTop() == 0);
                }
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                swipeRefreshLayout.setRefreshing(true);
                                                searchArrayList = new ArrayList<>();
                                                toxumMappers = new ArrayList<>();
                                                f = true;
                                                SendPostRequest();
                                            }
                                        }
                );
            }
        });

        oops = (RelativeLayout) view.findViewById(R.id.oops);
        search_listview = (ListView) view.findViewById(R.id.search_layout_list);
        search_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toxum = searchArrayList.get(i);
                Intent intent = new Intent(getActivity(), ToxumDetails.class);
                intent.putExtra("toxum",searchArrayList.get(i));
                getActivity().startActivity(intent);


            }
        });

        usedCities = new ArrayList<>();
        return view;
    }


    public void SendPostRequest() {

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        if(!f)
        pDialog.show();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest postRequest = new StringRequest(Request.Method.POST, toxumPostUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        try {
                            response = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONObject SEED_SELLERS = null;
                            JSONObject body = null;
                            SEED_SELLERS = obj.getJSONObject("SEED_SELLERS");
                            body = SEED_SELLERS.getJSONObject("body");

                            JSONArray objArray = null;
                            objArray = body.getJSONArray("SEED_SELLER");

                            for (int i = 0; i < objArray.length(); i++) {
                                JSONObject object = objArray.getJSONObject(i);
                                Log.d("-->>>>>>","phone 1");
   //(String toxumName, String toxumSort, String contact, String region, String sellerName, String email, String qeyd) {

                                ToxumMapper  m = new ToxumMapper(object.getString("bitki"),
                                        object.getString("sort"),object.getString("number"),
                                        object.getString("rayon"),object.getString("name"),"","");


                                toxumMappers.add(m);

                            }
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("ToxumElaveEdenler");
                            ref.addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            getToxumElaveEdenler((Map<String,Object>) dataSnapshot.getValue());
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                        }
                                    });





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

    private void getToxumElaveEdenler(Map<String,Object> users) {


        Log.d("---???", users.size() + " size");
        for (Map.Entry<String, Object> entry : users.entrySet()) {
            Map singleUser = (Map) entry.getValue();
            ToxumMapper mapper = new ToxumMapper();
            mapper.setSellerName((String) singleUser.get("sellerName"));
            mapper.setContact((String) singleUser.get("contact"));
            mapper.setEmail((String) singleUser.get("email"));
            mapper.setToxumName((String) singleUser.get("toxumName"));
            mapper.setQiymet((String) singleUser.get("qiymet"));
            mapper.setToxumSort((String) singleUser.get("toxumSort"));
            mapper.setRegion((String) singleUser.get("region"));
            toxumMappers.add(mapper);
        }

        for(int i=0;i<toxumMappers.size();i++){
            ToxumMapper t = toxumMappers.get(i);
            String region = t.getRegion();
            for(int j=0;j<cities.size();j++){

                String city = cities.get(j);

                if(region.contains(city)) {
                    if(!usedCities.contains(city)) {
                        Log.d("--->>>>>>",city +" city");
                        usedCities.add(city);
                    }
                    if (cityMap.get(city) == null) {
                        ArrayList<ToxumMapper> arTxm = new ArrayList<ToxumMapper>();
                        arTxm.add(t);
                        cityMap.put(city, arTxm);
                    } else {
                        cityMap.get(city).add(t);
                    }
                    break;
                }
            }

        }
        Collections.sort(usedCities);
        swipeRefreshLayout.setRefreshing(false);
        f = false;
        listAdapter = new MainListAdapter(cityMap, R.layout.custom_toxum_listview, getActivity(),usedCities);
        listview_view_list.setAdapter(listAdapter);
        pDialog.dismiss();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        searchArrayList.clear();
        inflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setIconified(true);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchArrayList.clear();

                oops.setVisibility(View.GONE);
                search_listview.setVisibility(View.VISIBLE);
                listview_view_list.setVisibility(View.GONE);

            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchArrayList.clear();

                oops.setVisibility(View.GONE);
                search_listview.setVisibility(View.GONE);
                listview_view_list.setVisibility(View.VISIBLE);
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.length()>0) {
                    oops.setVisibility(View.GONE);
                    searchArrayList.clear();
                    search_listview.setVisibility(View.VISIBLE);
                    for (int i = 0; i < toxumMappers.size(); i++) {
                        if (toxumMappers.get(i).getToxumName().contains(s) ||toxumMappers.get(i).getRegion().contains(s) ||toxumMappers.get(i).getToxumSort().contains(s) ) {
                            searchArrayList.add(toxumMappers.get(i));
                        }
                    }
                    if (searchArrayList.size() > 0) {
                        CustomSearchAdapter searchAdapter = new CustomSearchAdapter(getActivity(), R.layout.custom_search_layout, searchArrayList);
                        search_listview.setAdapter(searchAdapter);
                    } else {
                        oops.setVisibility(View.VISIBLE);
                        listview_view_list.setVisibility(View.GONE);
                        search_listview.setVisibility(View.GONE);
                    }
                }
                else {
                    oops.setVisibility(View.GONE);
                    search_listview.setVisibility(View.GONE);
                }
                return false;
            }
        });
        searchView.setQueryHint("Search");


        super.onCreateOptionsMenu(menu, inflater);
    }

}
