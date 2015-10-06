package chisel_bugs

import Chisel._

object BugTestMain {
  def main(args: Array[String]) {
    val chiselArgs = args.slice(1, args.length)
    args(0) match {
      case "RightShift" =>
        chiselMain.run(chiselArgs, () => new RightShift,
          (c: RightShift) => new RightShiftTester(c))
    }
  }
}
