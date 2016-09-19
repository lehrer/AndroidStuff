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

public class BookRealmProxy extends be.gershon_lehrer.mybooksathome.model.Book
    implements RealmObjectProxy, BookRealmProxyInterface {

    static final class BookColumnInfo extends ColumnInfo {

        public final long idIndex;
        public final long mISBNIndex;
        public final long mSubjectIndex;
        public final long mGoogleIDIndex;
        public final long mTitleIndex;
        public final long mDescriptionIndex;
        public final long mThumbnailURLIndex;
        public final long mAuthorsIndex;
        public final long mPublishersIndex;
        public final long mValueOfBookStatusIndex;
        public final long mReadingProgressIndex;
        public final long mPreviewLinkIndex;
        public final long mValueOfBookTypeIndex;

        BookColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(13);
            this.idIndex = getValidColumnIndex(path, table, "Book", "id");
            indicesMap.put("id", this.idIndex);

            this.mISBNIndex = getValidColumnIndex(path, table, "Book", "mISBN");
            indicesMap.put("mISBN", this.mISBNIndex);

            this.mSubjectIndex = getValidColumnIndex(path, table, "Book", "mSubject");
            indicesMap.put("mSubject", this.mSubjectIndex);

            this.mGoogleIDIndex = getValidColumnIndex(path, table, "Book", "mGoogleID");
            indicesMap.put("mGoogleID", this.mGoogleIDIndex);

            this.mTitleIndex = getValidColumnIndex(path, table, "Book", "mTitle");
            indicesMap.put("mTitle", this.mTitleIndex);

            this.mDescriptionIndex = getValidColumnIndex(path, table, "Book", "mDescription");
            indicesMap.put("mDescription", this.mDescriptionIndex);

            this.mThumbnailURLIndex = getValidColumnIndex(path, table, "Book", "mThumbnailURL");
            indicesMap.put("mThumbnailURL", this.mThumbnailURLIndex);

            this.mAuthorsIndex = getValidColumnIndex(path, table, "Book", "mAuthors");
            indicesMap.put("mAuthors", this.mAuthorsIndex);

            this.mPublishersIndex = getValidColumnIndex(path, table, "Book", "mPublishers");
            indicesMap.put("mPublishers", this.mPublishersIndex);

            this.mValueOfBookStatusIndex = getValidColumnIndex(path, table, "Book", "mValueOfBookStatus");
            indicesMap.put("mValueOfBookStatus", this.mValueOfBookStatusIndex);

            this.mReadingProgressIndex = getValidColumnIndex(path, table, "Book", "mReadingProgress");
            indicesMap.put("mReadingProgress", this.mReadingProgressIndex);

            this.mPreviewLinkIndex = getValidColumnIndex(path, table, "Book", "mPreviewLink");
            indicesMap.put("mPreviewLink", this.mPreviewLinkIndex);

            this.mValueOfBookTypeIndex = getValidColumnIndex(path, table, "Book", "mValueOfBookType");
            indicesMap.put("mValueOfBookType", this.mValueOfBookTypeIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final BookColumnInfo columnInfo;
    private final ProxyState proxyState;
    private RealmList<be.gershon_lehrer.mybooksathome.model.Author> mAuthorsRealmList;
    private RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> mPublishersRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("mISBN");
        fieldNames.add("mSubject");
        fieldNames.add("mGoogleID");
        fieldNames.add("mTitle");
        fieldNames.add("mDescription");
        fieldNames.add("mThumbnailURL");
        fieldNames.add("mAuthors");
        fieldNames.add("mPublishers");
        fieldNames.add("mValueOfBookStatus");
        fieldNames.add("mReadingProgress");
        fieldNames.add("mPreviewLink");
        fieldNames.add("mValueOfBookType");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    BookRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (BookColumnInfo) columnInfo;
        this.proxyState = new ProxyState(be.gershon_lehrer.mybooksathome.model.Book.class, this);
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
    public String realmGet$mISBN() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mISBNIndex);
    }

    public void realmSet$mISBN(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mISBNIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mISBNIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$mSubject() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mSubjectIndex);
    }

    public void realmSet$mSubject(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mSubjectIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mSubjectIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$mGoogleID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mGoogleIDIndex);
    }

    public void realmSet$mGoogleID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mGoogleIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mGoogleIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$mTitle() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mTitleIndex);
    }

    public void realmSet$mTitle(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field mTitle to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.mTitleIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$mDescription() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mDescriptionIndex);
    }

    public void realmSet$mDescription(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mDescriptionIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mDescriptionIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$mThumbnailURL() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mThumbnailURLIndex);
    }

    public void realmSet$mThumbnailURL(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mThumbnailURLIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mThumbnailURLIndex, value);
    }

    public RealmList<be.gershon_lehrer.mybooksathome.model.Author> realmGet$mAuthors() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (mAuthorsRealmList != null) {
            return mAuthorsRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.mAuthorsIndex);
            mAuthorsRealmList = new RealmList<be.gershon_lehrer.mybooksathome.model.Author>(be.gershon_lehrer.mybooksathome.model.Author.class, linkView, proxyState.getRealm$realm());
            return mAuthorsRealmList;
        }
    }

    public void realmSet$mAuthors(RealmList<be.gershon_lehrer.mybooksathome.model.Author> value) {
        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.mAuthorsIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmModel linkedObject : (RealmList<? extends RealmModel>) value) {
            if (!RealmObject.isValid(linkedObject)) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (((RealmObjectProxy)linkedObject).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObjectProxy)linkedObject).realmGet$proxyState().getRow$realm().getIndex());
        }
    }

    public RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> realmGet$mPublishers() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (mPublishersRealmList != null) {
            return mPublishersRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.mPublishersIndex);
            mPublishersRealmList = new RealmList<be.gershon_lehrer.mybooksathome.model.Publisher>(be.gershon_lehrer.mybooksathome.model.Publisher.class, linkView, proxyState.getRealm$realm());
            return mPublishersRealmList;
        }
    }

    public void realmSet$mPublishers(RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> value) {
        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.mPublishersIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmModel linkedObject : (RealmList<? extends RealmModel>) value) {
            if (!RealmObject.isValid(linkedObject)) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (((RealmObjectProxy)linkedObject).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObjectProxy)linkedObject).realmGet$proxyState().getRow$realm().getIndex());
        }
    }

    @SuppressWarnings("cast")
    public String realmGet$mValueOfBookStatus() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mValueOfBookStatusIndex);
    }

    public void realmSet$mValueOfBookStatus(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mValueOfBookStatusIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mValueOfBookStatusIndex, value);
    }

    @SuppressWarnings("cast")
    public int realmGet$mReadingProgress() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.mReadingProgressIndex);
    }

    public void realmSet$mReadingProgress(int value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.mReadingProgressIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$mPreviewLink() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mPreviewLinkIndex);
    }

    public void realmSet$mPreviewLink(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mPreviewLinkIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mPreviewLinkIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$mValueOfBookType() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mValueOfBookTypeIndex);
    }

    public void realmSet$mValueOfBookType(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mValueOfBookTypeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mValueOfBookTypeIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Book")) {
            Table table = transaction.getTable("class_Book");
            table.addColumn(RealmFieldType.STRING, "id", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mISBN", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mSubject", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mGoogleID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mTitle", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mDescription", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mThumbnailURL", Table.NULLABLE);
            if (!transaction.hasTable("class_Author")) {
                AuthorRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "mAuthors", transaction.getTable("class_Author"));
            if (!transaction.hasTable("class_Publisher")) {
                PublisherRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "mPublishers", transaction.getTable("class_Publisher"));
            table.addColumn(RealmFieldType.STRING, "mValueOfBookStatus", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "mReadingProgress", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mPreviewLink", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mValueOfBookType", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return transaction.getTable("class_Book");
    }

    public static BookColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Book")) {
            Table table = transaction.getTable("class_Book");
            if (table.getColumnCount() != 13) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 13 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 13; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final BookColumnInfo columnInfo = new BookColumnInfo(transaction.getPath(), table);

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
            if (!columnTypes.containsKey("mISBN")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mISBN' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mISBN") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mISBN' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.mISBNIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mISBN' is required. Either set @Required to field 'mISBN' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("mSubject")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mSubject' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mSubject") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mSubject' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.mSubjectIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mSubject' is required. Either set @Required to field 'mSubject' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("mGoogleID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mGoogleID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mGoogleID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mGoogleID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.mGoogleIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mGoogleID' is required. Either set @Required to field 'mGoogleID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("mTitle")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mTitle' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mTitle") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mTitle' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.mTitleIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mTitle' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'mTitle' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("mDescription")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mDescription' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mDescription") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mDescription' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.mDescriptionIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mDescription' is required. Either set @Required to field 'mDescription' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("mThumbnailURL")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mThumbnailURL' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mThumbnailURL") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mThumbnailURL' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.mThumbnailURLIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mThumbnailURL' is required. Either set @Required to field 'mThumbnailURL' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("mAuthors")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mAuthors'");
            }
            if (columnTypes.get("mAuthors") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'Author' for field 'mAuthors'");
            }
            if (!transaction.hasTable("class_Author")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_Author' for field 'mAuthors'");
            }
            Table table_7 = transaction.getTable("class_Author");
            if (!table.getLinkTarget(columnInfo.mAuthorsIndex).hasSameSchema(table_7)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'mAuthors': '" + table.getLinkTarget(columnInfo.mAuthorsIndex).getName() + "' expected - was '" + table_7.getName() + "'");
            }
            if (!columnTypes.containsKey("mPublishers")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mPublishers'");
            }
            if (columnTypes.get("mPublishers") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'Publisher' for field 'mPublishers'");
            }
            if (!transaction.hasTable("class_Publisher")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_Publisher' for field 'mPublishers'");
            }
            Table table_8 = transaction.getTable("class_Publisher");
            if (!table.getLinkTarget(columnInfo.mPublishersIndex).hasSameSchema(table_8)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'mPublishers': '" + table.getLinkTarget(columnInfo.mPublishersIndex).getName() + "' expected - was '" + table_8.getName() + "'");
            }
            if (!columnTypes.containsKey("mValueOfBookStatus")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mValueOfBookStatus' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mValueOfBookStatus") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mValueOfBookStatus' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.mValueOfBookStatusIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mValueOfBookStatus' is required. Either set @Required to field 'mValueOfBookStatus' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("mReadingProgress")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mReadingProgress' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mReadingProgress") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'mReadingProgress' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.mReadingProgressIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mReadingProgress' does support null values in the existing Realm file. Use corresponding boxed type for field 'mReadingProgress' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("mPreviewLink")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mPreviewLink' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mPreviewLink") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mPreviewLink' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.mPreviewLinkIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mPreviewLink' is required. Either set @Required to field 'mPreviewLink' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("mValueOfBookType")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mValueOfBookType' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mValueOfBookType") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mValueOfBookType' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.mValueOfBookTypeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mValueOfBookType' is required. Either set @Required to field 'mValueOfBookType' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Book class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Book";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static be.gershon_lehrer.mybooksathome.model.Book createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        be.gershon_lehrer.mybooksathome.model.Book obj = null;
        if (update) {
            Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Book.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (json.isNull("id")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                obj = new io.realm.BookRealmProxy(realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Book.class));
                ((RealmObjectProxy)obj).realmGet$proxyState().setRealm$realm(realm);
                ((RealmObjectProxy)obj).realmGet$proxyState().setRow$realm(table.getUncheckedRow(rowIndex));
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.BookRealmProxy) realm.createObject(be.gershon_lehrer.mybooksathome.model.Book.class, null);
                } else {
                    obj = (io.realm.BookRealmProxy) realm.createObject(be.gershon_lehrer.mybooksathome.model.Book.class, json.getString("id"));
                }
            } else {
                obj = (io.realm.BookRealmProxy) realm.createObject(be.gershon_lehrer.mybooksathome.model.Book.class);
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                ((BookRealmProxyInterface) obj).realmSet$id(null);
            } else {
                ((BookRealmProxyInterface) obj).realmSet$id((String) json.getString("id"));
            }
        }
        if (json.has("mISBN")) {
            if (json.isNull("mISBN")) {
                ((BookRealmProxyInterface) obj).realmSet$mISBN(null);
            } else {
                ((BookRealmProxyInterface) obj).realmSet$mISBN((String) json.getString("mISBN"));
            }
        }
        if (json.has("mSubject")) {
            if (json.isNull("mSubject")) {
                ((BookRealmProxyInterface) obj).realmSet$mSubject(null);
            } else {
                ((BookRealmProxyInterface) obj).realmSet$mSubject((String) json.getString("mSubject"));
            }
        }
        if (json.has("mGoogleID")) {
            if (json.isNull("mGoogleID")) {
                ((BookRealmProxyInterface) obj).realmSet$mGoogleID(null);
            } else {
                ((BookRealmProxyInterface) obj).realmSet$mGoogleID((String) json.getString("mGoogleID"));
            }
        }
        if (json.has("mTitle")) {
            if (json.isNull("mTitle")) {
                ((BookRealmProxyInterface) obj).realmSet$mTitle(null);
            } else {
                ((BookRealmProxyInterface) obj).realmSet$mTitle((String) json.getString("mTitle"));
            }
        }
        if (json.has("mDescription")) {
            if (json.isNull("mDescription")) {
                ((BookRealmProxyInterface) obj).realmSet$mDescription(null);
            } else {
                ((BookRealmProxyInterface) obj).realmSet$mDescription((String) json.getString("mDescription"));
            }
        }
        if (json.has("mThumbnailURL")) {
            if (json.isNull("mThumbnailURL")) {
                ((BookRealmProxyInterface) obj).realmSet$mThumbnailURL(null);
            } else {
                ((BookRealmProxyInterface) obj).realmSet$mThumbnailURL((String) json.getString("mThumbnailURL"));
            }
        }
        if (json.has("mAuthors")) {
            if (json.isNull("mAuthors")) {
                ((BookRealmProxyInterface) obj).realmSet$mAuthors(null);
            } else {
                ((BookRealmProxyInterface) obj).realmGet$mAuthors().clear();
                JSONArray array = json.getJSONArray("mAuthors");
                for (int i = 0; i < array.length(); i++) {
                    be.gershon_lehrer.mybooksathome.model.Author item = AuthorRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((BookRealmProxyInterface) obj).realmGet$mAuthors().add(item);
                }
            }
        }
        if (json.has("mPublishers")) {
            if (json.isNull("mPublishers")) {
                ((BookRealmProxyInterface) obj).realmSet$mPublishers(null);
            } else {
                ((BookRealmProxyInterface) obj).realmGet$mPublishers().clear();
                JSONArray array = json.getJSONArray("mPublishers");
                for (int i = 0; i < array.length(); i++) {
                    be.gershon_lehrer.mybooksathome.model.Publisher item = PublisherRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((BookRealmProxyInterface) obj).realmGet$mPublishers().add(item);
                }
            }
        }
        if (json.has("mValueOfBookStatus")) {
            if (json.isNull("mValueOfBookStatus")) {
                ((BookRealmProxyInterface) obj).realmSet$mValueOfBookStatus(null);
            } else {
                ((BookRealmProxyInterface) obj).realmSet$mValueOfBookStatus((String) json.getString("mValueOfBookStatus"));
            }
        }
        if (json.has("mReadingProgress")) {
            if (json.isNull("mReadingProgress")) {
                throw new IllegalArgumentException("Trying to set non-nullable field mReadingProgress to null.");
            } else {
                ((BookRealmProxyInterface) obj).realmSet$mReadingProgress((int) json.getInt("mReadingProgress"));
            }
        }
        if (json.has("mPreviewLink")) {
            if (json.isNull("mPreviewLink")) {
                ((BookRealmProxyInterface) obj).realmSet$mPreviewLink(null);
            } else {
                ((BookRealmProxyInterface) obj).realmSet$mPreviewLink((String) json.getString("mPreviewLink"));
            }
        }
        if (json.has("mValueOfBookType")) {
            if (json.isNull("mValueOfBookType")) {
                ((BookRealmProxyInterface) obj).realmSet$mValueOfBookType(null);
            } else {
                ((BookRealmProxyInterface) obj).realmSet$mValueOfBookType((String) json.getString("mValueOfBookType"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static be.gershon_lehrer.mybooksathome.model.Book createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        be.gershon_lehrer.mybooksathome.model.Book obj = realm.createObject(be.gershon_lehrer.mybooksathome.model.Book.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((BookRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((BookRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
            } else if (name.equals("mISBN")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((BookRealmProxyInterface) obj).realmSet$mISBN(null);
                } else {
                    ((BookRealmProxyInterface) obj).realmSet$mISBN((String) reader.nextString());
                }
            } else if (name.equals("mSubject")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((BookRealmProxyInterface) obj).realmSet$mSubject(null);
                } else {
                    ((BookRealmProxyInterface) obj).realmSet$mSubject((String) reader.nextString());
                }
            } else if (name.equals("mGoogleID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((BookRealmProxyInterface) obj).realmSet$mGoogleID(null);
                } else {
                    ((BookRealmProxyInterface) obj).realmSet$mGoogleID((String) reader.nextString());
                }
            } else if (name.equals("mTitle")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((BookRealmProxyInterface) obj).realmSet$mTitle(null);
                } else {
                    ((BookRealmProxyInterface) obj).realmSet$mTitle((String) reader.nextString());
                }
            } else if (name.equals("mDescription")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((BookRealmProxyInterface) obj).realmSet$mDescription(null);
                } else {
                    ((BookRealmProxyInterface) obj).realmSet$mDescription((String) reader.nextString());
                }
            } else if (name.equals("mThumbnailURL")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((BookRealmProxyInterface) obj).realmSet$mThumbnailURL(null);
                } else {
                    ((BookRealmProxyInterface) obj).realmSet$mThumbnailURL((String) reader.nextString());
                }
            } else if (name.equals("mAuthors")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((BookRealmProxyInterface) obj).realmSet$mAuthors(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        be.gershon_lehrer.mybooksathome.model.Author item = AuthorRealmProxy.createUsingJsonStream(realm, reader);
                        ((BookRealmProxyInterface) obj).realmGet$mAuthors().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("mPublishers")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((BookRealmProxyInterface) obj).realmSet$mPublishers(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        be.gershon_lehrer.mybooksathome.model.Publisher item = PublisherRealmProxy.createUsingJsonStream(realm, reader);
                        ((BookRealmProxyInterface) obj).realmGet$mPublishers().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("mValueOfBookStatus")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((BookRealmProxyInterface) obj).realmSet$mValueOfBookStatus(null);
                } else {
                    ((BookRealmProxyInterface) obj).realmSet$mValueOfBookStatus((String) reader.nextString());
                }
            } else if (name.equals("mReadingProgress")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field mReadingProgress to null.");
                } else {
                    ((BookRealmProxyInterface) obj).realmSet$mReadingProgress((int) reader.nextInt());
                }
            } else if (name.equals("mPreviewLink")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((BookRealmProxyInterface) obj).realmSet$mPreviewLink(null);
                } else {
                    ((BookRealmProxyInterface) obj).realmSet$mPreviewLink((String) reader.nextString());
                }
            } else if (name.equals("mValueOfBookType")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((BookRealmProxyInterface) obj).realmSet$mValueOfBookType(null);
                } else {
                    ((BookRealmProxyInterface) obj).realmSet$mValueOfBookType((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static be.gershon_lehrer.mybooksathome.model.Book copyOrUpdate(Realm realm, be.gershon_lehrer.mybooksathome.model.Book object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (be.gershon_lehrer.mybooksathome.model.Book) cachedRealmObject;
        } else {
            be.gershon_lehrer.mybooksathome.model.Book realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Book.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((BookRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    realmObject = new io.realm.BookRealmProxy(realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Book.class));
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

    public static be.gershon_lehrer.mybooksathome.model.Book copy(Realm realm, be.gershon_lehrer.mybooksathome.model.Book newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (be.gershon_lehrer.mybooksathome.model.Book) cachedRealmObject;
        } else {
            be.gershon_lehrer.mybooksathome.model.Book realmObject = realm.createObject(be.gershon_lehrer.mybooksathome.model.Book.class, ((BookRealmProxyInterface) newObject).realmGet$id());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((BookRealmProxyInterface) realmObject).realmSet$id(((BookRealmProxyInterface) newObject).realmGet$id());
            ((BookRealmProxyInterface) realmObject).realmSet$mISBN(((BookRealmProxyInterface) newObject).realmGet$mISBN());
            ((BookRealmProxyInterface) realmObject).realmSet$mSubject(((BookRealmProxyInterface) newObject).realmGet$mSubject());
            ((BookRealmProxyInterface) realmObject).realmSet$mGoogleID(((BookRealmProxyInterface) newObject).realmGet$mGoogleID());
            ((BookRealmProxyInterface) realmObject).realmSet$mTitle(((BookRealmProxyInterface) newObject).realmGet$mTitle());
            ((BookRealmProxyInterface) realmObject).realmSet$mDescription(((BookRealmProxyInterface) newObject).realmGet$mDescription());
            ((BookRealmProxyInterface) realmObject).realmSet$mThumbnailURL(((BookRealmProxyInterface) newObject).realmGet$mThumbnailURL());

            RealmList<be.gershon_lehrer.mybooksathome.model.Author> mAuthorsList = ((BookRealmProxyInterface) newObject).realmGet$mAuthors();
            if (mAuthorsList != null) {
                RealmList<be.gershon_lehrer.mybooksathome.model.Author> mAuthorsRealmList = ((BookRealmProxyInterface) realmObject).realmGet$mAuthors();
                for (int i = 0; i < mAuthorsList.size(); i++) {
                    be.gershon_lehrer.mybooksathome.model.Author mAuthorsItem = mAuthorsList.get(i);
                    be.gershon_lehrer.mybooksathome.model.Author cachemAuthors = (be.gershon_lehrer.mybooksathome.model.Author) cache.get(mAuthorsItem);
                    if (cachemAuthors != null) {
                        mAuthorsRealmList.add(cachemAuthors);
                    } else {
                        mAuthorsRealmList.add(AuthorRealmProxy.copyOrUpdate(realm, mAuthorsList.get(i), update, cache));
                    }
                }
            }


            RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> mPublishersList = ((BookRealmProxyInterface) newObject).realmGet$mPublishers();
            if (mPublishersList != null) {
                RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> mPublishersRealmList = ((BookRealmProxyInterface) realmObject).realmGet$mPublishers();
                for (int i = 0; i < mPublishersList.size(); i++) {
                    be.gershon_lehrer.mybooksathome.model.Publisher mPublishersItem = mPublishersList.get(i);
                    be.gershon_lehrer.mybooksathome.model.Publisher cachemPublishers = (be.gershon_lehrer.mybooksathome.model.Publisher) cache.get(mPublishersItem);
                    if (cachemPublishers != null) {
                        mPublishersRealmList.add(cachemPublishers);
                    } else {
                        mPublishersRealmList.add(PublisherRealmProxy.copyOrUpdate(realm, mPublishersList.get(i), update, cache));
                    }
                }
            }

            ((BookRealmProxyInterface) realmObject).realmSet$mValueOfBookStatus(((BookRealmProxyInterface) newObject).realmGet$mValueOfBookStatus());
            ((BookRealmProxyInterface) realmObject).realmSet$mReadingProgress(((BookRealmProxyInterface) newObject).realmGet$mReadingProgress());
            ((BookRealmProxyInterface) realmObject).realmSet$mPreviewLink(((BookRealmProxyInterface) newObject).realmGet$mPreviewLink());
            ((BookRealmProxyInterface) realmObject).realmSet$mValueOfBookType(((BookRealmProxyInterface) newObject).realmGet$mValueOfBookType());
            return realmObject;
        }
    }

    public static long insert(Realm realm, be.gershon_lehrer.mybooksathome.model.Book object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Book.class);
        long tableNativePtr = table.getNativeTablePointer();
        BookColumnInfo columnInfo = (BookColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Book.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$id = ((BookRealmProxyInterface)object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id);
        }
        String realmGet$mISBN = ((BookRealmProxyInterface)object).realmGet$mISBN();
        if (realmGet$mISBN != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mISBNIndex, rowIndex, realmGet$mISBN);
        }
        String realmGet$mSubject = ((BookRealmProxyInterface)object).realmGet$mSubject();
        if (realmGet$mSubject != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mSubjectIndex, rowIndex, realmGet$mSubject);
        }
        String realmGet$mGoogleID = ((BookRealmProxyInterface)object).realmGet$mGoogleID();
        if (realmGet$mGoogleID != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mGoogleIDIndex, rowIndex, realmGet$mGoogleID);
        }
        String realmGet$mTitle = ((BookRealmProxyInterface)object).realmGet$mTitle();
        if (realmGet$mTitle != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mTitleIndex, rowIndex, realmGet$mTitle);
        }
        String realmGet$mDescription = ((BookRealmProxyInterface)object).realmGet$mDescription();
        if (realmGet$mDescription != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mDescriptionIndex, rowIndex, realmGet$mDescription);
        }
        String realmGet$mThumbnailURL = ((BookRealmProxyInterface)object).realmGet$mThumbnailURL();
        if (realmGet$mThumbnailURL != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mThumbnailURLIndex, rowIndex, realmGet$mThumbnailURL);
        }

        RealmList<be.gershon_lehrer.mybooksathome.model.Author> mAuthorsList = ((BookRealmProxyInterface) object).realmGet$mAuthors();
        if (mAuthorsList != null) {
            long mAuthorsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.mAuthorsIndex, rowIndex);
            for (be.gershon_lehrer.mybooksathome.model.Author mAuthorsItem : mAuthorsList) {
                Long cacheItemIndexmAuthors = cache.get(mAuthorsItem);
                if (cacheItemIndexmAuthors == null) {
                    cacheItemIndexmAuthors = AuthorRealmProxy.insert(realm, mAuthorsItem, cache);
                }
                LinkView.nativeAdd(mAuthorsNativeLinkViewPtr, cacheItemIndexmAuthors);
            }
            LinkView.nativeClose(mAuthorsNativeLinkViewPtr);
        }


        RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> mPublishersList = ((BookRealmProxyInterface) object).realmGet$mPublishers();
        if (mPublishersList != null) {
            long mPublishersNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.mPublishersIndex, rowIndex);
            for (be.gershon_lehrer.mybooksathome.model.Publisher mPublishersItem : mPublishersList) {
                Long cacheItemIndexmPublishers = cache.get(mPublishersItem);
                if (cacheItemIndexmPublishers == null) {
                    cacheItemIndexmPublishers = PublisherRealmProxy.insert(realm, mPublishersItem, cache);
                }
                LinkView.nativeAdd(mPublishersNativeLinkViewPtr, cacheItemIndexmPublishers);
            }
            LinkView.nativeClose(mPublishersNativeLinkViewPtr);
        }

        String realmGet$mValueOfBookStatus = ((BookRealmProxyInterface)object).realmGet$mValueOfBookStatus();
        if (realmGet$mValueOfBookStatus != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mValueOfBookStatusIndex, rowIndex, realmGet$mValueOfBookStatus);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.mReadingProgressIndex, rowIndex, ((BookRealmProxyInterface)object).realmGet$mReadingProgress());
        String realmGet$mPreviewLink = ((BookRealmProxyInterface)object).realmGet$mPreviewLink();
        if (realmGet$mPreviewLink != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mPreviewLinkIndex, rowIndex, realmGet$mPreviewLink);
        }
        String realmGet$mValueOfBookType = ((BookRealmProxyInterface)object).realmGet$mValueOfBookType();
        if (realmGet$mValueOfBookType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mValueOfBookTypeIndex, rowIndex, realmGet$mValueOfBookType);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Book.class);
        long tableNativePtr = table.getNativeTablePointer();
        BookColumnInfo columnInfo = (BookColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Book.class);
        be.gershon_lehrer.mybooksathome.model.Book object = null;
        while (objects.hasNext()) {
            object = (be.gershon_lehrer.mybooksathome.model.Book) objects.next();
            if(!cache.containsKey(object)) {
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$id = ((BookRealmProxyInterface)object).realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id);
                }
                String realmGet$mISBN = ((BookRealmProxyInterface)object).realmGet$mISBN();
                if (realmGet$mISBN != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mISBNIndex, rowIndex, realmGet$mISBN);
                }
                String realmGet$mSubject = ((BookRealmProxyInterface)object).realmGet$mSubject();
                if (realmGet$mSubject != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mSubjectIndex, rowIndex, realmGet$mSubject);
                }
                String realmGet$mGoogleID = ((BookRealmProxyInterface)object).realmGet$mGoogleID();
                if (realmGet$mGoogleID != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mGoogleIDIndex, rowIndex, realmGet$mGoogleID);
                }
                String realmGet$mTitle = ((BookRealmProxyInterface)object).realmGet$mTitle();
                if (realmGet$mTitle != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mTitleIndex, rowIndex, realmGet$mTitle);
                }
                String realmGet$mDescription = ((BookRealmProxyInterface)object).realmGet$mDescription();
                if (realmGet$mDescription != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mDescriptionIndex, rowIndex, realmGet$mDescription);
                }
                String realmGet$mThumbnailURL = ((BookRealmProxyInterface)object).realmGet$mThumbnailURL();
                if (realmGet$mThumbnailURL != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mThumbnailURLIndex, rowIndex, realmGet$mThumbnailURL);
                }

                RealmList<be.gershon_lehrer.mybooksathome.model.Author> mAuthorsList = ((BookRealmProxyInterface) object).realmGet$mAuthors();
                if (mAuthorsList != null) {
                    long mAuthorsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.mAuthorsIndex, rowIndex);
                    for (be.gershon_lehrer.mybooksathome.model.Author mAuthorsItem : mAuthorsList) {
                        Long cacheItemIndexmAuthors = cache.get(mAuthorsItem);
                        if (cacheItemIndexmAuthors == null) {
                            cacheItemIndexmAuthors = AuthorRealmProxy.insert(realm, mAuthorsItem, cache);
                        }
                        LinkView.nativeAdd(mAuthorsNativeLinkViewPtr, cacheItemIndexmAuthors);
                    }
                    LinkView.nativeClose(mAuthorsNativeLinkViewPtr);
                }


                RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> mPublishersList = ((BookRealmProxyInterface) object).realmGet$mPublishers();
                if (mPublishersList != null) {
                    long mPublishersNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.mPublishersIndex, rowIndex);
                    for (be.gershon_lehrer.mybooksathome.model.Publisher mPublishersItem : mPublishersList) {
                        Long cacheItemIndexmPublishers = cache.get(mPublishersItem);
                        if (cacheItemIndexmPublishers == null) {
                            cacheItemIndexmPublishers = PublisherRealmProxy.insert(realm, mPublishersItem, cache);
                        }
                        LinkView.nativeAdd(mPublishersNativeLinkViewPtr, cacheItemIndexmPublishers);
                    }
                    LinkView.nativeClose(mPublishersNativeLinkViewPtr);
                }

                String realmGet$mValueOfBookStatus = ((BookRealmProxyInterface)object).realmGet$mValueOfBookStatus();
                if (realmGet$mValueOfBookStatus != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mValueOfBookStatusIndex, rowIndex, realmGet$mValueOfBookStatus);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.mReadingProgressIndex, rowIndex, ((BookRealmProxyInterface)object).realmGet$mReadingProgress());
                String realmGet$mPreviewLink = ((BookRealmProxyInterface)object).realmGet$mPreviewLink();
                if (realmGet$mPreviewLink != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mPreviewLinkIndex, rowIndex, realmGet$mPreviewLink);
                }
                String realmGet$mValueOfBookType = ((BookRealmProxyInterface)object).realmGet$mValueOfBookType();
                if (realmGet$mValueOfBookType != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mValueOfBookTypeIndex, rowIndex, realmGet$mValueOfBookType);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, be.gershon_lehrer.mybooksathome.model.Book object, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Book.class);
        long tableNativePtr = table.getNativeTablePointer();
        BookColumnInfo columnInfo = (BookColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Book.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((BookRealmProxyInterface) object).realmGet$id();
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
        String realmGet$id = ((BookRealmProxyInterface)object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex);
        }
        String realmGet$mISBN = ((BookRealmProxyInterface)object).realmGet$mISBN();
        if (realmGet$mISBN != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mISBNIndex, rowIndex, realmGet$mISBN);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mISBNIndex, rowIndex);
        }
        String realmGet$mSubject = ((BookRealmProxyInterface)object).realmGet$mSubject();
        if (realmGet$mSubject != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mSubjectIndex, rowIndex, realmGet$mSubject);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mSubjectIndex, rowIndex);
        }
        String realmGet$mGoogleID = ((BookRealmProxyInterface)object).realmGet$mGoogleID();
        if (realmGet$mGoogleID != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mGoogleIDIndex, rowIndex, realmGet$mGoogleID);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mGoogleIDIndex, rowIndex);
        }
        String realmGet$mTitle = ((BookRealmProxyInterface)object).realmGet$mTitle();
        if (realmGet$mTitle != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mTitleIndex, rowIndex, realmGet$mTitle);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mTitleIndex, rowIndex);
        }
        String realmGet$mDescription = ((BookRealmProxyInterface)object).realmGet$mDescription();
        if (realmGet$mDescription != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mDescriptionIndex, rowIndex, realmGet$mDescription);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mDescriptionIndex, rowIndex);
        }
        String realmGet$mThumbnailURL = ((BookRealmProxyInterface)object).realmGet$mThumbnailURL();
        if (realmGet$mThumbnailURL != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mThumbnailURLIndex, rowIndex, realmGet$mThumbnailURL);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mThumbnailURLIndex, rowIndex);
        }

        long mAuthorsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.mAuthorsIndex, rowIndex);
        LinkView.nativeClear(mAuthorsNativeLinkViewPtr);
        RealmList<be.gershon_lehrer.mybooksathome.model.Author> mAuthorsList = ((BookRealmProxyInterface) object).realmGet$mAuthors();
        if (mAuthorsList != null) {
            for (be.gershon_lehrer.mybooksathome.model.Author mAuthorsItem : mAuthorsList) {
                Long cacheItemIndexmAuthors = cache.get(mAuthorsItem);
                if (cacheItemIndexmAuthors == null) {
                    cacheItemIndexmAuthors = AuthorRealmProxy.insertOrUpdate(realm, mAuthorsItem, cache);
                }
                LinkView.nativeAdd(mAuthorsNativeLinkViewPtr, cacheItemIndexmAuthors);
            }
        }
        LinkView.nativeClose(mAuthorsNativeLinkViewPtr);


        long mPublishersNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.mPublishersIndex, rowIndex);
        LinkView.nativeClear(mPublishersNativeLinkViewPtr);
        RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> mPublishersList = ((BookRealmProxyInterface) object).realmGet$mPublishers();
        if (mPublishersList != null) {
            for (be.gershon_lehrer.mybooksathome.model.Publisher mPublishersItem : mPublishersList) {
                Long cacheItemIndexmPublishers = cache.get(mPublishersItem);
                if (cacheItemIndexmPublishers == null) {
                    cacheItemIndexmPublishers = PublisherRealmProxy.insertOrUpdate(realm, mPublishersItem, cache);
                }
                LinkView.nativeAdd(mPublishersNativeLinkViewPtr, cacheItemIndexmPublishers);
            }
        }
        LinkView.nativeClose(mPublishersNativeLinkViewPtr);

        String realmGet$mValueOfBookStatus = ((BookRealmProxyInterface)object).realmGet$mValueOfBookStatus();
        if (realmGet$mValueOfBookStatus != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mValueOfBookStatusIndex, rowIndex, realmGet$mValueOfBookStatus);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mValueOfBookStatusIndex, rowIndex);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.mReadingProgressIndex, rowIndex, ((BookRealmProxyInterface)object).realmGet$mReadingProgress());
        String realmGet$mPreviewLink = ((BookRealmProxyInterface)object).realmGet$mPreviewLink();
        if (realmGet$mPreviewLink != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mPreviewLinkIndex, rowIndex, realmGet$mPreviewLink);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mPreviewLinkIndex, rowIndex);
        }
        String realmGet$mValueOfBookType = ((BookRealmProxyInterface)object).realmGet$mValueOfBookType();
        if (realmGet$mValueOfBookType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mValueOfBookTypeIndex, rowIndex, realmGet$mValueOfBookType);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mValueOfBookTypeIndex, rowIndex);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(be.gershon_lehrer.mybooksathome.model.Book.class);
        long tableNativePtr = table.getNativeTablePointer();
        BookColumnInfo columnInfo = (BookColumnInfo) realm.schema.getColumnInfo(be.gershon_lehrer.mybooksathome.model.Book.class);
        long pkColumnIndex = table.getPrimaryKey();
        be.gershon_lehrer.mybooksathome.model.Book object = null;
        while (objects.hasNext()) {
            object = (be.gershon_lehrer.mybooksathome.model.Book) objects.next();
            if(!cache.containsKey(object)) {
                String primaryKeyValue = ((BookRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                    if (primaryKeyValue != null) {
                        Table.nativeSetString(tableNativePtr, pkColumnIndex, rowIndex, ((BookRealmProxyInterface) object).realmGet$id());
                    }
                }
                cache.put(object, rowIndex);
                String realmGet$id = ((BookRealmProxyInterface)object).realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex);
                }
                String realmGet$mISBN = ((BookRealmProxyInterface)object).realmGet$mISBN();
                if (realmGet$mISBN != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mISBNIndex, rowIndex, realmGet$mISBN);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mISBNIndex, rowIndex);
                }
                String realmGet$mSubject = ((BookRealmProxyInterface)object).realmGet$mSubject();
                if (realmGet$mSubject != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mSubjectIndex, rowIndex, realmGet$mSubject);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mSubjectIndex, rowIndex);
                }
                String realmGet$mGoogleID = ((BookRealmProxyInterface)object).realmGet$mGoogleID();
                if (realmGet$mGoogleID != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mGoogleIDIndex, rowIndex, realmGet$mGoogleID);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mGoogleIDIndex, rowIndex);
                }
                String realmGet$mTitle = ((BookRealmProxyInterface)object).realmGet$mTitle();
                if (realmGet$mTitle != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mTitleIndex, rowIndex, realmGet$mTitle);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mTitleIndex, rowIndex);
                }
                String realmGet$mDescription = ((BookRealmProxyInterface)object).realmGet$mDescription();
                if (realmGet$mDescription != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mDescriptionIndex, rowIndex, realmGet$mDescription);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mDescriptionIndex, rowIndex);
                }
                String realmGet$mThumbnailURL = ((BookRealmProxyInterface)object).realmGet$mThumbnailURL();
                if (realmGet$mThumbnailURL != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mThumbnailURLIndex, rowIndex, realmGet$mThumbnailURL);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mThumbnailURLIndex, rowIndex);
                }

                long mAuthorsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.mAuthorsIndex, rowIndex);
                LinkView.nativeClear(mAuthorsNativeLinkViewPtr);
                RealmList<be.gershon_lehrer.mybooksathome.model.Author> mAuthorsList = ((BookRealmProxyInterface) object).realmGet$mAuthors();
                if (mAuthorsList != null) {
                    for (be.gershon_lehrer.mybooksathome.model.Author mAuthorsItem : mAuthorsList) {
                        Long cacheItemIndexmAuthors = cache.get(mAuthorsItem);
                        if (cacheItemIndexmAuthors == null) {
                            cacheItemIndexmAuthors = AuthorRealmProxy.insertOrUpdate(realm, mAuthorsItem, cache);
                        }
                        LinkView.nativeAdd(mAuthorsNativeLinkViewPtr, cacheItemIndexmAuthors);
                    }
                }
                LinkView.nativeClose(mAuthorsNativeLinkViewPtr);


                long mPublishersNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.mPublishersIndex, rowIndex);
                LinkView.nativeClear(mPublishersNativeLinkViewPtr);
                RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> mPublishersList = ((BookRealmProxyInterface) object).realmGet$mPublishers();
                if (mPublishersList != null) {
                    for (be.gershon_lehrer.mybooksathome.model.Publisher mPublishersItem : mPublishersList) {
                        Long cacheItemIndexmPublishers = cache.get(mPublishersItem);
                        if (cacheItemIndexmPublishers == null) {
                            cacheItemIndexmPublishers = PublisherRealmProxy.insertOrUpdate(realm, mPublishersItem, cache);
                        }
                        LinkView.nativeAdd(mPublishersNativeLinkViewPtr, cacheItemIndexmPublishers);
                    }
                }
                LinkView.nativeClose(mPublishersNativeLinkViewPtr);

                String realmGet$mValueOfBookStatus = ((BookRealmProxyInterface)object).realmGet$mValueOfBookStatus();
                if (realmGet$mValueOfBookStatus != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mValueOfBookStatusIndex, rowIndex, realmGet$mValueOfBookStatus);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mValueOfBookStatusIndex, rowIndex);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.mReadingProgressIndex, rowIndex, ((BookRealmProxyInterface)object).realmGet$mReadingProgress());
                String realmGet$mPreviewLink = ((BookRealmProxyInterface)object).realmGet$mPreviewLink();
                if (realmGet$mPreviewLink != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mPreviewLinkIndex, rowIndex, realmGet$mPreviewLink);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mPreviewLinkIndex, rowIndex);
                }
                String realmGet$mValueOfBookType = ((BookRealmProxyInterface)object).realmGet$mValueOfBookType();
                if (realmGet$mValueOfBookType != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.mValueOfBookTypeIndex, rowIndex, realmGet$mValueOfBookType);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.mValueOfBookTypeIndex, rowIndex);
                }
            }
        }
    }

    public static be.gershon_lehrer.mybooksathome.model.Book createDetachedCopy(be.gershon_lehrer.mybooksathome.model.Book realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        be.gershon_lehrer.mybooksathome.model.Book unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (be.gershon_lehrer.mybooksathome.model.Book)cachedObject.object;
            } else {
                unmanagedObject = (be.gershon_lehrer.mybooksathome.model.Book)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new be.gershon_lehrer.mybooksathome.model.Book();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((BookRealmProxyInterface) unmanagedObject).realmSet$id(((BookRealmProxyInterface) realmObject).realmGet$id());
        ((BookRealmProxyInterface) unmanagedObject).realmSet$mISBN(((BookRealmProxyInterface) realmObject).realmGet$mISBN());
        ((BookRealmProxyInterface) unmanagedObject).realmSet$mSubject(((BookRealmProxyInterface) realmObject).realmGet$mSubject());
        ((BookRealmProxyInterface) unmanagedObject).realmSet$mGoogleID(((BookRealmProxyInterface) realmObject).realmGet$mGoogleID());
        ((BookRealmProxyInterface) unmanagedObject).realmSet$mTitle(((BookRealmProxyInterface) realmObject).realmGet$mTitle());
        ((BookRealmProxyInterface) unmanagedObject).realmSet$mDescription(((BookRealmProxyInterface) realmObject).realmGet$mDescription());
        ((BookRealmProxyInterface) unmanagedObject).realmSet$mThumbnailURL(((BookRealmProxyInterface) realmObject).realmGet$mThumbnailURL());

        // Deep copy of mAuthors
        if (currentDepth == maxDepth) {
            ((BookRealmProxyInterface) unmanagedObject).realmSet$mAuthors(null);
        } else {
            RealmList<be.gershon_lehrer.mybooksathome.model.Author> managedmAuthorsList = ((BookRealmProxyInterface) realmObject).realmGet$mAuthors();
            RealmList<be.gershon_lehrer.mybooksathome.model.Author> unmanagedmAuthorsList = new RealmList<be.gershon_lehrer.mybooksathome.model.Author>();
            ((BookRealmProxyInterface) unmanagedObject).realmSet$mAuthors(unmanagedmAuthorsList);
            int nextDepth = currentDepth + 1;
            int size = managedmAuthorsList.size();
            for (int i = 0; i < size; i++) {
                be.gershon_lehrer.mybooksathome.model.Author item = AuthorRealmProxy.createDetachedCopy(managedmAuthorsList.get(i), nextDepth, maxDepth, cache);
                unmanagedmAuthorsList.add(item);
            }
        }

        // Deep copy of mPublishers
        if (currentDepth == maxDepth) {
            ((BookRealmProxyInterface) unmanagedObject).realmSet$mPublishers(null);
        } else {
            RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> managedmPublishersList = ((BookRealmProxyInterface) realmObject).realmGet$mPublishers();
            RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> unmanagedmPublishersList = new RealmList<be.gershon_lehrer.mybooksathome.model.Publisher>();
            ((BookRealmProxyInterface) unmanagedObject).realmSet$mPublishers(unmanagedmPublishersList);
            int nextDepth = currentDepth + 1;
            int size = managedmPublishersList.size();
            for (int i = 0; i < size; i++) {
                be.gershon_lehrer.mybooksathome.model.Publisher item = PublisherRealmProxy.createDetachedCopy(managedmPublishersList.get(i), nextDepth, maxDepth, cache);
                unmanagedmPublishersList.add(item);
            }
        }
        ((BookRealmProxyInterface) unmanagedObject).realmSet$mValueOfBookStatus(((BookRealmProxyInterface) realmObject).realmGet$mValueOfBookStatus());
        ((BookRealmProxyInterface) unmanagedObject).realmSet$mReadingProgress(((BookRealmProxyInterface) realmObject).realmGet$mReadingProgress());
        ((BookRealmProxyInterface) unmanagedObject).realmSet$mPreviewLink(((BookRealmProxyInterface) realmObject).realmGet$mPreviewLink());
        ((BookRealmProxyInterface) unmanagedObject).realmSet$mValueOfBookType(((BookRealmProxyInterface) realmObject).realmGet$mValueOfBookType());
        return unmanagedObject;
    }

    static be.gershon_lehrer.mybooksathome.model.Book update(Realm realm, be.gershon_lehrer.mybooksathome.model.Book realmObject, be.gershon_lehrer.mybooksathome.model.Book newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((BookRealmProxyInterface) realmObject).realmSet$mISBN(((BookRealmProxyInterface) newObject).realmGet$mISBN());
        ((BookRealmProxyInterface) realmObject).realmSet$mSubject(((BookRealmProxyInterface) newObject).realmGet$mSubject());
        ((BookRealmProxyInterface) realmObject).realmSet$mGoogleID(((BookRealmProxyInterface) newObject).realmGet$mGoogleID());
        ((BookRealmProxyInterface) realmObject).realmSet$mTitle(((BookRealmProxyInterface) newObject).realmGet$mTitle());
        ((BookRealmProxyInterface) realmObject).realmSet$mDescription(((BookRealmProxyInterface) newObject).realmGet$mDescription());
        ((BookRealmProxyInterface) realmObject).realmSet$mThumbnailURL(((BookRealmProxyInterface) newObject).realmGet$mThumbnailURL());
        RealmList<be.gershon_lehrer.mybooksathome.model.Author> mAuthorsList = ((BookRealmProxyInterface) newObject).realmGet$mAuthors();
        RealmList<be.gershon_lehrer.mybooksathome.model.Author> mAuthorsRealmList = ((BookRealmProxyInterface) realmObject).realmGet$mAuthors();
        mAuthorsRealmList.clear();
        if (mAuthorsList != null) {
            for (int i = 0; i < mAuthorsList.size(); i++) {
                be.gershon_lehrer.mybooksathome.model.Author mAuthorsItem = mAuthorsList.get(i);
                be.gershon_lehrer.mybooksathome.model.Author cachemAuthors = (be.gershon_lehrer.mybooksathome.model.Author) cache.get(mAuthorsItem);
                if (cachemAuthors != null) {
                    mAuthorsRealmList.add(cachemAuthors);
                } else {
                    mAuthorsRealmList.add(AuthorRealmProxy.copyOrUpdate(realm, mAuthorsList.get(i), true, cache));
                }
            }
        }
        RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> mPublishersList = ((BookRealmProxyInterface) newObject).realmGet$mPublishers();
        RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> mPublishersRealmList = ((BookRealmProxyInterface) realmObject).realmGet$mPublishers();
        mPublishersRealmList.clear();
        if (mPublishersList != null) {
            for (int i = 0; i < mPublishersList.size(); i++) {
                be.gershon_lehrer.mybooksathome.model.Publisher mPublishersItem = mPublishersList.get(i);
                be.gershon_lehrer.mybooksathome.model.Publisher cachemPublishers = (be.gershon_lehrer.mybooksathome.model.Publisher) cache.get(mPublishersItem);
                if (cachemPublishers != null) {
                    mPublishersRealmList.add(cachemPublishers);
                } else {
                    mPublishersRealmList.add(PublisherRealmProxy.copyOrUpdate(realm, mPublishersList.get(i), true, cache));
                }
            }
        }
        ((BookRealmProxyInterface) realmObject).realmSet$mValueOfBookStatus(((BookRealmProxyInterface) newObject).realmGet$mValueOfBookStatus());
        ((BookRealmProxyInterface) realmObject).realmSet$mReadingProgress(((BookRealmProxyInterface) newObject).realmGet$mReadingProgress());
        ((BookRealmProxyInterface) realmObject).realmSet$mPreviewLink(((BookRealmProxyInterface) newObject).realmGet$mPreviewLink());
        ((BookRealmProxyInterface) realmObject).realmSet$mValueOfBookType(((BookRealmProxyInterface) newObject).realmGet$mValueOfBookType());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Book = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mISBN:");
        stringBuilder.append(realmGet$mISBN() != null ? realmGet$mISBN() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mSubject:");
        stringBuilder.append(realmGet$mSubject() != null ? realmGet$mSubject() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mGoogleID:");
        stringBuilder.append(realmGet$mGoogleID() != null ? realmGet$mGoogleID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mTitle:");
        stringBuilder.append(realmGet$mTitle());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mDescription:");
        stringBuilder.append(realmGet$mDescription() != null ? realmGet$mDescription() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mThumbnailURL:");
        stringBuilder.append(realmGet$mThumbnailURL() != null ? realmGet$mThumbnailURL() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mAuthors:");
        stringBuilder.append("RealmList<Author>[").append(realmGet$mAuthors().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mPublishers:");
        stringBuilder.append("RealmList<Publisher>[").append(realmGet$mPublishers().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mValueOfBookStatus:");
        stringBuilder.append(realmGet$mValueOfBookStatus() != null ? realmGet$mValueOfBookStatus() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mReadingProgress:");
        stringBuilder.append(realmGet$mReadingProgress());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mPreviewLink:");
        stringBuilder.append(realmGet$mPreviewLink() != null ? realmGet$mPreviewLink() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mValueOfBookType:");
        stringBuilder.append(realmGet$mValueOfBookType() != null ? realmGet$mValueOfBookType() : "null");
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
        BookRealmProxy aBook = (BookRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aBook.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aBook.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aBook.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
