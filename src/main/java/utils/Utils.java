/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author aires
 */
public class Utils
{

    public static double converterDoubleDoisDecimais(double precoDouble)
    {
        DecimalFormat fmt = new DecimalFormat("0.00");
        String string = fmt.format(precoDouble);
        return Double.parseDouble(string);
    }

    public static double calcularPercentagemDoValor(double valorPercentagem, double valor)
    {

        double resultado = valor * (valorPercentagem / 100);
        return resultado; // para conter apenas duas casas decimais
    }

    public static String formatoValor(double valor)
    {
        if (valor % 10 == 0 || valor % 100 == 0)
        {
            return "" + (int) valor;
        }
        return getParteInteira(valor) + "." + getParteDecimal(valor);
    }

    public static int getParteInteira(double valor)
    {
        return (int) valor;
    }

    public static int getParteDecimal(double valor)
    {
        return (int) Math.ceil(valor * 100) % 100;
    }

    public static String getValorFormatoMonetarioPorRegiao(Locale regiao, double valor)
    {
        NumberFormat dinheiro = NumberFormat.getCurrencyInstance(regiao);
        return dinheiro.format(valor);
    }

    public static String removerNovasLinhas(String texto)
    {
        String line_separator = System.getProperty("line.separator");
        return texto.replaceAll("\n|" + line_separator, " ");
    }

    public static String limitaString(String texto, int maximo)
    {
        int diferenca = maximo - texto.length();
        if (texto.length() <= maximo)
        {
            String espaco = "";
            for (int i = 0; i < diferenca; i++)
            {
                espaco += " ";
            }

            return espaco + texto;
        }
        else
        {
            return texto.substring((-1) * diferenca, texto.length());
        }
    }

    // M??todo que pega o valor por extens??o
    // fonte: https://www.devmedia.com.br/valor-por-extenso-em-uma-aplicacao-java/21897
    public static String valorPorExtenso(double vlr)
    {
        if (vlr == 0)
        {
            return ("zero");
        }

        long inteiro = (long) Math.abs(vlr); // parte inteira do valor
        double resto = vlr - inteiro;       // parte fracion??ria do valor

        String vlrS = String.valueOf(inteiro);
        if (vlrS.length() > 15)
        {
            return ("Erro: valor superior a 999 trilh??es.");
        }

        String s = "", saux, vlrP;
        String centimos = String.valueOf((int) Math.round(resto * 100));

        String[] unidade =
        {
            "", "um", "dois", "tr??s", "quatro", "cinco",
            "seis", "sete", "oito", "nove", "dez", "onze",
            "doze", "treze", "quatorze", "quinze", "dezesseis",
            "dezessete", "dezoito", "dezenove"
        };
        String[] centena =
        {
            "", "cento", "duzentos", "trezentos",
            "quatrocentos", "quinhentos", "seiscentos",
            "setecentos", "oitocentos", "novecentos"
        };
        String[] dezena =
        {
            "", "", "vinte", "trinta", "quarenta", "cinquenta",
            "sessenta", "setenta", "oitenta", "noventa"
        };
        String[] qualificaS =
        {
            "", "mil", "milh??o", "bilh??o", "trilh??o"
        };
        String[] qualificaP =
        {
            "", "mil", "milh??es", "bilh??es", "trilh??es"
        };

// definindo o extenso da parte inteira do valor
        int n, unid, dez, cent, tam, i = 0;
        boolean umReal = false, tem = false;
        while (!vlrS.equals("0"))
        {
            tam = vlrS.length();
// retira do valor a 1a. parte, 2a. parte, por exemplo, para 123456789:
// 1a. parte = 789 (centena)
// 2a. parte = 456 (mil)
// 3a. parte = 123 (milh??es)
            if (tam > 3)
            {
                vlrP = vlrS.substring(tam - 3, tam);
                vlrS = vlrS.substring(0, tam - 3);
            }
            else
            { // ??ltima parte do valor
                vlrP = vlrS;
                vlrS = "0";
            }
            if (!vlrP.equals("000"))
            {
                saux = "";
                if (vlrP.equals("100"))
                {
                    saux = "cem";
                }
                else
                {
                    n = Integer.parseInt(vlrP, 10);  // para n = 371, tem-se:
                    cent = n / 100;                  // cent = 3 (centena trezentos)
                    dez = (n % 100) / 10;            // dez  = 7 (dezena setenta)
                    unid = (n % 100) % 10;           // unid = 1 (unidade um)
                    if (cent != 0)
                    {
                        saux = centena[cent];
                    }
                    if ((n % 100) <= 19)
                    {
                        if (saux.length() != 0)
                        {
                            saux = saux + " e " + unidade[n % 100];
                        }
                        else
                        {
                            saux = unidade[n % 100];
                        }
                    }
                    else
                    {
                        if (saux.length() != 0)
                        {
                            saux = saux + " e " + dezena[dez];
                        }
                        else
                        {
                            saux = dezena[dez];
                        }
                        if (unid != 0)
                        {
                            if (saux.length() != 0)
                            {
                                saux = saux + " e " + unidade[unid];
                            }
                            else
                            {
                                saux = unidade[unid];
                            }
                        }
                    }
                }
                if (vlrP.equals("1") || vlrP.equals("001"))
                {
                    if (i == 0) // 1a. parte do valor (um real)
                    {
                        umReal = true;
                    }
                    else
                    {
                        saux = saux + " " + qualificaS[i];
                    }
                }
                else if (i != 0)
                {
                    saux = saux + " " + qualificaP[i];
                }
                if (s.length() != 0)
                {
                    s = saux + ", " + s;
                }
                else
                {
                    s = saux;
                }
            }
            if (((i == 0) || (i == 1)) && s.length() != 0)
            {
                tem = true; // tem centena ou mil no valor
            }
            i = i + 1; // pr??ximo qualificador: 1- mil, 2- milh??o, 3- bilh??o, ...
        }

        if (s.length() != 0)
        {
            if (umReal)
            {
                s = s + " d??lar americano";
            }
            else if (tem)
            {
                s = s + " d??lares americanos";
            }
            else
            {
                s = s + " de d??lares americanos";
            }
        }

// definindo o extenso dos c??ntimos do valor
        if (!centimos.equals("0"))
        { // valor com c??ntimos
            if (s.length() != 0) // se n??o ?? valor somente com c??ntimos
            {
                s = s + " e ";
            }
            if (centimos.equals("1"))
            {
                s = s + "um c??ntimo";
            }
            else
            {
                n = Integer.parseInt(centimos, 10);
                if (n <= 19)
                {
                    s = s + unidade[n];
                }
                else
                {             // para n = 37, tem-se:
                    unid = n % 10;   // unid = 37 % 10 = 7 (unidade sete)
                    dez = n / 10;    // dez  = 37 / 10 = 3 (dezena trinta)
                    s = s + dezena[dez];
                    if (unid != 0)
                    {
                        s = s + " e " + unidade[unid];
                    }
                }
                s = s + " c??ntimos";
            }
        }
        return (s);
    }

    public static boolean pathExists(String path)
    {
        File file = new File(path);
        return (file.exists() && file.isDirectory());
    }

    public static String byPaddingZeros(int value, int paddingLength)
    {
        return String.format("%0" + paddingLength + "d", value);
    }
}
