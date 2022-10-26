package ru.itmo.lab_2.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable {
    private static final List<TableRow> table = new ArrayList<>();

    public static void addTableRow(TableRow tableRow) {
        table.add(tableRow);
    }

    public static void clearTable() {
        table.clear();
    }

    public static List<TableRow> getTable() {
        return table;
    }

}
