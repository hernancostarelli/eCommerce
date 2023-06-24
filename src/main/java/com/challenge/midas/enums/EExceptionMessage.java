package com.challenge.midas.enums;

import java.text.MessageFormat;

public enum EExceptionMessage {

    ////////////////////////////////////////////////////////////////////////////////////////////
    // GENERAL EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////

    ID_NOT_FOUND("ID NO ENCONTRADO"),
    ID_ALREADY_EXISTS("EL ID YA EXISTE"),
    PARAM_NOT_FOUND("PARÁMETRO NO ENCONTRADO"),
    EMAIL_ALREADY_EXISTS("EL EMAIL {0} YA EXISTE"),
    INCORRECT_USERNAME_OR_PASSWORD("USUARIO O CONTRASEÑA INCORRECTA"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // USER EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////

    THE_USER_NAME_CANNOT_BE_EMPTY_OR_BE_NULL("EL NOMBRE DEL CLIENTE NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_USER_SURNAME_CANNOT_BE_EMPTY_OR_BE_NULL("EL APELLIDO DEL CLIENTE NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_USER_EMAIL_CANNOT_BE_EMPTY_OR_BE_NULL("EL EMAIL DEL CLIENTE NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_USER_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL("LA CONTRASEÑA DEL CLIENTE NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_PASSWORD_CONFIRMATION_CANNOT_BE_EMPTY_OR_VOID("LA CONFIRMACIÓN DE LA CONTRASEÑA NO PUEDE ESTAR VACÍA O SER NULA"),
    THE_ENTERED_PASSWORDS_DO_NOT_MATCH("LAS CONTRASEÑAS INGRESADAS NO COINCIDEN"),
    WRONG_PASSWORD("PASSWORD INCORRECTO"),
    USER_NOT_FOUND("CLIENTE NO ENCONTRADO"),
    THE_USER_COULD_NOT_BE_ENABLED("EL CLIENTE NO PUDO SER HABILITADO"),
    THE_USER_COULD_NOT_BE_DISABLE("EL CLIENTE NO PUDO SER DESHABILITADO"),
    THE_USER_LIST_IS_EMPTY("LA LISTA DE USUARIOS ESTA VACÍA");

    private final String messageString;

    EExceptionMessage(String messageString) {
        this.messageString = messageString;
    }

    public String getMessage() {
        return messageString;
    }

    public String getMessage(String stringObject) {
        return MessageFormat.format(messageString, stringObject);
    }

    @Override
    public String toString() {
        return messageString;
    }

    public static EExceptionMessage typeOrValue(String value) {
        for (EExceptionMessage type : EExceptionMessage.class.getEnumConstants()) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("ENUM MESSAGE NOT FOUND");

    }
}