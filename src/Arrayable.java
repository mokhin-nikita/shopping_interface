import java.util.*;
@FunctionalInterface
public interface Arrayable<T> {
    void toArray(List<T> list, T[] arr);
}