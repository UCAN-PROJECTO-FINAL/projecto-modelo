/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.cache.utils.TabelaPadraoCacheUtils;
import ejbs.entities.GrlEstadoCivil;
import ejbs.facades.GrlEstadoCivilFacade;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import utils.Defs;
import utils.StringUtils;

/**
 *
 * @author Aires Veloso
 */
@Named(value = "grlEstadoCivilCache")
@ApplicationScoped
public class GrlEstadoCivilCache extends TabelaPadraoCacheUtils<GrlEstadoCivil>
{

    @EJB
    private GrlEstadoCivilFacade grlEstadoCivilFacade;

    /**
     * Creates a new instance of GrlEstadoCivilCache
     */
    public GrlEstadoCivilCache()
    {
    }

    @PostConstruct
    @Override
    public void init()
    {
        List<GrlEstadoCivil> sexoList = grlEstadoCivilFacade.findAll();

        sexoList.forEach((sexo) ->
        {
            put(sexo);
        });
        this.tabelaStringListObject.put("All", sexoList);
        putAllOrderedByNome(sexoList);
    }

    // implement abstract Methods
    @Override
    public int getPKEntity(Object ob)
    {
        GrlEstadoCivil sexo = (GrlEstadoCivil)ob;
        return sexo.getPkGrlEstadoCivil();
    }
    
    @Override
    public void put(GrlEstadoCivil p)
    {
        super.put(p);
        this.tabelaIntegerObject.put(p.getPkGrlEstadoCivil(), p);
        putByNome(p);
    }

    
    private void putAll(GrlEstadoCivil p)
    {
        put(p);
        List<GrlEstadoCivil> listAll = findAll();
        listAll.add(p);

        this.tabelaStringListObject.put("All", listAll);
        putAllOrderedByNome(listAll);
    }

    private void putByNome(GrlEstadoCivil p)
    {
        String nome = p.getNome();
        if (StringUtils.isNull(nome))
        {
            return;
        }
        String chave = this.geraChave("Nome", nome);
        this.tabelaStringObject.put(chave, p);
    }

    private GrlEstadoCivil getByNome(String nome)
    {
        String chave = this.geraChave("Nome", nome);
        return this.tabelaStringObject.get(chave);
    }

    private void putAllOrderedByNome(List<GrlEstadoCivil> list)
    {
        if (list.size() > 1)
        {
            Collections.sort(list, (o1, o2) ->
            {
                GrlEstadoCivil c1 = (GrlEstadoCivil) o1;
                GrlEstadoCivil c2 = (GrlEstadoCivil) o2;
                return c1.getNome().compareToIgnoreCase(c1.getNome());
            });
        }
        this.tabelaStringListObject.put("AllBYN", list);
    }

    public List<GrlEstadoCivil> findAllOrderedByNome()
    {
        return this.tabelaStringListObject.get("AllBYN");
    }

    public GrlEstadoCivil findByNome(String nome)
    {
        if (!StringUtils.isNull(nome))
        {
            return null;
        }
        nome = nome.trim();
        return this.getByNome(nome);
    }

    public GrlEstadoCivil getInstancia()
    {
        return find(Defs.PK_SEXO_PADRAO);
    }
    
    public String toString(GrlEstadoCivil reg)
    {
        return "[" + reg.getPkGrlEstadoCivil() + ", " + reg.getNome() + "]";
    }
    
   public void create(GrlEstadoCivil p)
    {
        this.grlEstadoCivilFacade.create(p);
        int pkGrlEstadoCivil = this.grlEstadoCivilFacade.findLastPkGrlEstadoCivil();
        p.setPkGrlEstadoCivil(pkGrlEstadoCivil);
        putAll(p);
    }

    public void edit(GrlEstadoCivil p)
    {
        this.grlEstadoCivilFacade.edit(p);
        putAll(p);
    }
}
