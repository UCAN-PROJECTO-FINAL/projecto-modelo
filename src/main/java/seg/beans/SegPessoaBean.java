/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.entities.GrlPessoa;
import ejbs.facades.GrlPessoaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author aires
 */
@Named(value = "segPessoaBean")
@ViewScoped
public class SegPessoaBean implements Serializable
{

	@EJB
	private GrlPessoaFacade grlPessoaFacade;
    
	/**
	 * Creates a new instance of SegPessoaBean
	 */
	public SegPessoaBean()
	{
            
	}
	
	public SegPessoa find(int pkpessoa)
	{
//System.err.println("0: SegPessoaBean.find()\tpkpessoa: " + pkpessoa);        
		GrlPessoa pessoa = this.grlPessoaFacade.find(pkpessoa);
//System.err.println("1: SegPessoaBean.find()\tpessoa: " + (pessoa == null ? "null" : pessoa.getNome()));    
		return pessoa == null ? null : new SegPessoa(pessoa);
	}
	
	public List<SegPessoa> findAllOrderedByNome(String nomePessoaPesquisar)
	{
            //System.err.println("1. findAllOrderedByNome(String nomePessoaPesquisar)" + nomePessoaPesquisar);
		List<GrlPessoa> pessoas = this.grlPessoaFacade.findAllPessoaColaboradorSemContaOrderedByNome(nomePessoaPesquisar);
            //System.err.println("2. findAllOrderedByNome(String nomePessoaPesquisar)");
                List<SegPessoa> segPessoas = new ArrayList();
		SegPessoa segPessoa;
		for (GrlPessoa pessoa : pessoas)
		{
			segPessoa = new SegPessoa(pessoa);
			segPessoas.add(segPessoa);
		}
		return segPessoas;
	}
        
        public SegPessoa getPessoaParaContaRoot()
        {
            GrlPessoa pessoa = this.grlPessoaFacade.getInstancia();
            pessoa.setNome("Root Root");
            grlPessoaFacade.create(pessoa);
            return this.find(pessoa.getPkGrlPessoa());
        }
}
