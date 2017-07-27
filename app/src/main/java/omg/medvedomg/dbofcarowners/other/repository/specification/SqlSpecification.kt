package omg.medvedomg.dbofcarowners.other.repository.specification

import omg.medvedomg.dbofcarowners.other.repository.specification.Specification

/**
 * Created by medvedomg on 26.07.17.
 */
interface SqlSpecification : Specification {
    fun toSqlQuery(): String
}