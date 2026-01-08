package github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.Exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String massege, String details) {}
