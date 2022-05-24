/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import ejbs.cache.LocLocalidadeCache;
import ejbs.entities.GrlEndereco;
import ejbs.facades.GrlEnderecoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import utils.mensagens.Mensagem;

/**
 *
 * @author smakambo
 */
@Named(value = "comumEnderecoBean")
@ApplicationScoped
public class ComumEnderecoBean implements Serializable
{

    @EJB
    private GrlEnderecoFacade grlEnderecoFacade;
    @Inject
    private LocLocalidadeCache locLocalidadeCache;

    private String pais, provincia, municipio, comunaOrDistrito;

    private GrlEndereco grlEndereco;

    /**
     * Creates a new instance of ComumEnderecoBean
     */
    public ComumEnderecoBean()
    {

    }

    @PostConstruct
    public void init()
    {
        pais = null;
        provincia = null;
        municipio = null;
        comunaOrDistrito = null;
        grlEndereco = new GrlEndereco();
    }

    public GrlEndereco salvaEGetEndereco()
    {
        try
        {
            if (locLocalidadeCache.findLocLocalidade(comunaOrDistrito) != null)
            {
                grlEndereco.setFkLoclocalidade(locLocalidadeCache.findLocLocalidade(comunaOrDistrito));
                grlEnderecoFacade.create(grlEndereco);
                locLocalidadeCache.init();
                return grlEndereco;
            } else
            {
                Mensagem.warnMsg(null, "Ocorreu um erro ao salvar o Endereço");
            }
            init();

        } catch (Exception e)
        {
            Mensagem.enviarMensagemFatal(null, "Erro ao salvar dados do Endereço, por favor contacte o administrador de sistemas !");
        }
        return null;
    }

    public LocLocalidadeCache getLocLocalidadeCache()
    {
        return locLocalidadeCache;
    }

    public void setLocLocalidadeCache(LocLocalidadeCache locLocalidadeCache)
    {
        this.locLocalidadeCache = locLocalidadeCache;
    }

    public String getPais()
    {
        return pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

    public String getProvincia()
    {
        return provincia;
    }

    public void setProvincia(String provincia)
    {
        this.provincia = provincia;
    }

    public String getMunicipio()
    {
        return municipio;
    }

    public void setMunicipio(String municipio)
    {
        this.municipio = municipio;
    }

    public GrlEndereco getGrlEndereco()
    {
        return grlEndereco;
    }

    public String getComunaOrDistrito()
    {
        return comunaOrDistrito;
    }

    public void setComunaOrDistrito(String comunaOrDistrito)
    {
        this.comunaOrDistrito = comunaOrDistrito;
    }

    public void setGrlEndereco(GrlEndereco grlEndereco)
    {
        this.grlEndereco = grlEndereco;
    }

}
