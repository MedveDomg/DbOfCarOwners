package omg.medvedomg.dbofcarowners.other.repository

import android.content.ContentValues
import android.database.Cursor
import android.os.Looper
import omg.medvedomg.dbofcarowners.mvp.model.DbHelper
import omg.medvedomg.dbofcarowners.other.models.Owner
import java.util.*
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import omg.medvedomg.dbofcarowners.other.CAR_BRAND
import omg.medvedomg.dbofcarowners.other.CAR_OWNER_ID
import omg.medvedomg.dbofcarowners.other.CAR_TABLE_NAME
import omg.medvedomg.dbofcarowners.other.OWNER_TABLE_NAME
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

                    database.insert(OWNER_TABLE_NAME, null, contentValues)
                }

                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
                database.close()
            }
        })

    }

    override fun update(item: Owner) : Observable<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(item: Owner) : Observable<Any>{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

}