package ejbs.cache;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejbs.cache.utils.TabelaPadraoCacheUtils;
import ejbs.entities.GrlSexo;
import ejbs.facades.GrlSexoFacade;
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
@Named(value = "grlSexoCache")
@ApplicationScoped
public class GrlSexoCache extends TabelaPadraoCacheUtils<GrlSexo>
{

    @EJB
    private GrlSexoFacade grlSexoFacade;

    /**
     * Creates a new instance of GrlSexoCache
     */
    public GrlSexoCache()
    {
    }

    @PostConstruct
    @Override
    public void init()
    {
        List<GrlSexo> sexoList = grlSexoFacade.findAll();

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
        GrlSexo sexo = (GrlSexo)ob;
        return sexo.getPkGrlSexo();
    }

    @Override
    public void put(GrlSexo p)
    {
        super.put(p);
        putByNome(p);
    }
    
    private void putAll(GrlSexo p)
    {
        put(p);
        List<GrlSexo> listAll = findAll();
        listAll.add(p);

        this.tabelaStringListObject.put("All", listAll);
        putAllOrderedByNome(listAll);
    }

    private void putByNome(GrlSexo p)
    {
        String nome = p.getNome();
        if (StringUtils.isNull(nome))
        {
            return;
        }
        String chave = this.geraChave("Nome", nome);
        this.tabelaStringObject.put(chave, p);
    }

    private GrlSexo getByNome(String nome)
    {
        String chave = this.geraChave("Nome", nome);
        return this.tabelaStringObject.get(chave);
    }

    private void putAllOrderedByNome(List<GrlSexo> list)
    {
        if (list.size() > 1)
        {
            Collections.sort(list, (o1, o2) ->
            {
                GrlSexo c1 = (GrlSexo) o1;
                GrlSexo c2 = (GrlSexo) o2;
                return c1.getNome().compareToIgnoreCase(c1.getNome());
            });
        }
        this.tabelaStringListObject.put("AllBYN", list);
    }

    public List<GrlSexo> findAllOrderedByNome()
    {
        return this.tabelaStringListObject.get("AllBYN");
    }

    public GrlSexo findByNome(String nome)
    {
        if (!StringUtils.isNull(nome))
        {
            return null;
        }
        nome = nome.trim();
        return this.getByNome(nome);
    }

    public GrlSexo getInstancia()
    {
        return find(Defs.PK_SEXO_PADRAO);
    }
    
    public String toString(GrlSexo reg)
    {
        return "[" + reg.getPkGrlSexo() + ", " + reg.getNome() + "]";
    }
   
    /*
   public void create(GrlSexo p)
    {
        this.grlSexoFacade.create(p);
        int pkGrlSexo = this.grlSexoFacade.findLastPkGrlSexo();
        p.setPkGrlSexo(pkGrlSexo);
        putAll(p);
    }*/

    public void edit(GrlSexo p)
    {
        this.grlSexoFacade.edit(p);
        putAll(p);
    }
}
