package ru.itmo.lab_2.handlers;

import java.util.regex.Pattern;

public class Validator {
    private static final String NUMBER_PATTERN =
            "^([-+]?\\d+[.]\\d+|[-+]?\\d+|[-+]?\\d+([.]\\d+)?e[-+]?\\d+)$";

    private static final String NEGATIVE_NUMBER_PATTERN =
            "^(-\\d+[.]\\d+|-\\d+|-\\d+([.]\\d+)?e[-+]?\\d+)$";
    private static final String MESSAGE_ABOUT_MISSING_PARAMS =
            "Вы забыли присвоить значение какого-то аргумента(";
    private static final String MESSAGE_ABOUT_NOT_NUMBER_ARGUMENT =
            "Необходимо присвоить всем аргументам числа! (разделитель - точка)";
    private static final String MESSAGE_ABOUT_NEGATIVE_ARGUMENT =
            "R должно быть неотрицательное";
    public static void validate( String R, String X, String Y ) {
        if ( R == null || X == null || Y == null ) {
            throw new IllegalArgumentException( MESSAGE_ABOUT_MISSING_PARAMS );
        }

        if ( !(Pattern.matches(NUMBER_PATTERN, X) &&
                Pattern.matches(NUMBER_PATTERN, Y) &&
                Pattern.matches(NUMBER_PATTERN, R)) ) {
            throw new NumberFormatException( MESSAGE_ABOUT_NOT_NUMBER_ARGUMENT );
        }

        if ( Pattern.matches(NEGATIVE_NUMBER_PATTERN, R) ) {
            throw new IllegalArgumentException( MESSAGE_ABOUT_NEGATIVE_ARGUMENT );
        }
    }
}
