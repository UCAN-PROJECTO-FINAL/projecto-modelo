/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import utils.HashMapUtils;
import utils.ListUtils;
import utils.StringUtils;

/**
 *
 * @author Aires Veloso
 * @param <T>
 */
public abstract class TabelaPadraoCacheUtils<T> implements CacheInterface<T>, InitInterface
{

    protected ConcurrentHashMap<Integer, T> tabelaIntegerObject;

    protected ConcurrentHashMap<String, T> tabelaStringObject,
        tabelaStringObjectBkUp;
    protected ConcurrentHashMap<String, List<T>> tabelaStringListObject,
        tabelaStringListObjectBkUp;

    protected boolean updated;

    protected boolean active = false;
    private int count;

    public TabelaPadraoCacheUtils()
    {
        count = 0;
        inic();
    }

    protected void inic()
    {
        tabelaStringObject = new ConcurrentHashMap<>();
        tabelaIntegerObject = new ConcurrentHashMap<>();
        tabelaStringListObject = new ConcurrentHashMap<>();
        updated = false;
//System.err.println("0: TabelaPadraoCacheUtils.inic()");        
    }

    @Override
    public int getPKEntity(Object t)
    {
        return 0;
    }

    @Override
    public String toStringKey(Object t)
    {
        return "";
    }

    @Override
    public String nomeEntidade()
    {
        return "";
    }
    
    @Override
    public boolean areTwins(Object t1, Object t2)
    {
        return false;
    }

    @Override
    public void resetAll()
    {
        tabelaStringObject = new ConcurrentHashMap<>();
        tabelaIntegerObject = new ConcurrentHashMap<>();
        tabelaStringListObject = new ConcurrentHashMap<>();
        this.resetBkUps();
        System.gc();
    }
    
     public void resetParcial()
    {
        tabelaStringObject = new ConcurrentHashMap<>();
        tabelaStringListObject = new ConcurrentHashMap<>();
        this.resetBkUps();
        System.gc();
    }

    public void put(T t)
    {
        if (active)
        {
//            System.err.println("0: TabelaPadraoCacheUtils.put(T)\tpKEntity: "
//                + this.getPKEntity(t));
        }
        this.tabelaIntegerObject.put(this.getPKEntity(t), t);
        updated = false;
    }

    public boolean remove(T t)
    {
//        System.err.println("0: TabelaPadraoCacheUtils.remove(T)");
        boolean rt = this.tabelaIntegerObject.remove(this.getPKEntity(t)) != null;
        updated = false;
        return rt;
    }

    public int size()
    {
        return findAll().size();
    }
    
    public List<T> toList()
    {
        Enumeration<Integer> keys = tabelaIntegerObject.keys();

        T object;
        List<T> list = new ArrayList();

        while (keys.hasMoreElements())
        {
            Integer key = keys.nextElement();
            object = tabelaIntegerObject.get(key);
            list.add(object);
        }
        return list;
    }

    protected void restart()
    {
//System.err.println("0: TabelaPadraoCacheUtils.restart()");        
        if (updated)
        {
//System.err.println("1: TabelaPadraoCacheUtils.restart()");            
            return;
        }
//System.err.println("2: TabelaPadraoCacheUtils.restart()");        
        Enumeration<Integer> keys = tabelaIntegerObject.keys();

        T object;
        List<T> list = new ArrayList();

        while (keys.hasMoreElements())
        {
            Integer key = keys.nextElement();
            object = tabelaIntegerObject.get(key);
            list.add(object);
            count++;
        }
//        if (active)
//        {
//            System.err.println("3: TabelaPadraoCacheUtils.restart()\tcount: " + count);
//        }
        tabelaStringListObject = new ConcurrentHashMap<>();

        this.tabelaStringObject = new ConcurrentHashMap<>();

        this.tabelaStringListObject.put("All", list);
        updated = true;
    }

    protected void resetBkUps()
    {
        tabelaStringListObjectBkUp = new ConcurrentHashMap<>();
        this.tabelaStringObjectBkUp = new ConcurrentHashMap<>();
    }

    public void geraCloneTabelas()
    {
        tabelaStringObjectBkUp = HashMapUtils.clone(tabelaStringObject);
        this.tabelaStringListObjectBkUp
            = HashMapUtils.cloneTabelaTypeList(tabelaStringListObject);
    }

    public void rollback()
    {
//System.err.println("0: TabelaPadraoCacheUtils.rollback()");        
        tabelaStringListObject = tabelaStringListObjectBkUp;
        tabelaStringObject = tabelaStringObjectBkUp;
    }

    protected String geraChave(String arg1, int arg2)
    {
        return ChaveUtils.geraChave(arg1, arg2);
    }

    protected String geraChave(String arg1, int arg2, int arg3)
    {
        return ChaveUtils.geraChave(arg1, arg2, arg3);
    }

    protected String geraChave(String arg1, String arg2, int arg3)
    {
        return ChaveUtils.geraChave(arg1, arg2, arg3);
    }

    protected String geraChave(String arg1, int arg2, String arg3)
    {
        return ChaveUtils.geraChave(arg1, arg2, arg3);
    }

    protected String geraChave(String arg1, boolean arg2)
    {
        return ChaveUtils.geraChave(arg1, arg2);
    }

    protected static String geraChave(String arg1, Integer arg2, List<String> args)
    {
        return ChaveUtils.geraChave(arg1, arg2, args);
    }

    protected static String geraChave(String... args)
    {
        return ChaveUtils.geraChave(args);
    }

    public boolean existeRegisto(int pk)
    {
        T reg = find(pk);
        return reg != null;
    }

    public T find(Integer pkType)
    {
        this.restart();
        return tabelaIntegerObject.get(pkType);
    }

    public List<T> findAll()
    {
//System.err.println("0: TabelaPadraoCacheUtils.findAll()\tupdated: " + this.updated);        
        this.restart();
        List<T> list = tabelaStringListObject.get("All");
//System.err.println("1: TabelaPadraoCacheUtils.findAll()\tsz: " + 
//    (list == null ? 0 : list.size()));        
        return list == null ? new ArrayList() : list;
    }

    public static String padronizaProvincia(String provincia)
    {
//System.err.println("0: TabelaPadraoCacheUtils.padronizaProvincia()\tprovincia: " + provincia);          
        provincia = StringUtils.reduceSpaces(provincia);
//System.err.println("00: TabelaPadraoCacheUtils.padronizaProvincia()\tprovincia: " + provincia);        
        ArrayList<String> partes = new ArrayList<>();
        if ((provincia.indexOf(' ') != -1) || (provincia.indexOf('-') != -1))
        {
//System.err.println("1: TabelaPadraoCacheUtils.padronizaProvincia()\tprovincia: " + provincia);            
            partes.add(provincia); //= provincia.split(' ', '-');//StringUtils.split(provincia, ' ', '-');
//System.err.println("2: TabelaPadraoCacheUtils.padronizaProvincia()\tpartes.length: " 
//    + partes.size() + "\tpartes[0]: " + partes.get(0) + "\tpartes[1]: " + partes.get(1));            
            if ((partes.get(0).equalsIgnoreCase("Kuando") || partes.get(0).equalsIgnoreCase("Cuando"))
                && (partes.get(1).equalsIgnoreCase("Kubango") || partes.get(1).equalsIgnoreCase("Cubango")))
            {
                return "Kuando-Kubango";
            }

            if ((partes.get(0).equalsIgnoreCase("Kwanza") || partes.get(0).equalsIgnoreCase("Cwanza")
                || partes.get(0).equalsIgnoreCase("Kuanza") || partes.get(0).equalsIgnoreCase("Cuanza")))
            {
//System.err.println("3: TabelaPadraoCacheUtils.padronizaProvincia()\tprovincia: " + provincia);                
                if (partes.get(1).equalsIgnoreCase("Norte"))
                {
//System.err.println("4: TabelaPadraoCacheUtils.padronizaProvincia()\tprovincia: " + provincia);                    
                    return "Kwanza-Norte";
                }
//System.err.println("5: TabelaPadraoCacheUtils.padronizaProvincia()\tprovincia: " + provincia);                
                if (partes.get(1).equalsIgnoreCase("Sul"))
                {
//System.err.println("6: TabelaPadraoCacheUtils.padronizaProvincia()\tprovincia: " + provincia);                    
                    return "Kwanza-Sul";
                }
            }
//System.err.println("7: TabelaPadraoCacheUtils.padronizaProvincia()\tprovincia: " + provincia);            
            if (partes.get(0).equalsIgnoreCase("Lunda"))
            {
                if (partes.get(1).equalsIgnoreCase("Norte"))
                {
                    return "Lunda-Norte";
                }
                if (partes.get(1).equalsIgnoreCase("Sul"))
                {
                    return "Lunda-Sul";
                }
            }
        }
//System.err.println("8: TabelaPadraoCacheUtils.padronizaProvincia()\tprovincia: " + provincia);        
        if (provincia.equalsIgnoreCase("Kunene"))
        {
            return "Cunene";
        }
        if (provincia.equalsIgnoreCase("Malange"))
        {
            return "Malanje";
        }
        if (provincia.equalsIgnoreCase("Huila"))
        {
            return "Huíla";
        }
        if (provincia.equalsIgnoreCase("Bie"))
        {
            return "Bié";
        }
        if (provincia.equalsIgnoreCase("Zaíre"))
        {
            return "Zaire";
        }
//System.err.println("9: TabelaPadraoCacheUtils.padronizaProvincia()\tprovincia: " + provincia);        
        return provincia;
    }

    public boolean isUpdated()
    {
        return updated;
    }

    public void setUpdated(boolean updated)
    {
        this.updated = updated;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public void verificarDuplicados(List<T> tList)
    {
//        System.err.println("0: TabelaPadraoCacheUtils.verificarDuplicados(List<PrjProcessoCandidatura>)");
        VerificarDuplicados vd = new VerificarDuplicados(tList);

        String msg = "A Entidade \"" + nomeEntidade() + "\" não contem duplicados";
        if (vd.temDuplicados())
        {
//            System.err.println("1: TabelaPadraoCacheUtils.verificarDuplicados(List<PrjProcessoCandidatura>)");
            msg = "A Entidade \"" + nomeEntidade() + "\" contem os seguintes duplicados:\n";
            msg += vd.toString();
        }
        System.err.println(msg);
//        System.err.println("2: TabelaPadraoCacheUtils.verificarDuplicados(List<PrjProcessoCandidatura>)");
    }

    public class VerificarDuplicados<T>
    {

        private List<T> tList;
        private List<List<T>> duplicadosListList;
        private int size;

        public VerificarDuplicados(List<T> tList)
        {
            this.tList = tList;
            duplicadosListList = new ArrayList();
            size = this.tList.size();
            int i = 0;
            List<T> duplicadosList;
            T t;
            for (; i < size - 1; i++)
            {
                t = tList.get(i);
                if (includes(t))
                {
                    continue;
                }
                duplicadosList = pesquizarDuplos(t, i + 1);
                if (!duplicadosList.isEmpty())
                {
                    duplicadosListList.add(duplicadosList);
                }
            }
        }

        private List<T> pesquizarDuplos(T t, int indice)
        {
            List<T> duplicadosList = new ArrayList();
            T tmp;
//            String chave = prjProcessoCandidatura.getChave();
            for (int i = indice; i < size - 1; i++)
            {
                tmp = this.tList.get(i);
//                if (chave.equalsIgnoreCase(processo.getChave()))
                if (areTwins(t, tmp))
                {
                    duplicadosList.add(tmp);
                }
            }
            if (!duplicadosList.isEmpty())
            {
                duplicadosList.add(0, t);
            }
            return duplicadosList;
        }

        public boolean temDuplicados()
        {
            return !this.duplicadosListList.isEmpty();
        }

        public boolean include(List<T> list, T t)
        {
            List<Integer> pkList = new ArrayList();
            for (T p : list)
            {
                pkList.add(getPKEntity(p));
            }
            return ListUtils.includes(pkList, getPKEntity(t));
        }

        private boolean includes(T t)
        {
            if (duplicadosListList.isEmpty())
            {
                return false;
            }
            for (List<T> lista : duplicadosListList)
            {
                if (include(lista, t))
                {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString()
        {
            String msg = "";
            if (!temDuplicados())
            {
                return msg;
            }
            msg += "{ ";

            for (List<T> duplicadosList : this.duplicadosListList)
            {
                msg += toString(duplicadosList);
            }
            msg += " }";
            return msg;
        }

        public String toString(List<T> duplicadosList)
        {
            String msg = "";
            boolean first = true;
            T t = duplicadosList.get(0);
            msg += "{ " + toStringKey(t) + ": ";
            for (T processo : duplicadosList)
            {
                if (!first)
                {
                    msg += ", ";
                }
                else
                {
                    first = false;
                }
                msg += getPKEntity(processo);
            }
            msg += " }";
            return msg;
        }
    }

    // Getters and Setters

    public ConcurrentHashMap<Integer, T> getTabelaIntegerObject()
    {
        return tabelaIntegerObject;
    }
    
}
