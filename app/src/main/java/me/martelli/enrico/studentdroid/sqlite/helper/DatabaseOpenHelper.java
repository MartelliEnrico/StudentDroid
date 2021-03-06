package me.martelli.enrico.studentdroid.sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.martelli.enrico.studentdroid.sqlite.model.Compito;
import me.martelli.enrico.studentdroid.sqlite.model.Lezione;
import me.martelli.enrico.studentdroid.sqlite.model.Materia;
import me.martelli.enrico.studentdroid.sqlite.model.Reminder;
import me.martelli.enrico.studentdroid.sqlite.model.Vacanza;
import me.martelli.enrico.studentdroid.sqlite.model.Verifica;
import me.martelli.enrico.studentdroid.sqlite.model.Voto;

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
    private static final String TABLE_VACANZA = "vacanze";

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
    private static final String CREATE_TABLE_MATERIA = "CREATE TABLE " + TABLE_MATERIA + " ("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NOME + " TEXT, " + KEY_NOME_PROFESSORE
            + " TEXT, " + KEY_DESCRIZIONE + " TEXT, " + KEY_COLORE + " INTEGER, " + KEY_CREATED_AT
            + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    private static final String CREATE_TABLE_LEZIONE = "CREATE TABLE " + TABLE_LEZIONE + " ("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_GIORNO + " INTEGER, " + KEY_ORA_FINE
            + " INTEGER, " + KEY_ORA_INIZIO + " INTEGER, " + KEY_CLASSE + " TEXT, "
            + KEY_DESCRIZIONE + " TEXT, " + KEY_ID_MATERIA + " INTEGER REFERENCES " + TABLE_MATERIA
            + " (" + KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE, " + KEY_CREATED_AT
            + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    private static final String CREATE_TABLE_COMPITO = "CREATE TABLE " + TABLE_COMPITO + " ("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_GIORNO + " INTEGER, " + KEY_DESCRIZIONE
            + " TEXT, " + KEY_ID_LEZIONE + " INTEGER REFERENCES " + TABLE_LEZIONE + " (" + KEY_ID
            + ") ON UPDATE CASCADE ON DELETE CASCADE, " + KEY_CREATED_AT + " DATETIME DEFAULT"
            + " CURRENT_TIMESTAMP);";

    private static final String CREATE_TABLE_VERIFICA = "CREATE TABLE " + TABLE_VERIFICA + " ("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_GIORNO + " INTEGER, " + KEY_TIPOLOGIA
            + " TEXT, " + KEY_DESCRIZIONE + " TEXT, " + KEY_ID_LEZIONE + " INTEGER REFERENCES "
            + TABLE_LEZIONE + " (" + KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE, "
            + KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    private static final String CREATE_TABLE_VOTO = "CREATE TABLE " + TABLE_VOTO + " (" + KEY_ID
            + " INTEGER PRIMARY KEY, " + KEY_GIORNO + " INTEGER, " + KEY_VOTO + " DECIMAL(5,2), "
            + KEY_DESCRIZIONE + " TEXT, " + KEY_ID_MATERIA + " INTEGER REFERENCES " + TABLE_MATERIA
            + " (" + KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE, " + KEY_CREATED_AT
            + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    private static final String CREATE_TABLE_REMINDER = "CREATE TABLE " + TABLE_REMINDER + " ("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_ID_VERIFICA + " INTEGER REFERENCES "
            + TABLE_VERIFICA + " (" + KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE, "
            + KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    private static final String CREATE_TABLE_VACANZA = "CREATE TABLE " + TABLE_VACANZA + " ("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_GIORNO_INIZIO + " INTEGER, "
            + KEY_GIORNO_FINE + " INTEGER, " + KEY_CREATED_AT + " DATETIME DEFAULT"
            + " CURRENT_TIMESTAMP);";

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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VACANZA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERIFICA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPITO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEZIONE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIA);

        // create new tables
        onCreate(db);
    }

    private long getDateTime() {
        return new Date().getTime();
    }

    public long create(DbModel model) {
        if(model instanceof Materia) {
            return createMateria((Materia) model);
        } else if(model instanceof Lezione) {
            return createLezione((Lezione) model);
        } else if(model instanceof Compito) {
            return createCompito((Compito) model);
        } else if(model instanceof Verifica) {
            return createVerifica((Verifica) model);
        } else if(model instanceof Voto) {
            return createVoto((Voto) model);
        } else if(model instanceof Reminder) {
            return createReminder((Reminder) model);
        } else if(model instanceof Vacanza) {
            return createVacanza((Vacanza) model);
        } else {
            return -1;
        }
    }

    public int update(DbModel model) {
        if(model instanceof Materia) {
            return updateMateria((Materia) model);
        } else if(model instanceof Lezione) {
            return updateLezione((Lezione) model);
        } else if(model instanceof Compito) {
            return updateCompito((Compito) model);
        } else if(model instanceof Verifica) {
            return updateVerifica((Verifica) model);
        } else if(model instanceof Voto) {
            return updateVoto((Voto) model);
        } else if(model instanceof Reminder) {
            return updateReminder((Reminder) model);
        } else if(model instanceof Vacanza) {
            return updateVacanza((Vacanza) model);
        } else {
            return 0;
        }
    }

    public void delete(DbModel model) {
        if(model instanceof Materia) {
            deleteMateria(model.getId());
        } else if(model instanceof Lezione) {
            deleteLezione(model.getId());
        } else if(model instanceof Compito) {
            deleteCompito(model.getId());
        } else if(model instanceof Verifica) {
            deleteVerifica(model.getId());
        } else if(model instanceof Voto) {
            deleteVoto(model.getId());
        } else if(model instanceof Reminder) {
            deleteReminder(model.getId());
        } else if(model instanceof Vacanza) {
            deleteVacanza(model.getId());
        }
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

    public Materia getMateria(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_MATERIA + " WHERE " + KEY_ID + " = " + id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c == null || ! c.moveToFirst()) return null;

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

        if(c != null && c.moveToFirst()) {
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

    public void deleteMateria(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_MATERIA, KEY_ID + " = ?", new String[] {
                String.valueOf(id)
        });
    }

    public long createLezione(Lezione lezione) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GIORNO, lezione.getGiorno());
        values.put(KEY_ORA_INIZIO, lezione.getInizio().getTime());
        values.put(KEY_ORA_FINE, lezione.getFine().getTime());
        values.put(KEY_CLASSE, lezione.getClasse());
        values.put(KEY_DESCRIZIONE, lezione.getDescrizione());
        values.put(KEY_ID_MATERIA, lezione.getIdMateria());

        return db.insert(TABLE_LEZIONE, null, values);
    }

    public Lezione getLezione(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_LEZIONE + " WHERE " + KEY_ID + " = " + id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (! (c != null && c.moveToFirst())) return null;

        Lezione lezione = new Lezione();
        lezione.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        lezione.setGiorno(c.getInt(c.getColumnIndex(KEY_GIORNO)));
        lezione.setInizio(new Date(c.getLong(c.getColumnIndex(KEY_ORA_INIZIO))));
        lezione.setFine(new Date(c.getLong(c.getColumnIndex(KEY_ORA_FINE))));
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

        if(c != null && c.moveToFirst()) {
            do {
                Lezione lezione = new Lezione();
                lezione.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                lezione.setGiorno(c.getInt(c.getColumnIndex(KEY_GIORNO)));
                lezione.setInizio(new Date(c.getLong(c.getColumnIndex(KEY_ORA_INIZIO))));
                lezione.setFine(new Date(c.getLong(c.getColumnIndex(KEY_ORA_FINE))));
                lezione.setClasse(c.getString(c.getColumnIndex(KEY_CLASSE)));
                lezione.setDescrizione(c.getString(c.getColumnIndex(KEY_DESCRIZIONE)));
                lezione.setIdMateria(c.getInt(c.getColumnIndex(KEY_ID_MATERIA)));

                lezioni.add(lezione);
            } while(c.moveToNext());
        }

        return lezioni;
    }

    public List<Lezione> getTodayLezioni() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Lezione> lezioni = new ArrayList<Lezione>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        String selectQuery = "SELECT * FROM " + TABLE_LEZIONE + " WHERE " + KEY_GIORNO + " = "
                + dayOfWeek + " ORDER BY " + KEY_ORA_INIZIO + " ASC";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null && c.moveToFirst()) {
            do {
                Lezione lezione = new Lezione();
                lezione.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                lezione.setGiorno(c.getInt(c.getColumnIndex(KEY_GIORNO)));
                lezione.setInizio(new Date(c.getLong(c.getColumnIndex(KEY_ORA_INIZIO))));
                lezione.setFine(new Date(c.getLong(c.getColumnIndex(KEY_ORA_FINE))));
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
        values.put(KEY_ORA_INIZIO, lezione.getInizio().getTime());
        values.put(KEY_ORA_FINE, lezione.getFine().getTime());
        values.put(KEY_CLASSE, lezione.getClasse());
        values.put(KEY_DESCRIZIONE, lezione.getDescrizione());
        values.put(KEY_ID_MATERIA, lezione.getIdMateria());

        return db.update(TABLE_LEZIONE, values, KEY_ID + " = ?", new String[] {
                String.valueOf(lezione.getId())
        });
    }

    public void deleteLezione(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_LEZIONE, KEY_ID + " = ?", new String[] {
                String.valueOf(id)
        });
    }

    public long createCompito(Compito compito) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GIORNO, compito.getGiorno().getTime());
        values.put(KEY_DESCRIZIONE, compito.getDescrizione());
        values.put(KEY_ID_LEZIONE, compito.getIdLezione());

        return db.insert(TABLE_COMPITO, null, values);
    }

    public Compito getCompito(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_COMPITO + " WHERE " + KEY_ID + " = " + id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (! (c != null && c.moveToFirst())) return null;

        Compito compito = new Compito();
        compito.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        compito.setGiorno(new Date(c.getLong(c.getColumnIndex(KEY_GIORNO))));
        compito.setDescrizione(c.getString(c.getColumnIndex(KEY_DESCRIZIONE)));
        compito.setIdLezione(c.getInt(c.getColumnIndex(KEY_ID_LEZIONE)));

        return compito;
    }

    public List<Compito> getAllCompiti() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Compito> compiti = new ArrayList<Compito>();

        String selectQuery = "SELECT * FROM " + TABLE_COMPITO;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null && c.moveToFirst()) {
            do {
                Compito compito = new Compito();
                compito.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                compito.setGiorno(new Date(c.getLong(c.getColumnIndex(KEY_GIORNO))));
                compito.setDescrizione(c.getString(c.getColumnIndex(KEY_DESCRIZIONE)));
                compito.setIdLezione(c.getInt(c.getColumnIndex(KEY_ID_LEZIONE)));

                compiti.add(compito);
            } while(c.moveToNext());
        }

        return compiti;
    }

    public List<Compito> getNextCompiti() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Compito> compiti = new ArrayList<Compito>();

        Date oggi = new Date();
        long oggiTime = oggi.getTime() - (oggi.getTime() % (1000 * 60 * 60 * 24));

        String selectQuery = "SELECT * FROM " + TABLE_COMPITO + " WHERE " + KEY_GIORNO + " >= "
                + oggiTime + " ORDER BY " + KEY_GIORNO + " ASC";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null && c.moveToFirst()) {
            do {
                Compito compito = new Compito();
                compito.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                compito.setGiorno(new Date(c.getLong(c.getColumnIndex(KEY_GIORNO))));
                compito.setDescrizione(c.getString(c.getColumnIndex(KEY_DESCRIZIONE)));
                compito.setIdLezione(c.getInt(c.getColumnIndex(KEY_ID_LEZIONE)));

                compiti.add(compito);
            } while(c.moveToNext());
        }

        return compiti;
    }

    public int updateCompito(Compito compito) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GIORNO, compito.getGiorno().getTime());
        values.put(KEY_DESCRIZIONE, compito.getDescrizione());
        values.put(KEY_ID_LEZIONE, compito.getIdLezione());

        return db.update(TABLE_COMPITO, values, KEY_ID + " = ?", new String[] {
                String.valueOf(compito.getId())
        });
    }

    public void deleteCompito(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_COMPITO, KEY_ID + " = ?", new String[] {
                String.valueOf(id)
        });
    }

    public long createVerifica(Verifica verifica) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GIORNO, verifica.getGiorno().getTime());
        values.put(KEY_TIPOLOGIA, verifica.getTipologia());
        values.put(KEY_ID_LEZIONE, verifica.getIdLezione());

        return db.insert(TABLE_VERIFICA, null, values);
    }

    public Verifica getVerifica(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_VERIFICA + " WHERE " + KEY_ID + " = " + id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (! (c != null && c.moveToFirst())) return null;

        Verifica verifica = new Verifica();
        verifica.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        verifica.setGiorno(new Date(c.getLong(c.getColumnIndex(KEY_GIORNO))));
        verifica.setTipologia(c.getString(c.getColumnIndex(KEY_TIPOLOGIA)));
        verifica.setIdLezione(c.getInt(c.getColumnIndex(KEY_ID_LEZIONE)));

        return verifica;
    }

    public List<Verifica> getAllVerifiche() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Verifica> verifiche = new ArrayList<Verifica>();

        String selectQuery = "SELECT * FROM " + TABLE_VERIFICA;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null && c.moveToFirst()) {
            do {
                Verifica verifica = new Verifica();
                verifica.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                verifica.setGiorno(new Date(c.getLong(c.getColumnIndex(KEY_GIORNO))));
                verifica.setTipologia(c.getString(c.getColumnIndex(KEY_TIPOLOGIA)));
                verifica.setIdLezione(c.getInt(c.getColumnIndex(KEY_ID_LEZIONE)));

                verifiche.add(verifica);
            } while(c.moveToNext());
        }

        return verifiche;
    }

    public int updateVerifica(Verifica verifica) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GIORNO, verifica.getGiorno().getTime());
        values.put(KEY_TIPOLOGIA, verifica.getTipologia());
        values.put(KEY_ID_LEZIONE, verifica.getIdLezione());

        return db.update(TABLE_VERIFICA, values, KEY_ID + " = ?", new String[] {
                String.valueOf(verifica.getId())
        });
    }

    public void deleteVerifica(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_VERIFICA, KEY_ID + " = ?", new String[] {
                String.valueOf(id)
        });
    }

    public long createVoto(Voto voto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VOTO, voto.getVoto());
        values.put(KEY_TIPOLOGIA, voto.getTipologia());
        values.put(KEY_DESCRIZIONE, voto.getDescrizione());
        values.put(KEY_ID_MATERIA, voto.getIdMateria());

        return db.insert(TABLE_VOTO, null, values);
    }

    public Voto getVoto(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_VOTO + " WHERE " + KEY_ID + " = " + id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (! (c != null && c.moveToFirst())) return null;

        Voto voto = new Voto();
        voto.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        voto.setVoto(c.getInt(c.getColumnIndex(KEY_VOTO)));
        voto.setTipologia(c.getString(c.getColumnIndex(KEY_TIPOLOGIA)));
        voto.setDescrizione(c.getString(c.getColumnIndex(KEY_DESCRIZIONE)));
        voto.setIdMateria(c.getInt(c.getColumnIndex(KEY_ID_MATERIA)));

        return voto;
    }

    public List<Voto> getAllVoti() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Voto> voti = new ArrayList<Voto>();

        String selectQuery = "SELECT * FROM " + TABLE_VOTO;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null && c.moveToFirst()) {
            do {
                Voto voto = new Voto();
                voto.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                voto.setVoto(c.getInt(c.getColumnIndex(KEY_VOTO)));
                voto.setTipologia(c.getString(c.getColumnIndex(KEY_TIPOLOGIA)));
                voto.setDescrizione(c.getString(c.getColumnIndex(KEY_DESCRIZIONE)));
                voto.setIdMateria(c.getInt(c.getColumnIndex(KEY_ID_MATERIA)));

                voti.add(voto);
            } while(c.moveToNext());
        }

        return voti;
    }

    public int updateVoto(Voto voto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VOTO, voto.getVoto());
        values.put(KEY_TIPOLOGIA, voto.getTipologia());
        values.put(KEY_DESCRIZIONE, voto.getDescrizione());
        values.put(KEY_ID_MATERIA, voto.getIdMateria());

        return db.update(TABLE_VOTO, values, KEY_ID + " = ?", new String[] {
                String.valueOf(voto.getId())
        });
    }

    public void deleteVoto(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_VOTO, KEY_ID + " = ?", new String[] {
                String.valueOf(id)
        });
    }

    public long createReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_VERIFICA, reminder.getIdVerifica());

        return db.insert(TABLE_REMINDER, null, values);
    }

    public Reminder getReminder(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_REMINDER + " WHERE " + KEY_ID + " = " + id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (! (c != null && c.moveToFirst())) return null;

        Reminder reminder = new Reminder();
        reminder.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        reminder.setIdVerifica(c.getInt(c.getColumnIndex(KEY_ID_VERIFICA)));

        return reminder;
    }

    public List<Reminder> getAllReminders() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Reminder> reminders = new ArrayList<Reminder>();

        String selectQuery = "SELECT * FROM " + TABLE_REMINDER;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null && c.moveToFirst()) {
            do {
                Reminder reminder = new Reminder();
                reminder.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                reminder.setIdVerifica(c.getInt(c.getColumnIndex(KEY_ID_VERIFICA)));

                reminders.add(reminder);
            } while(c.moveToNext());
        }

        return reminders;
    }

    public int updateReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_VERIFICA, reminder.getIdVerifica());

        return db.update(TABLE_REMINDER, values, KEY_ID + " = ?", new String[] {
                String.valueOf(reminder.getId())
        });
    }

    public void deleteReminder(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_REMINDER, KEY_ID + " = ?", new String[] {
                String.valueOf(id)
        });
    }

    public long createVacanza(Vacanza vacanza) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GIORNO_INIZIO, vacanza.getInizio().getTime());
        values.put(KEY_GIORNO_FINE, vacanza.getFine().getTime());
        values.put(KEY_DESCRIZIONE, vacanza.getDescrizione());

        return db.insert(TABLE_VACANZA, null, values);
    }

    public Vacanza getVacanza(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_VACANZA + " WHERE " + KEY_ID + " = " + id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (! (c != null && c.moveToFirst())) return null;

        Vacanza vacanza = new Vacanza();
        vacanza.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        vacanza.setInizio(new Date(c.getLong(c.getColumnIndex(KEY_GIORNO_INIZIO))));
        vacanza.setFine(new Date(c.getLong(c.getColumnIndex(KEY_GIORNO_FINE))));
        vacanza.setDescrizione(c.getString(c.getColumnIndex(KEY_DESCRIZIONE)));

        return vacanza;
    }

    public List<Vacanza> getAllVacanze() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Vacanza> vacanze = new ArrayList<Vacanza>();

        String selectQuery = "SELECT * FROM " + TABLE_VACANZA;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null && c.moveToFirst()) {
            do {
                Vacanza vacanza = new Vacanza();
                vacanza.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                vacanza.setInizio(new Date(c.getLong(c.getColumnIndex(KEY_GIORNO_INIZIO))));
                vacanza.setFine(new Date(c.getLong(c.getColumnIndex(KEY_GIORNO_FINE))));
                vacanza.setDescrizione(c.getString(c.getColumnIndex(KEY_DESCRIZIONE)));

                vacanze.add(vacanza);
            } while(c.moveToNext());
        }

        return vacanze;
    }

    public int updateVacanza(Vacanza vacanza) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GIORNO_INIZIO, vacanza.getInizio().getTime());
        values.put(KEY_GIORNO_FINE, vacanza.getFine().getTime());
        values.put(KEY_DESCRIZIONE, vacanza.getDescrizione());

        return db.update(TABLE_VACANZA, values, KEY_ID + " = ?", new String[] {
                String.valueOf(vacanza.getId())
        });
    }

    public void deleteVacanza(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_VACANZA, KEY_ID + " = ?", new String[] {
                String.valueOf(id)
        });
    }
}
