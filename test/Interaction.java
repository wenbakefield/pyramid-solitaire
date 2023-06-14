/**
 * An interaction with the user consists of some input to send the program and some output to
 * expect. Takes in two StringBuilders and produces the intended effects on them.
 */
interface Interaction {
  void apply(StringBuilder in, StringBuilder out);
}