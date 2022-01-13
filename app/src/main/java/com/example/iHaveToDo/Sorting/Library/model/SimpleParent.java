package com.example.iHaveToDo.Sorting.Library.model;

import java.util.List;

/**
 * Implémentation simple de l'interface ParentListItem,
 * par défaut, tous les éléments ne sont pas initialement développés.
 *
 * @param <C> Type d'élément enfant détenu par le parent.
 */
public class SimpleParent<C> implements Parent<C> {

    private List<C> mChildList;

    protected SimpleParent(List<C> childItemList) {
        mChildList = childItemList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    @Override
    public List<C> getChildList() {
        return mChildList;
    }

    public void setChildList(List<C> childList) {
        mChildList = childList;
    }
}
