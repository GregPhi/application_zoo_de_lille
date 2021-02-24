package com.example.zoodelille.view.qrcode.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zoodelille.R;
import com.example.zoodelille.data.di.DepencyInjector;
import com.example.zoodelille.view.animal.adapter.AnimalItemViewModel;
import com.example.zoodelille.view.animal.info.AnimalInfoActivity;
import com.example.zoodelille.view.model.AnimalViewModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class QRCodeFragment extends Fragment {
    public static final String name = "QRCode";
    public static final int icon = R.drawable.drawable_qrcode;

    private View m_view;

    private String resultQRCode;

    public static QRCodeFragment newInstance(){
        return new QRCodeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        m_view = inflater.inflate(R.layout.fragment_qrcode, container, false);
        return m_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        IntentIntegrator scanIntegrator = new IntentIntegrator(getActivity());
        scanIntegrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        System.out.println("scanningResult :"+scanningResult);
        if (scanningResult != null) {
            resultQRCode = scanningResult.getContents();
            System.out.println("result :"+resultQRCode);
            Toast toast = Toast.makeText(getContext(), "QR Code :"+resultQRCode, Toast.LENGTH_SHORT);
            toast.show();
            AnimalViewModel animalViewModel = new ViewModelProvider(requireActivity(), DepencyInjector.getViewModelFactoryAnimal()).get(AnimalViewModel.class);
            animalViewModel.getAnimalEntityWithName(resultQRCode).observe(getViewLifecycleOwner(), new Observer<AnimalItemViewModel>() {
                @Override
                public void onChanged(AnimalItemViewModel animalItemViewModels) {
                    Intent intent = new Intent(getContext(), AnimalInfoActivity.class);
                    intent.putExtra("animal",animalItemViewModels);
                    startActivity(intent);
                }
            });
        } else {
            Toast toast = Toast.makeText(getContext(), "Nous n'avons pas réussi à scanner votre QR Code", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
