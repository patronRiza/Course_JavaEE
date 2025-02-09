package ru.prj.utils;

import java.util.HashMap;
import java.util.Iterator;

public class PersonalHashMap<K,V> extends HashMap<K,V> {

    //Индивидуальная мапа, переопределяет только метод toString()

    @Override
    public String toString() {
        Iterator<Entry<K,V>> i = entrySet().iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (;;) {
            Entry<K,V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append("\t");
            sb.append(key   == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
            if (! i.hasNext())
                return sb.append("\n}").toString();
            sb.append(',').append('\n');
        }
    }
}
