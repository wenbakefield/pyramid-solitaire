/**
 * Represents the printing of a sequence of lines to output.
 */
class PrintInteraction implements Interaction {
  private final String[] lines;

  PrintInteraction(String... lines) {
    this.lines = lines;
  }

  public void apply(StringBuilder in, StringBuilder out) {
    for (String line : lines) {
      out.append(line);
    }
  }
}