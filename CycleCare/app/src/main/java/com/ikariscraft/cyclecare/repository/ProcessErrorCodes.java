package com.ikariscraft.cyclecare.repository;

public enum ProcessErrorCodes {
    //onFailure or unhandled errors
    FATAL_ERROR ,
    //400 responses related to format
    REQUEST_FORMAT_ERROR,
    //responses 401 or 403
    AUTH_ERROR,
    //404 responses
    NOT_FOUND_ERROR,
    //500 responses
    SERVICE_NOT_AVAILABLE_ERROR
}
