package com.app.partyplanets.DashBoad;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.partyplanets.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfiel extends Fragment {
    private EditText fname,lname,address1,address2,city,state,contact;
    CircleImageView profile;
    Button update;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_edit_profiel, container, false);
        editprofile(v);
        return v;
    }
    public void editprofile(View v)
    {
        fname=v.findViewById(R.id.fname);
        lname=v.findViewById(R.id.lname);
        address1=v.findViewById(R.id.address1);
        address2=v.findViewById(R.id.address2);
        city=v.findViewById(R.id.city);
        state=v.findViewById(R.id.state);
        contact=v.findViewById(R.id.contact);
        profile=v.findViewById(R.id.profile);
        update=v.findViewById(R.id.update);

    }
}