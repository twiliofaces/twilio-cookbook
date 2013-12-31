/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.recipes.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.giavacms.common.model.Search;
import org.giavacms.common.repository.AbstractRepository;
import org.jboss.logging.Logger;
import org.twiliofaces.recipes.model.User;
import org.twiliofaces.recipes.model.UserExpired;
import org.twiliofaces.recipes.utils.PasswordUtils;

@Named
@Stateless
@LocalBean
public class UserRepository extends AbstractRepository<User> implements
         Serializable
{

   private static final long serialVersionUID = 1L;

   Logger logger = Logger.getLogger(getClass());

   @PersistenceContext
   EntityManager em;

   public UserRepository()
   {
   }

   @Override
   protected void applyRestrictions(Search<User> search, String alias,
            String separator, StringBuffer sb, Map<String, Object> params)
   {

      // NAME
      if (search.getObj().getName() != null
               && !search.getObj().getName().isEmpty())
      {
         sb.append(separator + " upper(").append(alias)
                  .append(".name) LIKE :NAME ");
         params.put("NAME", likeParam(search.getObj().getName()));
      }
      // USERNAME
      if (search.getObj().getUsername() != null
               && !search.getObj().getUsername().isEmpty())
      {
         sb.append(separator + " upper(").append(alias)
                  .append(".username) LIKE :USERNAME ");
         params.put("USERNAME", likeParam(search.getObj().getUsername()));
      }

      // ROLE
      if (search.getObj().getRole() != null
               && !search.getObj().getRole().isEmpty())
      {
         sb.append(separator).append(".role = :ROLE ");
         params.put("ROLE", search.getObj().getRole());
      }

   }

   @SuppressWarnings("unchecked")
   public void verifyConfiguration()
   {
      List<User> list = em
               .createQuery(
                        "select t from " + User.class.getSimpleName() + " t where t.role = :ROLEADMIN")
               .setParameter("ROLEADMIN", "admin").getResultList();
      if (list == null || list.size() == 0)
      {
         User user = new User();
         user.setName("admin");
         user.setUsername("admin");
         user.setPassword(PasswordUtils.createPassword("admin"));
         user.setRole("admin");
         persist(user);
         logger.info("admin created");
      }
      else
      {
         logger.info("admin exists");
      }
   }

   @SuppressWarnings("unchecked")
   public User findByUsername(String username)
   {
      try
      {
         List<User> list = em
                  .createQuery(
                           "select t from " + User.class.getSimpleName() + " t where t.username = :USERNAME")
                  .setParameter("USERNAME", username).getResultList();
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
      return "username asc";
   }

   public User getAccountByName(String accountName)
   {
      try
      {
         @SuppressWarnings("unchecked")
         List<User> list = em
                  .createQuery(
                           "select t from " + User.class.getSimpleName() + " t where t.name = :NAME")
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
   public boolean delete(Object key)
   {
      try
      {
         User user = find(key);
         UserExpired userExpired = new UserExpired(user);
         userExpired.setExpirationDate(new Date());
         getEm().remove(user);
         getEm().persist(userExpired);
         return true;
      }
      catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return false;
      }
   }

   @Override
   public User persist(User object)
   {
      object.setRegistrationDate(new Date());
      return super.persist(object);
   }
}
