package com.example.presentation.util


enum class VeganTypes(val veganType:String) {
    NONE("알고 있지 않아요"),
    VEGAN("비건"),
    LACTO_VEGETARIAN("락토 베지테리언"),
    OVO_VEGETARIAN("오보 베지테리언"),
    LACTO_OVO_VEGETARIAN("락토 오보 베지테리언"),
    PASCATARIAN("페스코 베지테리언"),
    POLLOTARIAN("폴로 베지테리언"),
    FLEXITARIAN("플렉시테리언");

    companion object{
        fun getValues(): MutableList<VeganTypes> =  values().toMutableList()
    }

}
