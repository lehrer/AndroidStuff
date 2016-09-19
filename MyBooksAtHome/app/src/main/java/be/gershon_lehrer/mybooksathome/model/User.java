package be.gershon_lehrer.mybooksathome.model;


import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by gershonlehrer on 04/07/16.
 */
public class User extends RealmObject implements Serializable{
   @PrimaryKey
   private int id;
   @Required
   private String mNaam;

   private String mEmail;

   public String getNaam() {
      return mNaam;
   }

   public void setNaam(String naam) {
      mNaam = naam;
   }

   public String getEmail() {
      return mEmail;
   }

   public void setEmail(String email) {
      mEmail = email;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }
}
