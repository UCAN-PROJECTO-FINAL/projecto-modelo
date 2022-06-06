/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaction.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
//import notas.utils.Nota;
//import notas.utils.NotaInformacao;
//import notas.utils.NotaSucesso;
import transaction.utils.NotasAndStatus;
import transaction.utils.TransactionalProcessInterface;
import transaction.utils.TransactionalProcessWithNotaMessagesInterface;
import transaction.utils.TransactionalProcessWithTextMessagesInterface;
import utils.mensagens.LogFile;

/**
 *
 * @author aires
 */
@Named(value = "transactionalProcessBean")
@RequestScoped
public class TransactionalProcessBean
{

    /**
     * Creates a new instance of TransactionalProcessBean
     */
    public TransactionalProcessBean()
    {
    }
    @Resource
    private UserTransaction userTransaction;

    /**
     * Creates a new instance of TransactionalMethodBean
     *
     * @param msgCommitSucess
     * @param msgCommitFail
     * @param msgRollbackFail
     * @param transactionalProcessInterface
     * @return
     */
    public boolean runTransactionalMethod(String msgCommitSucess, String msgCommitFail, String msgRollbackFail,
        TransactionalProcessInterface transactionalProcessInterface)
    {
        try
        {
            userTransaction.begin();
            if (transactionalProcessInterface.process())
            {
                userTransaction.commit();
                if (msgCommitSucess != null && !msgCommitSucess.isEmpty())
                {
                    LogFile.sucessoMsg(null, msgCommitSucess);
                }
                return true;
            }
            else
            {
                if (msgCommitFail != null && !msgCommitFail.isEmpty())
                {
                    LogFile.warnMsg(null, msgCommitFail);
                }
                userTransaction.rollback();
            }
        }
        catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex)
        {
            try
            {
                if (msgCommitFail != null && !msgCommitFail.isEmpty())
                {
                    LogFile.warnMsg(null, msgCommitFail);
                }
                userTransaction.rollback();
            }
            catch (IllegalStateException | SecurityException | SystemException ex1)
            {
                if (msgRollbackFail != null && !msgRollbackFail.isEmpty())
                {
                    LogFile.warnMsg(null, msgRollbackFail);
                }
            }
        }
        return false;
    }

    /**
     * Creates a new instance of TransactionalMethodBean
     *
     * @param msgCommitSucess
     * @param msgCommitFail
     * @param msgRollbackFail
     * @param messages
     * @param transactionalProcessWithTextMessagesInterface
     * @return
     */
//    public boolean runTransactionalMethod(
//        String msgCommitSucess,
//        String msgCommitFail, String msgRollbackFail,
//        TransactionalProcessWithTextMessagesInterface transactionalProcessWithTextMessagesInterface
//        )
//    {
//        List<String> messages = new ArrayList();
//        try
//        {
////System.err.println("0: TransactionalProcessBean.runTransactionalMethod()");            
//            userTransaction.begin();
//            if (transactionalProcessWithTextMessagesInterface.process(messages))
//            {
//                if (msgCommitSucess != null && !msgCommitSucess.isEmpty())
//                {
//                    messages.add(msgCommitSucess);
//                }
////System.err.println("1: TransactionalProcessBean.runTransactionalMethod()");                
//                userTransaction.commit();
//                if (!messages.isEmpty())
//                {
////System.err.println("2: TransactionalProcessBean.runTransactionalMethod()");   
//
//                    LogFile.sucessoMsg(null, messages);
////System.err.println("3: TransactionalProcessBean.runTransactionalMethod()");                    
//                }
////System.err.println("4: TransactionalProcessBean.runTransactionalMethod()");                
//                return true;
//            }
//            else
//            {
//                if (msgCommitFail != null && !msgCommitFail.isEmpty())
//                {
//                    messages.add(msgCommitFail);
//                }
////System.err.println("5: TransactionalProcessBean.runTransactionalMethod()");                
//                if (!messages.isEmpty())
//                {
////System.err.println("6: TransactionalProcessBean.runTransactionalMethod()");                    
//                    LogFile.warnMsg(null, messages);
//                }
////System.err.println("7: TransactionalProcessBean.runTransactionalMethod()");                
//                userTransaction.rollback();
////System.err.println("8: TransactionalProcessBean.runTransactionalMethod()");                
//            }
//        }
//        catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex)
//        {
////System.err.println("9: TransactionalProcessBean.runTransactionalMethod()");            
//            try
//            {
//                if (msgCommitFail != null && !msgCommitFail.isEmpty())
//                {
//                    messages.add(msgCommitFail);
//                }
////System.err.println("10: TransactionalProcessBean.runTransactionalMethod()");                
//                if (!messages.isEmpty())
//                {
////System.err.println("11: TransactionalProcessBean.runTransactionalMethod()");                    
//                    LogFile.warnMsg(null, messages);
//                }
////System.err.println("12: TransactionalProcessBean.runTransactionalMethod()");                
//                userTransaction.rollback();
////System.err.println("13: TransactionalProcessBean.runTransactionalMethod()");                
//            }
//            catch (IllegalStateException | SecurityException | SystemException ex1)
//            {
//                if (msgRollbackFail != null && !msgRollbackFail.isEmpty())
//                {
//                    messages.add(msgRollbackFail);
//                }
////System.err.println("14: TransactionalProcessBean.runTransactionalMethod()");                
//                if (!messages.isEmpty())
//                {
////System.err.println("15: TransactionalProcessBean.runTransactionalMethod()");                    
//                    LogFile.warnMsg(null, messages);
////System.err.println("16: TransactionalProcessBean.runTransactionalMethod()");                    
//                }
//            }
//        }
////System.err.println("17: TransactionalProcessBean.runTransactionalMethod()");        
//        return false;
//    }
    public boolean runTransactionalMethodWithMessages(
        String msgCommitSucess,
        String msgCommitFail, String msgRollbackFail,
        TransactionalProcessWithTextMessagesInterface transactionalProcessWithMessagesInterface
    )
    {
        List<String> messages = new ArrayList();
        try
        {
//System.err.println("0: TransactionalProcessBean.runTransactionalMethod()");            
            userTransaction.begin();
            if (transactionalProcessWithMessagesInterface.process(messages))
            {
                if (msgCommitSucess != null && !msgCommitSucess.isEmpty())
                {
                    messages.add(msgCommitSucess);
                }
//System.err.println("1: TransactionalProcessBean.runTransactionalMethod()");                
                userTransaction.commit();
                if (!messages.isEmpty())
                {
//System.err.println("2: TransactionalProcessBean.runTransactionalMethod()");   

                    LogFile.sucessoMsg(null, messages);
//System.err.println("3: TransactionalProcessBean.runTransactionalMethod()");                    
                }
//System.err.println("4: TransactionalProcessBean.runTransactionalMethod()");                
                return true;
            }
            else
            {
                if (msgCommitFail != null && !msgCommitFail.isEmpty())
                {
                    messages.add(msgCommitFail);
                }
//System.err.println("5: TransactionalProcessBean.runTransactionalMethod()");                
                if (!messages.isEmpty())
                {
//System.err.println("6: TransactionalProcessBean.runTransactionalMethod()");                    
                    LogFile.warnMsg(null, messages);
                }
//System.err.println("7: TransactionalProcessBean.runTransactionalMethod()");                
                userTransaction.rollback();
//System.err.println("8: TransactionalProcessBean.runTransactionalMethod()");                
            }
        }
        catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex)
        {
//System.err.println("9: TransactionalProcessBean.runTransactionalMethod()");            
            try
            {
                if (msgCommitFail != null && !msgCommitFail.isEmpty())
                {
                    messages.add(msgCommitFail);
                }
//System.err.println("10: TransactionalProcessBean.runTransactionalMethod()");                
                if (!messages.isEmpty())
                {
//System.err.println("11: TransactionalProcessBean.runTransactionalMethod()");                    
                    LogFile.warnMsg(null, messages);
                }
//System.err.println("12: TransactionalProcessBean.runTransactionalMethod()");                
                userTransaction.rollback();
//System.err.println("13: TransactionalProcessBean.runTransactionalMethod()");                
            }
            catch (IllegalStateException | SecurityException | SystemException ex1)
            {
                if (msgRollbackFail != null && !msgRollbackFail.isEmpty())
                {
                    messages.add(msgRollbackFail);
                }
//System.err.println("14: TransactionalProcessBean.runTransactionalMethod()");                
                if (!messages.isEmpty())
                {
//System.err.println("15: TransactionalProcessBean.runTransactionalMethod()");                    
                    LogFile.warnMsg(null, messages);
//System.err.println("16: TransactionalProcessBean.runTransactionalMethod()");                    
                }
            }
        }
//System.err.println("17: TransactionalProcessBean.runTransactionalMethod()");        
        return false;
    }

//    public NotasAndStatus runTransactionalMethodWithMessages(
//        String msgCommitSucess,
//        String msgCommitFail, String msgRollbackFail,
//        TransactionalProcessWithNotaMessagesInterface transactionalProcessWithNotaMessagesInterface
//    )
//    {
//        //List<Nota> messages = new ArrayList();
//        try
//        {
//System.err.println("0: TransactionalProcessBean.runTransactionalMethod()");            
//            userTransaction.begin();
//            if (transactionalProcessWithNotaMessagesInterface.process())
//            {
//                if (msgCommitSucess != null && !msgCommitSucess.isEmpty())
//                {
//                   // messages.add(new NotaSucesso(msgCommitSucess));
//                }
//System.err.println("1: TransactionalProcessBean.runTransactionalMethod()");                
//                userTransaction.commit();
////                if (!messages.isEmpty())
////                {
//////System.err.println("2: TransactionalProcessBean.runTransactionalMethod()");   
////
//////                    LogFile.sucessoMsg(null, messages);
//////System.err.println("3: TransactionalProcessBean.runTransactionalMethod()");                    
////                }
//System.err.println("4: TransactionalProcessBean.runTransactionalMethod()");                
//                return new NotasAndStatus(messages, true);
//            }
//            else
//            {
//System.err.println("5: TransactionalProcessBean.runTransactionalMethod()");                  
//                if (msgCommitFail != null && !msgCommitFail.isEmpty())
//                {
//System.err.println("5.1: TransactionalProcessBean.runTransactionalMethod()");                       
//                    messages.add(new NotaInformacao(msgCommitFail));
//                }
//System.err.println("5.2: TransactionalProcessBean.runTransactionalMethod()");                
//                if (!messages.isEmpty())
//                {
////System.err.println("6: TransactionalProcessBean.runTransactionalMethod()");                    
////                    LogFile.warnMsg(null, messages);
//                }
//System.err.println("7: TransactionalProcessBean.runTransactionalMethod()");                
//                userTransaction.rollback();
//System.err.println("8: TransactionalProcessBean.runTransactionalMethod()");                
//            }
//        }
//        catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex)
//        {
//System.err.println("9: TransactionalProcessBean.runTransactionalMethod()");            
//            try
//            {
//                if (msgCommitFail != null && !msgCommitFail.isEmpty())
//                {
//                    messages.add(new NotaInformacao(msgCommitFail));
//                }
//System.err.println("10: TransactionalProcessBean.runTransactionalMethod()");                
//                if (!messages.isEmpty())
//                {
//System.err.println("11: TransactionalProcessBean.runTransactionalMethod()");                    
////                    LogFile.warnMsg(null, messages);
//                }
//System.err.println("12: TransactionalProcessBean.runTransactionalMethod()");                
//                userTransaction.rollback();
////System.err.println("13: TransactionalProcessBean.runTransactionalMethod()");                
//            }
//            catch (IllegalStateException | SecurityException | SystemException ex1)
//            {
//                if (msgRollbackFail != null && !msgRollbackFail.isEmpty())
//                {
//                    messages.add(new NotaInformacao(msgRollbackFail));
//                }
//System.err.println("14: TransactionalProcessBean.runTransactionalMethod()");                
//                if (!messages.isEmpty())
//                {
//System.err.println("15: TransactionalProcessBean.runTransactionalMethod()");                    
////                    LogFile.warnMsg(null, messages);
////System.err.println("16: TransactionalProcessBean.runTransactionalMethod()");                    
//                }
//            }
//        }
//System.err.println("17: TransactionalProcessBean.runTransactionalMethod()");        
//        return new NotasAndStatus(messages, false);
//    }
    
}
