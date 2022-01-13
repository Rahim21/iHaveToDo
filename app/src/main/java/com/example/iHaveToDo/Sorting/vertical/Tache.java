package com.example.iHaveToDo.Sorting.vertical;

import com.example.iHaveToDo.Database.EntityClass;
import com.example.iHaveToDo.Sorting.Library.model.Parent;

import java.util.List;

public class Tache implements Parent<EntityClass> {

    private String mName;
    private List<EntityClass> mIngredients;
    boolean expandbool=true;

    public Tache(String name, List<EntityClass> ingredients) {
        mName = name;
        mIngredients = ingredients;
    }

    public Tache(String name, List<EntityClass> ingredients, boolean expand) {
        mName = name;
        mIngredients = ingredients;
        expandbool=expand;
    }
    public String getName() {
        return mName;
    }

    @Override
    public List<EntityClass> getChildList() {
        return mIngredients;
    }

    @Override
    public boolean isInitiallyExpanded() {
        if(getName().toLowerCase().contains("today") || getName().toLowerCase().contains("liste") ) {
           return expandbool;
    } else{
        return false;
    }

    }

    public EntityClass getIngredient(int position) {
        return mIngredients.get(position);
    }

//    public boolean isVegetarian() {
//        for (EntityClass ingredient : mIngredients) {
////            if (!ingredient.getEventdate().equals(getIngredient(0))) {
////                return false;
////            }
//        }
//        return true;
//    }
}
