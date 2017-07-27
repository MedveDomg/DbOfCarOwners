package omg.medvedomg.dbofcarowners.other.repository.specification

import omg.medvedomg.dbofcarowners.other.OWNER_TABLE_NAME

/**
 * Created by medvedomg on 26.07.17.
 */
class AllOwnersSpecification : SqlSpecification{
    override fun toSqlQuery(): String {
        return "SELECT * FROM $OWNER_TABLE_NAME"
    }
}