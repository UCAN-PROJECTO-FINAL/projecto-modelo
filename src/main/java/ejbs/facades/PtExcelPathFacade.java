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
import ejbs.entities.PtExcelPath;
import ejbs.facades.AbstractFacade;
import patri.utils.Defs;

/**
 *
 * @author mdnext
 */
@Stateless
public class PtExcelPathFacade extends AbstractFacade<PtExcelPath> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtExcelPathFacade() {
        super(PtExcelPath.class);
    }
    
    public PtExcelPath findGrlExcelPath()
    {
        List<PtExcelPath> list = this.findAll();
        return list.isEmpty() ? null : list.get(0);
    }
    
    public void initPath()
    {
        PtExcelPath reg = findGrlExcelPath();
        
        if (reg == null)
        {
            reg = new PtExcelPath();
            reg.setPath(utils.Defs.FILES_EXCEL_LOCALIZACAO);
            this.create(reg);
        }
        else
        {
            reg.setPath(utils.Defs.FILES_EXCEL_LOCALIZACAO);
            this.edit(reg);
        }
    }
    
    public String getCurrentGrlExcelPath()
    {
        PtExcelPath reg = findGrlExcelPath();
        
        return reg.getPath();
    }
    
    public void saveGrlExcelPath(String path)
    {
        PtExcelPath reg = findGrlExcelPath();

        if (reg == null)
        {
            reg = new PtExcelPath();
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
