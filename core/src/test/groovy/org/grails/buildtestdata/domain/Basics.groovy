package org.grails.buildtestdata.domain

import grails.gorm.annotation.Entity
import org.grails.buildtestdata.domain.toone.Enum

import java.sql.Time
import java.sql.Timestamp
import java.time.*

@Entity
class Basics {

    Enum anEnum

    Integer integer
    Long aLong
    Short aShort
    Byte aByte
    Double aDouble
    Float aFloat
    BigInteger bigInteger
    BigDecimal bigDecimal
    String string
    Boolean aBoolean
    Character character
    UUID uuid
    URL url
    URI uri
    Date date
    Byte[] bytes
    Character[] characters
    Time time
    java.sql.Date sqlDate
    Timestamp timestamp
    Currency currency
    TimeZone timeZone

    LocalDate localDate
    LocalDateTime localDateTime
    LocalTime localTime
    OffsetDateTime offsetDateTime
    OffsetTime offsetTime
    ZonedDateTime zonedDateTime
    Instant instant

    char[] charsPrimitive
    byte[] bytesPrimitive
    int intPrimitive
    long longPrimitive
    short shortPrimitive
    byte bytePrimitive
    double doublePrimitive
    float floatPrimitive
    char charPrimitive
    boolean booleanPrimitive
}
