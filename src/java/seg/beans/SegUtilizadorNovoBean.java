/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

//import ejbs.cache2.RhColaboradorCache2;   COMENTADO
import ejbs.entities.GrlPessoa;
//import ejbs.entities.RhColaborador;        COMENTADO
import ejbs.entities.SegConta;
import ejbs.facades.SegContaFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import seg.utils.EncriptacaoDecriptacao;
import utils.StringUtils;
import utils.parametros_entre_formularios.ParametrosEntreFormulariosBean;  
/**
 * managed bean do formulário seg_utilizador.xhtml
 *
 * @author délcio benga; helena jorge
 */
@Named
@ViewScoped
public class SegUtilizadorNovoBean implements Serializable
{
    @Inject
    ParametrosEntreFormulariosBean parametrosEntreFormulariosBean;    

    @Inject
    SegPessoaBean segPessoaBean;

    @EJB
    private SegContaFacade segContaFacade;

    //@Inject
    //private RhColaboradorCache2 rhColaboradorCache2;  COMETADO

    
    private SegConta conta;

    //private List<RhColaborador> listaColaboradores;     COMENTADO

    private String palavaraPasseVizualizar;

    private SegLoginBean segLoginBean;

    private SegConta contaCorrente;

    /**
     * Creates a new instance of SegUtilizadorNovoBean
     */
    public SegUtilizadorNovoBean()
    {
    }

    @PostConstruct
    public void init()
    {
        conta = this.segContaFacade.getInstancia();
//System.err.println("0: SegUtilizadorNovoBean.init()");
       // listaColaboradores = this.rhColaboradorCache2.findAllColaboradoresSemConta();
//System.err.println("2: SegUtilizadorNovoBean.init()");        
    }

    public void preRenderView()
    {
//		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
    public void definirParametrosDaConta(SegConta userCorrente)
    {
        String nomeUtilizador;
        long numberCount;

        numberCount = segContaFacade.findAllQuantidadeDeContaByPkGrlPessoa(conta.getFkSegPessoa().getPkGrlPessoa());
        if (numberCount != 0)
        {
            nomeUtilizador = (StringUtils.removerAcentos(segPessoaBean.find(conta.getFkSegPessoa().getPkGrlPessoa()).getNome()) + conta.getFkSegPessoa()).toLowerCase();
        }
        else
        {
            nomeUtilizador = (StringUtils.removerAcentos(segPessoaBean.find(conta.getFkSegPessoa().getPkGrlPessoa()).getNome())).toLowerCase();
        }
        conta.setNomeUtilizador(nomeUtilizador.replaceAll(" ", "."));
        palavaraPasseVizualizar = StringUtils.removerAcentos(segPessoaBean.find(conta.getFkSegPessoa().getPkGrlPessoa()).getNome()).toUpperCase().replaceAll(" ", ".");
        conta.setPalavraPasse(EncriptacaoDecriptacao.encrypt(palavaraPasseVizualizar));
        conta.setDataCadastro(new Date());
        conta.setActivo(Boolean.TRUE);
        conta.setPrimeiroLoginConta(Boolean.TRUE);
        conta.setFkSegConta(userCorrente);
    }

    /**
     * @param pessoa
     */
    public void initProximaPagina(GrlPessoa pessoa)
    {
System.err.println("0: SegUtilizadorNovoBean.initProximaPagina()\tpessoa: " + pessoa.getNome());        
        contaCorrente = SegLoginBean.getInstanciaBean().getContaUtilizador();
System.err.println("1: SegUtilizadorNovoBean.initProximaPagina()\tpessoa: " + pessoa.getNome());        
        conta.setFkSegPessoa(pessoa);
System.err.println("2: SegUtilizadorNovoBean.initProximaPagina()\tpessoa: " + pessoa.getNome());        
        try
        {
System.err.println("3: SegUtilizadorNovoBean.initProximaPagina()\tpessoa: " + pessoa.getNome());            
            definirParametrosDaConta(contaCorrente);
System.err.println("4: SegUtilizadorNovoBean.initProximaPagina()\tpessoa: " + pessoa.getNome());            
        }
        catch (Exception ex)
        {
System.err.println("5: SegUtilizadorNovoBean.initProximaPagina()\tpessoa: " + pessoa.getNome());            
            Logger.getLogger(SegUtilizadorNovoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
System.err.println("6: SegUtilizadorNovoBean.initProximaPagina()\tpessoa: " + pessoa.getNome());        
        parametrosEntreFormulariosBean.setParametro("palavaraPasseVizualizar", palavaraPasseVizualizar);
        parametrosEntreFormulariosBean.setParametro("conta", conta);
System.err.println("7: SegUtilizadorNovoBean.initProximaPagina()\tpessoa: " + pessoa.getNome());        
    }


    /**
     * @return the conta
     */
    public SegConta getConta()
    {
        return conta;
    }

    /**
     * @param conta the conta to set
     */
    public void setConta(SegConta conta)
    {
        this.conta = conta;
    }

    public String getPalavaraPasseVizualizar()
    {
        return palavaraPasseVizualizar;
    }

    public void setPalavaraPasseVizualizar(String palavaraPasseVizualizar)
    {
        this.palavaraPasseVizualizar = palavaraPasseVizualizar;
    }

    public SegLoginBean getSegLoginBean()
    {
        return segLoginBean;
    }

    public void setSegLoginBean(SegLoginBean segLoginBean)
    {
        this.segLoginBean = segLoginBean;
    }
/*    COMENTADO
    public List<RhColaborador> getListaColaboradores()
    {
        return listaColaboradores;
    }
*/
    
    /*COMENTADO
    public void setListaColaboradores(List<RhColaborador> listaColaboradores)
    {
        this.listaColaboradores = listaColaboradores;
    }
*/

}
