package com.ottamotta.yunar

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Transient

@Entity
@Table(name = "ponies")
data class Pony (

    @Id
    val name: String,

    @Column(name = "available")
    val isAvailable: Boolean = false,

    @Column(name = "returned_timestamp")
    val returnedTimestamp: Long = 0,

    @Column(name = "recharge_time_mins")
    val rechargeTimeMins : Int) {

    @JsonIgnore
    fun isRecharged() : Boolean = returnedTimestamp < 0 || Instant.ofEpochMilli(returnedTimestamp)
                .plus(rechargeTimeMins.toLong(), ChronoUnit.MINUTES)
                .isBefore(Instant.ofEpochMilli(System.currentTimeMillis()))
}