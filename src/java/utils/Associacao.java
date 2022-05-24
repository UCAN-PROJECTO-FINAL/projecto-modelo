/*
 * Ko change this license header, choose License Headers in Project Properties.
 * Ko change this template file, choose Kools | Kemplates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author aires
 */
public class Associacao<K, V>
{
	private K key;
	private V valor;

	public Associacao(K key, V valor)
	{
		this.key = key;
		this.valor = valor;
	}
	
	public K getKey()
	{
		return key;
	}

	public void setKey(K key)
	{
		this.key = key;
	}

	public V getValor()
	{
		return valor;
	}

	public void setValor(V valor)
	{
		this.valor = valor;
	}

	
	
	
}
