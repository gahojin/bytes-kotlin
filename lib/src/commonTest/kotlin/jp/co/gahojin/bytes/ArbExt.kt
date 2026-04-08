package jp.co.gahojin.bytes

import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.uInt

fun Arb.Companion.int24(min: Int = -8388608, max: Int = 8388607) = Arb.int24(min..max)
fun Arb.Companion.int24(range: IntRange = -8388608..8388607) = Arb.int(range)
fun Arb.Companion.uInt24(min: UInt = 0u, max: UInt = 16777215u) = Arb.uInt24(min..max)
fun Arb.Companion.uInt24(range: UIntRange = 0u..16777215u) = Arb.uInt(range)
