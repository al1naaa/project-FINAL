package patterns;

import communication.Request;

public interface RequestHandler {
    void setNextHandler(RequestHandler nextHandler);
    void handleRequest(Request request);
}