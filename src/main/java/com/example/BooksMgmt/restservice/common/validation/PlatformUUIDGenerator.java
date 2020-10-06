package com.example.BooksMgmt.restservice.common.validation;

import com.fasterxml.uuid.Generators;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PlatformUUIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return get();
    }

    public static String get(){
        return Generators.
            randomBasedGenerator(ThreadLocalRandom.current())
            .generate().toString().toUpperCase().replace('-','_');
    }
}