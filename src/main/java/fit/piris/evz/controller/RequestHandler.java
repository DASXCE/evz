package fit.piris.evz.controller;

public interface RequestHandler {

	Response process(Request request) throws Exception;
}
