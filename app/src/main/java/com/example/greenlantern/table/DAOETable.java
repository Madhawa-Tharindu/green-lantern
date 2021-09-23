package com.example.greenlantern.table;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOETable {

    private DatabaseReference databaseReference;
    private boolean exist=false;
    public String message;


    public DAOETable(){
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        databaseReference =db.getReference(TableD.class.getSimpleName());
    }

    public Task<Void> insert(TableD table){
        return databaseReference.push().setValue(table);
    }

    //fetch all data
    public Query get(){
       return databaseReference.orderByKey();
    }

    public Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }




}
