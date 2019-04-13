public enum Shapes {
    POLYGON("P"),
    CIRCLE("C"),
    SEMICIRCLE("S");

    private String abbreviation;

    Shapes(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static Shapes getShape(String input) {
        switch (input) {
            case "P":
                return POLYGON;
            case "C":
                return CIRCLE;
            case "S":
                return SEMICIRCLE;
            default:
                return POLYGON;
        }
    }
}
