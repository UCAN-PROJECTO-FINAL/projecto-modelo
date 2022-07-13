/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.utils;

/**
 *
 * @author Delcio Benga
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class EncriptacaoDecriptacao
{

    private final static String alg = "AES";
    private final static String cI = "AES/CBC/PKCS5Padding";
    public static final String KEY = "92AE31A79FEEB2A3";
    public static final String IV = "0123456789ABCDEF";

    public static String encrypt(String cleartext)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(cI);
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(), alg);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(cleartext.getBytes());
            return new String(encodeBase64(encrypted));
        }
        catch (Exception ex)
        {
            Logger.getLogger(EncriptacaoDecriptacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String decrypt(String encrypted)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(cI);
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(), alg);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes());
            byte[] enc = decodeBase64(encrypted.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
            byte[] decrypted = cipher.doFinal(enc);
            return new String(decrypted);
        }
        catch (Exception ex)
        {
            Logger.getLogger(EncriptacaoDecriptacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
