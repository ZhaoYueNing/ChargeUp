package com.zhao.ChargeUp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhao.ChargeUp.unit.User;

import java.util.List;

/**
 * Created by Zhao on 2016/3/30.
 */
public class UsersAdapter extends ArrayAdapter<User> {
    private Context context;
    private int resource;

    public UsersAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource, null);
        TextView tv_userName = (TextView) view.findViewById(R.id.tv_user_name);
        ImageView iv_isCurrentUser = (ImageView) view.findViewById(R.id.iv_isCurrentUser);

        //设置用户名到item
        tv_userName.setText(user.getName());
        //如果是当前用户 前面的勾显示 否则隐藏
        if (user.isCurrentUser()) {
            iv_isCurrentUser.setVisibility(View.VISIBLE);
        } else {
            iv_isCurrentUser.setVisibility(View.INVISIBLE);
        }
        return view;
    }
}
