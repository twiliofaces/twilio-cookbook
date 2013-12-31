package org.twiliofaces.recipes.timer;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.giavacms.common.model.Search;
import org.jboss.logging.Logger;
import org.twiliofaces.recipes.model.User;
import org.twiliofaces.recipes.repository.UserRepository;

@Stateless
@LocalBean
public class UserExpirationTimer
{

   Logger logger = Logger.getLogger(getClass());

   @Inject
   UserRepository userRepository;

   @Schedule(hour = "*", minute = "25", persistent = false, info = "every 25-th minute of an hour")
   public void execute()
   {
      executeNow();
   }

   @PostConstruct
   public void executeNow()
   {
      logger.info("Running now.");
      try
      {
         Search<User> su = new Search<User>(User.class);
         Calendar cal = Calendar.getInstance();
         cal.add(Calendar.DAY_OF_YEAR, -1);
         su.getFrom().setRegistrationDate(cal.getTime());
         List<User> expiredUsers = userRepository.getList(su, 0, 0);
         logger.info("Found " + expiredUsers.size() + " to expire");
         for (User user : expiredUsers)
         {
            boolean result = userRepository.delete(user);
            logger.info("Removal of user " + user.getName() + ", " + user.getUsername() + ", " + user.getMobile()
                     + ": " + (result ? "success" : "failure"));
         }
      }
      catch (Exception e)
      {
         logger.error(e.getMessage(), e);
      }
   }
}
