/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author aires
 */
public class StringUtils
{

    public static boolean includes(List<String> list, String value)
    {
        value = value.trim();
        for (String item : list)
        {
            if (value.equalsIgnoreCase(item.trim()))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean equalsIgnoreCaseWithoutAcents(String str1, String str2)
    {
        String st1 = removerAcentos(str1);
        String st2 = removerAcentos(str2);
        return st1.equalsIgnoreCase(st2);
    }

    public static String removerAcentos(String str)
    {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public static boolean startWith(String texto, List<String> prefixos)
    {
//System.err.println("0: StringUtils.startWith()\ttexto: " +
//            texto);        
        for (String prefixo : prefixos)
        {
//System.err.println("1: StringUtils.startWith()\ttexto: " +
//            texto + "\tprefixo: " + prefixo);            
            if (texto.startsWith(prefixo))
            {
//System.err.println("2: StringUtils.startWith()\ttexto: " +
//            texto + "\tprefixo: " + prefixo);                
                return true;
            }
        }
//System.err.println("3: StringUtils.startWith()");        
        return false;
    }

    public static boolean contains(List<String> mensagens, String mensagem)
    {
        for (String msg : mensagens)
        {
            if (msg.equalsIgnoreCase(mensagem))
            {
                return true;
            }
        }
        return false;
    }

    public static List<String> toList(String... args)
    {
        List<String> mensagens = new ArrayList();
        int i = 0;
        for (String arg : args)
        {
            if (!isNull(arg))
            {
                System.err.println("0: StringUtils.toList()\targ[" + i + "]: " + arg);
                mensagens.add(arg);
            }
            i++;
        }
        return mensagens;
//        return Arrays.asList(args);
    }

    public static String[] toArray(String... args)
    {
        List<String> argsList = toList(args);

        String[] mensagens = new String[argsList.size()];
        int i = 0;
        for (String arg : args)
        {
            mensagens[i++] = arg;
        }
        return mensagens;
    }

    public static boolean ehDuplicado(String chave)
    {
        return chave.indexOf('$') != -1;
    }

    public static String getChaveOriginal(String chave)
    {
        int index = chave.indexOf('$');
        return index == -1 ? chave : chave.substring(0, index);
    }

    public static String geraMensagem(String... args)
    {
        String mensagem = "";
        boolean primeiro = true;
        for (String arg : args)
        {
            if (!isNull(arg))
            {
                if (!primeiro)
                {
                    mensagem += ", ";
                }
                mensagem += arg;
                primeiro = false;
            }
        }
        return mensagem;
    }

    public static boolean isCharacterSpace(String str)
    {
        int nl = str.length();
        for (int i = 0; i < nl; i++)
        {
            if (str.charAt(i) != ' ')
            {
                return false;
            }
        }
        return true;
    }

    public static boolean isNull(String str)
    {
        if (str == null)
        {
            return true;
        }
        str = str.trim();
        if (str.equals(""))
        {
            return true;
        }
        return str.isEmpty();
    }

    public static boolean isSingleWord(String str)
    {
        char separator[] =
        {
            ' ', ',', ';', ':', '{', '[', ']', '}', '(', ')', '/', '&', '%', '$',
            '#', '+', '-', '*', '\\', '!', '$', '%', '&', '|', '?', '=', '>', '<'
        };
        int len = str.length();
        str = str.trim();
        for (char sp : separator)
        {
            if (str.indexOf(sp) != -1)
            {
//                //System.err.println("0: StringUtils.isSingleWord()\tsp.indice: " + str.indexOf(sp) + "\tstr: " + str);
                return false;
            }
        }
        return true;
    }

    public static boolean includNonDigit(String target)
    {
        int nl = target.length();
        for (int i = 0; i < nl; i++)
        {
            if (!Character.isDigit(target.charAt(i)))
            {
                return true;
            }
        }
        return false;
    }

    public static String removeChars(String target, String chars)
    {
        chars = chars.trim();
        int nl = chars.length();
        target = target.trim();
        int indice;
        for (int i = 0; i < nl; i++)
        {

            indice = target.indexOf(chars.charAt(i));
            if (indice != -1)
            {
//				result = target.substring(0, indice) + target.substring(indice + 1, nl);
//				target = result;
                target = target.replace(chars.charAt(i) + "", "");
            }
        }
        return target;
    }

    public static StringBuilder removeNextSpaces(StringBuilder sb, int indice)
    {
        int len = sb.length();
        if (len < indice + 2)
        {
            return sb;
        }
        char char1;
        int first = indice + 1;
        for (; first < len; first++)
        {
            char1 = sb.charAt(first);
            if (char1 != ' ')
            {
                break;
            }
        }
        if (first != indice + 1)
        {
            sb = sb.replace(indice + 1, len, sb.substring(first));
        }
        return sb;
    }

    public static String reduceSpaces(String st)
    {
        if (st == null)
        {
//            //System.err.println("1: StringUtils.reduceSpaces()\tst: " + st);
            return null;
        }
        st = st.trim();
        if (st.isEmpty())
        {
//            //System.err.println("2: StringUtils.reduceSpaces()\tst: " + st);
            return null;
        }
        if (st.indexOf(' ') == -1)
        {
            return st;
        }
        String result = "";
        int nl = st.length();
        char ch;
        int spaces = 0;
        for (int i = 0; i < nl; i++)
        {
            ch = st.charAt(i);
            if (ch != ' ')
            {
                if (spaces != 0)
                {
                    result += ' ';
                    spaces = 0;
                }
                result += ch;
                continue;
            }
            else
            {
                spaces++;
            }
        }
        return result;
    }

    public static ArrayList<String> split(String frase, char... separators)
    {
        ArrayList<String> words = new ArrayList();
        String palavras[] = null;
        boolean exito = false;
        for (char ch : separators)
        {
            if (frase.indexOf(ch) != -1)
            {
                palavras = frase.split("" + ch);
                exito = true;
                break;
            }
        }
        if (exito)
        {
            for (String palavra : palavras)
            {
                words.add(palavra);
            }
        }
        return words;
    }

    public static String capitalizeEveryWord(String st)
    {
        //System.err.println("0: StringUtils.capitalizeEveryWord()\tst: " + st);
        if (StringUtils.isNull(st))
        {
            //System.err.println("1: StringUtils.capitalizeEveryWord()\tst: " + st);
            return "";
        }
        st = st.trim();
        if (st.isEmpty())
        {
            //System.err.println("2: StringUtils.capitalizeEveryWord()\tst: " + st);
            return "";
        }
        st = reduceSpaces(st);
        st = st.toLowerCase();

        //System.err.println("3: StringUtils.capitalizeEveryWord()\tst: " + st);
        String result = "", chUpper;

        StringBuilder word;

        if (st.indexOf(' ') == -1)
        {
            //System.err.println("4: StringUtils.capitalizeEveryWord()\tst: " + st);
            word = new StringBuilder(st);
            chUpper = new Character(word.charAt(0)).toString().toUpperCase();
            word.setCharAt(0, chUpper.charAt(0));
            //System.err.println("5: StringUtils.capitalizeEveryWord()\tst: " + st);
            return word.toString();
        }
        //System.err.println("6: StringUtils.capitalizeEveryWord()\tst: " + st);
        String words[] = st.split(" ");
        int nl = words.length;

        boolean first = true;
        //System.err.println("7: StringUtils.capitalizeEveryWord()\tst: " + st + "\tnl: " + nl);
        for (int i = 0; i < nl; i++)
        {
            if ((i != 0 && (i != (nl - 1)))
                && (words[i].equalsIgnoreCase("do") || words[i].equalsIgnoreCase("de")
                || words[i].equalsIgnoreCase("da") || words[i].equalsIgnoreCase("e")
                || words[i].equalsIgnoreCase("dos") || words[i].equalsIgnoreCase("das")))
            {
                //System.err.println("8: StringUtils.capitalizeEveryWord()\twords[: " + i + "]: " + words[i]);
                word = new StringBuilder(words[i].toLowerCase());
                //System.err.println("9: StringUtils.capitalizeEveryWord()\tword: " + word);
            }
            else
            {
                //System.err.println("10: StringUtils.capitalizeEveryWord()\twords[: " + i + "]: " + words[i]);
                word = new StringBuilder(words[i]);
                //System.err.println("11: StringUtils.capitalizeEveryWord()\tword: " + word);
                chUpper = new Character(word.charAt(0)).toString().toUpperCase();
                //System.err.println("12: StringUtils.capitalizeEveryWord()\tword: " + word);
                word.setCharAt(0, chUpper.charAt(0));
                //System.err.println("13: StringUtils.capitalizeEveryWord()\tword: " + word);
            }
            result += (first ? "" : " ") + word;
            //System.err.println("14: StringUtils.capitalizeEveryWord()\tresult: " + result);
            first = false;
        }
        //System.err.println("15: StringUtils.capitalizeEveryWord()\tresult: " + result);
        return result;
    }

    public static String geraNomeCompleto(String nome, String ultimoNome)
    {
        return StringUtils.capitalizeEveryWord(geraNomeCompletoPre(nome, ultimoNome));
    }

    private static String geraNomeCompletoPre(String nome, String ultimoNome)
    {
        nome = StringUtils.isNull(nome) ? "" : nome.trim();
        ultimoNome = StringUtils.isNull(ultimoNome) ? "" : ultimoNome.trim();
        if (nome.equals(""))
        {
            return ultimoNome;
        }
        else
        {
            if (ultimoNome.equals(""))
            {
                return nome;
            }
            return nome + " " + ultimoNome;
        }
    }

    public static int numeroDePartesDoNome(String nome)
    {
        String palavras[] = nome.split(" ");
        return palavras.length;
    }

    public static boolean hasNomeEApelido(String nome)
    {
        String palavras[] = nome.split(" ");
        return palavras.length > 1;
    }

    public static String getNome(String nome)
    {
        String palavras[] = nome.split(" ");
        return palavras[0];
    }

    public static String getApelido(String nome)
    {
        String palavras[] = nome.split(" ");
        return palavras[palavras.length - 1];
    }

    public static boolean nomesSemelhantes(String nome1, String nome2)
    {
//        //System.err.println("0: StringUtils.nomesSemelhantes()");
        if (isNull(nome1))
        {
            nome1 = null;
        }
        if (isNull(nome2))
        {
            nome2 = null;
        }
        if (nome1 == null && nome2 == null)
        {
            return true;
        }
        if (nome1 != null && nome2 == null)
        {
            return false;
        }
        if (nome1 == null && nome2 != null)
        {
            return false;
        }
////System.err.println("1: StringUtils.nomesSemelhantes()");        
        if (numeroDePartesDoNome(nome1) == 1 && numeroDePartesDoNome(nome2) == 1)
        {
////System.err.println("2: StringUtils.nomesSemelhantes()");            
            return nome1.equalsIgnoreCase(nome2);
        }
        if ((numeroDePartesDoNome(nome1) > 1) && (numeroDePartesDoNome(nome2) > 1) && palavrasComuns(nome1, nome2))
        {
            return true;
        }
////System.err.println("3: StringUtils.nomesSemelhantes()");
        return false;
    }

    public static boolean palavrasComuns(String str1, String str2)
    {
        String nome1 = str1.toLowerCase();
        String nome2 = str2.toLowerCase();

        String partes1[] = nome1.split(" ");
        String partes2[] = nome2.split(" ");

        List<Integer> indices = new ArrayList();

        int nl1 = partes1.length;

        String part;
        int indice;
////System.err.println("0: StringUtils.palavrasComuns()\tnl1: " + nl1);        
        for (int i = 0; i < nl1; i++)
        {
            indice = indexOf(partes1[i], partes2);
            if (indice != -1)
            {
                indices.add(indice);
            }
        }
        int sz = indices.size();
////System.err.println("1: StringUtils.palavrasComuns()\tsz: " + sz);        
        if (sz < 2)
        {
            return false;
        }
////System.err.println("2: StringUtils.palavrasComuns()");        
        for (int i = 0; i < sz - 1; i++)
        {
            if (indices.get(i).intValue() > indices.get(i + 1).intValue())
            {
                return false;
            }
        }
////System.err.println("3: StringUtils.palavrasComuns()");        
        return true;
    }

    public static int indexOf(String str1, String str2[])
    {
        int nl2 = str2.length;

        for (int i = 0; i < nl2; i++)
        {
            if (str1.equalsIgnoreCase(str2[i]))
            {
                return i;
            }
        }
        return -1;
    }

    public static String substring(String str, int pos)
    {
////System.err.println("0: StringUtils.substring(()");        
        StringBuffer stb = new StringBuffer();
        int len = str.length();
        for (int i = pos; i < len; i++)
        {
            stb.append(str.charAt(i));
////System.err.println("1: StringUtils.substring(()\tstb:" + stb);             
        }
////System.err.println("2: StringUtils.substring(()\tstb:" + stb);         
        stb.trimToSize();
////System.err.println("3: StringUtils.substring(()\tstb:" + stb);         
        str = stb.toString();
////System.err.println("4: StringUtils.substring(()\tstr:" + str);         
        return str;
    }

    public static boolean endsWith(String str, String pattern)
    {
////System.err.println("0: StringUtils.endsWith(()");        
        int len1 = str.length();
        int len2 = pattern.length();
        if (len1 < len2)
        {
            return false;
        }
        for (int i = len1 - 1, j = len2 - 1; j >= 0; i--, j--)
        {
            if (str.charAt(i) != pattern.charAt(j))
            {
                return false;
            }
////System.err.println("1: StringUtils.endsWith(()\ti: " + i);             
        }
////System.err.println("2: StringUtils.endsWith(()");         
        return true;
    }

    public static String unaccent(String src)
    {
        return Normalizer
            .normalize(src, Normalizer.Form.NFD)
            .replaceAll("[^\\p{ASCII}]", "");
    }

    public static String colocarPontoDecimal(String str)
    {
        int indice = str.indexOf(',');
        if (indice == -1)
        {
            return str;
        }
        StringBuffer stb = new StringBuffer(str);
        stb.setCharAt(indice, '.');
        return stb.toString();
    }
}
