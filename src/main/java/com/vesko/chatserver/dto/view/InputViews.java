package com.vesko.chatserver.dto.view;

public class InputViews {
    public interface General {}

    public interface New extends General {}

    public interface Exists extends General {}
    public interface Update extends Exists {}
    public interface Remove extends Exists {}
}
