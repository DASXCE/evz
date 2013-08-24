package fit.piris.evz.controller;

public interface Controller {

	Response processRequest(Request request);
	void addHandler(Request request, RequestHandler handler);
}
