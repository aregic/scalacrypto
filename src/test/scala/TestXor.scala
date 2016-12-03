import org.scalatest.FunSuite
import HexHelper.HexString
import HexHelper.parseIntList

/**
  * Created by regic on 11/19/16.
  */
class TestXor
extends FunSuite
{
    test("3 long int list xor with another 3 long bitlist")
    {
        // ok this time I really couldn't find out better names
        val a = List(5,8,1)
        val b = List(9,4,5)

        assertResult( List(12,12,4) ){ Xor.xor(a,b) }
    }

    test("xor 2 hex strings")
    {
        val a = "1c0111001f010100061a024b53535009181c"
        val b = "686974207468652062756c6c277320657965"
        assertResult("746865206b696420646f6e277420706c6179"){ Xor.xorHex(a,b).toString }
    }

    test("xor against shorter seq")
    {
        val text = "fccb"
        val key  = "45"
        val res  = "b98e"

        assertResult(res){ Xor.xorHex(text,key).toString }
    }
}
