/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.entities.SegConta;
import ejbs.facades.SegContaFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import utils.StringUtils;

/**
 *
 * @author bfg
 */
@Named(value = "segContaBean")
@ApplicationScoped
public class SegContaBean
{
    
    @EJB
    private SegContaFacade segContaFacade;
    
    @EJB
    private SegContaFacade contaFacade;
    
    private List<SegConta> listaContas;

    /**
     * Creates a new instance of SegContaBean
     */
    public SegContaBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
        listaContas = contaFacade.findAllContaOrderByNomeUtilizador();
    }
    
    public List<SegConta> getListaContas()
    {
        return listaContas;
    }
    
    public boolean disableIfRoot(SegConta conta)
    {
        if (conta == null || StringUtils.isNull(conta.getNomeUtilizador()))
        {
System.err.println("0. SegContaBean.disableIfRoot()");            
            return false;
        }
System.err.println("1. SegContaBean.disableIfRoot()");        
//        conta = conta != null ? conta : SegLoginBean.getInstanciaBean().getContaUtilizador();
System.err.println("2. SegContaBean.disableIfRoot()\tconta: " + (conta == null ? "null" : conta.getNomeUtilizador()));        
        boolean rt = this.segContaFacade.isRootAccount(conta);
System.err.println("3. SegContaBean.disableIfRoot()\trt: " + rt);
////System.err.println("4: SegContaBean.disableIfRoot()\tcontasPesquisadas: " + ListUtils.toString(contasPesquisadas, this.segContaFacade::toString));
        return rt;
    }
 
    public boolean isRootAccount(SegConta count)
    {
        if (count == null || StringUtils.isNull(count.getNomeUtilizador()))
            return false;
        System.err.println("0. SegContaBean.isRootAccount()\tcount: " + (count == null ? "null" : count.getNomeUtilizador()));        
        boolean rt = this.segContaFacade.isRootAccount(count);
        System.err.println("1. SegContaBean.isRootAccount()\trt: " + rt);
        return rt;
    }
    
    public void bloquearConta(SegConta conta)
    {
        conta.setActivo(false);
        this.segContaFacade.edit(conta);
    }
    
    public void desbloquearConta(SegConta conta)
    {
        conta.setActivo(true);
        this.segContaFacade.edit(conta);
    }
}
