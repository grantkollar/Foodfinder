package app.foodfinderapp.dto

import android.os.Parcel
import android.os.Parcelable

data class Restaurant(
    var restaurantID: String,
    var imageURL : String,
    var name:String,
    var category: String,
    var hours: String,
    var contact: String,
    var address: String,
    var ownerId: String? = null,
    var rating: Float
) : Parcelable {

    constructor() : this("", "", "", "", "", "", "", "", 0.0f)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(restaurantID)
        parcel.writeString(imageURL)
        parcel.writeString(name)
        parcel.writeString(category)
        parcel.writeString(hours)
        parcel.writeString(contact)
        parcel.writeString(address)
        parcel.writeString(ownerId)
        parcel.writeFloat(rating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Restaurant> {
        override fun createFromParcel(parcel: Parcel): Restaurant {
            return Restaurant(
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString(),
                parcel.readFloat()
            )
        }

        override fun newArray(size: Int): Array<Restaurant?> {
            return arrayOfNulls(size)
        }
    }
}
