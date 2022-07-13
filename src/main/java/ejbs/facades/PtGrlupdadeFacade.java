/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejbs.entities.PtGrlupdade;
import ejbs.facades.AbstractFacade;

/**
 *
 * @author mdnext
 */
@Stateless
public class PtGrlupdadeFacade extends AbstractFacade<PtGrlupdade> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtGrlupdadeFacade() {
        super(PtGrlupdade.class);
    }
    
    public PtGrlupdade obterRegistoPtGrlupdades()
    {
        List<PtGrlupdade> list = this.findAll();
        return list.isEmpty() ? null : list.get(0);
    }
    
    public Date dataPtMarcaTabela()
    {
        PtGrlupdade reg = obterRegistoPtGrlupdades();
        return reg == null ? null : reg.getPtMarcaDate();
    }

   
    public void escreverDataActualizacaoPtMarca(Date date)
    {
        PtGrlupdade reg = obterRegistoPtGrlupdades();

        if (reg == null)
        {
            reg = new PtGrlupdade();
            reg.setPtMarcaDate(date);
            this.create(reg);
        }
        else
        {
            reg.setPtMarcaDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataPtModeloTabela()
    {
        PtGrlupdade reg = obterRegistoPtGrlupdades();
        return reg == null ? null : reg.getPtModeloDate();
    }

   
    public void escreverDataActualizacaoPtModelo(Date date)
    {
        PtGrlupdade reg = obterRegistoPtGrlupdades();

        if (reg == null)
        {
            reg = new PtGrlupdade();
            reg.setPtModeloDate(date);
            this.create(reg);
        }
        else
        {
            reg.setPtModeloDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataPtEstadoConservacaoTabela()
    {
        PtGrlupdade reg = obterRegistoPtGrlupdades();
        return reg == null ? null : reg.getPtEstadoConservacaoDate();
    }

   
    public void escreverDataActualizacaoPtEstadoConservacao(Date date)
    {
        PtGrlupdade reg = obterRegistoPtGrlupdades();

        if (reg == null)
        {
            reg = new PtGrlupdade();
            reg.setPtEstadoConservacaoDate(date);
            this.create(reg);
        }
        else
        {
            reg.setPtEstadoConservacaoDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataPtFormaAquisicao()
    {
        PtGrlupdade reg = obterRegistoPtGrlupdades();
        return reg == null ? null : reg.getPtFormaAquisicaoDate();
    }

   
    public void escreverDataActualizacaoPtFormaAquisicao(Date date)
    {
        PtGrlupdade reg = obterRegistoPtGrlupdades();

        if (reg == null)
        {
            reg = new PtGrlupdade();
            reg.setPtFormaAquisicaoDate(date);
            this.create(reg);
        }
        else
        {
            reg.setPtFormaAquisicaoDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataPtCategoria()
    {
        PtGrlupdade reg = obterRegistoPtGrlupdades();
        return reg == null ? null : reg.getPtCategoriaDate();
    }

   
    public void escreverDataActualizacaoPtCategoria(Date date)
    {
        PtGrlupdade reg = obterRegistoPtGrlupdades();

        if (reg == null)
        {
            reg = new PtGrlupdade();
            reg.setPtCategoriaDate(date);
            this.create(reg);
        }
        else
        {
            reg.setPtCategoriaDate(date);
            this.edit(reg);
        }
    }
    
    public void escreverDataActualizacaoPtTipoSaidaBem(Date date)
    {
        PtGrlupdade reg = obterRegistoPtGrlupdades();

        if (reg == null)
        {
            reg = new PtGrlupdade();
            reg.setPtTipoSaidaBemDate(date);
            this.create(reg);
        }
        else
        {
            reg.setPtTipoSaidaBemDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataPtTipoSaidaBemTabela()
    {
        PtGrlupdade reg = obterRegistoPtGrlupdades();
        return reg == null ? null : reg.getPtTipoSaidaBemDate();
    }
}
