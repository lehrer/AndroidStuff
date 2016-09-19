package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserRealmProxy extends be.gershon_lehrer.mybooksathome.model.User
    implements RealmObjectProxy, UserRealmProxyInterface {

    static final class UserColumnInfo extends ColumnInfo {

        public final long idIndex;
        public final long mNaamIndex;
        public final long mEmailIndex;

        UserColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.idIndex = getValidColumnIndex(path, table, "User", "id");
            indicesMap.put("id", this.idIndex);

            this.mNaamIndex = getValidColumnIndex(path, table, "User", "mNaam");
            indicesMap.put("mNaam", this.mNaamIndex);

            this.mEmailIndex = getValidColumnIndex(path, table, "User", "mEmail");
            indicesMap.put("mEmail", this.mEmailIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final UserColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("mNaam");
        fieldNames.add("mEmail");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    UserRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (UserColumnInfo) columnInfo;
        this.proxyState = new ProxyState(be.gershon_lehrer.mybooksathome.model.User.class, this);
    }

    @SuppressWarnings("cast")
    public int realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idIndex);
    }

    public void realmSet$id(int value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.idIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$mNaam() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mNaamIndex);
    }

    public void realmSet$mNaam(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field mNaam to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.mNaamIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$mEmail() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mEmailIndex);
    }

    public void realmSet$mEmail(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mEmailIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mEmailIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_User")) {
            Table table = transaction.getTable("class_User");
            table.addColumn(RealmFieldType.INTEGER, "id", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mNaam", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mEmail", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return transaction.getTable("class_User");
    }

    public static UserColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_User")) {
            Table table = transaction.getTable("class_User");
            if (table.getColumnCount() != 3) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 3 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final UserColumnInfo columnInfo = new UserColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'id' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.idIndex) && table.findFirstNull(columnInfo.idIndex) != TableOrView.NO_MATCH) {
                throw new IllegalStateException("Cannot migrate an object with null value in field 'id'. Either maintain the same type for primary key field 'id', or remove the object with null value before migration.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'id' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("mNaam")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mNaam' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mNaam") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mNaam' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.mNaamIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mNaam' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'mNaam' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("mEmail")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mEmail' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mEmail") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mEmail' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.mEmailIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mEmail' is required. Either set @Required to field 'mEmail' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The User class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_User";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static be.gershon_lehrer.mybooksathome.model.User createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        be.gershon_lehrer.mybooksathome.model.User obj = null;
        if (update) {
            Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.User.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                obj = new io.realm.UserRealmProxy(realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.User.class));
                ((RealmObjectProxy)obj).realmGet$proxyState().setRealm$realm(realm);
                ((RealmObjectProxy)obj).realmGet$proxyState().setRow$realm(table.getUncheckedRow(rowIndex));
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.UserRealmProxy) realm.createObject(be.gershon_lehrer.mybooksathome.model.User.class, null);
                } else {
                    obj = (io.realm.UserRealmProxy) realm.createObject(be.gershon_lehrer.mybooksathome.model.User.class, json.getInt("id"));
                }
            } else {
                obj = (io.realm.UserRealmProxy) realm.createObject(be.gershon_lehrer.mybooksathome.model.User.class);
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field id to null.");
            } else {
                ((UserRealmProxyInterface) obj).realmSet$id((int) json.getInt("id"));
            }
        }
        if (json.has("mNaam")) {
            if (json.isNull("mNaam")) {
                ((UserRealmProxyInterface) obj).realmSet$mNaam(null);
            } else {
                ((UserRealmProxyInterface) obj).realmSet$mNaam((String) json.getString("mNaam"));
            }
        }
        if (json.has("mEmail")) {
            if (json.isNull("mEmail")) {
                ((UserRealmProxyInterface) obj).realmSet$mEmail(null);
            } else {
                ((UserRealmProxyInterface) obj).realmSet$mEmail((String) json.getString("mEmail"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static be.gershon_lehrer.mybooksathome.model.User createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        be.gershon_lehrer.mybooksathome.model.User obj = realm.createObject(be.gershon_lehrer.mybooksathome.model.User.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field id to null.");
                } else {
                    ((UserRealmProxyInterface) obj).realmSet$id((int) reader.nextInt());
                }
            } else if (name.equals("mNaam")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserRealmProxyInterface) obj).realmSet$mNaam(null);
                } else {
                    ((UserRealmProxyInterface) obj).realmSet$mNaam((String) reader.nextString());
                }
            } else if (name.equals("mEmail")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserRealmProxyInterface) obj).realmSet$mEmail(null);
                } else {
                    ((UserRealmProxyInterface) obj).realmSet$mEmail((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static be.gershon_lehrer.mybooksathome.model.User copyOrUpdate(Realm realm, be.gershon_lehrer.mybooksathome.model.User object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (be.gershon_lehrer.mybooksathome.model.User) cachedRealmObject;
        } else {
            be.gershon_lehrer.mybooksathome.model.User realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.User.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstLong(pkColumnIndex, ((UserRealmProxyInterface) object).realmGet$id());
                if (rowIndex != TableOrView.NO_MATCH) {
                    realmObject = new io.realm.UserRealmProxy(realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.User.class));
                    ((RealmObjectProxy)realmObject).realmGet$proxyState().setRealm$realm(realm);
                    ((RealmObjectProxy)realmObject).realmGet$proxyState().setRow$realm(table.getUncheckedRow(rowIndex));
                    cache.put(object, (RealmObjectProxy) realmObject);
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static be.gershon_lehrer.mybooksathome.model.User copy(Realm realm, be.gershon_lehrer.mybooksathome.model.User newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (be.gershon_lehrer.mybooksathome.model.User) cachedRealmObject;
        } else {
            be.gershon_lehrer.mybooksathome.model.User realmObject = realm.createObject(be.gershon_lehrer.mybooksathome.model.User.class, ((UserRealmProxyInterface) newObject).realmGet$id());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((UserRealmProxyInterface) realmObject).realmSet$id(((UserRealmProxyInterface) newObject).realmGet$id());
            ((UserRealmProxyInterface) realmObject).realmSet$mNaam(((UserRealmProxyInterface) newObject).realmGet$mNaam());
            ((UserRealmProxyInterface) realmObject).realmSet$mEmail(((UserRealmProxyInterface) newObject).realmGet$mEmail());
            return realmObject;
        }
    }

    public static long insert(Realm realm, be.gershon_lehrer.mybooksathome.model.User object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.User.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.User.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((UserRealmProxyInterface)object).realmGet$id());
        String realmGet$mNaam = ((UserRealmProxyInterface)object).realmGet$mNaam();
        if (realmGet$mNaam != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mNaamIndex, rowIndex, realmGet$mNaam);
        }
        String realmGet$mEmail = ((UserRealmProxyInterface)object).realmGet$mEmail();
        if (realmGet$mEmail != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mEmailIndex, rowIndex, realmGet$mEmail);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.User.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.User.class);
        be.gershon_lehrer.mybooksathome.model.User object = null;
        while (objects.hasNext()) {
            object = (be.gershon_lehrer.mybooksathome.model.User) objects.next();
            if(!cache.containsKey(object)) {
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((UserRealmProxyInterface)object).realmGet$id());
                String realmGet$mNaam = ((UserRealmProxyInterface)object).realmGet$mNaam();
                if (realmGet$mNaam != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mNaamIndex, rowIndex, realmGet$mNaam);
                }
                String realmGet$mEmail = ((UserRealmProxyInterface)object).realmGet$mEmail();
                if (realmGet$mEmail != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mEmailIndex, rowIndex, realmGet$mEmail);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, be.gershon_lehrer.mybooksathome.model.User object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.User.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.User.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = TableOrView.NO_MATCH;
        Object primaryKeyValue = ((UserRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = table.findFirstLong(pkColumnIndex, ((UserRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
            if (primaryKeyValue != null) {
                Table.nativeSetLong(tableNativePtr, pkColumnIndex, rowIndex, ((UserRealmProxyInterface) object).realmGet$id());
            }
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((UserRealmProxyInterface)object).realmGet$id());
        String realmGet$mNaam = ((UserRealmProxyInterface)object).realmGet$mNaam();
        if (realmGet$mNaam != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mNaamIndex, rowIndex, realmGet$mNaam);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mNaamIndex, rowIndex);
        }
        String realmGet$mEmail = ((UserRealmProxyInterface)object).realmGet$mEmail();
        if (realmGet$mEmail != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mEmailIndex, rowIndex, realmGet$mEmail);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mEmailIndex, rowIndex);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.User.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.User.class);
        long pkColumnIndex = table.getPrimaryKey();
        be.gershon_lehrer.mybooksathome.model.User object = null;
        while (objects.hasNext()) {
            object = (be.gershon_lehrer.mybooksathome.model.User) objects.next();
            if(!cache.containsKey(object)) {
                long rowIndex = TableOrView.NO_MATCH;
                Object primaryKeyValue = ((UserRealmProxyInterface) object).realmGet$id();
                if (primaryKeyValue != null) {
                    rowIndex = table.findFirstLong(pkColumnIndex, ((UserRealmProxyInterface) object).realmGet$id());
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                    if (primaryKeyValue != null) {
                        Table.nativeSetLong(tableNativePtr, pkColumnIndex, rowIndex, ((UserRealmProxyInterface) object).realmGet$id());
                    }
                }
                cache.put(object, rowIndex);
                Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((UserRealmProxyInterface)object).realmGet$id());
                String realmGet$mNaam = ((UserRealmProxyInterface)object).realmGet$mNaam();
                if (realmGet$mNaam != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mNaamIndex, rowIndex, realmGet$mNaam);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mNaamIndex, rowIndex);
                }
                String realmGet$mEmail = ((UserRealmProxyInterface)object).realmGet$mEmail();
                if (realmGet$mEmail != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mEmailIndex, rowIndex, realmGet$mEmail);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mEmailIndex, rowIndex);
                }
            }
        }
    }

    public static be.gershon_lehrer.mybooksathome.model.User createDetachedCopy(be.gershon_lehrer.mybooksathome.model.User realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        be.gershon_lehrer.mybooksathome.model.User unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (be.gershon_lehrer.mybooksathome.model.User)cachedObject.object;
            } else {
                unmanagedObject = (be.gershon_lehrer.mybooksathome.model.User)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new be.gershon_lehrer.mybooksathome.model.User();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((UserRealmProxyInterface) unmanagedObject).realmSet$id(((UserRealmProxyInterface) realmObject).realmGet$id());
        ((UserRealmProxyInterface) unmanagedObject).realmSet$mNaam(((UserRealmProxyInterface) realmObject).realmGet$mNaam());
        ((UserRealmProxyInterface) unmanagedObject).realmSet$mEmail(((UserRealmProxyInterface) realmObject).realmGet$mEmail());
        return unmanagedObject;
    }

    static be.gershon_lehrer.mybooksathome.model.User update(Realm realm, be.gershon_lehrer.mybooksathome.model.User realmObject, be.gershon_lehrer.mybooksathome.model.User newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((UserRealmProxyInterface) realmObject).realmSet$mNaam(((UserRealmProxyInterface) newObject).realmGet$mNaam());
        ((UserRealmProxyInterface) realmObject).realmSet$mEmail(((UserRealmProxyInterface) newObject).realmGet$mEmail());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("User = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mNaam:");
        stringBuilder.append(realmGet$mNaam());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mEmail:");
        stringBuilder.append(realmGet$mEmail() != null ? realmGet$mEmail() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRealmProxy aUser = (UserRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aUser.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUser.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aUser.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
