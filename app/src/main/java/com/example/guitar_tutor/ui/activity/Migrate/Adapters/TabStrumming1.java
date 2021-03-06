package com.example.guitar_tutor.ui.activity.Migrate.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guitar_tutor.R;

import java.util.ArrayList;

public class TabStrumming1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_strumming_one, container, false);

        ArrayList<StrummingList> strum = StrummingList.createStrummingList();

        RecyclerView rvStrum = view.findViewById(R.id.rvStrum);
        StrummingListAdapter adapter = new StrummingListAdapter(strum);
        rvStrum.setAdapter(adapter);
        rvStrum.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }
}