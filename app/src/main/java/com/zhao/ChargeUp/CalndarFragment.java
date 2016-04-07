package com.zhao.ChargeUp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Date;


public class CalndarFragment extends Fragment {
    private DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calndar, container, false);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        datePicker.setMaxDate(new Date().getTime());
        Date date = AddRecordFragment.getDate();
        Log.d("TAG++", date.getYear() + " " + date.getMonth());

        datePicker.init(date.getYear(), date.getMonth(), date.getDay(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                year = datePicker.getYear();
//                monthOfYear = datePicker.getMonth();
//                dayOfMonth = datePicker.getDayOfMonth();

                AddRecordFragment.setDate(year,monthOfYear,dayOfMonth);
                CalndarFragment.this.onDestroyView();
            }
        });

        return view;
    }

}
