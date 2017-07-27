package omg.medvedomg.dbofcarowners.mvp.model

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.Observable
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Looper
import omg.medvedomg.dbofcarowners.other.models.Car
import omg.medvedomg.dbofcarowners.other.models.Owner
import timber.log.Timber

/**
 * Created by medvedomg on 22.07.17.
 */

class DbHelper(context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {

        //ONWER TABLE
        public val OWNER_TABLE_NAME = "owner"
        public val OWNER_ID = "owner_id"
        public val OWNER_NAME = "owner_name"

        //CAR TABLE
        public  val CAR_TABLE_NAME = "car"
        public  val CAR_ID = "car_id"
        public  val CAR_OWNER_ID = "car_owner_id"
        public  val CAR_BRAND = "car_brand"
        public  val CAR_MODEL = "car_model"
        public  val CAR_COLOR = "car_color"
        public  val CAR_AGE = "car_age"

    override fun onCreate(db: SQLiteDatabase) {
        createTable(db)
    }

    private fun createTable(db: SQLiteDatabase) {

        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + OWNER_TABLE_NAME + "("
                            + OWNER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + OWNER_NAME + " TEXT "
                            + ")"
            )

            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + CAR_TABLE_NAME + "("
                            + CAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + CAR_OWNER_ID + " INTEGER, "
                            + CAR_BRAND + " TEXT, "
                            + CAR_MODEL + " TEXT, "
                            + CAR_COLOR + " TEXT, "
                            + CAR_AGE + " INTEGER "
                            + ")"
            )

        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + OWNER_TABLE_NAME)
        db.execSQL("DROP TABLE IF EXISTS " + CAR_TABLE_NAME)
        onCreate(db)
    }

    fun createOwner(owner: Owner?, cars: List<Car>): Long {
        val db = this.writableDatabase

        var valuesOwner = ContentValues()
        valuesOwner.put(OWNER_NAME,owner?.name)

        //insert owner
        val ownerId = db.insert(OWNER_TABLE_NAME,null,valuesOwner)

        for (item in cars) {
            createCarWithOwner(ownerId, item)
        }

        Timber.d("added owner: " + ownerId)

        db.close()

        return ownerId
    }

    fun createCarWithOwner(ownerId: Long, car: Car): Long{
        val db = this.writableDatabase

        var values = ContentValues()
        values.put(CAR_BRAND,car.brand)
        values.put(CAR_OWNER_ID,ownerId)

        //insert owner
        val carId = db.insert(CAR_TABLE_NAME,null,values)

        Timber.d("added car: " + car.brand)

        db.close()

        return carId
    }

    fun getAllOwners(): List<Owner>{
        
        var ownerList: ArrayList<Owner> = ArrayList()

        val selectAllOwnersQuery = "SELECT * FROM $OWNER_TABLE_NAME"


        val db = this.readableDatabase

        val cursor = db.rawQuery(selectAllOwnersQuery,null)

        if (cursor.moveToFirst()) {
            do{
                val ownerId = cursor.getInt(cursor.getColumnIndex(OWNER_ID))

                var carList = getOwnerCarList(ownerId)

                var owner = Owner(ownerId,cursor.getString(cursor.getColumnIndex(OWNER_NAME)),carList)

                ownerList.add(owner)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return ownerList
    }

    fun getOwnerCarList(ownerId: Int): List<Car> {

        var carList: ArrayList<Car> = ArrayList()

        val selectOwnerCarsQuery = "SELECT * FROM $CAR_TABLE_NAME " +
                "WHERE $CAR_OWNER_ID = $ownerId"

        val db = this.readableDatabase

        val cursor = db.rawQuery(selectOwnerCarsQuery,null)

        if (cursor.moveToFirst()) {
            do{
                var car = Car(cursor.getString(cursor.getColumnIndex(CAR_BRAND)))

                carList.add(car)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return carList
    }

    fun  deleteOwner(owner: Owner) {

        val db = this.writableDatabase

        db.delete(OWNER_TABLE_NAME,"$OWNER_ID = ?", arrayOf(owner.id.toString()))

        db.close()

        Timber.d("deleting success")

    }

    fun updateOwner(owner: Owner?, cars: List<Car>) {

        val db = this.writableDatabase

        val values = ContentValues()
        values.put(OWNER_NAME,owner?.name)

        db.update(OWNER_TABLE_NAME,values,"$OWNER_ID = ?", arrayOf(owner?.id.toString()))

        db.delete(CAR_TABLE_NAME,"$CAR_OWNER_ID = ?", arrayOf(owner?.id.toString()))

        for (item in cars) {
            createCarWithOwner((owner?.id)!!.toLong(), item)
        }

        db.close()

        Timber.d("editing success")
    }
}
