package jp.co.gahojin.bytes

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.long
import io.kotest.property.arbitrary.uInt
import io.kotest.property.arbitrary.uLong
import io.kotest.property.checkAll

class ToUByteArrayTest : FunSpec({
    context("short") {
        test("toUByteArray") {
            checkAll<Short> { sut ->
                val buf = UByteArray(2)
                sut.toUByteArray(buf)
                buf shouldBe sut.toUByteArray()
                buf.getShort(0) shouldBe sut
            }
        }

        test("toUByteArrayLe") {
            checkAll<Short> { sut ->
                val buf = UByteArray(2)
                sut.toUByteArrayLe(buf)
                buf shouldBe sut.toUByteArrayLe()
                buf.getShortLe(0) shouldBe sut
            }
        }
    }

    context("ushort") {
        test("toUByteArray") {
            checkAll<UShort> { sut ->
                val buf = UByteArray(2)
                sut.toUByteArray(buf)
                buf shouldBe sut.toUByteArray()
                buf.getUShort(0) shouldBe sut
            }
        }

        test("toUByteArrayLe") {
            checkAll<UShort> { sut ->
                val buf = UByteArray(2)
                sut.toUByteArrayLe(buf)
                buf shouldBe sut.toUByteArrayLe()
                buf.getUShortLe(0) shouldBe sut
            }
        }
    }

    context("int") {
        test("toUByteArray") {
            checkAll<Int> { sut ->
                val buf = UByteArray(4)
                sut.toUByteArray(buf)
                buf shouldBe sut.toUByteArray()
                buf.getInt(0) shouldBe sut
            }
        }

        test("toUByteArray(length)") {
            checkAll(Arb.int(Short.MIN_VALUE..Short.MAX_VALUE.toInt()), Arb.int(min = 2, max = 4)) { sut, len ->
                val buf = UByteArray(len)
                sut.toUByteArray(buf, 0, len)
                buf shouldBe sut.toUByteArray(len)
                buf.getInt(0, len) shouldBe sut
            }
        }

        test("toUByteArrayLe") {
            checkAll<Int> { sut ->
                val buf = UByteArray(4)
                sut.toUByteArrayLe(buf)
                buf shouldBe sut.toUByteArrayLe()
                buf.getIntLe(0) shouldBe sut
            }
        }

        test("toUByteArrayLe") {
            checkAll(Arb.int(Short.MIN_VALUE..Short.MAX_VALUE.toInt()), Arb.int(min = 2, max = 4)) { sut, len ->
                val buf = UByteArray(len)
                sut.toUByteArrayLe(buf, 0, len)
                buf shouldBe sut.toUByteArrayLe(len)
                buf.getIntLe(0, len) shouldBe sut
            }
        }
    }

    context("uint") {
        test("toUByteArray") {
            checkAll<UInt> { sut ->
                val buf = UByteArray(4)
                sut.toUByteArray(buf)
                buf shouldBe sut.toUByteArray()
                buf.getUInt(0) shouldBe sut
            }
        }

        test("toUByteArrayLe(length)") {
            checkAll(Arb.uInt(UShort.MIN_VALUE..UShort.MAX_VALUE), Arb.int(min = 2, max = 4)) { sut, len ->
                val buf = UByteArray(len)
                sut.toUByteArrayLe(buf, 0, len)
                buf shouldBe sut.toUByteArrayLe(len)
                buf.getUIntLe(0, len) shouldBe sut
            }
        }

        test("toUByteArray") {
            checkAll<UInt> { sut ->
                val buf = UByteArray(4)
                sut.toUByteArray(buf)
                buf shouldBe sut.toUByteArray()
                buf.getUInt(0) shouldBe sut
            }
        }

        test("toUByteArrayLe(length)") {
            checkAll(Arb.uInt(UShort.MIN_VALUE..UShort.MAX_VALUE), Arb.int(min = 2, max = 4)) { sut, len ->
                val buf = UByteArray(len)
                sut.toUByteArrayLe(buf, 0, len)
                buf shouldBe sut.toUByteArrayLe(len)
                buf.getUIntLe(0, len) shouldBe sut
            }
        }
    }

    context("long") {
        test("toUByteArray") {
            checkAll<Long> { sut ->
                val buf = UByteArray(8)
                sut.toUByteArray(buf)
                buf shouldBe sut.toUByteArray()
                buf.getLong(0) shouldBe sut
            }
        }

        test("toUByteArray(length)") {
            checkAll(Arb.long(Int.MIN_VALUE..Int.MAX_VALUE.toLong()), Arb.int(min = 4, max = 8)) { sut, len ->
                val buf = UByteArray(len)
                sut.toUByteArray(buf, 0, len)
                buf shouldBe sut.toUByteArray(len)
                buf.getLong(0, len) shouldBe sut
            }
        }

        test("toUByteArrayLe") {
            checkAll<Long> { sut ->
                val buf = UByteArray(8)
                sut.toUByteArrayLe(buf)
                buf shouldBe sut.toUByteArrayLe()
                buf.getLongLe(0) shouldBe sut
            }
        }

        test("toUByteArrayLe(length)") {
            checkAll(Arb.long(Int.MIN_VALUE..Int.MAX_VALUE.toLong()), Arb.int(min = 4, max = 8)) { sut, len ->
                val buf = UByteArray(len)
                sut.toUByteArrayLe(buf, 0, len)
                buf shouldBe sut.toUByteArrayLe(len)
                buf.getLongLe(0, len) shouldBe sut
            }
        }
    }

    context("ulong") {
        test("toUByteArray") {
            checkAll<ULong> { sut ->
                val buf = UByteArray(8)
                sut.toUByteArray(buf)
                buf shouldBe sut.toUByteArray()
                buf.getULong(0) shouldBe sut
            }
        }

        test("toUByteArray(length)") {
            checkAll(Arb.uLong(UInt.MIN_VALUE.toULong()..UInt.MAX_VALUE.toULong()), Arb.int(min = 4, max = 8)) { sut, len ->
                val buf = UByteArray(len)
                sut.toUByteArray(buf, 0, len)
                buf shouldBe sut.toUByteArray(len)
                buf.getULong(0, len) shouldBe sut
            }
        }

        test("toUByteArrayLe") {
            checkAll<ULong> { sut ->
                val buf = UByteArray(8)
                sut.toUByteArrayLe(buf)
                buf shouldBe sut.toUByteArrayLe()
                buf.getULongLe(0) shouldBe sut
            }
        }

        test("toUByteArrayLe(length)") {
            checkAll(Arb.uLong(UInt.MIN_VALUE.toULong()..UInt.MAX_VALUE.toULong()), Arb.int(min = 4, max = 8)) { sut, len ->
                val buf = UByteArray(len)
                sut.toUByteArrayLe(buf, 0, len)
                buf shouldBe sut.toUByteArrayLe(len)
                buf.getULongLe(0, len) shouldBe sut
            }
        }
    }
})
