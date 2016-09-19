package io.realm;


import android.util.JsonReader;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>();
        modelClasses.add(be.gershon_lehrer.mybooksathome.model.Author.class);
        modelClasses.add(be.gershon_lehrer.mybooksathome.model.Publisher.class);
        modelClasses.add(be.gershon_lehrer.mybooksathome.model.User.class);
        modelClasses.add(be.gershon_lehrer.mybooksathome.model.Book.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Table createTable(Class<? extends RealmModel> clazz, ImplicitTransaction transaction) {
        checkClass(clazz);

        if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
            return io.realm.AuthorRealmProxy.initTable(transaction);
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
            return io.realm.PublisherRealmProxy.initTable(transaction);
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
            return io.realm.UserRealmProxy.initTable(transaction);
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
            return io.realm.BookRealmProxy.initTable(transaction);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public ColumnInfo validateTable(Class<? extends RealmModel> clazz, ImplicitTransaction transaction) {
        checkClass(clazz);

        if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
            return io.realm.AuthorRealmProxy.validateTable(transaction);
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
            return io.realm.PublisherRealmProxy.validateTable(transaction);
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
            return io.realm.UserRealmProxy.validateTable(transaction);
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
            return io.realm.BookRealmProxy.validateTable(transaction);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
            return io.realm.AuthorRealmProxy.getFieldNames();
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
            return io.realm.PublisherRealmProxy.getFieldNames();
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
            return io.realm.UserRealmProxy.getFieldNames();
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
            return io.realm.BookRealmProxy.getFieldNames();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public String getTableName(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
            return io.realm.AuthorRealmProxy.getTableName();
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
            return io.realm.PublisherRealmProxy.getTableName();
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
            return io.realm.UserRealmProxy.getTableName();
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
            return io.realm.BookRealmProxy.getTableName();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, ColumnInfo columnInfo) {
        checkClass(clazz);

        if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
            return clazz.cast(new io.realm.AuthorRealmProxy(columnInfo));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
            return clazz.cast(new io.realm.PublisherRealmProxy(columnInfo));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
            return clazz.cast(new io.realm.UserRealmProxy(columnInfo));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
            return clazz.cast(new io.realm.BookRealmProxy(columnInfo));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
            return clazz.cast(io.realm.AuthorRealmProxy.copyOrUpdate(realm, (be.gershon_lehrer.mybooksathome.model.Author) obj, update, cache));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
            return clazz.cast(io.realm.PublisherRealmProxy.copyOrUpdate(realm, (be.gershon_lehrer.mybooksathome.model.Publisher) obj, update, cache));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.copyOrUpdate(realm, (be.gershon_lehrer.mybooksathome.model.User) obj, update, cache));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
            return clazz.cast(io.realm.BookRealmProxy.copyOrUpdate(realm, (be.gershon_lehrer.mybooksathome.model.Book) obj, update, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
            io.realm.AuthorRealmProxy.insert(realm, (be.gershon_lehrer.mybooksathome.model.Author) object, cache);
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
            io.realm.PublisherRealmProxy.insert(realm, (be.gershon_lehrer.mybooksathome.model.Publisher) object, cache);
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
            io.realm.UserRealmProxy.insert(realm, (be.gershon_lehrer.mybooksathome.model.User) object, cache);
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
            io.realm.BookRealmProxy.insert(realm, (be.gershon_lehrer.mybooksathome.model.Book) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new IdentityHashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
                io.realm.AuthorRealmProxy.insert(realm, (be.gershon_lehrer.mybooksathome.model.Author) object, cache);
            } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
                io.realm.PublisherRealmProxy.insert(realm, (be.gershon_lehrer.mybooksathome.model.Publisher) object, cache);
            } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
                io.realm.UserRealmProxy.insert(realm, (be.gershon_lehrer.mybooksathome.model.User) object, cache);
            } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
                io.realm.BookRealmProxy.insert(realm, (be.gershon_lehrer.mybooksathome.model.Book) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
                    io.realm.AuthorRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
                    io.realm.PublisherRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
                    io.realm.UserRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
                    io.realm.BookRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
            io.realm.AuthorRealmProxy.insertOrUpdate(realm, (be.gershon_lehrer.mybooksathome.model.Author) obj, cache);
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
            io.realm.PublisherRealmProxy.insertOrUpdate(realm, (be.gershon_lehrer.mybooksathome.model.Publisher) obj, cache);
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
            io.realm.UserRealmProxy.insertOrUpdate(realm, (be.gershon_lehrer.mybooksathome.model.User) obj, cache);
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
            io.realm.BookRealmProxy.insertOrUpdate(realm, (be.gershon_lehrer.mybooksathome.model.Book) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new IdentityHashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
                io.realm.AuthorRealmProxy.insertOrUpdate(realm, (be.gershon_lehrer.mybooksathome.model.Author) object, cache);
            } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
                io.realm.PublisherRealmProxy.insertOrUpdate(realm, (be.gershon_lehrer.mybooksathome.model.Publisher) object, cache);
            } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
                io.realm.UserRealmProxy.insertOrUpdate(realm, (be.gershon_lehrer.mybooksathome.model.User) object, cache);
            } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
                io.realm.BookRealmProxy.insertOrUpdate(realm, (be.gershon_lehrer.mybooksathome.model.Book) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
                    io.realm.AuthorRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
                    io.realm.PublisherRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
                    io.realm.UserRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
                    io.realm.BookRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
            return clazz.cast(io.realm.AuthorRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
            return clazz.cast(io.realm.PublisherRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
            return clazz.cast(io.realm.BookRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
            return clazz.cast(io.realm.AuthorRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
            return clazz.cast(io.realm.PublisherRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
            return clazz.cast(io.realm.BookRealmProxy.createUsingJsonStream(realm, reader));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Author.class)) {
            return clazz.cast(io.realm.AuthorRealmProxy.createDetachedCopy((be.gershon_lehrer.mybooksathome.model.Author) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Publisher.class)) {
            return clazz.cast(io.realm.PublisherRealmProxy.createDetachedCopy((be.gershon_lehrer.mybooksathome.model.Publisher) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.createDetachedCopy((be.gershon_lehrer.mybooksathome.model.User) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(be.gershon_lehrer.mybooksathome.model.Book.class)) {
            return clazz.cast(io.realm.BookRealmProxy.createDetachedCopy((be.gershon_lehrer.mybooksathome.model.Book) realmObject, 0, maxDepth, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

}
