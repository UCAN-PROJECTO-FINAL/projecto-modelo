/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author aires
 */
public class ConcurrentHashMapPersonalized<K, V> extends ConcurrentHashMap<K, V>
{
	public ConcurrentHashMapPersonalized()
	{
		super();
	}
	
	public ConcurrentHashMapPersonalized<K, V> put(Associacao<K, V> a)
	{
		super.put(a.getKey(), a.getValor());
		return this;
	}
	
	public ConcurrentHashMapPersonalized<K, V> put(Associacao<K, V> ... lista)
	{
		for (Associacao<K, V> a : lista)
			super.put(a.getKey(), a.getValor());
		return this;
	}
}
