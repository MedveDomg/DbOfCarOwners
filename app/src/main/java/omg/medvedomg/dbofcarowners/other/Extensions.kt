package omg.medvedomg.labracodetestapp.other

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import omg.medvedomg.dbofcarowners.mvp.model.models

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

fun List<models.Car>.getAllNamesInOneString(): String{

    val bigString = StringBuilder()

    for (item in this) {
        bigString.append(item.brand).append(", ")
    }

    return bigString.toString()
}
