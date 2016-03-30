package com.zhao.ChargeUp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.zhao.ChargeUp.unit.User;

/**
 * Created by Zhao on 2016/3/30.
 * 用户管理界面
 */
public class UserManagerFragment extends Fragment {
    private ListView lv_users;
    private Button bt_addUser;
    private FragmentManager fragmentManager;
    private UsersAdapter usersAdapter;
    private MainFragment mainFragment;

    public UserManagerFragment(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragme_user_manager, null);
        fragmentManager = getFragmentManager();

        bt_addUser = (Button) view.findViewById(R.id.bt_addUser);
        lv_users = (ListView) view.findViewById(R.id.lv_users);
        usersAdapter = new UsersAdapter(getActivity(), R.layout.item_user_manager, User.getUsers());

        bt_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentManager.findFragmentById(R.id.fl_addUser) == null) {
                    fragmentManager.beginTransaction()
                            .add(R.id.fl_addUser, new AddUserFragment())
                            .addToBackStack(null)
                            .commit();
                }else{
                    EditText et_userName = (EditText) container.findViewById(R.id.et_userName);
                    EditText et_initSum = (EditText) container.findViewById(R.id.et_initSum);

                    String name = et_userName.getText().toString();
                    String sum_s = et_initSum.getText().toString();
                    double sum;
                    if (name.equals("")) {
                        Toast.makeText(getActivity(), "请输入用户名", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (sum_s.equals("")) {
                        sum=0;
                    }else{
                        sum = Double.parseDouble(sum_s);
                    }
                    User newUser = new User(name, sum);
                    Toast.makeText(getActivity(), "添加用户成功", Toast.LENGTH_SHORT).show();
                }
                refurbish();
            }
        });
        lv_users.setAdapter(usersAdapter);
        return view;
    }
    public void refurbish(){
        usersAdapter.notifyDataSetChanged();
        mainFragment.refurbish(true);
    }
}
