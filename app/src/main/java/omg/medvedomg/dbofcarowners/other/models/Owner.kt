package omg.medvedomg.dbofcarowners.other.models

/**
 * Created by medvedomg on 23.07.17.
 */
data class Owner(var name: String?, var cars: List<Car>?){

    constructor(id: Int,
                name: String?,
                cars: List<Car>?) : this(name,cars) {

    }

//    constructor(name: String?,
//                cars: List<Car>?) : this(name) {
//
//    }
}