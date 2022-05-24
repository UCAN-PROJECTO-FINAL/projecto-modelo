/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

/**
 *
 * @author adilson
 * @author aires
 */
public class HSSFCellUtilities
{

    /**
     *
     * @param cell
     * @return
     */
    public static boolean isCellEmpty(final HSSFCell cell)
    {
        // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) retorna celula null
        if (cell == null)
        {
//////System.err.println("0: HSSFCellUtilities.isCellEmpty()");		
            return true;
        }
//////System.err.println("1: HSSFCellUtilities.isCellEmpty()");
        if (cell.getCellType() == CellType.BLANK)
        {
//////System.err.println("2: HSSFCellUtilities.isCellEmpty()");			
            return true;
        }

        boolean rt = false;
        if (cell.getCellType() == CellType.STRING)
        {
            String data = cell.getStringCellValue();
            if (data == null || data.trim().isEmpty())
            {
                return true;
            }
        }
//////System.err.println("3: HSSFCellUtilities.isCellEmpty()\trt: " + rt);		
        return rt;
    }

    public static String getStringCellValue(DataFormatter objDefaultFormat,
        FormulaEvaluator objFormulaEvaluator, final HSSFCell cell)
    {
        //System.err.println("0: HSSFCellUtilities.getStringCellValue()");
        if (cell == null)
        {
            //System.err.println("1: HSSFCellUtilities.getStringCellValue()");
            return null;
        }
        objFormulaEvaluator.evaluate(cell); // This will evaluate the cell, And any type of cell will return string val-ue
        return objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);

    }

    private static Integer getIntegerValue(DataFormatter objDefaultFormat,
        FormulaEvaluator objFormulaEvaluator, final HSSFCell cell)
    {
        Integer tmpPk;

        //LogFile.warnMsg(null, "0: HSSFCellUtilities.getIntegerValue()");
        String str = getStringCellValue(objDefaultFormat, objFormulaEvaluator, cell);
        if (str == null || str.length() == 0 || str.trim().isEmpty())
        {
//            LogFile.warnMsg(null, "1: HSSFCellUtilities.getIntegerValue()");
            return null;
        }
        try
        {
//            LogFile.warnMsg(null, "3: HSSFCellUtilities.getIntegerValue()\tstr: " + str);
            tmpPk = Integer.parseInt(str);
            return tmpPk;
        }
        catch (NumberFormatException ex)
        {
            //LogFile.warnMsg(null, "4: HSSFCellUtilities.getIntegerValue()");
            return null;
        }
    }

    public static Double getDoubleCellValue(DataFormatter objDefaultFormat,
        FormulaEvaluator objFormulaEvaluator, final HSSFCell cell)
    {
        Double tmpPk;

//        System.err.println("0: HSSFCellUtilities.getDoubleCellValue()");
        String str = getStringCellValue(objDefaultFormat, objFormulaEvaluator, cell);
        
//        System.err.println("1: HSSFCellUtilities.getDoubleCellValue()\tstr: " + (str == null ? "" : str));
        if (str == null || str.length() == 0 || str.trim().isEmpty())
        {
//            System.err.println("2: HSSFCellUtilities.getDoubleCellValue()");
            return null;
        }
        try
        {
            str = StringUtils.colocarPontoDecimal(str);
//            System.err.println("3: HSSFCellUtilities.getDoubleCellValue()\tstr: " + (str == null ? "" : str));
            tmpPk = Double.parseDouble(str);
//            System.err.println("3.1: HSSFCellUtilities.getDoubleCellValue()\ttmpPk: " 
//                    + (tmpPk == null ? "null" : tmpPk));
            return tmpPk;
        }
        catch (NumberFormatException ex)
        {
//            System.err.println("4: HSSFCellUtilities.getDoubleCellValue()");
            return null;
        }
    }

    public static Integer getIntCellValue(DataFormatter objDefaultFormat,
        FormulaEvaluator objFormulaEvaluator, final HSSFCell cell)
    {
        return getIntegerValue(objDefaultFormat, objFormulaEvaluator, cell);
    }

    public static String cellTypeToString(final HSSFCell cell)
    {
        if (cell == null)
        {
            return null;
        }

        switch (cell.getCellType())
        {
            case _NONE:
                return "NONE";
            case NUMERIC:
                return "NUMERIC";
            case STRING:
                return "STRING";
            case FORMULA:
                return "FORMULA";
            case BLANK:
                return "BLANK";
            case BOOLEAN:
                return "BOOLEAN";
            case ERROR:
                return "ERROR";
            default:
                return "STRANGE_TYPE";
        }
    }

}
