package com.symbiosis.studentservice.constants;

public interface StudentServiceConstants {
    String DEPARTMENT_SERVICE_URL_ALL= "http://localhost:8082/symbiosis/department/all";
    String DEPARTMENT_SERVICE_URL_UUID= "http://localhost:8082/symbiosis/department/single?uuid=";

    String EUREKA_DEPARTMENT_SERVICE_URL_ALL= "http://DEPARTMENT-SERVICE/symbiosis/department/all";
    String EUREKA_DEPARTMENT_SERVICE_URL_UUID= "http://DEPARTMENT-SERVICE/symbiosis/department/single?uuid=";
    String DEFAULT_PAGE = "0";
    String DEFAULT_PAGE_SIZE = "50";
    String DEFAULT_ORDER = "ASC";
    String DEFAULT_ORDER_BY = "uuid";
}
