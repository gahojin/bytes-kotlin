package jp.co.gahojin.bytes

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ByteArrayBinaryTest : FunSpec({
    test("inv") {
        // 反転していること
        val sut = byteArrayOf(0xff.toByte(), 0x00, 0xa5.toByte())

        // 上書きされていないこと
        sut.inv() shouldBe byteArrayOf(0x00, 0xff.toByte(), 0x5a)
        sut.inv(false) shouldBe byteArrayOf(0x00, 0xff.toByte(), 0x5a)
        sut shouldBe byteArrayOf(0xff.toByte(), 0x00, 0xa5.toByte())

        // 上書きされていること
        sut.inv(true)
        sut shouldBe byteArrayOf(0x00, 0xff.toByte(), 0x5a.toByte())
    }

    test("and") {
        val sut = byteArrayOf(0xff.toByte(), 0x00, 0xa5.toByte())
        sut and sut shouldBe sut
        sut and byteArrayOf(0xff.toByte(), 0xff.toByte(), 0xff.toByte()) shouldBe sut
        sut and byteArrayOf(0x00, 0x00, 0x00) shouldBe byteArrayOf(0x00, 0x00, 0x00)

        // 上書きされていないこと
        sut.and(sut) shouldBe sut
        sut.and(byteArrayOf(0xff.toByte(), 0xff.toByte(), 0xff.toByte()), false) shouldBe sut
        sut.and(byteArrayOf(0x00, 0x00, 0x00), false) shouldBe byteArrayOf(0x00, 0x00, 0x00)
        sut shouldBe byteArrayOf(0xff.toByte(), 0x00, 0xa5.toByte())

        // 上書きされていること
        sut.and(byteArrayOf(0xf0.toByte(), 0x0f, 0x8f.toByte()), true) shouldBe byteArrayOf(0xf0.toByte(), 0x00, 0x85.toByte())
        sut shouldBe byteArrayOf(0xf0.toByte(), 0x00, 0x85.toByte())

        // サイズ不一致
        shouldThrow<IllegalArgumentException> {
            sut and ByteArray(1)
        }
    }

    test("or") {
        val sut = byteArrayOf(0xff.toByte(), 0x00, 0xa5.toByte())
        sut or sut shouldBe sut
        sut or byteArrayOf(0xff.toByte(), 0xff.toByte(), 0xff.toByte()) shouldBe byteArrayOf(0xff.toByte(), 0xff.toByte(), 0xff.toByte())
        sut or byteArrayOf(0x00, 0x00, 0x00) shouldBe sut

        // 上書きされていないこと
        sut.or(sut) shouldBe sut
        sut.or(byteArrayOf(0xff.toByte(), 0xff.toByte(), 0xff.toByte()), false) shouldBe byteArrayOf(0xff.toByte(), 0xff.toByte(), 0xff.toByte())
        sut.or(byteArrayOf(0x00, 0x00, 0x00), false) shouldBe sut
        sut shouldBe byteArrayOf(0xff.toByte(), 0x00, 0xa5.toByte())

        // 上書きされていること
        sut.or(byteArrayOf(0x0f.toByte(), 0x0a.toByte(), 0x5a.toByte()), true) shouldBe byteArrayOf(0xff.toByte(), 0x0a.toByte(), 0xff.toByte())
        sut shouldBe byteArrayOf(0xff.toByte(), 0x0a.toByte(), 0xff.toByte())

        // サイズ不一致
        shouldThrow<IllegalArgumentException> {
            sut or ByteArray(1)
        }
    }

    test("xor") {
        val sut = byteArrayOf(0xff.toByte(), 0x00, 0xa5.toByte())
        sut xor sut shouldBe byteArrayOf(0x00, 0x00, 0x00)
        sut xor byteArrayOf(0xff.toByte(), 0xff.toByte(), 0xff.toByte()) shouldBe byteArrayOf(0x00, 0xff.toByte(), 0x5a.toByte())
        sut xor byteArrayOf(0x00, 0x00, 0x00) shouldBe sut

        // 上書きされていないこと
        sut.xor(sut) shouldBe byteArrayOf(0x00, 0x00, 0x00)
        sut.xor(byteArrayOf(0xff.toByte(), 0xff.toByte(), 0xff.toByte()), false) shouldBe byteArrayOf(0x00, 0xff.toByte(), 0x5a.toByte())
        sut.xor(byteArrayOf(0x00, 0x00, 0x00), false) shouldBe sut
        sut shouldBe byteArrayOf(0xff.toByte(), 0x00, 0xa5.toByte())

        // 上書きされていること
        sut.xor(byteArrayOf(0x0f, 0x0a, 0x55), true) shouldBe byteArrayOf(0xf0.toByte(), 0x0a, 0xf0.toByte())
        sut shouldBe byteArrayOf(0xf0.toByte(), 0x0a, 0xf0.toByte())

        // サイズ不一致
        shouldThrow<IllegalArgumentException> {
            sut xor ByteArray(1)
        }
    }

    test("shl") {
        val sut = byteArrayOf(0xff.toByte(), 0x00, 0xa5.toByte())
        sut shl 0 shouldBe sut
        sut shl 1 shouldBe byteArrayOf(0xfe.toByte(), 0x01, 0x4a)
        sut shl 2 shouldBe byteArrayOf(0xfc.toByte(), 0x02, 0x94.toByte())
        sut shl 8 shouldBe byteArrayOf(0x00, 0xa5.toByte(), 0x00)

        // 上書きされていないこと
        sut.shl(0, false) shouldBe sut
        sut.shl(1) shouldBe byteArrayOf(0xfe.toByte(), 0x01, 0x4a)
        sut.shl(1, false) shouldBe byteArrayOf(0xfe.toByte(), 0x01, 0x4a)
        sut.shl(2, false) shouldBe byteArrayOf(0xfc.toByte(), 0x02, 0x94.toByte())
        sut.shl(8, false) shouldBe byteArrayOf(0x00, 0xa5.toByte(), 0x00)
        sut shouldBe byteArrayOf(0xff.toByte(), 0x00, 0xa5.toByte())

        // 上書きされていること
        sut.shl(1, true) shouldBe byteArrayOf(0xfe.toByte(), 0x01, 0x4a)
        sut shouldBe byteArrayOf(0xfe.toByte(), 0x01, 0x4a)

    }

    test("shr") {
        val sut = byteArrayOf(0xff.toByte(), 0x00, 0xa5.toByte())
        sut shr 0 shouldBe sut
        sut shr 1 shouldBe byteArrayOf(0x7f, 0x80.toByte(), 0x52)
        sut shr 2 shouldBe byteArrayOf(0x3f, 0xc0.toByte(), 0x29)
        sut shr 8 shouldBe byteArrayOf(0x00, 0xff.toByte(), 0x00)

        // 上書きされていないこと
        sut.shr(0, false) shouldBe sut
        sut.shr(1) shouldBe byteArrayOf(0x7f, 0x80.toByte(), 0x52)
        sut.shr(1, false) shouldBe byteArrayOf(0x7f, 0x80.toByte(), 0x52)
        sut.shr(2, false) shouldBe byteArrayOf(0x3f, 0xc0.toByte(), 0x29)
        sut.shr(8, false) shouldBe byteArrayOf(0x00, 0xff.toByte(), 0x00)
        sut shouldBe byteArrayOf(0xff.toByte(), 0x00, 0xa5.toByte())

        // 上書きされていること
        sut.shr(1, true) shouldBe byteArrayOf(0x7f, 0x80.toByte(), 0x52)
        sut shouldBe byteArrayOf(0x7f, 0x80.toByte(), 0x52)
    }

    test("getBit") {
        // 1010 1111 0000 1010
        val sut = byteArrayOf(0xaf.toByte(), 0x0a)
        sut.getBit(0) shouldBe false
        sut.getBit(1) shouldBe true
        sut.getBit(4) shouldBe false
        sut.getBit(8) shouldBe true
    }

    test("getBitLe") {
        // 0000 1010 1010 1111
        val sut = byteArrayOf(0xaf.toByte(), 0x0a)
        sut.getBitLe(0) shouldBe true
        sut.getBitLe(1) shouldBe true
        sut.getBitLe(4) shouldBe false
        sut.getBitLe(8) shouldBe false
    }

    test("switchBit") {
        // 1010 1111 0000 1010
        val sut = byteArrayOf(0xaf.toByte(), 0x0a)
        // 上書きされていないこと
        sut.switchBit(0, true) shouldBe byteArrayOf(0xaf.toByte(), 0x0b)
        sut.switchBit(4, true, inPlace = false) shouldBe byteArrayOf(0xaf.toByte(), 0x1a)
        sut.switchBit(1, false) shouldBe byteArrayOf(0xaf.toByte(), 0x08)
        sut.switchBit(8, false, inPlace = false) shouldBe byteArrayOf(0xae.toByte(), 0x0a)
        sut.switchBit(0..7 step 2, true) shouldBe byteArrayOf(0xaf.toByte(), 0x5f)
        sut.switchBit(1..15 step 2, false) shouldBe byteArrayOf(0x05, 0x00)

        // 上書きされていること
        sut.switchBit(5, true, inPlace = true) shouldBe byteArrayOf(0xaf.toByte(), 0x2a)
        sut shouldBe byteArrayOf(0xaf.toByte(), 0x2a)
        sut.switchBit(1, false, inPlace = true) shouldBe byteArrayOf(0xaf.toByte(), 0x28)
        sut shouldBe byteArrayOf(0xaf.toByte(), 0x28)
        sut.switchBit(0..7 step 2, true, inPlace = true) shouldBe byteArrayOf(0xaf.toByte(), 0x7d)
        sut shouldBe byteArrayOf(0xaf.toByte(), 0x7d)
        sut.switchBit(1..15 step 2, false, inPlace = true) shouldBe byteArrayOf(0x05, 0x55)
        sut shouldBe byteArrayOf(0x05, 0x55)
    }

    test("switchBitLe") {
        // 0000 1010 1010 1111
        val sut = byteArrayOf(0xaf.toByte(), 0x0a)
        // 上書きされていないこと
        sut.switchBitLe(4, true) shouldBe byteArrayOf(0xbf.toByte(), 0x0a)
        sut.switchBitLe(8, true, inPlace = false) shouldBe byteArrayOf(0xaf.toByte(), 0x0b)
        sut.switchBitLe(0, false) shouldBe byteArrayOf(0xae.toByte(), 0x0a)
        sut.switchBitLe(2, false, inPlace = false) shouldBe byteArrayOf(0xab.toByte(), 0x0a)
        sut.switchBitLe(8..15 step 2, true) shouldBe byteArrayOf(0xaf.toByte(), 0x5f)
        sut.switchBitLe(0..7 step 2, false) shouldBe byteArrayOf(0xaa.toByte(), 0x0a)

        // 上書きされていること
        sut.switchBitLe(4, true, inPlace = true) shouldBe byteArrayOf(0xbf.toByte(), 0x0a)
        sut shouldBe byteArrayOf(0xbf.toByte(), 0x0a)
        sut.switchBitLe(0, false, inPlace = true) shouldBe byteArrayOf(0xbe.toByte(), 0x0a)
        sut shouldBe byteArrayOf(0xbe.toByte(), 0x0a)
        sut.switchBitLe(8..15 step 2, true, inPlace = true) shouldBe byteArrayOf(0xbe.toByte(), 0x5f)
        sut shouldBe byteArrayOf(0xbe.toByte(), 0x5f)
        sut.switchBitLe(0..7 step 2, false, inPlace = true) shouldBe byteArrayOf(0xaa.toByte(), 0x5f)
        sut shouldBe byteArrayOf(0xaa.toByte(), 0x5f)
    }

    test("flipBit") {
        // 1010 1111 0000 1010
        val sut = byteArrayOf(0xaf.toByte(), 0x0a)
        // 上書きされていないこと
        sut.flipBit(0) shouldBe byteArrayOf(0xaf.toByte(), 0x0b)
        sut.flipBit(1) shouldBe byteArrayOf(0xaf.toByte(), 0x08)
        sut.flipBit(0..7) shouldBe byteArrayOf(0xaf.toByte(), 0xf5.toByte())

        // 上書きされていること
        sut.flipBit(5, inPlace = true) shouldBe byteArrayOf(0xaf.toByte(), 0x2a)
        sut shouldBe byteArrayOf(0xaf.toByte(), 0x2a)
        sut.flipBit(1, inPlace = true) shouldBe byteArrayOf(0xaf.toByte(), 0x28)
        sut shouldBe byteArrayOf(0xaf.toByte(), 0x28)
        sut.flipBit(1..8, inPlace = true) shouldBe byteArrayOf(0xae.toByte(), 0xd6.toByte())
        sut shouldBe byteArrayOf(0xae.toByte(), 0xd6.toByte())
    }

    test("flipBitLe") {
        // 0000 1010 1010 1111
        val sut = byteArrayOf(0xaf.toByte(), 0x0a)
        // 上書きされていないこと
        sut.flipBitLe(0) shouldBe byteArrayOf(0xae.toByte(), 0x0a)
        sut.flipBitLe(1) shouldBe byteArrayOf(0xad.toByte(), 0x0a)
        sut.flipBitLe(0..7) shouldBe byteArrayOf(0x50, 0x0a)

        // 上書きされていること
        sut.flipBitLe(3, true) shouldBe byteArrayOf(0xa7.toByte(), 0x0a)
        sut shouldBe byteArrayOf(0xa7.toByte(), 0x0a)
        sut.flipBitLe(4, true) shouldBe byteArrayOf(0xb7.toByte(), 0x0a)
        sut shouldBe byteArrayOf(0xb7.toByte(), 0x0a)
        sut.flipBitLe(1..8, inPlace = true) shouldBe byteArrayOf(0x49, 0x0b)
        sut shouldBe byteArrayOf(0x49, 0x0b)
    }
})
