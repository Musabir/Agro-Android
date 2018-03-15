package com.example.musabir.agro.Fragment;

import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musabir.agro.Mapper.ToxumMapper;
import com.example.musabir.agro.Mapper.XidmetGosterenSexslerMapper;
import com.example.musabir.agro.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

/**
 * Created by Musabir on 11/24/2017.
 */

public class XidmetElaveEtFragment extends PreferenceFragment {
    View view;
    TextInputEditText name,xidmetin_adi,nomre,email,qiymet,qeyd;
    TextView txt2;
    Button button;
    RelativeLayout city_lyt;
    int city_rem=-1;
    SpinnerDialog spinnerDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.xidmet_elave_et, container, false);


        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        String[] arr = {"Ağcabədi","Ağdam","Ağdaş","Ağdərə","Ağstafa","Ağsu","Astara","Bakı","Balakən","Beyləqan","Bərdə","Biləsuvar","Cəbrayıl","Cəlilabad",
                "Culfa","Daşkəsən","Dəliməmmədli","Füzuli","Gədəbəy","Gəncə","Goranboy","Göyçay","Göygöl","Göytəpə","Hacıqabul","Horadiz","Xaçmaz"
                ,"Xankəndi","Xocalı","Xocavənd","Xırdalan","Xızı","Xudat","İmişli","İsmayıllı","Kəlbəcər","Kürdəmir","Qax"
                ,"Qazax","Qəbələ","Qobustan","Qovlar","Quba","Qubadlı","Qusar","Laçın","Lerik","Lənkəran","Liman","Masallı"
                ,"Mingəçevir","Naftalan","Naxçıvan","Neftçala","Oğuz","Ordubad","Saatlı","Sabirabad","Salyan","Samux","Siyəzən"
                ,"Sumqayıt","Şabran","Şahbuz","Şamaxı","Şəki","Şəmkir","Şərur","Şirvan","Şuşa","Tərtər","Tovuz"
                ,"Ucar","Yardımlı","Yevlax","Zaqatala","Zəngilan","Zəngilan"};
        final ArrayList<String> cities = new ArrayList<>(Arrays.asList(arr));
        toolbar.setTitle("Xidmət əlavə et");

        txt2 = (TextView) view.findViewById(R.id.txt2);
        name = (TextInputEditText) view.findViewById(R.id.ad_soyad);
        xidmetin_adi = (TextInputEditText) view.findViewById(R.id.xidmetin_adi);
        nomre = (TextInputEditText) view.findViewById(R.id.phoneName);
        email = (TextInputEditText) view.findViewById(R.id.emailtxt);
        qiymet = (TextInputEditText) view.findViewById(R.id.qiymettxt);
        qeyd = (TextInputEditText) view.findViewById(R.id.note);
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
                if(name.getText().toString().equals("")||xidmetin_adi.getText().toString().equals("")
                        ||nomre.getText().toString().equals("")||qiymet.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Zehmet olmasa lazım olan məlumatları daxil edin!",Toast.LENGTH_SHORT).show();

                }
                else if(txt2.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Zehmet olmasa şəhər seçin!",Toast.LENGTH_SHORT).show();

                }
                else {
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("XidmetElaveEdenler");
   //(String name, String xidmetAdi, String qiymet, String rayon, String contact_number, String qeyd,String email) {

                    String userId = mDatabase.push().getKey();
                    XidmetGosterenSexslerMapper xidmetGosterenSexslerMapper = new XidmetGosterenSexslerMapper(name.getText().toString(),xidmetin_adi.getText().toString()
                            ,qiymet.getText().toString(),txt2.getText().toString(),nomre.getText().toString(),qeyd.getText().toString(),email.getText().toString());
                    mDatabase.child(userId).setValue(xidmetGosterenSexslerMapper);
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
        xidmetin_adi.setText("");
        qeyd.setText("");
        txt2.setText("");
        nomre.setText("");
        email.setText("");
        qiymet.setText("");
    }
}
