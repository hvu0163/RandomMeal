package com.example.project.ui.home;

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
import com.example.project.model.Dishes;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private GridView gridView = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        DBContext db = new DBContext(root.getContext());
        final List<Dishes> content = db.getTopDisk();
//        final List<Dishes> list = new ArrayList<>();
//        System.out.println();
//        for (int i = 0; i < 9; i++) {
//            Dishes d = new Dishes();
//            d.setName("Phở bò");
//            d.setUrl("https://hocnauan.edu.vn/wp-content/uploads/2018/10/to-pho-bo-ha-noi.jpg");
////            Dishes dishes = db.getADisk();
//            list.add(d);
//        }
        gridView = root.findViewById(R.id.gridview);
        ListProductAdapter abc = new ListProductAdapter(getContext(), R.layout.fragment_home, content);
        gridView.setNumColumns(3);
        gridView.setHorizontalSpacing(5);
        gridView.setVerticalSpacing(10);
        gridView.setAdapter(abc);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), content.get(position).getName(), Toast.LENGTH_SHORT).show();

            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}
