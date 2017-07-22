package omg.medvedomg.dbofcarowners.mvp.model

/**
 * Created by medvedomg on 22.07.17.
 */
class models {
    data class Owner(
            val id: Int,
            val name: String?,
            val cars: List<Car>
    )

    data class Car(
            val id: Int,
            val brand: String?,
            val model: String?,
            val color: String?,
            val age: Int?
    )
}