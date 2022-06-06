/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbt.beans;

import ejbs.entities.BbtCdu;
import ejbs.facades.BbtCduFacade;

import java.io.IOException;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.Defs;
import utils.FileManager;
import utils.HSSFCellUtilities;

/**
 *
 * @author hermann
 */
@Named(value = "bbtCDUCarregarBean")
@RequestScoped
public class BbtCDUCarregarBean
{

    @EJB
    private BbtCduFacade bbtCduFacade;

    /**
     * Creates a new instance of BbtCDUCarregarBean
     */
    private HSSFWorkbook workbook = null;
    HSSFSheet sheet = null;
    HSSFRow row = null;
    private String pkBbtCdu, fkBbtCdu, designacao;

    public BbtCDUCarregarBean()
    {
    }

    public void carregarCDU() throws IOException
    {
        workbook = FileManager.openXLS_File(Defs.CAMINHO_CAMPOS);
        System.err.println("0: BbtCduCarregarBean.carregarCDU() yah");

        carregarFolhasCampos("CDU");
    }

    private void carregarFolhasCampos(String folha)
    {

        sheet = workbook.getSheet(folha);

        Iterator rowIterator = sheet.rowIterator();

        rowIterator.next(); //Pula a versao
        rowIterator.next(); //Pula o título
        rowIterator.next(); //Pula o cabeçalho

        BbtCdu reg;

        while (rowIterator.hasNext())
        {
            System.err.println("0: BbtCduCarregarBean.carregarFolhasCampos()");
            row = (HSSFRow) rowIterator.next();
            System.err.println("00: BbtCduCarregarBean.carregarFolhasCampos()");
            reg = processarRow(row);
            System.err.println("01: BbtCduCarregarBean.carregarFolhasCampos()");

            if (reg != null)
            {
                System.err.println("02: BbtCduCarregarBean.carregarFolhasCampos()\tPkCampo:" + reg.getPkBbtCdu());
                System.err.println("1: BbtCduCarregarBean.carregarFolhasCampos()\tDesignacao:" + reg.getDesignacao());
                System.err.println("2: BbtCduCarregarBean.carregarFolhasCampos()\tFkCampo:"
                    + (reg.getFkBbtCdu() == null ? "null" : reg.getFkBbtCdu().getPkBbtCdu()));

                if (bbtCduFacade.existe(reg))
                {
                    bbtCduFacade.edit(reg);
                    System.err.println("ALTERADO COM SUCESSO");
                }
                else
                {
                    bbtCduFacade.create(reg);
                    System.err.println("INSERIDO COM SUCESSO");
                }
                System.err.println("3: BbtCduCarregarBean.carregarFolhasCampos()");
            }
            System.err.println("4: BbtCduCarregarBean.carregarFolhasCampos()");
        }
        System.err.println("4: BbtCduCarregarBean.carregarFolhasCampos()");
    }
    

    private BbtCdu processarRow(HSSFRow row)
    {
        HSSFCell cells[];
        cells = new HSSFCell[2];

        for (int i = 0; i < 2; i++)
        {

            cells[i] = row.getCell((short) i);
            if (HSSFCellUtilities.isCellEmpty(cells[i]))
            {
                return null;
            }
        }

        BbtCdu bbtCdu = new BbtCdu(HSSFCellUtilities.getStringCellValue(cells[0]),
            HSSFCellUtilities.getStringCellValue(cells[1]));

        bbtCdu.setFkBbtCdu(bbtCduFacade.getBbtCampoPai(bbtCdu));

        return bbtCdu;
    }

}
