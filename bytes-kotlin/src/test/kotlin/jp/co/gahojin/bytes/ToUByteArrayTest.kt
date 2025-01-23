package jp.co.gahojin.bytes

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
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

        test("toUByteArrayLe") {
            checkAll<Int> { sut ->
                val buf = UByteArray(4)
                sut.toUByteArrayLe(buf)
                buf shouldBe sut.toUByteArrayLe()
                buf.getIntLe(0) shouldBe sut
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

        test("toUByteArrayLe") {
            checkAll<UInt> { sut ->
                val buf = UByteArray(4)
                sut.toUByteArrayLe(buf)
                buf shouldBe sut.toUByteArrayLe()
                buf.getUIntLe(0) shouldBe sut
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

        test("toUByteArrayLe") {
            checkAll<Long> { sut ->
                val buf = UByteArray(8)
                sut.toUByteArrayLe(buf)
                buf shouldBe sut.toUByteArrayLe()
                buf.getLongLe(0) shouldBe sut
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

        test("toUByteArrayLe") {
            checkAll<ULong> { sut ->
                val buf = UByteArray(8)
                sut.toUByteArrayLe(buf)
                buf shouldBe sut.toUByteArrayLe()
                buf.getULongLe(0) shouldBe sut
            }
        }
    }
})
