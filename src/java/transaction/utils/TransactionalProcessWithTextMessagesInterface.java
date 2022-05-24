/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaction.utils;

import java.util.List;

/**
 *
 * @author aires
 */
public interface TransactionalProcessWithTextMessagesInterface
{
    public boolean process(List<String> messages);
}
