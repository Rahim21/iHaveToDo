package com.example.iHaveToDo.Sorting.vertical;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.iHaveToDo.Database.EntityClass;
import com.example.iHaveToDo.R;
import com.example.iHaveToDo.Sorting.Library.ChildViewHolder;


public class TacheViewHolder extends ChildViewHolder {

    private TextView mIngredientTextView;

    public TacheViewHolder(@NonNull View itemView) {
        super(itemView);
        mIngredientTextView = (TextView) itemView.findViewById(R.id.todo_textview);
    }

    public void bind(@NonNull EntityClass ingredient) {
        mIngredientTextView.setText(ingredient.getEventname());
//        mIngredientTextView.setText("fgh");

    }
}
