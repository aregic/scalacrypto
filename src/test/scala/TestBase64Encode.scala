import org.scalatest.FunSuite

/**
  * Created by regic on 11/17/16.
  */
class TestBase64Encode
extends FunSuite
{
    test("one hex byte to base64")
    {
        val hex = "2A"

        assertResult("Kg=="){ Base64Encode.hexstrToBase64(hex) }
    }

    test("one hex byte to base64 2")
    {
        val hex = "49"

        assertResult("SQ=="){ Base64Encode.hexstrToBase64(hex) }
    }

    test("6 bits to base64 byte without added padding")
    {
        assertResult("q".toCharArray()(0).toInt){ Base64Encode.sixBitsToBase64( List(1,0,1,0,1,0) ) }
    }

    test("bit longer hex to base64 ")
    {
        val hex = "49A2FD"
        assertResult("SaL9"){ Base64Encode.hexstrToBase64(hex) }
    }

    test("FFFFFFAA to base64 ")
    {
        val hex = "FFFFFFAA"
        assertResult("////qg=="){ Base64Encode.hexstrToBase64(hex) }
    }

    test("man as hex to base64 ")
    {
        val hex = "4D616E"
        assertResult("TWFu"){ Base64Encode.hexstrToBase64(hex) }
    }

    test("even longer hex to base64 ")
    {
        val hex = "a4bb2f62"
        assertResult("pLsvYg=="){ Base64Encode.hexstrToBase64(hex) }
    }

    test("a longer input with one padding")
    {
        val input = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
        val expected = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"
        assertResult(expected){
            Base64Encode.hexstrToBase64(input)
        }
    }
}
