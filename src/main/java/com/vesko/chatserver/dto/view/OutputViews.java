package com.vesko.chatserver.dto.view;

public class OutputViews {
    public interface Short {}
    public interface Detailed extends Short {}
    public interface SuperDetailed extends Detailed {}
    public interface Admin extends Detailed {}

    public interface Message extends Short {}
}
