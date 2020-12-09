package com.example.mphonesimple;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentA extends Fragment {
    ListView listView;

    public FragmentA(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        listView = (ListView) view.findViewById(R.id.list);
        ArrayList stringList= new ArrayList();

        stringList.add("Basis Data");
        stringList.add("Struktur Data");
        stringList.add("Sistem Operasi");
        stringList.add("Sistem Berkas");
        stringList.add("Jaringan Komputer");
        stringList.add("Arsitektur Komputer");
        stringList.add("Grafika Komputer");
        stringList.add("Pemrograman Web");
        stringList.add("Rekayasa Perangkat Lunak");
        stringList.add("Pemrograman Jaringan");
        stringList.add("Cloud Programming");
        stringList.add("Data Mining");
        stringList.add("Mobile Programming");
        stringList.add("Kecerdasan Buatan");
        stringList.add("Machine Learning");
        stringList.add("Sistem Pakar");
        stringList.add("Semantic Web");
        stringList.add("Cloud Computing");
        stringList.add("Teori Bahasa dan Otomata");


        CustomeAdapter adapter = new CustomeAdapter(stringList,getActivity());
        listView.setAdapter(adapter);

        return view;
    }
}
