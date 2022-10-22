package com.dnlo.aiswebdnlobackend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Data
@Document
public class Localidade implements Serializable {

    @Id
    int id;

    @Field
    String icao;
}

