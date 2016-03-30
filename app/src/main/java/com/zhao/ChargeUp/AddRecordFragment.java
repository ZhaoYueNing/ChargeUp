package com.zhao.ChargeUp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.widget.Toast;

import com.zhao.ChargeUp.unit.Record;
import com.zhao.ChargeUp.unit.User;

import java.util.Date;

/**
 * Created by Zhao on 2016/3/28.
 * 添加收支记录Fragment
 */
public class AddRecordFragment extends Fragment {
    private User user;
    private EditText et_note;
    private EditText et_sum;
    private Button bt_addRecord;
    private AppCompatRadioButton rb_income;
    private AppCompatRadioButton rb_expend;
    private MainFragment mainFragment;


    public AddRecordFragment(MainFragment fragment) {
        this.user = User.getCurrentUser();
        this.mainFragment = fragment;
    }

    public AddRecordFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragme_add_record, null);
        et_note = (EditText) view.findViewById(R.id.et_note);
        et_sum = (EditText) view.findViewById(R.id.et_sum);
        bt_addRecord = (Button) view.findViewById(R.id.bt_addRecord);
        rb_expend = (AppCompatRadioButton) view.findViewById(R.id.rb_expend);
        rb_income = (AppCompatRadioButton) view.findViewById(R.id.rb_income);

        bt_addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sum = et_sum.getText().toString();
                String note = et_note.getText().toString();

                if (sum.equals("")) {
                    Toast.makeText(getActivity(), "请输入金额大小", Toast.LENGTH_SHORT).show();
                    return;
                }
                int recordType = rb_income.isChecked() ? Record.INCOME : Record.EXPEND;
                Record record = new Record(note, recordType, Double.parseDouble(sum), new Date());
                user.addRecord(record);
                Toast.makeText(getActivity(), "剩余金额 : "+user.getAmount(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mainFragment.refurbish();
    }
}
