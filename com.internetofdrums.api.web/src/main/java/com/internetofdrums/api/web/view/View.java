package com.internetofdrums.api.web.view;

import com.internetofdrums.api.web.Main;

abstract class View {

    public String toJson() {
        return Main.GSON.toJson(this);
    }
}
