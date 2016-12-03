import org.scalatest.FunSuite
import BoolListHelper.BoolList

/**
  * Created by regic on 11/20/16.
  */
class TestBoolListHelper
extends FunSuite
{
    test("complete binary list to length divisible by 6")
    {
        val blist = List(1,0,1)

        assertResult(List(1,0,1,0,0,0)){
            blist.addTrailingZeros(6) }
    }

    test("complete binary list which has already length 6")
    {
        val blist = List(1,0,1,1,1,0)

        assertResult(List(1,0,1,1,1,0)){
            blist.addTrailingZeros(6) }
    }

    test("4 bits input -> complete to 6 bits -> return with byte")
    {
        val blist = List(1,1,0,1)

        assertResult(13){ blist.toByte() }
    }
}
