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

public class PublisherRealmProxy extends be.gershon_lehrer.mybooksathome.model.Publisher
    implements RealmObjectProxy, PublisherRealmProxyInterface {

    static final class PublisherColumnInfo extends ColumnInfo {

        public final long mIdIndex;
        public final long mNameIndex;

        PublisherColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(2);
            this.mIdIndex = getValidColumnIndex(path, table, "Publisher", "mId");
            indicesMap.put("mId", this.mIdIndex);

            this.mNameIndex = getValidColumnIndex(path, table, "Publisher", "mName");
            indicesMap.put("mName", this.mNameIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final PublisherColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("mId");
        fieldNames.add("mName");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    PublisherRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (PublisherColumnInfo) columnInfo;
        this.proxyState = new ProxyState(be.gershon_lehrer.mybooksathome.model.Publisher.class, this);
    }

    @SuppressWarnings("cast")
    public String realmGet$mId() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mIdIndex);
    }

    public void realmSet$mId(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mIdIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mIdIndex, value);
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
        if (!transaction.hasTable("class_Publisher")) {
            Table table = transaction.getTable("class_Publisher");
            table.addColumn(RealmFieldType.STRING, "mId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mName", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("mId"));
            table.setPrimaryKey("mId");
            return table;
        }
        return transaction.getTable("class_Publisher");
    }

    public static PublisherColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Publisher")) {
            Table table = transaction.getTable("class_Publisher");
            if (table.getColumnCount() != 2) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 2 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 2; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final PublisherColumnInfo columnInfo = new PublisherColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("mId")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mId' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.mIdIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(),"@PrimaryKey field 'mId' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("mId")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'mId' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("mId"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'mId' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
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
            throw new RealmMigrationNeededException(transaction.getPath(), "The Publisher class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Publisher";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static be.gershon_lehrer.mybooksathome.model.Publisher createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        be.gershon_lehrer.mybooksathome.model.Publisher obj = null;
        if (update) {
            Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Publisher.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (json.isNull("mId")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("mId"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                obj = new io.realm.PublisherRealmProxy(realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Publisher.class));
                ((RealmObjectProxy)obj).realmGet$proxyState().setRealm$realm(realm);
                ((RealmObjectProxy)obj).realmGet$proxyState().setRow$realm(table.getUncheckedRow(rowIndex));
            }
        }
        if (obj == null) {
            if (json.has("mId")) {
                if (json.isNull("mId")) {
                    obj = (io.realm.PublisherRealmProxy) realm.createObject(be.gershon_lehrer.mybooksathome.model.Publisher.class, null);
                } else {
                    obj = (io.realm.PublisherRealmProxy) realm.createObject(be.gershon_lehrer.mybooksathome.model.Publisher.class, json.getString("mId"));
                }
            } else {
                obj = (io.realm.PublisherRealmProxy) realm.createObject(be.gershon_lehrer.mybooksathome.model.Publisher.class);
            }
        }
        if (json.has("mId")) {
            if (json.isNull("mId")) {
                ((PublisherRealmProxyInterface) obj).realmSet$mId(null);
            } else {
                ((PublisherRealmProxyInterface) obj).realmSet$mId((String) json.getString("mId"));
            }
        }
        if (json.has("mName")) {
            if (json.isNull("mName")) {
                ((PublisherRealmProxyInterface) obj).realmSet$mName(null);
            } else {
                ((PublisherRealmProxyInterface) obj).realmSet$mName((String) json.getString("mName"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static be.gershon_lehrer.mybooksathome.model.Publisher createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        be.gershon_lehrer.mybooksathome.model.Publisher obj = realm.createObject(be.gershon_lehrer.mybooksathome.model.Publisher.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("mId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PublisherRealmProxyInterface) obj).realmSet$mId(null);
                } else {
                    ((PublisherRealmProxyInterface) obj).realmSet$mId((String) reader.nextString());
                }
            } else if (name.equals("mName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PublisherRealmProxyInterface) obj).realmSet$mName(null);
                } else {
                    ((PublisherRealmProxyInterface) obj).realmSet$mName((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static be.gershon_lehrer.mybooksathome.model.Publisher copyOrUpdate(Realm realm, be.gershon_lehrer.mybooksathome.model.Publisher object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (be.gershon_lehrer.mybooksathome.model.Publisher) cachedRealmObject;
        } else {
            be.gershon_lehrer.mybooksathome.model.Publisher realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Publisher.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((PublisherRealmProxyInterface) object).realmGet$mId();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    realmObject = new io.realm.PublisherRealmProxy(realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Publisher.class));
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

    public static be.gershon_lehrer.mybooksathome.model.Publisher copy(Realm realm, be.gershon_lehrer.mybooksathome.model.Publisher newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (be.gershon_lehrer.mybooksathome.model.Publisher) cachedRealmObject;
        } else {
            be.gershon_lehrer.mybooksathome.model.Publisher realmObject = realm.createObject(be.gershon_lehrer.mybooksathome.model.Publisher.class, ((PublisherRealmProxyInterface) newObject).realmGet$mId());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((PublisherRealmProxyInterface) realmObject).realmSet$mId(((PublisherRealmProxyInterface) newObject).realmGet$mId());
            ((PublisherRealmProxyInterface) realmObject).realmSet$mName(((PublisherRealmProxyInterface) newObject).realmGet$mName());
            return realmObject;
        }
    }

    public static long insert(Realm realm, be.gershon_lehrer.mybooksathome.model.Publisher object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Publisher.class);
        long tableNativePtr = table.getNativeTablePointer();
        PublisherColumnInfo columnInfo = (PublisherColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Publisher.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$mId = ((PublisherRealmProxyInterface)object).realmGet$mId();
        if (realmGet$mId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mIdIndex, rowIndex, realmGet$mId);
        }
        String realmGet$mName = ((PublisherRealmProxyInterface)object).realmGet$mName();
        if (realmGet$mName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mNameIndex, rowIndex, realmGet$mName);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Publisher.class);
        long tableNativePtr = table.getNativeTablePointer();
        PublisherColumnInfo columnInfo = (PublisherColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Publisher.class);
        be.gershon_lehrer.mybooksathome.model.Publisher object = null;
        while (objects.hasNext()) {
            object = (be.gershon_lehrer.mybooksathome.model.Publisher) objects.next();
            if(!cache.containsKey(object)) {
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$mId = ((PublisherRealmProxyInterface)object).realmGet$mId();
                if (realmGet$mId != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mIdIndex, rowIndex, realmGet$mId);
                }
                String realmGet$mName = ((PublisherRealmProxyInterface)object).realmGet$mName();
                if (realmGet$mName != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mNameIndex, rowIndex, realmGet$mName);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, be.gershon_lehrer.mybooksathome.model.Publisher object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Publisher.class);
        long tableNativePtr = table.getNativeTablePointer();
        PublisherColumnInfo columnInfo = (PublisherColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Publisher.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((PublisherRealmProxyInterface) object).realmGet$mId();
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
        String realmGet$mId = ((PublisherRealmProxyInterface)object).realmGet$mId();
        if (realmGet$mId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mIdIndex, rowIndex, realmGet$mId);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mIdIndex, rowIndex);
        }
        String realmGet$mName = ((PublisherRealmProxyInterface)object).realmGet$mName();
        if (realmGet$mName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mNameIndex, rowIndex, realmGet$mName);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mNameIndex, rowIndex);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Publisher.class);
        long tableNativePtr = table.getNativeTablePointer();
        PublisherColumnInfo columnInfo = (PublisherColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Publisher.class);
        long pkColumnIndex = table.getPrimaryKey();
        be.gershon_lehrer.mybooksathome.model.Publisher object = null;
        while (objects.hasNext()) {
            object = (be.gershon_lehrer.mybooksathome.model.Publisher) objects.next();
            if(!cache.containsKey(object)) {
                String primaryKeyValue = ((PublisherRealmProxyInterface) object).realmGet$mId();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                    if (primaryKeyValue != null) {
                        Table.nativeSetString(tableNativePtr, pkColumnIndex, rowIndex, ((PublisherRealmProxyInterface) object).realmGet$mId());
                    }
                }
                cache.put(object, rowIndex);
                String realmGet$mId = ((PublisherRealmProxyInterface)object).realmGet$mId();
                if (realmGet$mId != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mIdIndex, rowIndex, realmGet$mId);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mIdIndex, rowIndex);
                }
                String realmGet$mName = ((PublisherRealmProxyInterface)object).realmGet$mName();
                if (realmGet$mName != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mNameIndex, rowIndex, realmGet$mName);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mNameIndex, rowIndex);
                }
            }
        }
    }

    public static be.gershon_lehrer.mybooksathome.model.Publisher createDetachedCopy(be.gershon_lehrer.mybooksathome.model.Publisher realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        be.gershon_lehrer.mybooksathome.model.Publisher unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (be.gershon_lehrer.mybooksathome.model.Publisher)cachedObject.object;
            } else {
                unmanagedObject = (be.gershon_lehrer.mybooksathome.model.Publisher)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new be.gershon_lehrer.mybooksathome.model.Publisher();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((PublisherRealmProxyInterface) unmanagedObject).realmSet$mId(((PublisherRealmProxyInterface) realmObject).realmGet$mId());
        ((PublisherRealmProxyInterface) unmanagedObject).realmSet$mName(((PublisherRealmProxyInterface) realmObject).realmGet$mName());
        return unmanagedObject;
    }

    static be.gershon_lehrer.mybooksathome.model.Publisher update(Realm realm, be.gershon_lehrer.mybooksathome.model.Publisher realmObject, be.gershon_lehrer.mybooksathome.model.Publisher newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((PublisherRealmProxyInterface) realmObject).realmSet$mName(((PublisherRealmProxyInterface) newObject).realmGet$mName());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Publisher = [");
        stringBuilder.append("{mId:");
        stringBuilder.append(realmGet$mId() != null ? realmGet$mId() : "null");
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
        PublisherRealmProxy aPublisher = (PublisherRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aPublisher.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aPublisher.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aPublisher.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
