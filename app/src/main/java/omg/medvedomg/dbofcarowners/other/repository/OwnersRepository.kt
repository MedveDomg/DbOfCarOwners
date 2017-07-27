package omg.medvedomg.dbofcarowners.other.repository

import android.content.ContentValues
import android.database.Cursor
import android.os.Looper
import omg.medvedomg.dbofcarowners.mvp.model.DbHelper
import omg.medvedomg.dbofcarowners.other.models.Owner
import java.util.*
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import omg.medvedomg.dbofcarowners.other.*
import omg.medvedomg.dbofcarowners.other.models.Car
import omg.medvedomg.dbofcarowners.other.repository.specification.Specification
import omg.medvedomg.dbofcarowners.other.repository.specification.SqlSpecification
import timber.log.Timber
import java.util.concurrent.Callable
import javax.inject.Inject


/**
 * Created by medvedomg on 26.07.17.
 */
class OwnersRepository(val dbHelper: DbHelper) : Repository<Owner>{


    val toContentValuesMapper: Mapper<Owner, ContentValues>
    val toOwnersMapper: Mapper<Cursor, Owner>
    val toCarMapper: Mapper<Cursor, Car>

    init {
        toContentValuesMapper = OwnersToContentValuesMapper()
        toOwnersMapper = CursorToOwnersMapper()
        toCarMapper = CursorToCarsMapper()
    }

    override fun add(item: Owner){
        add(Collections.singletonList(item))
    }

    override fun add(items: Iterable<Owner>) : Observable<Any>{
        return Observable.fromCallable(Callable {

            val database = dbHelper.getWritableDatabase()
            database.beginTransaction()

            try {
                for (item in items) {
                    val contentValues = toContentValuesMapper.map(item)


                    val ownerId = database.insert(OWNER_TABLE_NAME, null, contentValues)

                    for (item in item.cars!!) {
                        createCarWithOwner(ownerId, item)
                    }
                }

                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
                database.close()
            }
        })

    }

    override fun update(owner: Owner) : Observable<Any> {

        return Observable.fromCallable(Callable {
            val db = dbHelper.writableDatabase

            val values = ContentValues()
            values.put(OWNER_NAME,owner?.name)

            db.update(OWNER_TABLE_NAME,values,"$OWNER_ID = ?", arrayOf(owner?.id.toString()))

            db.delete(CAR_TABLE_NAME,"$CAR_OWNER_ID = ?", arrayOf(owner?.id.toString()))

            for (item in owner.cars!!) {
                createCarWithOwner((owner?.id)!!.toLong(), item)
            }

            db.close()
        })
    }

    override fun remove(owner: Owner) : Observable<Any>{

        return Observable.fromCallable(Callable {
            val db = dbHelper.writableDatabase

            db.delete(OWNER_TABLE_NAME,"$OWNER_ID = ?", arrayOf(owner.id.toString()))

            db.delete(CAR_TABLE_NAME,"$CAR_OWNER_ID = ?", arrayOf(owner?.id.toString()))

            db.close()

            if (Looper.myLooper() == Looper.getMainLooper()) {
                // Current Thread is Main Thread.;
                Timber.d("UI THREAD")
            } else {
                Timber.d("BACKGROUND THREAD")

            }
        })


    }

    override fun remove(specification: Specification?) : Observable<Any>{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(specification: Specification?): Observable<List<Owner>> {
        return Observable.create(ObservableOnSubscribe {
            val sqlSpecification = specification as SqlSpecification

            val database = dbHelper.getReadableDatabase()
            val owners = ArrayList<Owner>()

            try {
                val cursor = database.rawQuery(sqlSpecification.toSqlQuery(), arrayOf<String>())

                var i = 0
                val size = cursor.getCount()
                while (i < size) {
                    cursor.moveToPosition(i)

                    var owner = toOwnersMapper.map(cursor)

                    owner.cars = getOwnerCarList(owner?.id)

                    owners.add(owner)

                    i++
                }

                if (Looper.myLooper() == Looper.getMainLooper()) {
                    // Current Thread is Main Thread.;
                    Timber.d("UI THREAD")
                } else {
                    Timber.d("BACKGROUND THREAD")

                }

                cursor.close()

                it.onNext(owners)
                it.onComplete()

            } finally {

                database.close()

            } })
    }

    fun getOwnerCarList(ownerId: Int?): List<Car> {

        if (Looper.myLooper() == Looper.getMainLooper()) {
            // Current Thread is Main Thread.;
            Timber.d("UI THREAD")
        } else {
            Timber.d("BACKGROUND THREAD")

        }

        var carList: ArrayList<Car> = ArrayList()

        val selectOwnerCarsQuery = "SELECT * FROM $CAR_TABLE_NAME " +
                "WHERE $CAR_OWNER_ID = $ownerId"

        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(selectOwnerCarsQuery,null)

        if (cursor.moveToFirst()) {
            do{
                var car = Car(cursor.getString(cursor.getColumnIndex(CAR_BRAND)))

                Timber.d("$car")

                carList.add(car)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return carList
    }

    fun createCarWithOwner(ownerId: Long, car: Car): Long{
        val db = dbHelper.writableDatabase

        var values = ContentValues()
        values.put(CAR_BRAND,car.brand)
        values.put(CAR_OWNER_ID,ownerId)

        //insert owner
        val carId = db.insert(CAR_TABLE_NAME,null,values)

        Timber.d("added car: " + car.brand)

        return carId
    }
}