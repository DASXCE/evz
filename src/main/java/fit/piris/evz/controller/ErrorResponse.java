package fit.piris.evz.controller;

public class ErrorResponse implements Response {

	private Request originalRequest;
	private Exception originalException;
	
	public ErrorResponse(Request request, Exception exception) {
		super();
		this.originalRequest = request;
		this.originalException = exception;
	}

	public Request getOriginalRequest() {
		return originalRequest;
	}

	public Exception getOriginalException() {
		return originalException;
	}

	public String getName() {
		return "ErrorResponseTest";
	}
	
	
}
