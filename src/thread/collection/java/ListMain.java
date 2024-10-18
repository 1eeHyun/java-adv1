package thread.collection.java;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListMain {

    public static void main(String[] args) {

        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("list = " + list);

        Set<Object> skipSet = new ConcurrentSkipListSet<>();
        skipSet.add(3);
        skipSet.add(2);
        skipSet.add(1);
        System.out.println("skipSet = " + skipSet);
    }
}
