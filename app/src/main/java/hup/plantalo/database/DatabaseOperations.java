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

    public static final int database_version = 1;
    public String create_table_cultivos_query = "CREATE TABLE " + CultivosTable.TableInfoCultivos.TABLE_NAME + "(" +
            CultivosTable.TableInfoCultivos.CULTIVO_NAME + " TEXT, " +
            CultivosTable.TableInfoCultivos.CULTIVO_DESCRIPTION + " TEXT, " +
            CultivosTable.TableInfoCultivos.CULTIVO_IMAGE + " BLOB );";
    public String create_table_comentarios_query = "CREATE TABLE " + ComentariosTable.TableInfoComentarios.TABLE_NAME + "("+
            ComentariosTable.TableInfoComentarios.AUTOR_COMENTARIO + " TEXT, " +
            ComentariosTable.TableInfoComentarios.COMENTARIO + " TEXT, " +
            ComentariosTable.TableInfoComentarios.FECHA + " TEXT);";


    public DatabaseOperations(Context context) {
        super(context, CultivosTable.TableInfoCultivos.DATABASE_NAME, null, database_version);
        Log.d("Database operation", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {

        sdb.execSQL(create_table_cultivos_query);
        sdb.execSQL(create_table_comentarios_query);
        Log.d("Database operation", "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void agregarInformacionCultivo(DatabaseOperations dbop, String nombre, String descripcion, byte[] imagen){
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

    public void agregarComentario(DatabaseOperations dbop, String autor, String comentario, String fecha){
        SQLiteDatabase SQ = dbop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ComentariosTable.TableInfoComentarios.AUTOR_COMENTARIO, autor);
        cv.put(ComentariosTable.TableInfoComentarios.COMENTARIO, comentario);
        cv.put(ComentariosTable.TableInfoComentarios.FECHA, fecha);
        long k = SQ.insert(ComentariosTable.TableInfoComentarios.TABLE_NAME, null, cv);
        Log.d("Database operations", "Se ha insertado un comentario");
    }
}