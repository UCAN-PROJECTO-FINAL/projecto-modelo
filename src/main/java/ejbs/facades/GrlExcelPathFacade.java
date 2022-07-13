/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GrlExcelPath;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utils.Defs;

/**
 *
 * @author smakambo
 */
@Stateless
public class GrlExcelPathFacade extends AbstractFacade<GrlExcelPath> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrlExcelPathFacade() {
        super(GrlExcelPath.class);
    }
    
      public GrlExcelPath findGrlExcelPath()
    {
        List<GrlExcelPath> list = this.findAll();
        return list.isEmpty() ? null : list.get(0);
    }
    
    public void initPath()
    {
        GrlExcelPath reg = findGrlExcelPath();
        
        if (reg == null)
        {
            reg = new GrlExcelPath();
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
        GrlExcelPath reg = findGrlExcelPath();
        
        return reg.getPath();
    }
    
    public void saveGrlExcelPath(String path)
    {
        GrlExcelPath reg = findGrlExcelPath();

        if (reg == null)
        {
            reg = new GrlExcelPath();
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
