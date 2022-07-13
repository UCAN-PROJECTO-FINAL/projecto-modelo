/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbt.beans;

import ejbs.entities.BbtCampo;
import ejbs.facades.BbtCampoFacade;
import utils.Defs;
import utils.FileManager;
import utils.HSSFCellUtilities;
import java.io.IOException;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author hermann
 */
@Named(value = "bbtCamposCarregarBean")
@RequestScoped
public class BbtCamposCarregarBean {

    @EJB
    private BbtCampoFacade bbtCampoFacade;

    private HSSFWorkbook workbook = null;
    HSSFSheet sheet = null;
    HSSFRow row = null;
    private String pkBbtCampo, fkBbtCampo, designacao;

    /**
     * Creates a new instance of BbtCamposCarregarBean
     */
    public BbtCamposCarregarBean() {
    }

    public void carregarCampos() throws IOException {
        workbook = FileManager.openXLS_File(Defs.CAMINHO_CAMPOS);
        System.err.println("0: BbtCamposCarregarBean.carregarCampos() yah");

        carregarFolhasCampos("campos");
    }

    private void carregarFolhasCampos(String folha) {

        sheet = workbook.getSheet(folha);

        Iterator rowIterator = sheet.rowIterator();
        rowIterator.next(); //Pula o título
        rowIterator.next(); //Pula o cabeçalho

        BbtCampo reg;

        while (rowIterator.hasNext()) {
            row = (HSSFRow) rowIterator.next();
            reg = processarRow(row);

            if (reg != null) {
                System.err.println("PkCampo:" + reg.getPkBbtCampo());
                System.err.println("Designacao:" + reg.getDesignacao());
                
                if(!bbtCampoFacade.isSubCampo(reg))
                {
                    bbtCampoFacade.create(reg);
                    System.err.println("CRIADO COM SUCESSO");
                }
                
                else
                {
                    reg.setFkBbtCampo(bbtCampoFacade.getBbtCampoPai(reg));
                    bbtCampoFacade.create(reg);
                     System.err.println("CRIADO COM SUCESSO SUBCAMPO");
                }
            }

        }
    }

    private BbtCampo processarRow(HSSFRow row) {
        HSSFCell cells[];
        cells = new HSSFCell[2];

        for (int i = 0; i < 2; i++) {

            cells[i] = row.getCell((short) i);
            if (HSSFCellUtilities.isCellEmpty(cells[i])) {
                return null;
            }
        }

        //  System.err.println("Codigo:" + HSSFCellUtilities.getStringCellValue(cells[0]));
        //System.err.println("Designacao:" + HSSFCellUtilities.getStringCellValue(cells[1]));
        BbtCampo reg = new BbtCampo(HSSFCellUtilities.getStringCellValue(cells[0]));
        
             //   reg.setFkBbtCampo(HSSFCellUtilities.getStringCellValue(cells[1]));

                
        return reg; 
    }

}
