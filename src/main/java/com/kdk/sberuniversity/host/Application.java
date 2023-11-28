package com.kdk.sberuniversity.host;

import com.kdk.sberuniversity.host.config.Config;
import com.kdk.sberuniversity.host.config.ConfigService;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        Config config = ConfigService.getInstance();

        new AppContext().start(config.getPort());

        System.out.println("INFO :: SYSTEM ONLINE");
        System.out.println("INFO :: LISTENING LOCALHOST:" + config.getPort());
    }

}
