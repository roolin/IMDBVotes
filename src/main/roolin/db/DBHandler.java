package db;

public interface DBHandler<T> {
    T getById(int id);

    long insert(T m);
}
