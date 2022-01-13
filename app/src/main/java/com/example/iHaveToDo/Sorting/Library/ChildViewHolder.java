package com.example.iHaveToDo.Sorting.Library;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;

/**
 *  ViewHolder pour un élément de liste enfant.
 *  <p>
 *  L'utilisateur doit étendre cette classe et l'implémenter comme il le souhaite pour son
 *  élément de liste enfant.
 */
public class ChildViewHolder<C> extends RecyclerView.ViewHolder {
    C mChild;
    ExpandableRecyclerAdapter mExpandableAdapter;

    /**
     * Constructeur par défaut.
     *
     * @param itemView Le {@link View} est hébergé dans ce ViewHolder
     */
    public ChildViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    /**
     * @return le childListItem associé à ce support de vue
     */
    @UiThread
    public C getChild() {
        return mChild;
    }

    /**
     * Return la position de l'adaptateur du parent associé à ce ChildViewHolder.
     *
     * @return La position du Parent dans l'adaptateur, s'il existe encore dans l'adaptateur.
     * RecyclerView.NO_POSITION si l'élément a été retiré de l'adaptateur,
     * RecyclerView.Adapter.notifyDataSetChanged() a été appelé après le dernier
     * layout pass ou la ViewHolder a déjà été recyclé.
     */
    @UiThread
    public int getParentAdapterPosition() {
        int flatPosition = getAdapterPosition();
        if (mExpandableAdapter == null || flatPosition == RecyclerView.NO_POSITION) {
            return RecyclerView.NO_POSITION;
        }

        return mExpandableAdapter.getNearestParentPosition(flatPosition);
    }

    /**
     * Return la position de l'adaptateur du parent associé à ce ChildViewHolder.
     *
     * @return La position du Parent dans l'adaptateur, s'il existe encore dans l'adaptateur.
     * RecyclerView.NO_POSITION si l'élément a été retiré de l'adaptateur,
     * RecyclerView.Adapter.notifyDataSetChanged() a été appelé après le dernier
     * layout pass ou la ViewHolder a déjà été recyclé.
     */
    @UiThread
    public int getChildAdapterPosition() {
        int flatPosition = getAdapterPosition();
        if (mExpandableAdapter == null || flatPosition == RecyclerView.NO_POSITION) {
            return RecyclerView.NO_POSITION;
        }

        return mExpandableAdapter.getChildPosition(flatPosition);
    }
}