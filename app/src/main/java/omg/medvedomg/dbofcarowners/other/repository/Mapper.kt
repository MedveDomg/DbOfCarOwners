package omg.medvedomg.dbofcarowners.other.repository

/**
 * Created by medvedomg on 26.07.17.
 */
interface Mapper<From, To> {
    fun map(from: From): To
}