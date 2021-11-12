package com.example.velu_task.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

data class StarterModel(
    var dishName: String,
    var dishDes: String,
    var dishCount: Int,
    var dishPrice: String,
    var certificate1: Boolean,
    var certificate2: Boolean
): Parcelable{

    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(source: Parcel): this(source.readString()!!,source.readString()!!, source.readInt(),source.readString()!!,source.readBoolean(),source.readBoolean())


    override fun describeContents(): Int {
        return 0
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(this.dishName)
        dest?.writeString(this.dishDes)
        dest?.writeInt(this.dishCount)
        dest?.writeString(this.dishPrice)
        dest?.writeBoolean(this.certificate1)
        dest?.writeBoolean(this.certificate2)
    }
    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<StarterModel> = object : Parcelable.Creator<StarterModel> {
            @RequiresApi(Build.VERSION_CODES.Q)
            override fun createFromParcel(source: Parcel): StarterModel{
                return StarterModel(source)
            }

            override fun newArray(size: Int): Array<StarterModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
