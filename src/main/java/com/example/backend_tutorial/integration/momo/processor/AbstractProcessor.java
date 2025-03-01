package com.example.backend_tutorial.integration.momo.processor;

import com.example.backend_tutorial.integration.momo.config.MoMoEnvironment;
import com.example.backend_tutorial.integration.momo.config.PartnerInfo;
import com.example.backend_tutorial.integration.momo.shared.exception.MoMoException;
import com.example.backend_tutorial.integration.momo.shared.utils.Execute;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractProcessor<T, V> {
    protected PartnerInfo partnerInfo;
    protected MoMoEnvironment environment;
    protected Execute execute = new Execute();

    public AbstractProcessor(MoMoEnvironment environment) {
        this.environment = environment;
        this.partnerInfo = environment.getPartnerInfo();
    }

    public static Gson getGson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .create();
    }

    public abstract V execute(T request) throws MoMoException;
}
