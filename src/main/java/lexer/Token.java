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

        return this.name.equals(rhs.getName()) && this.pattern.equals(rhs.getPattern());
    }
}
