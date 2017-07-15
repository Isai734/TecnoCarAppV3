package com.example.navi.tecnocarappv3.view;

import com.example.navi.tecnocarappv3.model.ResponseApi;

public interface PresenterViewListener {
    void showProgress(boolean show);

    void setOperationError(String response);

    void setOperationSucess(String response);
}
