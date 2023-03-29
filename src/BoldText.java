public class BoldText {
    private String text;

    public BoldText(String text) {
        this.text = "\u001b[1m" + text + "\u001b[0m";
    }

    @Override
    public String toString() {
        return text;
    }
}
