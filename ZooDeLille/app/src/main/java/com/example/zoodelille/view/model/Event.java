package com.example.zoodelille.view.model;

/**
 * event when add or delete an entity to this database (when action favorite)
 * @param <T> : a class (when use the class is String representing the id of entity)
 */
public class Event<T> {
    private boolean hasBeenHandled;
    private final T context;

    public Event(T c){ this.context = c;}

    public T getContextIfHasNotBeenHandled(){
        if(hasBeenHandled){
            return null;
        }else{
            hasBeenHandled = true;
            return context;
        }
    }
}
