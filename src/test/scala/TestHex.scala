import org.scalatest.FunSuite
import HexHelper.HexString
import HexHelper.parseIntList
import HexHelper.parseByteArray

/**
  * Created by regic on 11/20/16.
  */
class TestHex
extends FunSuite
{
    test("hex to string")
    {
        val hex = "2A"

        assertResult(List(42)){ hex.toHexArray() }
    }

    test("odd length hex to string")
    {
        val hex = "2AB"

        intercept[IllegalArgumentException]{ hex.toHexArray() }
    }

    test("longer hex to string")
    {
        val hex = "2AB4"

        assertResult(List(42, 180)){ hex.toHexArray() }
    }

    test("convert hex to base64")
    {
        //val value = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
    }

    test("int list to hex string")
    {
        val a = List(5,4,12,210)
        assertResult("05040cd2"){ parseIntList(a).toString }
    }

    test("hex to binarylist")
    {
        val hex = "2A"

        assertResult(List(0,0,1,0,1,0,1,0)){ hex.toBinaryList() }
    }

    test("longer hex to binarylist")
    {
        val hex = "FF2A"

        assertResult(List(1,1,1,1,1,1,1,1,0,0,1,0,1,0,1,0)){ hex.toBinaryList() }
    }

    test("FFFF as byte array")
    {
        val hex = "FFFF"
        val res = for ( i <- 1 to 16 ) yield 1
        assertResult(res){ hex.toBinaryList }
    }

    test("encrypt text with repeating-key XOR")
    {
        val key = "ICE"

        val input =
            "Burning 'em, if you ain't quick and nimble\n" +
            "I go crazy when I hear a cymbal"
        val expected =
            "0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272" +
            "a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f"

        println("getBytes: " + parseByteArray(".\0".getBytes()).toString)

        assertResult(expected){ parseByteArray( Xor.xorText(input, key).getBytes() ).toString }
    }
}
