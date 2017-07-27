package omg.medvedomg.dbofcarowners.other.repository

import android.database.Cursor
import omg.medvedomg.dbofcarowners.other.models.Car

/**
 * Created by medvedomg on 27.07.17.
 */
class CursorToCarsMapper : Mapper<Cursor, Car>{

    override fun map(cursor: Cursor): Car {

        val car = Car("test")

        return car

    }

}