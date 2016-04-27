package tree;

import java.util.List;

/**
 * Created by Stephen on 4/20/2016.
 */
public interface BTree<T> {

    public Node<List<T>> getRoot();

    public int size();

    public int height();

    public void delete(T element);

    public void insert(T element);

    public void search(T element);
}
