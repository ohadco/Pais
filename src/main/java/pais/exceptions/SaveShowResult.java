package pais.exceptions;

import pais.models.Show;

/**
 * Created by lidan on 15/12/14.
 */
public class SaveShowResult {
    private boolean success = false;
    private String message;
    private Show show;

    public SaveShowResult(Show show, String errorMessage) {
        this.show = show;
        this.message = errorMessage;
        this.success = false;
    }

    public SaveShowResult(Show show) {
        this.show = show;
        this.success = true;
    }

    public Show getShow() {
        return show;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
