package com.example.iutassistant.AdapterClasses;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iutassistant.Model.ProfileInfo;
import com.example.iutassistant.R;

public class ProfileAdapter extends  RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private Context mCtx;
    private List<ProfileInfo> profileList;

    public ProfileAdapter(Context mCtx, List<ProfileInfo> profileList) {
        this.mCtx = mCtx;
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.profile_info_list, parent, false);
        return new ProfileViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        ProfileInfo profileInfo = profileList.get(position);
        holder.name.setText("Name: "+profileInfo.name);
        holder.id.setText("Id: " + profileInfo.id);
        holder.sec.setText("Sec: " + profileInfo.sec);
        holder.prog.setText("Prog: " + profileInfo.programme);
        holder.prog.setText("Prog: " + profileInfo.deptment);
        holder.prog.setText("Prog: " + profileInfo.uni);
        holder.prog.setText("Prog: " + profileInfo.profession);
    }

    @Override
    public int getItemCount() {
        return profileList.size();









    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView name,id,sec,prog,dept,uni,prof;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);

             name = itemView.findViewById(R.id.name);
             id = itemView.findViewById(R.id.id);
             sec = itemView.findViewById(R.id.sec);
             prog = itemView.findViewById(R.id.prog);
             dept = itemView.findViewById(R.id.dept);
             uni = itemView.findViewById(R.id.uni);
             prof = itemView.findViewById(R.id.prof);
        }
    }

}

