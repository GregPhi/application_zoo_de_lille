package com.example.zoodelille.view.map.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zoodelille.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MapFragment extends Fragment
        implements OnMapReadyCallback {

    private static final LatLng ZOO_DE_LILLE = new LatLng(50.637903, 3.045939);

    private static final LatLng NEAR_ZOO_DE_LILLE = new LatLng(50.636711, 3.044256);

    private GroundOverlay groundOverlayRotated;

    private View m_view;

    private MapView mapView;

    public static MapFragment newInstance(){
        return new MapFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        m_view = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = m_view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        return m_view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onMapReady(GoogleMap map) {
        map.clear();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ZOO_DE_LILLE, 18));
        groundOverlayRotated = map.addGroundOverlay(new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.zoo_carte))
                .anchor(0f, 1f)
                .position(NEAR_ZOO_DE_LILLE, 310f, 203f)
                .bearing(-17));
        map.setMinZoomPreference(5f);

        // Override the default content description on the view, for accessibility mode.
        // Ideally this string would be localised.
        map.setContentDescription("Google Map with ground overlay of the zoo.");
    }
}