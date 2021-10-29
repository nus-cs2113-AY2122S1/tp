package medbot.utilities;

public class Trio<X, Y, Z> {
    public final X first;
    public final Y second;
    public final Z third;

    public Trio(X x, Y y, Z z) {
        this.first = x;
        this.second = y;
        this.third = z;
    }
}
