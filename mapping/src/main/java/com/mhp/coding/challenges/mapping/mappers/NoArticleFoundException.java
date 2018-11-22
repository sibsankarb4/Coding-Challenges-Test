package com.mhp.coding.challenges.mapping.mappers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No Article found with this Article Number.")
public class NoArticleFoundException extends RuntimeException{

    private static final long serialVersionUID =3935230281455340039L;
}
