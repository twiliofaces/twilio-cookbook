package org.twiliofaces.chapter1.recipe6.controller;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.twiliofaces.chapter1.recipe6.model.CompanyNumber;
import org.twiliofaces.chapter1.recipe6.util.ExtensionUtils;

@ApplicationScoped
@Named
public class CompanyDirectoryMapController
{
   public CompanyDirectoryMapController()
   {
   }

   /*
    * This array is your company directory, the format is as follows: 'extension'=> array( 'phone'=>'phone number to
    * call', 'firstname'=>'first name', 'lastname' => 'lastname' )
    */
   // extension - number
   private Map<String, String> indexes;
   // number - companyNumber
   private Map<String, CompanyNumber> directory;

   /*
    * This little piece of fun lets you take a person's last name and assign matching digits from phone numbers... :)
    */

   public CompanyNumber getPhoneNumberByExtension(String ext)
   {
      if (getIndexes().containsKey(ext))
      {
         String number = getIndexes().get(ext);
         return getDirectory().get(number);
      }
      return null;
   }

   public CompanyNumber getPhoneNumberByDigits(String digits)
   {
      if (getDirectory().containsKey(digits))
         return getDirectory().get(digits);
      return null;
   }

   public Map<String, CompanyNumber> getDirectory()
   {
      if (directory == null)
      {
         directory = new HashMap<String, CompanyNumber>();
         CompanyNumber first = new CompanyNumber("0", "415-555-1111", "John", "Smith");
         directory.put(first.getExtension(), first);
         getIndexes().put(ExtensionUtils.stringToDigits(first.getPhone()), first.getPhone());
         CompanyNumber second = new CompanyNumber("1234", "415-555-2222", "Joe", "Doe");
         directory.put(second.getExtension(), second);
         getIndexes().put(ExtensionUtils.stringToDigits(second.getPhone()), second.getPhone());
         CompanyNumber thirt = new CompanyNumber("4321", "415-555-3333", "Eric", "Anderson");
         directory.put(thirt.getExtension(), thirt);
         getIndexes().put(ExtensionUtils.stringToDigits(thirt.getPhone()), thirt.getPhone());
      }
      return directory;
   }

   public void setDirectory(Map<String, CompanyNumber> directory)
   {
      this.directory = directory;
   }

   public Map<String, String> getIndexes()
   {
      if (indexes == null)
      {
         indexes = new HashMap<String, String>();
      }
      return indexes;
   }

   public void setIndexes(Map<String, String> indexes)
   {
      this.indexes = indexes;
   }
}
