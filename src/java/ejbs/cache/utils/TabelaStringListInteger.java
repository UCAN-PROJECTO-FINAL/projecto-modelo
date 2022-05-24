/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache.utils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import utils.HashMapUtils;

/**
 *
 * @author Aires Veloso
 * @param <T>
 */
public abstract class TabelaStringListInteger<T> extends TabelaPadraoCacheUtils<T>
{

    protected ConcurrentHashMap<String, List<Integer>> tabelaStringListInteger,
        tabelaStringListIntegerBkUp;

    public TabelaStringListInteger()
    {
        tabelaStringListInteger = new ConcurrentHashMap<>();
    }

    @Override
    protected void restart()
    {
        if (updated)
        {
            return;
        }
        super.restart();
        this.tabelaStringListInteger = new ConcurrentHashMap<>();
        tabelaStringListIntegerBkUp = new ConcurrentHashMap<>();
    }

    @Override
    public void resetAll()
    {
        super.resetAll();
        tabelaStringListInteger = new ConcurrentHashMap<>();
        this.resetBkUps();
        System.gc();
    }
    
    @Override
    protected void resetBkUps()
    {
        super.resetBkUps();
        this.tabelaStringListIntegerBkUp = new ConcurrentHashMap<>();
    }

    @Override
    public void geraCloneTabelas()
    {
        super.resetBkUps();
        this.tabelaStringListIntegerBkUp = HashMapUtils.clone(tabelaStringListInteger);
    }
    
    @Override
    public void rollback()
    {
         tabelaStringListInteger = tabelaStringListIntegerBkUp;
    }
}
