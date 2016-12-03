/**
  * Created by regic on 11/17/16.
  */

import BoolListHelper.BoolList
import HexHelper.HexString

object Base64Encode
{
    val asciiOfCapitalA = 65
    val asciiOfSmallA   = 97
    val asciiOfZero     = 48

    val base64OfSmallA  = 26
    val base64OfZero    = 52
    val base64OfPlus    = 62
    val base64OfSlash   = 63


    // only works for at most 6 long list
    def sixBitsToBase64( blist : BoolList ) : Int = {
        val oneByte = blist.toByte
        if ( oneByte < base64OfSmallA )
            oneByte + asciiOfCapitalA
        else if ( oneByte < base64OfZero )
             oneByte + asciiOfSmallA - base64OfSmallA
        else if ( oneByte < base64OfPlus )// input is a number in base64
            oneByte + asciiOfZero - base64OfZero
        else if ( oneByte == base64OfPlus )
            "+".toCharArray()(0).toInt
        else
            "/".toCharArray()(0).toInt
    }

    def addTrailingEqualSigns( s : HexString, expectedSize : Int ) : HexString =
        if ( s.length % expectedSize != 0 )
            addTrailingEqualSigns( s + "=", expectedSize )
        else
            s

    def hexstrToBase64( hex : HexString ) : String =
        addTrailingEqualSigns(
            new String(
                hex.toBinaryList().addTrailingZeros(6).grouped(6).toList.map{
                    sixBitsToBase64(_).asInstanceOf[Char]
                }.toArray
            ),
            4
        )

    def base64ToHex( base64 : String ) : HexString =

}
