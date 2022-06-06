/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejbs.entities.LocGrlExcelPath;
import ejbs.facades.AbstractFacade;
import utils.Defs;

/**
 *
 * @author mdnex
 */
@Stateless
public class LocGrlExcelPathFacade extends AbstractFacade<LocGrlExcelPath>
{

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public LocGrlExcelPathFacade()
    {
        super(LocGrlExcelPath.class);
    }
    
    public LocGrlExcelPath findGrlExcelPath()
    {
        List<LocGrlExcelPath> list = this.findAll();
        return list.isEmpty() ? null : list.get(0);
    }
    
    public void initPath()
    {
        LocGrlExcelPath reg = findGrlExcelPath();
        
        if (reg == null)
        {
            reg = new LocGrlExcelPath();
            reg.setPath(Defs.FILES_EXCEL_LOCALIZACAO);
            this.create(reg);
        }
        else
        {
            reg.setPath(Defs.FILES_EXCEL_LOCALIZACAO);
            this.edit(reg);
        }
    }
    
    public String getCurrentGrlExcelPath()
    {
        LocGrlExcelPath reg = findGrlExcelPath();
        
        return reg.getPath();
    }
    
    public void saveGrlExcelPath(String path)
    {
        LocGrlExcelPath reg = findGrlExcelPath();

        if (reg == null)
        {
            reg = new LocGrlExcelPath();
            reg.setPath(path);
            this.create(reg);
        }
        else
        {
            reg.setPath(path);
            this.edit(reg);
        }
    }
    
    
}
