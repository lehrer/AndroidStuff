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

public class AuthorRealmProxy extends be.gershon_lehrer.mybooksathome.model.Author
    implements RealmObjectProxy, AuthorRealmProxyInterface {

    static final class AuthorColumnInfo extends ColumnInfo {

        public final long idIndex;
        public final long mNameIndex;

        AuthorColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(2);
            this.idIndex = getValidColumnIndex(path, table, "Author", "id");
            indicesMap.put("id", this.idIndex);

            this.mNameIndex = getValidColumnIndex(path, table, "Author", "mName");
            indicesMap.put("mName", this.mNameIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final AuthorColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("mName");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    AuthorRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (AuthorColumnInfo) columnInfo;
        this.proxyState = new ProxyState(be.gershon_lehrer.mybooksathome.model.Author.class, this);
    }

    @SuppressWarnings("cast")
    public String realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idIndex);
    }

    public void realmSet$id(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.idIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.idIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$mName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mNameIndex);
    }

    public void realmSet$mName(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field mName to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.mNameIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Author")) {
            Table table = transaction.getTable("class_Author");
            table.addColumn(RealmFieldType.STRING, "id", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mName", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return transaction.getTable("class_Author");
    }

    public static AuthorColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Author")) {
            Table table = transaction.getTable("class_Author");
            if (table.getColumnCount() != 2) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 2 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 2; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final AuthorColumnInfo columnInfo = new AuthorColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'id' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.idIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(),"@PrimaryKey field 'id' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'id' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("mName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mName' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.mNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mName' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'mName' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Author class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Author";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static be.gershon_lehrer.mybooksathome.model.Author createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        be.gershon_lehrer.mybooksathome.model.Author obj = null;
        if (update) {
            Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Author.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (json.isNull("id")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                obj = new io.realm.AuthorRealmProxy(realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Author.class));
                ((RealmObjectProxy)obj).realmGet$proxyState().setRealm$realm(realm);
                ((RealmObjectProxy)obj).realmGet$proxyState().setRow$realm(table.getUncheckedRow(rowIndex));
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.AuthorRealmProxy) realm.createObject(be.gershon_lehrer.mybooksathome.model.Author.class, null);
                } else {
                    obj = (io.realm.AuthorRealmProxy) realm.createObject(be.gershon_lehrer.mybooksathome.model.Author.class, json.getString("id"));
                }
            } else {
                obj = (io.realm.AuthorRealmProxy) realm.createObject(be.gershon_lehrer.mybooksathome.model.Author.class);
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                ((AuthorRealmProxyInterface) obj).realmSet$id(null);
            } else {
                ((AuthorRealmProxyInterface) obj).realmSet$id((String) json.getString("id"));
            }
        }
        if (json.has("mName")) {
            if (json.isNull("mName")) {
                ((AuthorRealmProxyInterface) obj).realmSet$mName(null);
            } else {
                ((AuthorRealmProxyInterface) obj).realmSet$mName((String) json.getString("mName"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static be.gershon_lehrer.mybooksathome.model.Author createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        be.gershon_lehrer.mybooksathome.model.Author obj = realm.createObject(be.gershon_lehrer.mybooksathome.model.Author.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((AuthorRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((AuthorRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
            } else if (name.equals("mName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((AuthorRealmProxyInterface) obj).realmSet$mName(null);
                } else {
                    ((AuthorRealmProxyInterface) obj).realmSet$mName((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static be.gershon_lehrer.mybooksathome.model.Author copyOrUpdate(Realm realm, be.gershon_lehrer.mybooksathome.model.Author object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (be.gershon_lehrer.mybooksathome.model.Author) cachedRealmObject;
        } else {
            be.gershon_lehrer.mybooksathome.model.Author realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Author.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((AuthorRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    realmObject = new io.realm.AuthorRealmProxy(realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Author.class));
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

    public static be.gershon_lehrer.mybooksathome.model.Author copy(Realm realm, be.gershon_lehrer.mybooksathome.model.Author newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (be.gershon_lehrer.mybooksathome.model.Author) cachedRealmObject;
        } else {
            be.gershon_lehrer.mybooksathome.model.Author realmObject = realm.createObject(be.gershon_lehrer.mybooksathome.model.Author.class, ((AuthorRealmProxyInterface) newObject).realmGet$id());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((AuthorRealmProxyInterface) realmObject).realmSet$id(((AuthorRealmProxyInterface) newObject).realmGet$id());
            ((AuthorRealmProxyInterface) realmObject).realmSet$mName(((AuthorRealmProxyInterface) newObject).realmGet$mName());
            return realmObject;
        }
    }

    public static long insert(Realm realm, be.gershon_lehrer.mybooksathome.model.Author object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Author.class);
        long tableNativePtr = table.getNativeTablePointer();
        AuthorColumnInfo columnInfo = (AuthorColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Author.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$id = ((AuthorRealmProxyInterface)object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id);
        }
        String realmGet$mName = ((AuthorRealmProxyInterface)object).realmGet$mName();
        if (realmGet$mName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mNameIndex, rowIndex, realmGet$mName);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Author.class);
        long tableNativePtr = table.getNativeTablePointer();
        AuthorColumnInfo columnInfo = (AuthorColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Author.class);
        be.gershon_lehrer.mybooksathome.model.Author object = null;
        while (objects.hasNext()) {
            object = (be.gershon_lehrer.mybooksathome.model.Author) objects.next();
            if(!cache.containsKey(object)) {
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$id = ((AuthorRealmProxyInterface)object).realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id);
                }
                String realmGet$mName = ((AuthorRealmProxyInterface)object).realmGet$mName();
                if (realmGet$mName != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mNameIndex, rowIndex, realmGet$mName);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, be.gershon_lehrer.mybooksathome.model.Author object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Author.class);
        long tableNativePtr = table.getNativeTablePointer();
        AuthorColumnInfo columnInfo = (AuthorColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Author.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((AuthorRealmProxyInterface) object).realmGet$id();
        long rowIndex = TableOrView.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = table.findFirstNull(pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
            if (primaryKeyValue != null) {
                Table.nativeSetString(tableNativePtr, pkColumnIndex, rowIndex, (String)primaryKeyValue);
            }
        }
        cache.put(object, rowIndex);
        String realmGet$id = ((AuthorRealmProxyInterface)object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex);
        }
        String realmGet$mName = ((AuthorRealmProxyInterface)object).realmGet$mName();
        if (realmGet$mName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mNameIndex, rowIndex, realmGet$mName);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mNameIndex, rowIndex);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Author.class);
        long tableNativePtr = table.getNativeTablePointer();
        AuthorColumnInfo columnInfo = (AuthorColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Author.class);
        long pkColumnIndex = table.getPrimaryKey();
        be.gershon_lehrer.mybooksathome.model.Author object = null;
        while (objects.hasNext()) {
            object = (be.gershon_lehrer.mybooksathome.model.Author) objects.next();
            if(!cache.containsKey(object)) {
                String primaryKeyValue = ((AuthorRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                    if (primaryKeyValue != null) {
                        Table.nativeSetString(tableNativePtr, pkColumnIndex, rowIndex, ((AuthorRealmProxyInterface) object).realmGet$id());
                    }
                }
                cache.put(object, rowIndex);
                String realmGet$id = ((AuthorRealmProxyInterface)object).realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex);
                }
                String realmGet$mName = ((AuthorRealmProxyInterface)object).realmGet$mName();
                if (realmGet$mName != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mNameIndex, rowIndex, realmGet$mName);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mNameIndex, rowIndex);
                }
            }
        }
    }

    public static be.gershon_lehrer.mybooksathome.model.Author createDetachedCopy(be.gershon_lehrer.mybooksathome.model.Author realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        be.gershon_lehrer.mybooksathome.model.Author unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (be.gershon_lehrer.mybooksathome.model.Author)cachedObject.object;
            } else {
                unmanagedObject = (be.gershon_lehrer.mybooksathome.model.Author)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new be.gershon_lehrer.mybooksathome.model.Author();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((AuthorRealmProxyInterface) unmanagedObject).realmSet$id(((AuthorRealmProxyInterface) realmObject).realmGet$id());
        ((AuthorRealmProxyInterface) unmanagedObject).realmSet$mName(((AuthorRealmProxyInterface) realmObject).realmGet$mName());
        return unmanagedObject;
    }

    static be.gershon_lehrer.mybooksathome.model.Author update(Realm realm, be.gershon_lehrer.mybooksathome.model.Author realmObject, be.gershon_lehrer.mybooksathome.model.Author newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((AuthorRealmProxyInterface) realmObject).realmSet$mName(((AuthorRealmProxyInterface) newObject).realmGet$mName());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Author = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mName:");
        stringBuilder.append(realmGet$mName());
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
        AuthorRealmProxy aAuthor = (AuthorRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aAuthor.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aAuthor.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aAuthor.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
