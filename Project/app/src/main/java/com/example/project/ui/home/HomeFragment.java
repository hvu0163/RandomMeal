package com.example.project.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.R;
import com.example.project.adapter.ListProductAdapter;
import com.example.project.controller.DBContext;
import com.example.project.controller.Detail;
import com.example.project.model.Dishes;
import com.example.project.model.Disk;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private GridView gridView = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        DBContext db = new DBContext(root.getContext());
        final List<Disk> content = db.getTopDisk();
        gridView = root.findViewById(R.id.gridview);
        ListProductAdapter abc = new ListProductAdapter(getContext(), R.layout.fragment_home, content);
        gridView.setNumColumns(3);
        gridView.setHorizontalSpacing(10);
        gridView.setVerticalSpacing(10);
        gridView.setAdapter(abc);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), content.get(position).getDescription(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(root.getContext(), Detail.class);
                intent.putExtra("id", content.get(position).getDiskID());
                startActivity(intent);

            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}
