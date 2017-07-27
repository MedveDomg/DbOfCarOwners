package omg.medvedomg.dbofcarowners.other.repository

import io.reactivex.Observable
import omg.medvedomg.dbofcarowners.other.repository.specification.Specification


/**
 * Created by medvedomg on 26.07.17.
 */
interface Repository<T>{

    fun add(item: T)

    fun add(items: Iterable<T>) : Observable<Any>

    fun update(item: T) : Observable<Any>

    fun remove(item: T) : Observable<Any>

    fun remove(specification: Specification?) : Observable<Any>

    fun query(specification: Specification?): Observable<List<T>>

}