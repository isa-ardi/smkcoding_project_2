package com.isa.project2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profil")
data class ProfilModel(
    var name: String,
    var email: String,
    var telp: String,
    var alamat: String,
    @PrimaryKey var key: String
){
    constructor() : this("","","","",""
    )
}