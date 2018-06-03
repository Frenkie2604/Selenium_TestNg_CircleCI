package runner;

@FunctionalInterface
public interface SetUI<T> {
    void fillIn(T data);
}
