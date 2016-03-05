package hup.plantalo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by eliasPlease on 02-03-2016.
 */
public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_version = 3;
    public String create_table_cultivos_query = "CREATE TABLE " + CultivosTable.TableInfoCultivos.TABLE_NAME + "(" +
            CultivosTable.TableInfoCultivos.CULTIVO_NAME + " TEXT, " +
            CultivosTable.TableInfoCultivos.CULTIVO_DESCRIPTION + " TEXT, " +
            CultivosTable.TableInfoCultivos.CULTIVO_IMAGE + " TEXT );";
    public String create_table_comentarios_tips_query = "CREATE TABLE " + ComentariosTipsTable.TableInfoComentariosTips.TABLE_NAME + "("+
            ComentariosTipsTable.TableInfoComentariosTips.AUTOR + " TEXT, " +
            ComentariosTipsTable.TableInfoComentariosTips.CONTENIDO + " TEXT, " +
            ComentariosTipsTable.TableInfoComentariosTips.FECHA + " datetime, " +
            ComentariosTipsTable.TableInfoComentariosTips.TIPO + " TEXT, " +
            ComentariosTipsTable.TableInfoComentariosTips.CULTIVO + " TEXT);";
    public String create_table_mis_cultivos_query = "CREATE TABLE " + MisCultivosTable.TableMisCultivosInfo.TABLE_NAME + "(" +
            MisCultivosTable.TableMisCultivosInfo.CULTIVO + " TEXT);";

    public DatabaseOperations(Context context) {
        super(context, CultivosTable.TableInfoCultivos.DATABASE_NAME, null, database_version);
        Log.d("Database operation", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {

        sdb.execSQL(create_table_cultivos_query);
        sdb.execSQL(create_table_comentarios_tips_query);
        sdb.execSQL(create_table_mis_cultivos_query);
        Log.d("Database operation", "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void agregarInformacionCultivo(DatabaseOperations dbop, String nombre, String descripcion, String imagen){
        SQLiteDatabase SQ = dbop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CultivosTable.TableInfoCultivos.CULTIVO_NAME, nombre);
        cv.put(CultivosTable.TableInfoCultivos.CULTIVO_DESCRIPTION, descripcion);
        cv.put(CultivosTable.TableInfoCultivos.CULTIVO_IMAGE, imagen);
        long k = SQ.insert(CultivosTable.TableInfoCultivos.TABLE_NAME, null, cv);
        Log.d("Database Operations", "Se ha insertado una fila");
    }

    public Cursor obtenerCultivos(DatabaseOperations dbop){
        SQLiteDatabase SQ = dbop.getReadableDatabase();
        String[] columns = {CultivosTable.TableInfoCultivos.CULTIVO_NAME, CultivosTable.TableInfoCultivos.CULTIVO_DESCRIPTION, CultivosTable.TableInfoCultivos.CULTIVO_IMAGE};
        Cursor CR = SQ.query(CultivosTable.TableInfoCultivos.TABLE_NAME, columns, null, null, null, null, null);
        Log.d("Obtener cultivo", "Se han obtenido " + CR.getCount() + " cultivos");
        return CR;
    }

    public boolean dbExiste(DatabaseOperations dbop){
        SQLiteDatabase SQ = dbop.getReadableDatabase();
        String[] columns = {CultivosTable.TableInfoCultivos.CULTIVO_NAME, CultivosTable.TableInfoCultivos.CULTIVO_DESCRIPTION, CultivosTable.TableInfoCultivos.CULTIVO_IMAGE};
        Cursor CR = SQ.query(CultivosTable.TableInfoCultivos.TABLE_NAME, columns, null, null, null, null, null);
        if (CR.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void agregarComentarioTips(DatabaseOperations dbop, String autor, String comentario, String fecha, String tipo, String comentario_de_cultivo){
        SQLiteDatabase SQ = dbop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ComentariosTipsTable.TableInfoComentariosTips.AUTOR, autor);
        cv.put(ComentariosTipsTable.TableInfoComentariosTips.CONTENIDO, comentario);
        cv.put(ComentariosTipsTable.TableInfoComentariosTips.FECHA, fecha);
        cv.put(ComentariosTipsTable.TableInfoComentariosTips.TIPO, tipo);
        cv.put(ComentariosTipsTable.TableInfoComentariosTips.CULTIVO,comentario_de_cultivo);
        long k = SQ.insert(ComentariosTipsTable.TableInfoComentariosTips.TABLE_NAME, null, cv);
        Log.d("Database operations", "Se ha insertado un comentario");
    }

    public Cursor obtenerComentariosTips(DatabaseOperations dbop){
        SQLiteDatabase SQ = dbop.getReadableDatabase();
        String[] columns = {ComentariosTipsTable.TableInfoComentariosTips.AUTOR, ComentariosTipsTable.TableInfoComentariosTips.CONTENIDO, ComentariosTipsTable.TableInfoComentariosTips.FECHA, ComentariosTipsTable.TableInfoComentariosTips.TIPO, ComentariosTipsTable.TableInfoComentariosTips.CULTIVO};
        Cursor CR = SQ.query(ComentariosTipsTable.TableInfoComentariosTips.TABLE_NAME, columns, null, null, null, null, null);
        Log.d("Obtener comentario", "Se han logrado obtener " + CR.getCount() + " los comentarios");
        return CR;
    }

    public void agregarMiCultivo(DatabaseOperations dbop, String nombreCultivo){
        SQLiteDatabase SQ = dbop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MisCultivosTable.TableMisCultivosInfo.CULTIVO, nombreCultivo);
        long k = SQ.insert(MisCultivosTable.TableMisCultivosInfo.TABLE_NAME, null, cv);
        Log.d("Database operations", "Se ha insertado un nuevo cultivo a Mis cultivos");
    }

    public Cursor obtenerComentariosTipsDeMisCultivos(DatabaseOperations dbop){
        SQLiteDatabase SQ = dbop.getReadableDatabase();
        Cursor cursor = SQ.rawQuery("SELECT * FROM " + ComentariosTipsTable.TableInfoComentariosTips.TABLE_NAME + ", " + MisCultivosTable.TableMisCultivosInfo.TABLE_NAME +
                " WHERE " + ComentariosTipsTable.TableInfoComentariosTips.TABLE_NAME + "." + ComentariosTipsTable.TableInfoComentariosTips.CULTIVO + " = " + MisCultivosTable.TableMisCultivosInfo.TABLE_NAME + "." + MisCultivosTable.TableMisCultivosInfo.CULTIVO +
                " AND " + ComentariosTipsTable.TableInfoComentariosTips.TABLE_NAME + "." + ComentariosTipsTable.TableInfoComentariosTips.TIPO + " = 'c'", null);
        Log.d("CURSOR", "El cusor encontro " + cursor.getCount() + " resultados");
        return cursor;
    }
}