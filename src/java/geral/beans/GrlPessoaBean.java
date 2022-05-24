/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.beans;

//import ejbs.cache2.GrlPessoaCache2;
import ejbs.entities.GrlPessoa;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author root
 */
@Named(value = "grlPessoaBean")
@ApplicationScoped
public class GrlPessoaBean {

//    @Inject
//    private GrlPessoaCache2 GrlPessoaCache2;
    /**
     * Creates a new instance of GrlPessoaBean
     */
    public GrlPessoaBean() {
    }
    
//    public GrlPessoa findByPkGrlPessoa(int pkGrlPessoa)
//    {
//        return this.GrlPessoaCache2.find(pkGrlPessoa);
//    }
    
    
}
