/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.recipes.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.giavacms.common.model.Search;
import org.giavacms.common.repository.AbstractRepository;
import org.twiliofaces.recipes.model.UserAccount;

@Stateless
@LocalBean
public class UserAccountRepository extends
         AbstractRepository<UserAccount> implements Serializable
{
   private static final long serialVersionUID = 1L;

   @PersistenceContext
   EntityManager em;

   public UserAccount getAccountByName(String accountName)
   {
      try
      {
         @SuppressWarnings("unchecked")
         List<UserAccount> list = em
                  .createQuery(
                           "select t from " + UserAccount.class.getSimpleName() + " t where t.name = :NAME")
                  .setParameter("NAME", accountName).getResultList();
         if (list != null && list.size() > 0)
            return list.get(0);
         return null;
      }
      catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return null;
      }
   }

   @Override
   protected EntityManager getEm()
   {
      return em;
   }

   @Override
   public void setEm(EntityManager em)
   {
      this.em = em;
   }

   @Override
   protected String getDefaultOrderBy()
   {
      return "name asc";
   }

   @Override
   protected void applyRestrictions(Search<UserAccount> search, String alias, String separator, StringBuffer sb,
            Map<String, Object> params)
   {
      // separatore
      separator = " and ";

      if (search.getObj().getName() != null
               && !search.getObj().getName().isEmpty())
      {
         sb.append(separator).append(alias)
                  .append(".name = :name ");
         params.put("name", likeParam(search.getObj().getName()));
      }
   }

}
