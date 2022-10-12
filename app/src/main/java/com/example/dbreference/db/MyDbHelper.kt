package com.example.dbreference.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.dbreference.models.Buyurtma
import com.example.dbreference.models.Sotuvchi
import com.example.dbreference.models.Xaridor

class MyDbHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    MyDbInterface {

    companion object {

        const val DB_NAME = "market_db"
        const val DB_VERSION = 1


        const val SOTUVCHI_TABLE = "sotuvchi_table"
        const val SOTUVCHI_ID = "id"
        const val SOTUVCHI_NAME = "name"
        const val SOTUVCHI_NUMBER = "number"


        const val XARIDOR_TABLE = "xaridor_table"
        const val XARIDOR_ID = "id"
        const val XARIDOR_NAME = "name"
        const val XARIDOR_NUMBER = "number"
        const val XARIDOR_ADDRESS = "address"

        const val BUYURTMA_TABLE = "orders_table"
        const val BUYURTMA_ID = "id"
        const val BUYURTMA_NAME = "name"
        const val BUYURTMA_PRICE = "price"
        const val BUYURTMA_SOTUVCHI_ID = "sotuvchi_id"
        const val BUYURTMA_XARIDOR_ID = "xaridor_id"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val querySotuvchi =
            "create  table $SOTUVCHI_TABLE ($SOTUVCHI_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,$SOTUVCHI_NAME text not null ,$SOTUVCHI_NUMBER text not null);"
        val queryXaridor =
            "create  table $XARIDOR_TABLE ($XARIDOR_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,$XARIDOR_NAME text not null ,$XARIDOR_NUMBER text not null,$XARIDOR_ADDRESS text not null);"
        val queryBuyurtma =
            "create  table $BUYURTMA_TABLE ($BUYURTMA_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,$BUYURTMA_NAME text not null ,$BUYURTMA_PRICE integer not null,$BUYURTMA_SOTUVCHI_ID integer not null,$BUYURTMA_XARIDOR_ID integer not null ,foreign key ($BUYURTMA_SOTUVCHI_ID) references $SOTUVCHI_TABLE ($SOTUVCHI_ID),foreign key ($BUYURTMA_XARIDOR_ID) references $XARIDOR_TABLE ($XARIDOR_ID));"
        db?.execSQL(querySotuvchi)
        db?.execSQL(queryXaridor)
           db?.execSQL(queryBuyurtma)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    override fun addSalesman(sotuvchi: Sotuvchi) {
        var database = writableDatabase
        var contextValues = ContentValues()
        contextValues.put(SOTUVCHI_NAME, sotuvchi.name)
        contextValues.put(SOTUVCHI_NUMBER, sotuvchi.number)
        database.insert(SOTUVCHI_TABLE, null, contextValues)
        database.close()
    }

    override fun addCustomer(xaridor: Xaridor) {


        var database = writableDatabase
        var contextValues = ContentValues()
        contextValues.put(XARIDOR_NAME, xaridor.name)
        contextValues.put(XARIDOR_NUMBER, xaridor.number)
        contextValues.put(XARIDOR_ADDRESS, xaridor.address)
        database.insert(XARIDOR_TABLE, null, contextValues)
        database.close()
    }

    override fun addOlder(buyurtma: Buyurtma) {
        var database = writableDatabase
        var contextValues = ContentValues()
        contextValues.put(BUYURTMA_NAME, buyurtma.name)
        contextValues.put(BUYURTMA_PRICE, buyurtma.price)
        contextValues.put(BUYURTMA_SOTUVCHI_ID, buyurtma.sotuvchi?.id)
        contextValues.put(BUYURTMA_XARIDOR_ID, buyurtma.xaridor?.id)
        database.insert(BUYURTMA_TABLE, null, contextValues)
        database.close()

    }

    override fun getAllSalesman(): List<Sotuvchi> {
        val list = ArrayList<Sotuvchi>()
        val query = "Select * from $SOTUVCHI_TABLE"
        val database = readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {


            do {
                list.add(

                    Sotuvchi(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)


                    )


                )


            } while (cursor.moveToNext()
            )
        }
        return list


    }

    override fun getAllCustomer(): List<Xaridor> {
        val list = ArrayList<Xaridor>()
        val query = "Select * from $XARIDOR_TABLE"
        val database = readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {


            do {
                list.add(

                    Xaridor(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)




                    )


                )


            } while (cursor.moveToNext()
            )
        }
        return list



    }

    override fun getAllOrders(): List<Buyurtma> {

        val list=ArrayList<Buyurtma>()

        val database=readableDatabase
         val query="select *from $BUYURTMA_TABLE"
         val cursor=database.rawQuery(query,null)
        if (cursor.moveToFirst()){

            do {
               list.add(
                   Buyurtma(
                       cursor.getInt(0),
                       cursor.getString(1),
                       cursor.getInt(2),
                        getSalesmanById(cursor.getInt(3)),
                       getCustomerById(cursor.getInt(4))
               )
               )
            }while (
                cursor.moveToNext()
            )
        }


        return list
    }

    override fun getSalesmanById(id: Int): Sotuvchi {
         val database=readableDatabase
        val cursor=database.query(
            SOTUVCHI_TABLE,
            arrayOf(



                SOTUVCHI_ID,
                SOTUVCHI_NAME,
                SOTUVCHI_NUMBER


            ),
            "$SOTUVCHI_ID=?",
            arrayOf(id.toString()),
            null,null,null

        )

        cursor.moveToFirst()
        val sotuvchi=Sotuvchi(

            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),



        )
        return sotuvchi


    }

    override fun getCustomerById(id: Int): Xaridor {

        val database=readableDatabase
        val cursor=database.query(
            XARIDOR_TABLE,
            arrayOf(

                XARIDOR_ID,
                XARIDOR_NAME,
                XARIDOR_NUMBER,
                XARIDOR_ADDRESS


            ),
            "$XARIDOR_ID=?",
            arrayOf(id.toString()),
            null,null,null

        )

        cursor.moveToNext()
        val xaridor=Xaridor(

            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)

        )
        return xaridor



    }
}