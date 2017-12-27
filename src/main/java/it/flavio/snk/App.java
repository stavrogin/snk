package it.flavio.snk;

import java.io.IOException;

import it.flavio.snk.database.DatabaseHelper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.createDatabase();
    }
}
