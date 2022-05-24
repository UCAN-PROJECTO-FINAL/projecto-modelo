/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache.utils;

import java.io.Serializable;

/**
 *
 * @author Aires Veloso
 */
public interface CacheInterface<T> extends Serializable
{

    public int getPKEntity(Object t);

    public String toStringKey(Object t);
    
    public String nomeEntidade();

    public void resetAll();

    public boolean areTwins(Object t1, Object t2);
}
