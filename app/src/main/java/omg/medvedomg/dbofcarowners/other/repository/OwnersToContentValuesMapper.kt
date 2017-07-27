package omg.medvedomg.dbofcarowners.other.repository

import android.content.ContentValues
import omg.medvedomg.dbofcarowners.other.OWNER_NAME
import omg.medvedomg.dbofcarowners.other.models.Owner

/**
 * Created by medvedomg on 26.07.17.
 */
class OwnersToContentValuesMapper : Mapper<omg.medvedomg.dbofcarowners.other.models.Owner, ContentValues> {
    override fun map(from: Owner): ContentValues {

        var valuesOwner = ContentValues()
        valuesOwner.put(OWNER_NAME,from?.name)

        return valuesOwner
    }

}