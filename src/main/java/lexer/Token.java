package lexer;

public class Token {
    private String name;
    private String pattern;

    public Token() {
        this.name = null;
        this.pattern = null;
    }

    public Token(Token rhs) {
        this(rhs.getPattern(), rhs.getName());
    }

    public Token(String pattern) {
        this(pattern, null);
    }

    public Token(String pattern, String name) {
        this.pattern = pattern;

        if (name == null) {
            this.name = pattern;
        } else {
            this.name = name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public  String getPattern() {
        return pattern;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Token)) {
            return false;
        }

        Token rhs = (Token)obj;

        boolean nm = this.name.equals(rhs.getName());
        boolean pat = this.pattern.equals(rhs.getPattern());
        boolean eq = nm && pat;

        return eq;
    }

    @Override
    public String toString() {
        if (getName().length() > 8) {
            return getName() + "\t" + getPattern();
        } else {
            return getName() + "\t\t" + getPattern();
        }
    }

    public boolean isNumeric() {
        return getName().equals("BASE_TYPE") || getName().equals("REAL") ||
                getName().equals("NUM") || getName().equals("TRUE") ||
                getName().equals("FALSE");
    }

    public boolean isFloat() {
        return isNumeric() && getPattern().equals("float");
    }

    public boolean isInt() {
        return isNumeric() && getPattern().equals("int");
    }

    public boolean isBool() {
        return isNumeric() && getPattern().equals("bool");
    }
}
