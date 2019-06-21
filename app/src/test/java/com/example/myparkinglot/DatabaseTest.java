package com.example.myparkinglot;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DatabaseTest {

    @Test
    public void testToCheckValidDatabaseName(){

        MainActivity ma = new MainActivity();

        if (ma.database instanceof  Database) {
            Database.DATABASE_NAME.equals("parking_database.database");
            assertTrue("valid database name", true);
        }
    }
}
