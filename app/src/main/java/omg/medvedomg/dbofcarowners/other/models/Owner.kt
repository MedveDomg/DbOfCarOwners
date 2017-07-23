package omg.medvedomg.dbofcarowners.other.models

/**
 * Created by medvedomg on 23.07.17.
 */
data class Owner(var id: Int?,var name: String?, var cars: List<Car>?){
    var _name: String? = name
    set(value){
        field = value
    }

}