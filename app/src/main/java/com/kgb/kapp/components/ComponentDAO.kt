package com.kgb.kapp.components

import android.os.Parcel
import android.os.Parcelable

class ComponentDAO(val challengeType: ChallengeType, val step: Int, val stepProgress: StepProgress) : Parcelable {


    companion object CREATOR : Parcelable.Creator<ComponentDAO> {
        const val MAX_LEVEL = 10
        override fun createFromParcel(parcel: Parcel): ComponentDAO {
            return ComponentDAO(parcel)
        }

        override fun newArray(size: Int): Array<ComponentDAO?> {
            return arrayOfNulls(size)
        }
    }

    var finished: Boolean = false

    var goal: Int = 40
    var series: Int = 1

    constructor(parcel: Parcel) : this(
        ChallengeType.valueOf(parcel.readString()),
        parcel.readInt(),
        StepProgress.valueOf(parcel.readString())) {
        finished = parcel.readString()?.toBoolean() ?: false
        goal = parcel.readInt()
        series = parcel.readInt()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
       dest?.let {
           it.writeString(challengeType.toString())
           it.writeInt(step)
           it.writeString(challengeType.toString())
           it.writeString(finished.toString())
           it.writeInt(goal)
           it.writeInt(series)
       }
    }

    override fun describeContents(): Int {
        return 0
    }
}