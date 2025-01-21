package jp.co.gahojin.bytes

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class UtilTest : FunSpec({
    test("Byte.shl") {
        val sut: Byte = 0xa5.toByte()
        sut shl 1 shouldBe 0x014a
        sut shl 2 shouldBe 0x0294
    }

    test("Byte.shr") {
        val sut: Byte = 0xa5.toByte()
        sut shr 1 shouldBe 0x52
        sut shr 2 shouldBe 0x29
    }

    test("Byte.and") {
        val sut: Byte = 0xa5.toByte()
        sut and 0x3f shouldBe 0x25
        sut and 0x3fL shouldBe 0x25L
    }

    test("UByte.shl") {
        val sut: UByte = 0xa5u
        sut shl 1 shouldBe 0x014au
        sut shl 2 shouldBe 0x0294u
    }

   test("UByte.shr") {
        val sut: UByte = 0xa5u
        sut shr 1 shouldBe 0x52u
        sut shr 2 shouldBe 0x29u
    }
})
