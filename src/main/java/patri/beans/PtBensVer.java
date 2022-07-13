 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patri.beans;

import ejbs.entities.PtBemImovel;
import ejbs.entities.PtBemItangivel;
import ejbs.entities.PtBemMovel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import patri.ejbs.cache.PtBemImovelCache;
import patri.ejbs.cache.PtBemItangivelCache;
import patri.ejbs.cache.PtBemMovelCache;

/**
 *
 * @author mdnext
 */
@Named(value = "ptBensVerBean")
@ViewScoped
public class PtBensVer implements Serializable
{
    
    final String bemMovelTipoStr = "movel", bemImovelTipoStr = "imovel", bemItangivelTipoStr = "itangivel";
    private List<PtBemMovel> listPtBemMovel;
    private List<PtBemImovel> listPtBemImovel;
    private List<PtBemItangivel> listPtBemItangivel;
    
    @Inject
    private PtBemMovelCache ptBemMovelCache;
    
    @Inject
    private PtBemImovelCache ptBemImovelCache;
    
    @Inject
    private PtBemItangivelCache ptBemItangivelCache;
    
    private boolean hasDetail = false,encaminharActive = false;
    
    private String ptDetailsBem,editarLink,saidaLink;
    
    
    
    @PostConstruct
    private void init()
    {
        listPtBemMovel = ptBemMovelCache.getListaPtBemMovel();
        
        listPtBemImovel = ptBemImovelCache.getListaPtBemImovel();
        
        listPtBemItangivel = ptBemItangivelCache.getListaPtBemItangivel();
        
    }
    
    public void changeSelectedBem(int pkBem,String tipoBem)
    {   
        hasDetail = true;
        encaminharActive = false;
        if(tipoBem.equals(bemMovelTipoStr))
        {
            encaminharActive = true;
            detailsBemMovel(ptBemMovelCache.findPtBemMovel(pkBem));
        }
        else if(tipoBem.equals(bemImovelTipoStr))
        {
            detailsBemImovel(ptBemImovelCache.findPtBemImovel(pkBem));
        }
        else if(tipoBem.equals(bemItangivelTipoStr))
        {
            detailsBemItangivel(ptBemItangivelCache.findPtBemItangivel(pkBem));
        }
    }
    
    public void detailsBemMovel(PtBemMovel reg)
    {
        editarLink = ""+reg.getPkPtBemMovel();
        ptDetailsBem = "<br><strong>Localização:<strong/>"+reg.getFkEstruturaFisica().getFkEstruturaFisica().getFkEstruturaFisica().getDesignacao()+" --> "+
                reg.getFkEstruturaFisica().getFkEstruturaFisica().getDesignacao()+"-->"+reg.getFkEstruturaFisica().getDesignacao()+"<br>"+
                "<br><strong>Detalhe:<strong/>"+reg.getDetalhes();
    }
    
    public void detailsBemImovel(PtBemImovel reg)
    {
        editarLink = ""+reg.getPkPtBemImovel();
        ptDetailsBem = "<br><strong>Localização:<strong/>"+reg.getFkEstruturaFisica().getDesignacao() +" "+"<br><strong>Detalhe:<strong/>"+reg.getDetalhes();
    }
    
    public void detailsBemItangivel(PtBemItangivel reg)
    {
      editarLink = ""+reg.getPkPtBemItangivel();
        ptDetailsBem ="<br><strong>Detalhe:<strong/>"+reg.getDescricaoDetalhada();   
    }
    
    //--------------------------------------------------------------------------

    public List<PtBemMovel> getListPtBemMovel() {
        return listPtBemMovel;
    }

    public void setListPtBemMovel(List<PtBemMovel> listPtBemMovel) {
        this.listPtBemMovel = listPtBemMovel;
    }

    public List<PtBemImovel> getListPtBemImovel() {
        return listPtBemImovel;
    }

    public void setListPtBemImovel(List<PtBemImovel> listPtBemImovel) {
        this.listPtBemImovel = listPtBemImovel;
    }

    public List<PtBemItangivel> getListPtBemItangivel() {
        return listPtBemItangivel;
    }

    public void setListPtBemItangivel(List<PtBemItangivel> listPtBemItangivel) {
        this.listPtBemItangivel = listPtBemItangivel;
    }

    public PtBemMovelCache getPtBemMovelCache() {
        return ptBemMovelCache;
    }

    public void setPtBemMovelCache(PtBemMovelCache ptBemMovelCache) {
        this.ptBemMovelCache = ptBemMovelCache;
    }

    public PtBemImovelCache getPtBemImovelCache() {
        return ptBemImovelCache;
    }

    public void setPtBemImovelCache(PtBemImovelCache ptBemImovelCache) {
        this.ptBemImovelCache = ptBemImovelCache;
    }

    public PtBemItangivelCache getPtBemItangivelCache() {
        return ptBemItangivelCache;
    }

    public void setPtBemItangivelCache(PtBemItangivelCache ptBemItangivelCache) {
        this.ptBemItangivelCache = ptBemItangivelCache;
    }

    public boolean isHasDetail() {
        return hasDetail;
    }

    public void setHasDetail(boolean hasDetail) {
        this.hasDetail = hasDetail;
    }

    public String getPtDetailsBem() {
        return ptDetailsBem;
    }

    public void setPtDetailsBem(String ptDetailsBem) {
        this.ptDetailsBem = ptDetailsBem;
    }

    public String getBemMovelTipoStr() {
        return bemMovelTipoStr;
    }

    public String getBemImovelTipoStr() {
        return bemImovelTipoStr;
    }

    public String getBemItangivelTipoStr() {
        return bemItangivelTipoStr;
    }

    public String getEditarLink() {
        return editarLink;
    }

    public void setEditarLink(String editarLink) {
        this.editarLink = editarLink;
    }

    public String getSaidaLink() {
        return saidaLink;
    }

    public void setSaidaLink(String saidaLink) {
        this.saidaLink = saidaLink;
    }
    
}
