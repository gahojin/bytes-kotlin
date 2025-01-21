package jp.co.gahojin.bytes

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows

class UByteArrayBinaryTest : FunSpec({
    test("inv") {
        // 反転していること
        val sut = ubyteArrayOf(0xffu, 0x00u, 0xa5u)

        // 上書きされていないこと
        sut.inv() shouldBe ubyteArrayOf(0x00u, 0xffu, 0x5au)
        sut.inv(false) shouldBe ubyteArrayOf(0x00u, 0xffu, 0x5au)
        sut shouldBe ubyteArrayOf(0xffu, 0x00u, 0xa5u)

        // 上書きされていること
        sut.inv(true)
        sut shouldBe ubyteArrayOf(0x00u, 0xffu, 0x5au)
    }

    test("and") {
        val sut = ubyteArrayOf(0xffu, 0x00u, 0xa5u)
        sut and sut shouldBe sut
        sut and ubyteArrayOf(0xffu, 0xffu, 0xffu) shouldBe sut
        sut and ubyteArrayOf(0x00u, 0x00u, 0x00u) shouldBe ubyteArrayOf(0x00u, 0x00u, 0x00u)

        // 上書きされていないこと
        sut.and(sut) shouldBe sut
        sut.and(ubyteArrayOf(0xffu, 0xffu, 0xffu), false) shouldBe sut
        sut.and(ubyteArrayOf(0x00u, 0x00u, 0x00u), false) shouldBe ubyteArrayOf(0x00u, 0x00u, 0x00u)
        sut shouldBe ubyteArrayOf(0xffu, 0x00u, 0xa5u)

        // 上書きされていること
        sut.and(ubyteArrayOf(0xf0u, 0x0fu, 0x8fu), true) shouldBe ubyteArrayOf(0xf0u, 0x00u, 0x85u)
        sut shouldBe ubyteArrayOf(0xf0u, 0x00u, 0x85u)

        // サイズ不一致
        assertThrows<IllegalArgumentException> {
            sut and UByteArray(1)
        }
    }

    test("or") {
        val sut = ubyteArrayOf(0xffu, 0x00u, 0xa5u)
        sut or sut shouldBe sut
        sut or ubyteArrayOf(0xffu, 0xffu, 0xffu) shouldBe ubyteArrayOf(0xffu, 0xffu, 0xffu)
        sut or ubyteArrayOf(0x00u, 0x00u, 0x00u) shouldBe sut

        // 上書きされていないこと
        sut.or(sut) shouldBe sut
        sut.or(ubyteArrayOf(0xffu, 0xffu, 0xffu), false) shouldBe ubyteArrayOf(0xffu, 0xffu, 0xffu)
        sut.or(ubyteArrayOf(0x00u, 0x00u, 0x00u), false) shouldBe sut
        sut shouldBe ubyteArrayOf(0xffu, 0x00u, 0xa5u)

        // 上書きされていること
        sut.or(ubyteArrayOf(0x0fu, 0x0au, 0x5au), true) shouldBe ubyteArrayOf(0xffu, 0x0au, 0xffu)
        sut shouldBe ubyteArrayOf(0xffu, 0x0au, 0xffu)

        // サイズ不一致
        assertThrows<IllegalArgumentException> {
            sut or UByteArray(1)
        }
    }

    test("xor") {
        val sut = ubyteArrayOf(0xffu, 0x00u, 0xa5u)
        sut xor sut shouldBe ubyteArrayOf(0x00u, 0x00u, 0x00u)
        sut xor ubyteArrayOf(0xffu, 0xffu, 0xffu) shouldBe ubyteArrayOf(0x00u, 0xffu, 0x5au)
        sut xor ubyteArrayOf(0x00u, 0x00u, 0x00u) shouldBe sut

        // 上書きされていないこと
        sut.xor(sut) shouldBe ubyteArrayOf(0x00u, 0x00u, 0x00u)
        sut.xor(ubyteArrayOf(0xffu, 0xffu, 0xffu), false) shouldBe ubyteArrayOf(0x00u, 0xffu, 0x5au)
        sut.xor(ubyteArrayOf(0x00u, 0x00u, 0x00u), false) shouldBe sut
        sut shouldBe ubyteArrayOf(0xffu, 0x00u, 0xa5u)

        // 上書きされていること
        sut.xor(ubyteArrayOf(0x0fu, 0x0au, 0x55u), true) shouldBe ubyteArrayOf(0xf0u, 0x0au, 0xf0u)
        sut shouldBe ubyteArrayOf(0xf0u, 0x0au, 0xf0u)

        // サイズ不一致
        assertThrows<IllegalArgumentException> {
            sut xor UByteArray(1)
        }
    }

    test("shl") {
        val sut = ubyteArrayOf(0xffu, 0x00u, 0xa5u)
        sut shl 0 shouldBe sut
        sut shl 1 shouldBe ubyteArrayOf(0xfeu, 0x01u, 0x4au)
        sut shl 2 shouldBe ubyteArrayOf(0xfcu, 0x02u, 0x94u)
        sut shl 8 shouldBe ubyteArrayOf(0x00u, 0xa5u, 0x00u)

        // 上書きされていないこと
        sut.shl(0, false) shouldBe sut
        sut.shl(1) shouldBe ubyteArrayOf(0xfeu, 0x01u, 0x4au)
        sut.shl(1, false) shouldBe ubyteArrayOf(0xfeu, 0x01u, 0x4au)
        sut.shl(2, false) shouldBe ubyteArrayOf(0xfcu, 0x02u, 0x94u)
        sut.shl(8, false) shouldBe ubyteArrayOf(0x00u, 0xa5u, 0x00u)
        sut shouldBe ubyteArrayOf(0xffu, 0x00u, 0xa5u)

        // 上書きされていること
        sut.shl(1, true) shouldBe ubyteArrayOf(0xfeu, 0x01u, 0x4au)
        sut shouldBe ubyteArrayOf(0xfeu, 0x01u, 0x4au)

    }

    test("shr") {
        val sut = ubyteArrayOf(0xffu, 0x00u, 0xa5u)
        sut shr 0 shouldBe sut
        sut shr 1 shouldBe ubyteArrayOf(0x7fu, 0x80u, 0x52u)
        sut shr 2 shouldBe ubyteArrayOf(0x3fu, 0xc0u, 0x29u)
        sut shr 8 shouldBe ubyteArrayOf(0x00u, 0xffu, 0x00u)

        // 上書きされていないこと
        sut.shr(0, false) shouldBe sut
        sut.shr(1) shouldBe ubyteArrayOf(0x7fu, 0x80u, 0x52u)
        sut.shr(1, false) shouldBe ubyteArrayOf(0x7fu, 0x80u, 0x52u)
        sut.shr(2, false) shouldBe ubyteArrayOf(0x3fu, 0xc0u, 0x29u)
        sut.shr(8, false) shouldBe ubyteArrayOf(0x00u, 0xffu, 0x00u)
        sut shouldBe ubyteArrayOf(0xffu, 0x00u, 0xa5u)

        // 上書きされていること
        sut.shr(1, true) shouldBe ubyteArrayOf(0x7fu, 0x80u, 0x52u)
        sut shouldBe ubyteArrayOf(0x7fu, 0x80u, 0x52u)
    }

    test("getBit") {
        // 1010 1111 0000 1010
        val sut = ubyteArrayOf(0xafu, 0x0au)
        sut.getBit(0) shouldBe false
        sut.getBit(1) shouldBe true
        sut.getBit(4) shouldBe false
        sut.getBit(8) shouldBe true
    }

    test("getBitLe") {
        // 0000 1010 1010 1111
        val sut = ubyteArrayOf(0xafu, 0x0au)
        sut.getBitLe(0) shouldBe true
        sut.getBitLe(1) shouldBe true
        sut.getBitLe(4) shouldBe false
        sut.getBitLe(8) shouldBe false
    }

    test("switchBit") {
        // 1010 1111 0000 1010
        val sut = ubyteArrayOf(0xafu, 0x0au)
        // 上書きされていないこと
        sut.switchBit(0, true) shouldBe ubyteArrayOf(0xafu, 0x0bu)
        sut.switchBit(4, true, inPlace = false) shouldBe ubyteArrayOf(0xafu, 0x1au)
        sut.switchBit(1, false) shouldBe ubyteArrayOf(0xafu, 0x08u)
        sut.switchBit(8, false, inPlace = false) shouldBe ubyteArrayOf(0xaeu, 0x0au)
        sut.switchBit(0..7 step 2, true) shouldBe ubyteArrayOf(0xafu, 0x5fu)
        sut.switchBit(1..15 step 2, false) shouldBe ubyteArrayOf(0x05u, 0x00u)

        // 上書きされていること
        sut.switchBit(5, true, inPlace = true) shouldBe ubyteArrayOf(0xafu, 0x2au)
        sut shouldBe ubyteArrayOf(0xafu, 0x2au)
        sut.switchBit(1, false, inPlace = true) shouldBe ubyteArrayOf(0xafu, 0x28u)
        sut shouldBe ubyteArrayOf(0xafu, 0x28u)
        sut.switchBit(0..7 step 2, true, inPlace = true) shouldBe ubyteArrayOf(0xafu, 0x7du)
        sut shouldBe ubyteArrayOf(0xafu, 0x7du)
        sut.switchBit(1..15 step 2, false, inPlace = true) shouldBe ubyteArrayOf(0x05u, 0x55u)
        sut shouldBe ubyteArrayOf(0x05u, 0x55u)
    }

    test("switchBitLe") {
        // 0000 1010 1010 1111
        val sut = ubyteArrayOf(0xafu, 0x0au)
        // 上書きされていないこと
        sut.switchBitLe(4, true) shouldBe ubyteArrayOf(0xbfu, 0x0au)
        sut.switchBitLe(8, true, inPlace = false) shouldBe ubyteArrayOf(0xafu, 0x0bu)
        sut.switchBitLe(0, false) shouldBe ubyteArrayOf(0xaeu, 0x0au)
        sut.switchBitLe(2, false, inPlace = false) shouldBe ubyteArrayOf(0xabu, 0x0au)
        sut.switchBitLe(8..15 step 2, true) shouldBe ubyteArrayOf(0xafu, 0x5fu)
        sut.switchBitLe(0..7 step 2, false) shouldBe ubyteArrayOf(0xaau, 0x0au)

        // 上書きされていること
        sut.switchBitLe(4, true, inPlace = true) shouldBe ubyteArrayOf(0xbfu, 0x0au)
        sut shouldBe ubyteArrayOf(0xbfu, 0x0au)
        sut.switchBitLe(0, false, inPlace = true) shouldBe ubyteArrayOf(0xbeu, 0x0au)
        sut shouldBe ubyteArrayOf(0xbeu, 0x0au)
        sut.switchBitLe(8..15 step 2, true, inPlace = true) shouldBe ubyteArrayOf(0xbeu, 0x5fu)
        sut shouldBe ubyteArrayOf(0xbeu, 0x5fu)
        sut.switchBitLe(0..7 step 2, false, inPlace = true) shouldBe ubyteArrayOf(0xaau, 0x5fu)
        sut shouldBe ubyteArrayOf(0xaau, 0x5fu)
    }

    test("flipBit") {
        // 1010 1111 0000 1010
        val sut = ubyteArrayOf(0xafu, 0x0au)
        // 上書きされていないこと
        sut.flipBit(0) shouldBe ubyteArrayOf(0xafu, 0x0bu)
        sut.flipBit(1) shouldBe ubyteArrayOf(0xafu, 0x08u)
        sut.flipBit(0..7) shouldBe ubyteArrayOf(0xafu, 0xf5u)

        // 上書きされていること
        sut.flipBit(5, inPlace = true) shouldBe ubyteArrayOf(0xafu, 0x2au)
        sut shouldBe ubyteArrayOf(0xafu, 0x2au)
        sut.flipBit(1, inPlace = true) shouldBe ubyteArrayOf(0xafu, 0x28u)
        sut shouldBe ubyteArrayOf(0xafu, 0x28u)
        sut.flipBit(1..8, inPlace = true) shouldBe ubyteArrayOf(0xaeu, 0xd6u)
        sut shouldBe ubyteArrayOf(0xaeu, 0xd6u)
    }

    test("flipBitLe") {
        // 0000 1010 1010 1111
        val sut = ubyteArrayOf(0xafu, 0x0au)
        // 上書きされていないこと
        sut.flipBitLe(0) shouldBe ubyteArrayOf(0xaeu, 0x0au)
        sut.flipBitLe(1) shouldBe ubyteArrayOf(0xadu, 0x0au)
        sut.flipBitLe(0..7) shouldBe ubyteArrayOf(0x50u, 0x0au)

        // 上書きされていること
        sut.flipBitLe(3, true) shouldBe ubyteArrayOf(0xa7u, 0x0au)
        sut shouldBe ubyteArrayOf(0xa7u, 0x0au)
        sut.flipBitLe(4, true) shouldBe ubyteArrayOf(0xb7u, 0x0au)
        sut shouldBe ubyteArrayOf(0xb7u, 0x0au) // 0000 1010 1011 0111
        sut.flipBitLe(1..8, inPlace = true) shouldBe ubyteArrayOf(0x49u, 0x0bu)
        sut shouldBe ubyteArrayOf(0x49u, 0x0bu)
    }
})
