package omg.medvedomg.dbofcarowners.other.models

/**
 * Created by medvedomg on 23.07.17.
 */
data class Car(val brand:String?){
    constructor(id: Int,
                brand: String?,
                model: String?,
                color: String?,
                age: Int?) : this(brand)
}