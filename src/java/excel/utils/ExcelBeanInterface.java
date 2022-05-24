
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel.utils;

import utils.ExcepcaoCarregamentoTabelasExcel;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author aires
 */
public interface ExcelBeanInterface
{

	public void carregar();

	public boolean carregar(UploadedFile fileCarregarCandidaturas);

	public boolean carregarTabelas() throws ExcepcaoCarregamentoTabelasExcel;

	public boolean lerTabela() throws ExcepcaoCarregamentoTabelasExcel;
}
