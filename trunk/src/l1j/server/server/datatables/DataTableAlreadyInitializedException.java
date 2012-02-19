package l1j.server.server.datatables;

public class DataTableAlreadyInitializedException extends RuntimeException {
	private static final long serialVersionUID = 5758673687111601491L;

	public DataTableAlreadyInitializedException(Class<?> cls) {
		super(String.format("Already %s has been initialized.", cls
				.getSimpleName()));
	}
}
