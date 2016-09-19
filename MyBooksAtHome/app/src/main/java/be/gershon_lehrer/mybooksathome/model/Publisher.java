package be.gershon_lehrer.mybooksathome.model;


import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by gershonlehrer on 04/07/16.
 */
public class Publisher extends RealmObject implements Serializable{
    @PrimaryKey
    private String mId;
    @Required
    private String mName;

    public Publisher(){
        mId= UUID.randomUUID().toString();
    }

    public Publisher(String nameOfPubisher){
        this();
        this.setName(nameOfPubisher);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getId() {
        return mId;
    }

}
