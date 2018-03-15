package com.example.musabir.agro.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musabir.agro.Mapper.SuniMayalanmaMapper;
import com.example.musabir.agro.Mapper.ToxumMapper;
import com.example.musabir.agro.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

/**
 * Created by Musabir on 11/24/2017.
 */

public class ToxumElaveEt extends PreferenceFragment {
    View view;
    TextInputEditText name,toxum_adi,toxum_sortu,nomre,email,qiymet;
    TextView txt2;
    Button button;
    RelativeLayout city_lyt;
    int city_rem=-1;
    SpinnerDialog spinnerDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.toxum_elave_et, container, false);


        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        String[] arr = {"Ağcabədi","Ağdam","Ağdaş","Ağdərə","Ağstafa","Ağsu","Astara","Bakı","Balakən","Beyləqan","Bərdə","Biləsuvar","Cəbrayıl","Cəlilabad",
                "Culfa","Daşkəsən","Dəliməmmədli","Füzuli","Gədəbəy","Gəncə","Goranboy","Göyçay","Göygöl","Göytəpə","Hacıqabul","Horadiz","Xaçmaz"
                ,"Xankəndi","Xocalı","Xocavənd","Xırdalan","Xızı","Xudat","İmişli","İsmayıllı","Kəlbəcər","Kürdəmir","Qax"
                ,"Qazax","Qəbələ","Qobustan","Qovlar","Quba","Qubadlı","Qusar","Laçın","Lerik","Lənkəran","Liman","Masallı"
                ,"Mingəçevir","Naftalan","Naxçıvan","Neftçala","Oğuz","Ordubad","Saatlı","Sabirabad","Salyan","Samux","Siyəzən"
                ,"Sumqayıt","Şabran","Şahbuz","Şamaxı","Şəki","Şəmkir","Şərur","Şirvan","Şuşa","Tərtər","Tovuz"
                ,"Ucar","Yardımlı","Yevlax","Zaqatala","Zəngilan","Zəngilan"};
        final ArrayList<String> cities = new ArrayList<>(Arrays.asList(arr));
        toolbar.setTitle("Toxum əlavə et");

        txt2 = (TextView) view.findViewById(R.id.txt2);
        name = (TextInputEditText) view.findViewById(R.id.ad_soyad);
        toxum_adi = (TextInputEditText) view.findViewById(R.id.toxumun_adi);
        toxum_sortu = (TextInputEditText) view.findViewById(R.id.sort);
        nomre = (TextInputEditText) view.findViewById(R.id.phoneName);
        email = (TextInputEditText) view.findViewById(R.id.emailtxt);
        qiymet = (TextInputEditText) view.findViewById(R.id.qiymettxt);
        button =(Button) view.findViewById(R.id.submit);
        city_lyt = (RelativeLayout) view.findViewById(R.id.city_lyt);
        final AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                getActivity());
        final ArrayList<String> finalCities = cities;
        spinnerDialog=new SpinnerDialog(getActivity(),cities,"Şəhər seçin");// With 	Animation


        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                txt2.setText(item);
            }
        });
        city_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerDialog.showSpinerDialog();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("=-----?","clicked");
                if(name.getText().toString().equals("")||toxum_adi.getText().toString().equals("")||toxum_sortu.getText().toString().equals("")
                        ||nomre.getText().toString().equals("")||qiymet.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Zehmet olmasa lazım olan məlumatları daxil edin!",Toast.LENGTH_SHORT).show();

                }
                else if(txt2.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Zehmet olmasa şəhər seçin!",Toast.LENGTH_SHORT).show();

                }
                else {
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("ToxumElaveEdenler");
    //(String toxumName, String toxumSort, String contact, String region, String sellerName, String email, String qiymet) {

                    String userId = mDatabase.push().getKey();
                    ToxumMapper toxumMapper = new ToxumMapper(toxum_adi.getText().toString(),toxum_sortu.getText().toString(),
                            nomre.getText().toString(),txt2.getText().toString(),name.getText().toString(),email.getText().toString(),qiymet.getText().toString());
                    mDatabase.child(userId).setValue(toxumMapper);
                    if(isOnline()){
                        Toast.makeText(getActivity(),"Uğurla əlavə edildi.",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getActivity(),"Sizin məlumatlar internete qoşulduğunuz zaman əlavə ediləcəkş",Toast.LENGTH_SHORT).show();

                    }
                    clear();
                }
            }
        });


        return view;
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    private void clear(){
        name.setText("");
        toxum_adi.setText("");
        toxum_sortu.setText("");
        txt2.setText("");
        nomre.setText("");
        email.setText("");
        qiymet.setText("");
    }
}