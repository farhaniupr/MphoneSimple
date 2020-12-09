package com.example.mphonesimple;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentB extends Fragment {
    RecyclerView recyclerView;
    ListView listView;

    public FragmentB(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        /**View rootView = inflater.inflate(
                R.layout.fragment, container, false);
        //return rootView;

        /**The below code was when the ListView was used in place of RecyclerView. **/

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

    /*
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] items = getResources().getStringArray(R.array.Semester_2021);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(items);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }*/
}
