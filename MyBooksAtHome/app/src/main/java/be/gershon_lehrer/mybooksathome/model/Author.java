package be.gershon_lehrer.mybooksathome.model;


import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by gershonlehrer on 04/07/16.
 */

public class Author extends RealmObject implements Serializable {
    @PrimaryKey
    private String id;
    @Required
    private String mName;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Author(){
        this(null);
    }
    public Author(String name){
        id = UUID.randomUUID().toString();
        this.mName = name;
    }
}
