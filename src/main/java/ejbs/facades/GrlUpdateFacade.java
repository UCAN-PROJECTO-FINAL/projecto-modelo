/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GrlUpdate;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smakambo
 */
@Stateless
public class GrlUpdateFacade extends AbstractFacade<GrlUpdate> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrlUpdateFacade() {
        super(GrlUpdate.class);
    }
    
    
       
    public GrlUpdate obterRegistoGrlUpdates()
    {
        List<GrlUpdate> list = this.findAll();
        return list.isEmpty() ? null : list.get(0);
    }

    public Date dataPaisTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getLocalidadesDate();
    }

   
    public void escreverDataActualizacaoLocalidade(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setLocalidadesDate(date);
            this.create(reg);
        }
        else
        {
            reg.setLocalidadesDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataEstruturaFisicaTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getEstruturaFisicaDate();
    }

   
    public void escreverDataActualizacaoEstruturaFisica(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setEstruturaFisicaDate(date);
            this.create(reg);
        }
        else
        {
            reg.setEstruturaFisicaDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataEstruturaLogicaFisicaTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getLocalidadesDate();
    }
    
    public void escreverDataActualizacaoEstruturaLogica(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setEstruturaLogicaDate(date);
            this.create(reg);
        }
        else
        {
            reg.setEstruturaLogicaDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataEstruturaLogicaTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getEstruturaLogicaDate();
    }
    
     
    //
    
    public void escreverDataActualizacaoBanco(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setBancoDate(date);
            this.create(reg);
        }
        else
        {
            reg.setBancoDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataBancoTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getBancoDate();
    }
    //
    
     //
    
    public void escreverDataActualizacaoTipoCartao(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setTipoCartaoDate(date);
            this.create(reg);
        }
        else
        {
            reg.setTipoCartaoDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataTipoCartaoTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getTipoCartaoDate();
    }
    //
    
     //
    
    public void escreverDataActualizacaoTipoConta(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setTipoContaDate(date);
            this.create(reg);
        }
        else
        {
            reg.setTipoContaDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataTipoContaTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getTipoContaDate();
    }
    //
    
     //
    
    public void escreverDataActualizacaoComumMoeda(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setMoedaDate(date);
            this.create(reg);
        }
        else
        {
            reg.setMoedaDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataComumMoedaTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getMoedaDate();
    }
    //
   
  
    
    public void escreverDataActualizacaoComunaTabela(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setComunaDate(date);
            this.create(reg);
        }
        else
        {
            reg.setComunaDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataComuna()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getComunaDate();
    }
    //
    
    public void escreverDataActualizacaoDistritoTabela(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setDistritoDate(date);
            this.create(reg);
        }
        else
        {
            reg.setDistritoDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataDistrito()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getDistritoDate();
    }
    
     //
    
    public void escreverDataActualizacaoMunicipiosTabela(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setMunicipioDate(date);
            this.create(reg);
        }
        else
        {
            reg.setMunicipioDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataMunicipiosTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getMunicipioDate();
    }
    
    //
    
    public void escreverDataActualizacaoPaisTabela(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setPaisDate(date);
            this.create(reg);
        }
        else
        {
            reg.setPaisDate(date);
            this.edit(reg);
        }
    }
    
    
    
     //
    
    public void escreverDataActualizacaoProvinciaTabela(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setProvinciaDate(date);
            this.create(reg);
        }
        else
        {
            reg.setProvinciaDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataProvinciasTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getProvinciaDate();
    }
    
     //
    
    public void escreverDataActualizacaoTipoCategoriaTabela(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setTipoCategoriaDate(date);
            this.create(reg);
        }
        else
        {
            reg.setTipoCategoriaDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataTipoCategoriaTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getTipoCategoriaDate();
    }
    
    
     //
    
    public void escreverDataActualizacaoCategoriaTabela(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setCategoriaDate(date);
            this.create(reg);
        }
        else
        {
            reg.setCategoriaDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataCategoriaTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getCategoriaDate();
    }
    
    
     //
    
    public void escreverDataActualizacaoSubCategoriaTabela(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setSubCategoriaDate(date);
            this.create(reg);
        }
        else
        {
            reg.setSubCategoriaDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataSubCategoriaTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getSubCategoriaDate();
    }
   
    
}
