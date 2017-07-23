package omg.medvedomg.labracodetestapp.other

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import omg.medvedomg.dbofcarowners.other.models.Car

/**
 * Created by medvedomg on 04.07.17.
 */

//extension for easier inflating in viewholder
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun String.addHas(): String{
    return this.format("%s has:",this).toString()
}

fun List<Car>.getAllNamesInOneString(): String{

    var bigString = StringBuilder()

    for (i in this.indices) {
        if (!TextUtils.isEmpty(this.get(i).brand)) {
                bigString.append(this.get(i).brand).append(", ")
        }
    }

    return bigString.substring(0,bigString.length - 2).toString()
}
