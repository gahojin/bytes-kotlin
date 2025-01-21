package jp.co.gahojin.bytes

import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.uInt

fun Arb.Companion.int24(min: Int = -8388608, max: Int = 8388607) = Arb.int(min, max)
fun Arb.Companion.uInt24(min: UInt = 0u, max: UInt = 16777215u) = Arb.uInt(min, max)
