/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;

/**
 *
 * @author dello
 */
public class PathManager
{

	public static File saltarPara(int nivel, File directorioAtual)
	{
		if (nivel == 0)
		{
			return directorioAtual;
		}

		directorioAtual = new File(directorioAtual.getParent());

		return saltarPara(nivel - 1, directorioAtual);
	}

	public static String getUrlFile(String caminhoRelativo)
	{
		String urlCompleto = PathManager.class.getResource(".." + caminhoRelativo).getFile().replaceAll("%20", " ");
		File file = new File(urlCompleto);
		System.out.println("Path " + file.getParentFile());
		if (file.exists())
		{
			return file.toString().trim();
		}

		return "";
	}
}
