package chisel_bugs

import Chisel._

class RightShift extends Module {
  val W = 64

  val io = new Bundle {
    val a = SInt(INPUT, W)
    val b = UInt(INPUT, log2Up(W))
    val signed = Bool(INPUT)
    val out = SInt(OUTPUT, W)
  }

  val toshift = Cat(io.a(W - 1) & io.signed, io.a).toSInt
  io.out := toshift >> io.b
}

class RightShiftTester(c: RightShift) extends Tester(c) {
  val test = BigInt("-8000000000000000", 16)

  poke(c.io.a, test)
  poke(c.io.signed, 1)

  for (i <- 0 until 8) {
    poke(c.io.b, i)
    step(1)
    expect(c.io.out, test >> i)
  }
}
