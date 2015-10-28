package model;

import java.io.Serializable;


/**
 * Local class, created for stashing 2 elements together, itself stashed in other hashmap
 * @param <K>
 * @param <V>
 * @author  Artiom,Yoav
 */
public class Tuple<K,V> implements Serializable
{
    private final K key;
    private final V value;

    public Tuple(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public K getKey()
    {
        return key;
    }

    public V getValue()
    {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple<?, ?> tuple = (Tuple<?, ?>) o;

        if (key != null ? !key.equals(tuple.key) : tuple.key != null) return false;
        return !(value != null ? !value.equals(tuple.value) : tuple.value != null);

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
