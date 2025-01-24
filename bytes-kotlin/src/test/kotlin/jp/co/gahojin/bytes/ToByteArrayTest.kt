package jp.co.gahojin.bytes

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.long
import io.kotest.property.arbitrary.uInt
import io.kotest.property.arbitrary.uLong
import io.kotest.property.checkAll

class ToByteArrayTest : FunSpec({
    context("short") {
        test("toByteArray") {
            checkAll<Short> { sut ->
                val buf = ByteArray(2)
                sut.toByteArray(buf)
                buf shouldBe sut.toByteArray()
                buf.getShort(0) shouldBe sut
            }
        }

        test("toByteArrayLe") {
            checkAll<Short> { sut ->
                val buf = ByteArray(2)
                sut.toByteArrayLe(buf)
                buf shouldBe sut.toByteArrayLe()
                buf.getShortLe(0) shouldBe sut
            }
        }
    }

    context("ushort") {
        test("toByteArray") {
            checkAll<UShort> { sut ->
                val buf = ByteArray(2)
                sut.toByteArray(buf)
                buf shouldBe sut.toByteArray()
                buf.getUShort(0) shouldBe sut
            }
        }

        test("toByteArrayLe") {
            checkAll<UShort> { sut ->
                val buf = ByteArray(2)
                sut.toByteArrayLe(buf)
                buf shouldBe sut.toByteArrayLe()
                buf.getUShortLe(0) shouldBe sut
            }
        }
    }

    context("int") {
        test("toByteArray") {
            checkAll<Int> { sut ->
                val buf = ByteArray(4)
                sut.toByteArray(buf)
                buf shouldBe sut.toByteArray()
                buf.getInt(0) shouldBe sut
            }
        }

        test("toByteArray(length)") {
            checkAll(Arb.int(Short.MIN_VALUE..Short.MAX_VALUE.toInt()), Arb.int(min = 2, max = 4)) { sut, len ->
                val buf = ByteArray(len)
                sut.toByteArray(buf, 0, len)
                buf shouldBe sut.toByteArray(len)
                buf.getInt(0, len) shouldBe sut
            }
        }

        test("toByteArrayLe") {
            checkAll<Int> { sut ->
                val buf = ByteArray(4)
                sut.toByteArrayLe(buf)
                buf shouldBe sut.toByteArrayLe()
                buf.getIntLe(0) shouldBe sut
            }
        }

        test("toByteArrayLe(length)") {
            checkAll(Arb.int(Short.MIN_VALUE..Short.MAX_VALUE.toInt()), Arb.int(min = 2, max = 4)) { sut, len ->
                val buf = ByteArray(len)
                sut.toByteArrayLe(buf, 0, len)
                buf shouldBe sut.toByteArrayLe(len)
                buf.getIntLe(0, len) shouldBe sut
            }
        }

    }

    context("uint") {
        test("toByteArray") {
            checkAll<UInt> { sut ->
                val buf = ByteArray(4)
                sut.toByteArray(buf)
                buf shouldBe sut.toByteArray()
                buf.getUInt(0) shouldBe sut
            }
        }

        test("toByteArray(length)") {
            checkAll(Arb.uInt(UShort.MIN_VALUE..UShort.MAX_VALUE), Arb.int(min = 2, max = 4)) { sut, len ->
                val buf = ByteArray(len)
                sut.toByteArray(buf, 0, len)
                buf shouldBe sut.toByteArray(len)
                buf.getUInt(0, len) shouldBe sut
            }
        }

        test("toByteArrayLe") {
            checkAll<UInt> { sut ->
                val buf = ByteArray(4)
                sut.toByteArrayLe(buf)
                buf shouldBe sut.toByteArrayLe()
                buf.getUIntLe(0) shouldBe sut
            }
        }

        test("toByteArrayLe(length)") {
            checkAll(Arb.uInt(UShort.MIN_VALUE..UShort.MAX_VALUE), Arb.int(min = 2, max = 4)) { sut, len ->
                val buf = ByteArray(len)
                sut.toByteArrayLe(buf, 0, len)
                buf shouldBe sut.toByteArrayLe(len)
                buf.getUIntLe(0, len) shouldBe sut
            }
        }
    }

    context("long") {
        test("toByteArray") {
            checkAll<Long> { sut ->
                val buf = ByteArray(8)
                sut.toByteArray(buf)
                buf shouldBe sut.toByteArray()
                buf.getLong(0) shouldBe sut
            }
        }

        test("toByteArray(length)") {
            checkAll(Arb.long(Int.MIN_VALUE..Int.MAX_VALUE.toLong()), Arb.int(min = 4, max = 8)) { sut, len ->
                val buf = ByteArray(len)
                sut.toByteArray(buf, 0, len)
                buf shouldBe sut.toByteArray(len)
                buf.getLong(0, len) shouldBe sut
            }
        }

        test("toByteArrayLe") {
            checkAll<Long> { sut ->
                val buf = ByteArray(8)
                sut.toByteArrayLe(buf)
                buf shouldBe sut.toByteArrayLe()
                buf.getLongLe(0) shouldBe sut
            }
        }

        test("toByteArrayLe(length)") {
            checkAll(Arb.long(Int.MIN_VALUE..Int.MAX_VALUE.toLong()), Arb.int(min = 4, max = 8)) { sut, len ->
                val buf = ByteArray(len)
                sut.toByteArrayLe(buf, 0, len)
                buf shouldBe sut.toByteArrayLe(len)
                buf.getLongLe(0, len) shouldBe sut
            }
        }
    }

    context("ulong") {
        test("toByteArray") {
            checkAll<ULong> { sut ->
                val buf = ByteArray(8)
                sut.toByteArray(buf)
                buf shouldBe sut.toByteArray()
                buf.getULong(0) shouldBe sut
            }
        }

        test("toByteArray(length)") {
            checkAll(Arb.uLong(UInt.MIN_VALUE.toULong()..UInt.MAX_VALUE.toULong()), Arb.int(min = 4, max = 8)) { sut, len ->
                val buf = ByteArray(len)
                sut.toByteArray(buf, 0, len)
                buf shouldBe sut.toByteArray(len)
                buf.getULong(0, len) shouldBe sut
            }
        }

        test("toByteArrayLe") {
            checkAll<ULong> { sut ->
                val buf = ByteArray(8)
                sut.toByteArrayLe(buf)
                buf shouldBe sut.toByteArrayLe()
                buf.getULongLe(0) shouldBe sut
            }
        }

        test("toByteArrayLe(length)") {
            checkAll(Arb.uLong(UInt.MIN_VALUE.toULong()..UInt.MAX_VALUE.toULong()), Arb.int(min = 4, max = 8)) { sut, len ->
                val buf = ByteArray(len)
                sut.toByteArrayLe(buf, 0, len)
                buf shouldBe sut.toByteArrayLe(len)
                buf.getULongLe(0, len) shouldBe sut
            }
        }
    }
})
