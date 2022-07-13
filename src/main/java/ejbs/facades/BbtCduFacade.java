/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.BbtCdu;
import ejbs.facades.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hermann
 */
@Stateless
public class BbtCduFacade extends AbstractFacade<BbtCdu>
{

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public BbtCduFacade()
    {
        super(BbtCdu.class);
    }

    public boolean isPai(BbtCdu cdu)
    {
        return (cdu.getPkBbtCdu().trim().length() == 1);
    }

    public boolean existe(BbtCdu cdu)
    {
        return (find(cdu.getPkBbtCdu()) != null);
        //locLocalidadeFacade.find(reg.getPkLocLocalidade()) == null
    }

    public BbtCdu getBbtCampoPai(BbtCdu cdu)
    {
        /*
            se cdu.length == 1
                return null
            repeat
                pai = filtraPai(cdu)
                cduPai = obterPai(pai)
                se cduPai != null
                    return pai
                cdu = pai
            fim_repeat 
         */
        if (cdu.getPkBbtCdu().length() == 1)
        {
            return null;
        }
        String pkCdu = cdu.getPkBbtCdu();
        do
        {
            String pkCduPai = filtraPai(pkCdu);
            System.err.println("0: BbtCduFacade.getBbtCampoPai()\tpkCdu: " + pkCdu + "\tpkCduPai: " + pkCduPai);
            BbtCdu cduPai = find(pkCduPai);
            
              System.err.println("1: BbtCduFacade.getBbtCampoPai()\tcduPai: " + 
                  (cduPai == null ? "null" : cduPai.getPkBbtCdu()));
            if (cduPai != null)
            {
                System.err.println("2: BbtCduFacade.getBbtCampoPai()");
                return cduPai;
            }
            System.err.println("3: BbtCduFacade.getBbtCampoPai()");
            pkCdu = pkCduPai;
        }
        while (true);

    }

    private String filtraPai(String pkBbtCdu)
    {
        /**
         * Eliminar o último caractere Se o último caracter == . eliminá-lo
         */
        String pai = pkBbtCdu.substring(0, pkBbtCdu.length() - 1);
        if (pai.charAt(pai.length() - 1) == '.')
        {
            pai = pai.substring(0, pai.length() - 1);
            
        }
        return pai;
    }



}
