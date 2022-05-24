/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaction.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import transaction.utils.TransactionalMethodsInterface;

/**
 *
 * @author jomar.domingos
 */
@Named(value = "transactionalMethodBean")
@RequestScoped
public class TransactionalMethodBean
{

	@Resource
	private UserTransaction userTransaction;

	/**
	 * Creates a new instance of TransactionalMethodBean
	 */
	public TransactionalMethodBean()
	{
	}

	public void runTransactionalMethod(TransactionalMethodsInterface transactionalMethodsInterface)
	{
		try
		{
			userTransaction.begin();
			transactionalMethodsInterface.transactionalMethod();
			userTransaction.commit();
		}
		catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex)
		{
			try
			{
				Logger.getLogger(TransactionalMethodBean.class.getName()).log(Level.SEVERE, null, ex);
				userTransaction.rollback();
			}
			catch (IllegalStateException | SecurityException | SystemException ex1)
			{
				Logger.getLogger(TransactionalMethodBean.class.getName()).log(Level.SEVERE, null, ex1);
			}
		}
	}

}
