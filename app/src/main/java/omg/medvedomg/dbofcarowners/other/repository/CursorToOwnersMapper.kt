package omg.medvedomg.dbofcarowners.other.repository

import android.database.Cursor
import omg.medvedomg.dbofcarowners.other.OWNER_ID
import omg.medvedomg.dbofcarowners.other.OWNER_NAME
import omg.medvedomg.dbofcarowners.other.models.Owner

/**
 * Created by medvedomg on 26.07.17.
 */
class CursorToOwnersMapper : Mapper<Cursor,Owner>{

    override fun map(cursor: Cursor): Owner {

        val ownerId = cursor.getInt(cursor.getColumnIndex(OWNER_ID))

        var owner = Owner(ownerId,cursor.getString(cursor.getColumnIndex(OWNER_NAME)),null)

        return owner
    }

}