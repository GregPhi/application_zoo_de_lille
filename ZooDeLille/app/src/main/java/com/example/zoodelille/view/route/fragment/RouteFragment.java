package com.example.zoodelille.view.route.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.zoodelille.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RouteFragment extends Fragment {
    public static final String name = "Parcours";
    public static final int icon = R.drawable.drawable_routes;

    private View m_view;
    private Toolbar toolbar;

    public static RouteFragment newInstance(){
        return new RouteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        m_view = inflater.inflate(R.layout.fragment_routes, container, false);
        return m_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toolbar = m_view.findViewById(R.id.toolbar_routes);
        toolbar.inflateMenu(R.menu.routes_filter);
        toolbar.setTitle(R.string.routes);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.routes_filter_alpha)
                {
                    // do something
                }
                else if(item.getItemId()== R.id.routes_filter_like)
                {
                    // do something
                }
                else if(item.getItemId()== R.id.routes_filter_distance)
                {
                    // do something
                }
                else{
                    // do something
                }
                return false;
            }
        });
    }
}
