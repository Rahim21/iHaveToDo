package com.example.iHaveToDo.Sorting.Library.model;

import java.util.List;

/**
 * Interface permettant de mettre en œuvre les méthodes requises dans un parent.
 */
public interface Parent<C> {

    /**
     * Getter pour la liste des éléments enfants de ce parent.
     * <p>
     * Si la liste est vide, le parent n'a pas d'attributs enfants.
     *
     * @return A {@link List} of the children of this {@link Parent}
     */
    List<C> getChildList();

    /**
     * Getter utilisé pour déterminer si le {@link Parent}
     * {@link android.view.View} devrait s'afficher initialement comme étendu.
     *
     * @return vrai si développé, faux sinon
     */
    boolean isInitiallyExpanded();
}