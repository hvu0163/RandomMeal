package com.example.project.ui.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.R;
import com.example.project.adapter.ListProductAdapter;
import com.example.project.controller.DBContext;
import com.example.project.controller.Detail;
import com.example.project.model.Disk;

import java.util.List;

public class FavoriteFragment extends Fragment {

    private FavoriteViewModel favoriteViewModel;
    private GridView gridView = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoriteViewModel =
                ViewModelProviders.of(this).get(FavoriteViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_favorite, container, false);
        DBContext db = new DBContext(root.getContext());
        final List<Disk> content = db.getLikeDisk();
        gridView = root.findViewById(R.id.gridview);
        ListProductAdapter abc = new ListProductAdapter(getContext(), R.layout.fragment_home, content);gridView.setNumColumns(3);
        gridView.setHorizontalSpacing(10);
        gridView.setVerticalSpacing(10);
        gridView.setAdapter(abc);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(root.getContext(), Detail.class);
                intent.putExtra("id", String.valueOf(content.get(position).getDiskID()));
                startActivity(intent);

            }
        });
        return root;
    }
}
