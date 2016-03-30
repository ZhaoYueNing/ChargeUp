package com.zhao.ChargeUp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhao.ChargeUp.unit.Record;
import com.zhao.ChargeUp.unit.User;

import java.util.Date;


/**
 * Created by Zhao on 2016/3/27.
 *
 */
public class MainFragment extends Fragment {
    private ListView lv_recodes;
    private TextView tv_amount;
    private TextView tv_currentUserName;
    private Button bt_addRecord;
    private Button bt_selectUser;
    private ArrayAdapter<Record> adapter;
    private FragmentManager fragmentManager;
    private User currentUser;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //添加23条测试数据
        if (User.getCurrentUser()==null)
            testAddData(23);
        currentUser = User.getCurrentUser();

        View view = inflater.inflate(R.layout.fragme_main,container,false);
        adapter = new RecordAdapter(getActivity(),  R.layout.item_record,  currentUser);

        lv_recodes = (ListView) view.findViewById(R.id.lv_recodes);
        lv_recodes.setAdapter(adapter);
        tv_amount = (TextView) view.findViewById(R.id.tv_amount);
        tv_currentUserName = (TextView) view.findViewById(R.id.tv_currentUserName);
        bt_addRecord = (Button) view.findViewById(R.id.bt_addRecord);
        bt_selectUser = (Button) view.findViewById(R.id.bt_selectUser);
        fragmentManager = getFragmentManager();

        tv_currentUserName.setText(currentUser.getName());
        tv_amount.setText("剩余金额 : "+currentUser.getAmount());
        // 点击进入添加收支记录界面 AddRecordFragment
        bt_addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentManager.beginTransaction()
                        .add(R.id.fragme_main, new AddRecordFragment(MainFragment.this))
                        .addToBackStack(null)
                        .commit();
            }
        });
        //进入用户管理界面
        bt_selectUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .add(R.id.fragme_main,new UserManagerFragment(MainFragment.this))
                        .addToBackStack(null)
                        .commit();
            }
        });
        //设置对listview 每一个条目长按的监听 长按删除
        lv_recodes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setCancelable(false);
                builder.setMessage("是否删除该条收支记录，删除后对应的金额总量同时将被调整");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (currentUser.removeRecord(position)) {
                            Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                            refurbish();
                        } else {
                            Toast.makeText(getActivity(), "删除失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
                return false;
            }
        });
        return view;
    }

    /**
     * 添加测试数据
     * @param size 添加记录条数
     */
    public User testAddData(int size) {
        User testUser = new User("TestUser", 1000);
        for(int i=0;i<size;i++) {
            Record record = new Record("#"+i,i%2,1000+i*2,new Date());
            testUser.addRecord(record);
        }
        return testUser;
    }


    /**
     * 刷新
     * @param isChangUser 是否更改用户
     */
    public void refurbish(boolean isChangUser) {
        if (isChangUser == true) {
            currentUser=User.getCurrentUser();
            adapter = new RecordAdapter(getActivity(),R.layout.item_record,currentUser);
            lv_recodes.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();

        tv_amount.setText("剩余金额 : "+currentUser.getAmount());
        tv_currentUserName.setText(currentUser.getName());
    }

    public void refurbish() {
        adapter.notifyDataSetChanged();

        tv_amount.setText("剩余金额 : "+currentUser.getAmount());
        tv_currentUserName.setText(currentUser.getName());
    }
}
