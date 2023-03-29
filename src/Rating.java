public class Rating {
    private String rating;
    public static final String ANSI_RESET = "\u001B[0m";  // Text Reset
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public Rating(String rating, int roundRating) {
        if(roundRating > 7){
            this.rating = ANSI_GREEN + rating + ANSI_RESET;
        } else if (roundRating > 4){
            this.rating = ANSI_YELLOW + rating + ANSI_RESET;
        } else {
            this.rating = ANSI_RED + rating + ANSI_RESET;
        }
    }

    @Override
    public String toString() {
        return rating;
    }
}
