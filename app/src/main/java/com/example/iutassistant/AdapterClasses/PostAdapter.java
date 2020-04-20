package com.example.iutassistant.AdapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.iutassistant.Post;
import com.example.iutassistant.R;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Post> posts;



    public PostAdapter(Context mContext, ArrayList<Post> posts) {
        this.mContext = mContext;
        this.posts = posts;
        System.out.println(posts.size()+" ****post  size 2*****");
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_view_post_item,viewGroup,false);
        }

        TextView posterNameBox = view.findViewById(R.id.posterNameBox);
        TextView postBox = view.findViewById(R.id.postBox);
        TextView posterId=view.findViewById(R.id.posterIdBox);
        TextView postArea=view.findViewById(R.id.postArea);

        posterNameBox.setText(posts.get(i).getPoster_name());
        postBox.setText(posts.get(i).getPost());
        posterId.setText(posts.get(i).getId());
        postArea.setText(posts.get(i).getPostArea());




        return view;
    }
}
