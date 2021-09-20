package com.example.greenlantern.table;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOETable {

    private DatabaseReference databaseReference;

    public DAOETable(){
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        databaseReference =db.getReference(TableD.class.getSimpleName());
    }

    public Task<Void> insert(TableD table){
        return databaseReference.push().setValue(table);
    }


}
