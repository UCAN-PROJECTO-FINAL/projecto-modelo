/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.EstruturaGrlExcelPath;
import ejbs.facades.AbstractFacade;
import utils.Defs;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mdnex
 */
@Stateless
public class EstruturaGrlExcelPathFacade extends AbstractFacade<EstruturaGrlExcelPath>
{

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public EstruturaGrlExcelPathFacade()
    {
        super(EstruturaGrlExcelPath.class);
    }
    
    public EstruturaGrlExcelPath findGrlExcelPath()
    {
        List<EstruturaGrlExcelPath> list = this.findAll();
        return list.isEmpty() ? null : list.get(0);
    }
    
    public void initPath()
    {
        EstruturaGrlExcelPath reg = findGrlExcelPath();
        
        if (reg == null)
        {
            reg = new EstruturaGrlExcelPath();
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
        EstruturaGrlExcelPath reg = findGrlExcelPath();
        
        return reg.getPath();
    }
    
    public void saveGrlExcelPath(String path)
    {
        EstruturaGrlExcelPath reg = findGrlExcelPath();

        if (reg == null)
        {
            reg = new EstruturaGrlExcelPath();
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
