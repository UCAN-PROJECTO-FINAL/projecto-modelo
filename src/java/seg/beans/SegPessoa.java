/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.entities.GrlEstadoCivil;
import ejbs.entities.GrlPessoa;
import ejbs.entities.GrlSexo;

/**
 *
 * @author aires
 */
public class SegPessoa
{

	private int pkSegPessoa;
	private String nome, numeroIdentificacao;
	private GrlEstadoCivil fkEstadoCivil;
	private GrlSexo fkSexo;
	// metodos get()

        public SegPessoa()
	{

	}

	public SegPessoa(GrlPessoa pessoa)
	{
            pkSegPessoa = pessoa.getPkGrlPessoa();
            nome = pessoa.getNome();
            numeroIdentificacao = pessoa.getNumeroIdentificacao();
            fkEstadoCivil = pessoa.getFkEstadoCivil();
            fkSexo = pessoa.getFkSexo();
	}

	public String getNome()
	{
		return nome;
	}

	public String getNumeroIdentificacao()
	{
		return numeroIdentificacao;
	}

	public GrlEstadoCivil getFkEstadoCivil()
	{
		return fkEstadoCivil;
	}

	public GrlSexo getFkSexo()
	{
		return fkSexo;
	}

	public int getPkSegPessoa()
	{
		return pkSegPessoa;
	}
}
