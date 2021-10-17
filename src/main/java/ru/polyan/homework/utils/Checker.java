package ru.polyan.homework.utils;

import ru.polyan.homework.exceptions.ServiceError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Checker {

    public static ServiceError checkReqFields(Map<String, String> reqFields, Map<String, String> checkFlds){

        String errors = "";
        List<String> fields = new ArrayList<>();

        for(Map.Entry<String, String> reqFld : reqFields.entrySet()){
            String fldVal = checkFlds.get(reqFld.getKey());
            if(fldVal==null){
                fields.add(reqFld.getKey());
                errors = errors.concat("Expected required field '" + reqFld.getKey() + "'; ");
                continue;
            }
            if(fldVal.isBlank()){
                fields.add(reqFld.getKey());
                errors = errors.concat("Required field '" + reqFld.getValue() + "' is empty; ");
            }
        }

        ServiceError srvError = new ServiceError(errors);
        srvError.setFieldsWithError(fields);

        return srvError;
    }

}
