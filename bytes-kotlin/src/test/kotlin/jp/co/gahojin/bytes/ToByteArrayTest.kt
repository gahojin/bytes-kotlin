package jp.co.gahojin.bytes

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
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

        test("toByteArrayLe") {
            checkAll<Int> { sut ->
                val buf = ByteArray(4)
                sut.toByteArrayLe(buf)
                buf shouldBe sut.toByteArrayLe()
                buf.getIntLe(0) shouldBe sut
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

        test("toByteArrayLe") {
            checkAll<UInt> { sut ->
                val buf = ByteArray(4)
                sut.toByteArrayLe(buf)
                buf shouldBe sut.toByteArrayLe()
                buf.getUIntLe(0) shouldBe sut
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

        test("toByteArrayLe") {
            checkAll<Long> { sut ->
                val buf = ByteArray(8)
                sut.toByteArrayLe(buf)
                buf shouldBe sut.toByteArrayLe()
                buf.getLongLe(0) shouldBe sut
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

        test("toByteArrayLe") {
            checkAll<ULong> { sut ->
                val buf = ByteArray(8)
                sut.toByteArrayLe(buf)
                buf shouldBe sut.toByteArrayLe()
                buf.getULongLe(0) shouldBe sut
            }
        }
    }
})
