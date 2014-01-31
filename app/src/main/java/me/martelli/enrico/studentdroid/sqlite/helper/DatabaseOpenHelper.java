package me.martelli.enrico.studentdroid.sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import me.martelli.enrico.studentdroid.sqlite.model.Lezione;
import me.martelli.enrico.studentdroid.sqlite.model.Materia;

/**
 * Created by Enrico on 31/01/14.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseOpenHelper";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentDroid";

    // tables name
    private static final String TABLE_MATERIA = "materie";
    private static final String TABLE_LEZIONE = "lezioni";
    private static final String TABLE_COMPITO = "compiti";
    private static final String TABLE_VERIFICA = "verifiche";
    private static final String TABLE_VOTO = "voti";
    private static final String TABLE_REMINDER = "reminders";
    private static final String TABLE_VANCAZA = "vacanze";

    // common columns
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_DESCRIZIONE = "descrizione";
    private static final String KEY_TIPOLOGIA = "tipologia";
    private static final String KEY_GIORNO = "giorno";

    private static final String KEY_ID_MATERIA = "id_materia";
    private static final String KEY_ID_LEZIONE = "id_lezione";
    private static final String KEY_ID_VERIFICA = "id_verifica";

    // materia
    private static final String KEY_NOME = "nome";
    private static final String KEY_NOME_PROFESSORE = "nome_professore";
    private static final String KEY_COLORE = "colore";

    // lezione
    private static final String KEY_ORA_INIZIO = "ora_inizio";
    private static final String KEY_ORA_FINE = "ora_fine";
    private static final String KEY_CLASSE = "classe";

    // voto
    private static final String KEY_VOTO = "voto";

    // vacanza
    private static final String KEY_GIORNO_INIZIO = "giorno_inizio";
    private static final String KEY_GIORNO_FINE = "giorno_fine";

    // table create statement
    private static final String CREATE_TABLE_MATERIA = "CREATE TABLE " + TABLE_MATERIA + "("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NOME + " TEXT, " + KEY_NOME_PROFESSORE
            + " TEXT, " + KEY_DESCRIZIONE + " TEXT, " + KEY_COLORE + " INTEGER, " + KEY_CREATED_AT
            + " DATETIME);";

    private static final String CREATE_TABLE_LEZIONE = "CREATE TABLE " + TABLE_LEZIONE + "("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_GIORNO + " DATETIME, " + KEY_ORA_FINE
            + " DATETIME, " + KEY_ORA_INIZIO + " DATETIME, " + KEY_CLASSE + " TEXT, "
            + KEY_DESCRIZIONE + " TEXT, " + KEY_ID_MATERIA + " INTEGER REFERENCE " + TABLE_MATERIA
            + "(" + KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE, " + KEY_CREATED_AT
            + " DATETIME);";

    private static final String CREATE_TABLE_COMPITO = "CREATE TABLE " + TABLE_COMPITO + "("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_GIORNO + " DATETIME, " + KEY_DESCRIZIONE
            + " TEXT, " + KEY_ID_LEZIONE + " INTEGER REFERENCE " + TABLE_LEZIONE + "(" + KEY_ID
            + ") ON UPDATE CASCADE ON DELETE CASCADE, " + KEY_CREATED_AT + " DATETIME);";

    private static final String CREATE_TABLE_VERIFICA = "CREATE TABLE " + TABLE_VERIFICA + "("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_GIORNO + " DATETIME, " + KEY_TIPOLOGIA
            + " TEXT, " + KEY_DESCRIZIONE + " TEXT, " + KEY_ID_LEZIONE + " INTEGER REFERENCE "
            + TABLE_LEZIONE + "(" + KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE, "
            + KEY_CREATED_AT + " DATETIME);";

    private static final String CREATE_TABLE_VOTO = "CREATE TABLE " + TABLE_VOTO + "(" + KEY_ID
            + " INTEGER PRIMARY KEY, " + KEY_GIORNO + " DATETIME, " + KEY_VOTO + " DECIMAL(5,2), "
            + KEY_DESCRIZIONE + " TEXT, " + KEY_ID_MATERIA + " INTEGER REFERENCE " + TABLE_MATERIA
            + "(" + KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE, " + KEY_CREATED_AT
            + " DATETIME);";

    private static final String CREATE_TABLE_REMINDER = "CREATE TABLE " + TABLE_REMINDER + "("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_ID_VERIFICA + " INTEGER REFERENCE "
            + TABLE_VERIFICA + "(" + KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE, "
            + KEY_CREATED_AT + " DATETIME);";

    private static final String CREATE_TABLE_VACANZA = "CREATE TABLE " + TABLE_COMPITO + "("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_GIORNO_INIZIO + " DATETIME, "
            + KEY_GIORNO_FINE + " DATETIME, " + KEY_CREATED_AT + " DATETIME);";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_MATERIA);
        db.execSQL(CREATE_TABLE_LEZIONE);
        db.execSQL(CREATE_TABLE_COMPITO);
        db.execSQL(CREATE_TABLE_VERIFICA);
        db.execSQL(CREATE_TABLE_VOTO);
        db.execSQL(CREATE_TABLE_REMINDER);
        db.execSQL(CREATE_TABLE_VACANZA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VANCAZA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERIFICA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPITO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEZIONE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIA);

        // create new tables
        onCreate(db);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    // CRUD

    public long createMateria(Materia materia) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, materia.getNome());
        values.put(KEY_NOME_PROFESSORE, materia.getNomeProfessore());
        values.put(KEY_DESCRIZIONE, materia.getDescrizione());
        values.put(KEY_COLORE, materia.getColore());

        return db.insert(TABLE_MATERIA, null, values);
    }

    public Materia getMateria(long idMateria) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_MATERIA + " WHERE " + KEY_ID + " = " + idMateria;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) c.moveToFirst();

        Materia materia = new Materia();
        materia.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        materia.setNome(c.getString(c.getColumnIndex(KEY_NOME)));
        materia.setNomeProfessore(c.getString(c.getColumnIndex(KEY_NOME_PROFESSORE)));
        materia.setDescrizione(c.getString(c.getColumnIndex(KEY_DESCRIZIONE)));
        materia.setColore(c.getInt(c.getColumnIndex(KEY_COLORE)));

        return materia;
    }

    public List<Materia> getAllMaterie() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Materia> materie = new ArrayList<Materia>();

        String selectQuery = "SELECT * FROM " + TABLE_MATERIA;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                Materia materia = new Materia();
                materia.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                materia.setNome(c.getString(c.getColumnIndex(KEY_NOME)));
                materia.setNomeProfessore(c.getString(c.getColumnIndex(KEY_NOME_PROFESSORE)));
                materia.setDescrizione(c.getString(c.getColumnIndex(KEY_DESCRIZIONE)));
                materia.setColore(c.getInt(c.getColumnIndex(KEY_COLORE)));

                materie.add(materia);
            } while(c.moveToNext());
        }

        return materie;
    }

    public int updateMateria(Materia materia) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, materia.getNome());
        values.put(KEY_NOME_PROFESSORE, materia.getNomeProfessore());
        values.put(KEY_DESCRIZIONE, materia.getDescrizione());
        values.put(KEY_COLORE, materia.getColore());

        return db.update(TABLE_MATERIA, values, KEY_ID + " = ?", new String[] {
                String.valueOf(materia.getId())
        });
    }

    public void deleteMateria(long idMateria) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_MATERIA, KEY_ID + " = ?", new String[] {
                String.valueOf(idMateria)
        });
    }

    public long createLezione(Lezione lezione) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GIORNO, lezione.getGiorno());
        values.put(KEY_ORA_INIZIO, lezione.getInizio());
        values.put(KEY_ORA_FINE, lezione.getFine());
        values.put(KEY_CLASSE, lezione.getClasse());
        values.put(KEY_DESCRIZIONE, lezione.getClasse());
        values.put(KEY_ID_MATERIA, lezione.getIdMateria());

        return db.insert(TABLE_LEZIONE, null, values);
    }

    public Lezione getLezione(long idLezione) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_LEZIONE + " WHERE " + KEY_ID + " = " + idLezione;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) c.moveToFirst();

        Lezione lezione = new Lezione();
        lezione.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        lezione.setGiorno(c.getInt(c.getColumnIndex(KEY_GIORNO)));
        lezione.setInizio(c.getInt(c.getColumnIndex(KEY_ORA_INIZIO)));
        lezione.setFine(c.getInt(c.getColumnIndex(KEY_ORA_FINE)));
        lezione.setClasse(c.getString(c.getColumnIndex(KEY_CLASSE)));
        lezione.setDescrizione(c.getString(c.getColumnIndex(KEY_DESCRIZIONE)));
        lezione.setIdMateria(c.getInt(c.getColumnIndex(KEY_ID_MATERIA)));

        return lezione;
    }

    public List<Lezione> getAllLezioni() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Lezione> lezioni = new ArrayList<Lezione>();

        String selectQuery = "SELECT * FROM " + TABLE_LEZIONE;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                Lezione lezione = new Lezione();
                lezione.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                lezione.setGiorno(c.getInt(c.getColumnIndex(KEY_GIORNO)));
                lezione.setInizio(c.getInt(c.getColumnIndex(KEY_ORA_INIZIO)));
                lezione.setFine(c.getInt(c.getColumnIndex(KEY_ORA_FINE)));
                lezione.setClasse(c.getString(c.getColumnIndex(KEY_CLASSE)));
                lezione.setDescrizione(c.getString(c.getColumnIndex(KEY_DESCRIZIONE)));
                lezione.setIdMateria(c.getInt(c.getColumnIndex(KEY_ID_MATERIA)));

                lezioni.add(lezione);
            } while(c.moveToNext());
        }

        return lezioni;
    }

    public int updateLezione(Lezione lezione) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GIORNO, lezione.getGiorno());
        values.put(KEY_ORA_INIZIO, lezione.getInizio());
        values.put(KEY_ORA_FINE, lezione.getFine());
        values.put(KEY_CLASSE, lezione.getClasse());
        values.put(KEY_DESCRIZIONE, lezione.getClasse());
        values.put(KEY_ID_MATERIA, lezione.getIdMateria());

        return db.update(TABLE_LEZIONE, values, KEY_ID + " = ?", new String[] {
                String.valueOf(lezione.getId())
        });
    }

    public void deleteLezione(long idLezione) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_LEZIONE, KEY_ID + " = ?", new String[] {
                String.valueOf(idLezione)
        });
    }

    // TODO: finire i metodi per COMPITO, VERIFICA, VOTO, REMINDER e VACANZA
}
