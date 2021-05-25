package com.finite.covidinfoleads.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.finite.covidinfoleads.Model.vacmodel;
import com.finite.covidinfoleads.R;

import java.util.ArrayList;

public class vac_adapter extends RecyclerView.Adapter<vac_adapter.vac_viewholder>{

    private ArrayList<vacmodel> arrayList;

    public vac_adapter(ArrayList<vacmodel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public vac_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //View inflate = layoutInflater.inflate(R.layout.vacsinglerow,parent,false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vacsinglerow,parent,false);
        //vac_viewholder vac_viewholder = new vac_viewholder(inflate);
        //return vac_viewholder;
        return new vac_adapter.vac_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vac_viewholder holder, int position) {

        vacmodel vacmodel = arrayList.get(position);

        //vac_viewholder.centName.setText(vacmodel.getCentName());
        holder.centName.setText("🏢 " +vacmodel.getCenterName());
        holder.centAddress.setText("📍 " +vacmodel.getCentAddress());
        holder.vacTime.setText("⌛ From: "+vacmodel.getCenterFromTime()+"   To:  "+vacmodel.getCenterToTime());
        holder.vacName.setText("💉 : "+vacmodel.getVaccineName());


        String dose1 = String.valueOf(vacmodel.getDose1());
        String dose2 = String.valueOf(vacmodel.getDose2());
        String age = String.valueOf(vacmodel.getAgeLimit());
        holder.vacrate.setText("Charges: "+vacmodel.getFeeType()+"\t\t     Age Limit: "+age+"+");

        holder.doses.setText("Dose Availability :\nDose 1: "+dose1+"\nDose 2: "+dose2);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class vac_viewholder extends RecyclerView.ViewHolder {

        TextView centName,centAddress,vacTime,vacName,vacrate,doses;


        public vac_viewholder(@NonNull View itemView) {
            super(itemView);

            centName = itemView.findViewById(R.id.centName);
            centAddress = itemView.findViewById(R.id.centAddress);
            vacTime = itemView.findViewById(R.id.vacTime);
            vacName = itemView.findViewById(R.id.vacName);
            vacrate = itemView.findViewById(R.id.vacrate);
            doses = itemView.findViewById(R.id.doses);

        }
    }
}
