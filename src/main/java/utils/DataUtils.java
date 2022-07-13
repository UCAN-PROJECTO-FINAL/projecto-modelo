/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.faces.model.SelectItem;
import javax.xml.datatype.XMLGregorianCalendar;
import utils.mensagens.LogFile;

/**
 *
 * @author KiamiSoft_ACosta
 */
public class DataUtils
{

    /**
     *
     * @param data1
     * @param data2
     * @return
     */
    public static final String meses[] =
    {
        "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
    };

    public static final int maxDiaDoMex[] =
    {
        31, 28, 31, 30, 31, 30,
        31, 31, 30, 31, 30, 31,
    };

    public static int getMesIndice(String mes)
    {
        for (int i = 0; i < 12; i++)
        {
            if (meses[i].equalsIgnoreCase(mes))
            {
                return i;
            }
        }
        return -1;
    }

    public static int maxDiaDoMes(int mesIndice, int ano)
    {
        boolean anoBisexto = (ano % 4 == 0);
        return (mesIndice == 1 && anoBisexto) ? DataUtils.maxDiaDoMex[mesIndice] + 1 : DataUtils.maxDiaDoMex[mesIndice];
    }

    public static Date lerData(String dateStr)
	{
        String msg;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt", "PT"));
		Date date;
		try
		{
			date = sdf.parse(dateStr);
		}
		catch (ParseException ex)
		{
			msg = "A data  '" + dateStr + "' tem formato impróprio. Fale com o administrador do sistema";
			LogFile.warnMsg(null, msg);

			return null;
		}
		return date;
	}
    
    public static boolean isMoreRecent(Date data1, Date data2)
    {
//System.out.println("0: DataUtils.isMoreRecent()\tdata1: " + toStringFull(data1));
//System.out.println("1: DataUtils.isMoreRecent()\tdata2: " + toStringFull(data2));
        if (data1 == null)
        {
            return false;
        }
        if (data2 == null)
        {
            return true;
        }
        return (data1.compareTo(data2) <= 0) ? false : true;
    }

    public static Date endDay(Date data)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date createDate(int dias, int meses, int anos)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.YEAR, anos);
        cal.set(Calendar.MONTH, meses);
        cal.set(Calendar.DAY_OF_MONTH, dias);

        return cal.getTime();
    }

    public static Date create(int dias, String mes, int ano)
    {
        int indiceMes = getMesIndice(mes);
        return createDate(dias, indiceMes, ano);
    }

    // hh:mm:ss = 00:00:00
    public static Date resetTime(Date data)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date setMillissegundos(Date data, int qtd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.MILLISECOND, qtd);
        return cal.getTime();
    }

    public static Date setSegundos(Date data, int qtd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.SECOND, qtd);
        return cal.getTime();
    }

    public static Date setMinutos(Date data, int qtd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.MINUTE, qtd);
        return cal.getTime();
    }

    public static Date setHoras(Date data, int qtd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, qtd);
        return cal.getTime();
    }

    /**
     * Adiciona quantidade de anos na data.
     *
     * @param data1
     * @param data2
     * @return
     */
    public static Date addAnos(Date data, int qtd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.YEAR, qtd);
        return cal.getTime();
    }

    /**
     * Adiciona quantidade de meses na data.
     *
     * @param data
     * @param qtd
     * @return
     */
    public static Date addMeses(Date data, int qtd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.MONTH, qtd);
        return cal.getTime();
    }

    public static Date addDias(Date data, int qtd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.DAY_OF_MONTH, qtd);
        return cal.getTime();
    }

    public static Date addMinutos(Date data, int qtd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.MINUTE, qtd);
        return cal.getTime();
    }

    public static Date addSemanas(Date data, int qtd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.WEEK_OF_YEAR, qtd);
        return cal.getTime();
    }

    public static long diff(Date data1, Date data2)
    {
        Calendar data1Calendar = Calendar.getInstance();
        data1Calendar.setTime(data1);

        Calendar data2Calendar = Calendar.getInstance();
        data2Calendar.setTime(data2);
        return data1Calendar.getTimeInMillis() - data2Calendar.getTimeInMillis();
    }

    public static int compare(Date data1, Date data2)
    {
        long cp = diff(data1, data2);
        return cp > 0 ? 1 : (cp == 0 ? 0 : -1);
    }

    public static Date addDate(Date data, int dias, int meses, int anos)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);

        cal.add(Calendar.YEAR, anos);
        cal.add(Calendar.MONTH, meses);
        cal.add(Calendar.DAY_OF_MONTH, dias);

        return cal.getTime();
    }

    public static String toStringNow()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", new Locale("pt", "PT")).format(timestamp.getTime());
    }

    public static String toStringFull(Date d)
    {
        if (d == null)
        {
            return "null";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMMM, yyyy HH:mm:ss", new Locale("pt", "PT"));
        return sdf.format(d);
    }

    public static String toStringFullFrom(Date d)
    {
        return toStringFull(diff(new Date(), d));
    }

    public static String toStringFull(long milissegundos)
    {
        int anos = 0, meses = 0, diasMes = 0, horas = 0, minutos = 0, segundos = 0;
        long segTotal = milissegundos / 1000;
        long milissegundosRemanescentes = milissegundos % 1000;

        long minutosTotal = segTotal / 60;
        long horasTotal = minutosTotal / 60;
        long diasTotal = horasTotal / 24;
        long mesesTotal = diasTotal / 30;
        anos = (int) mesesTotal / 12;
        meses = (int) mesesTotal % 12;
        diasMes = (int) diasTotal % 30;
        horas = (int) horasTotal % 24;
        minutos = (int) minutosTotal % 60;
        segundos = (int) segTotal % 60;

        String msg = "";
        if (anos != 0)
        {
            msg += anos + (anos == 1 ? " ano" : " anos");
        }
        if (meses != 0)
        {
            if (!msg.equals(""))
            {
                msg += ", ";
            }
            msg += meses + (meses == 1 ? " mes" : " meses");
        }
        if (diasMes != 0)
        {
            if (!msg.equals(""))
            {
                msg += ", ";
            }
            msg += diasMes + (diasMes == 1 ? " dia" : " dias");
        }
        if (horas != 0)
        {
            if (!msg.equals(""))
            {
                msg += ", ";
            }
            msg += horas + (horas == 1 ? " hora" : " horas");
        }
        if (minutos != 0)
        {
            if (!msg.equals(""))
            {
                msg += ", ";
            }
            msg += minutos + (minutos == 1 ? " minuto" : " minutos");
        }
        if (segundos != 0)
        {
            if (!msg.equals(""))
            {
                msg += ", ";
            }
            msg += segundos + (segundos == 1 ? " segundo" : " segundos");
        }
        if (milissegundosRemanescentes != 0)
        {
            if (!msg.equals(""))
            {
                msg += ", ";
            }
            msg += milissegundosRemanescentes + (milissegundosRemanescentes == 1 ? " milissegundo" : " milissegundos");
        }
        return msg;
    }

    public static String toString(Date d)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy", new Locale("pt", "PT"));
        return sdf.format(d);
    }

    public static String toStringExtended(Date d)
    {
//		SimpleDateFormat sdf = new SimpleDateFormat("dd de MMMMM de yyyy", new Locale("pt", "PT"));

        Calendar cal = Calendar.getInstance();
        cal.setTime(d);

        int dia, mes, ano;
        ano = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);
        return "" + dia + " de " + meses[mes] + " de " + ano;
    }

    public static String toStringExtendedFull(Date d)
    {
//		SimpleDateFormat sdf = new SimpleDateFormat("dd de MMMMM de yyyy às HH:mm:ss", new Locale("pt", "PT"));
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);

        int segundos, minutos, horas, dia, mes, ano;
        ano = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);
        horas = cal.get(Calendar.HOUR_OF_DAY);
        minutos = cal.get(Calendar.MINUTE);
        segundos = cal.get(Calendar.SECOND);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", new Locale("pt", "PT"));
        return "" + dia + " de " + meses[mes] + " de " + ano + " às " + sdf.format(d);
    }

    public static String dataTimeAgoraFull()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy HH:mm:ss", new Locale("pt", "PT"));
        return sdf.format(new Date());
    }

    public static String dataTimeAgora()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy", new Locale("pt", "PT"));
        return sdf.format(new Date());
    }

    public static String dataAgora()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", new Locale("pt", "PT"));
        return sdf.format(new Date());
    }

    public static String timeAgora()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", new Locale("pt", "PT"));
        return sdf.format(new Date());
    }

    public static String dateToString(Date data)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy HH:mm:ss", new Locale("pt", "PT"));
        return sdf.format(data);
    }

    public static String dateToDate(Date data)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy", new Locale("pt", "PT"));
        return sdf.format(data);
    }

    public static Date stringToDate(String string)
    {
        Date data;
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy HH:mm:ss", new Locale("pt", "PT"));

        try
        {
            data = sdf.parse(string);
        }
        catch (ParseException exception)
        {
            System.out.println("Data invalida");
            return null;
        }

        return data;
    }
    
    public static Date stringToDate(String string, String dataFormat)
    {
        Date data;
        SimpleDateFormat sdf = new SimpleDateFormat(dataFormat, new Locale("pt", "PT"));

        try
        {
            data = sdf.parse(string);
        }
        catch (ParseException exception)
        {
            System.out.println("Data invalida");
            return null;
        }

        return data;
    }

    public static Date converterStringToDate(String string)
    {
        Date data;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try
        {
            data = sdf.parse(string);
        }
        catch (ParseException exception)
        {
            System.out.println("Data invalida");
            return null;
        }

        return data;
    }

    public static String converterDateToString(Date date)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public static int getYear(Date d)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(d);

        return c.get(Calendar.YEAR);
    }

    public static boolean isToday(Date d)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(d);

        int dia = c.get(Calendar.DATE);
        int mes = c.get(Calendar.MONTH) + 1;
        int ano = c.get(Calendar.YEAR);

        c.setTime(new Date());
        int diaHoje = c.get(Calendar.DATE);
        if (dia != diaHoje)
        {
            return false;
        }

        int mesHoje = c.get(Calendar.MONTH) + 1;
        if (mes != mesHoje)
        {
            return false;
        }

        int anoHoje = c.get(Calendar.YEAR);
        return ano == anoHoje;
    }

    public static String timeToString(Date data)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", new Locale("pt", "PT"));
        return sdf.format(data);
    }

    public static Date dataModificacaoFicheiro(String filename)
    {
        File file = new File(filename);
        return new Date(file.lastModified());
    }

    public static Date getMomentoActual()
    {
        return new Date();
    }

    public static String getTimeStampNow()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("YYYY_MM_dd_HH_mm_ss", new Locale("pt", "PT")).format(timestamp.getTime());
    }

    public static String getDataAgora()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd", new Locale("pt", "PT"));
        return sdf.format(new Date());
    }

    public static ArrayList<SelectItem> selectMesesListar()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        itens.add(new SelectItem(1, "Janeiro"));
        itens.add(new SelectItem(2, "Fevereiro"));
        itens.add(new SelectItem(3, "Março"));
        itens.add(new SelectItem(4, "Abril"));
        itens.add(new SelectItem(5, "Maio"));
        itens.add(new SelectItem(6, "Junho"));
        itens.add(new SelectItem(7, "Julho"));
        itens.add(new SelectItem(8, "Agosto"));
        itens.add(new SelectItem(9, "Setembro"));
        itens.add(new SelectItem(10, "Outubro"));
        itens.add(new SelectItem(11, "Novembro"));
        itens.add(new SelectItem(12, "Dezembro"));

        return itens;
    }

    public static ArrayList<SelectItem> carregarMes()
    {
        ArrayList<SelectItem> item = new ArrayList<>();
        Integer mes = new Date().getMonth();
        for (int i = mes; i < selectMesesListar().size(); i++)
        {
            item.add(selectMesesListar().get(i));
        }
        return item;
    }

    /**
     *
     */
    public static List<Date> processarDiasDaMesmaSemana(Date dt)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.HOUR_OF_DAY, 1); // Devido ao horário de verão...
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        List<Date> ret = new ArrayList<Date>();
        // Devemos coletar os dias da mesma semana. Para tanto, vamos pegar o dia da semana agora:
        int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
        // Digamos que hoje seja quarta-feira (WEDNESDAY = 4). O primeiro dia da semana é domingo (SUNDAY = 1),
        // e devemos contar desde o primeiro dia da semana (hoje - 4 + 1). 
        Calendar calFirst = (Calendar) cal.clone();
        calFirst.add(Calendar.DATE, -diaSemana + Calendar.SUNDAY);
        for (int i = 0; i < 7; ++i)
        {

            ret.add(calFirst.getTime());
            calFirst.add(Calendar.DATE, 1);
        }

        return ret;
    }

    public static List<Integer> diasDaMesmaSemana(Date dt)
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        List<Integer> diasDaSemana = new ArrayList();

        DateFormat df2 = new SimpleDateFormat("dd");
        List<Date> dias = processarDiasDaMesmaSemana(dt);
        for (Date dia : dias)
        {
            diasDaSemana.add(Integer.parseInt(df2.format(dia)));
        }
        return diasDaSemana;
    }

    public static int calcularIdade(Date dataNascimento)
    {
        Calendar dataNascimentoCalendar = new GregorianCalendar();

        dataNascimentoCalendar.setTime(dataNascimento);

        Calendar hoje = Calendar.getInstance();

        return hoje.get(Calendar.YEAR) - dataNascimentoCalendar.get(Calendar.YEAR);
    }

    public static Date getInstanciaDate(XMLGregorianCalendar gc)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, gc.getYear());
        cal.set(Calendar.MONTH, gc.getMonth());
        cal.set(Calendar.DAY_OF_MONTH, gc.getDay());
        cal.set(Calendar.HOUR_OF_DAY, gc.getHour());
        cal.set(Calendar.MINUTE, gc.getMinute());
        cal.set(Calendar.SECOND, gc.getSecond());

        return cal.getTime();
    }
}
