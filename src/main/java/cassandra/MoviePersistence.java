package cassandra;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import java.util.Optional;

import static java.lang.System.out;

/**
 * Handles movie persistence access.
 */
public class MoviePersistence {
	private final CassandraConnector client = new CassandraConnector();

	public MoviePersistence(final String newHost, final int newPort) {
		out.println("Connecting to IP Address " + newHost + ":" + newPort
				+ "...");
		client.connect(newHost, newPort);
	}

	/**
	 * Persist provided movie information.
	 *
	 * @param title
	 *            Title of movie to be persisted.
	 * @param year
	 *            Year of movie to be persisted.
	 * @param description
	 *            Description of movie to be persisted.
	 * @param mmpaRating
	 *            MMPA rating.
	 * @param dustinRating
	 *            Dustin's rating.
	 */
	public void persistMovie(final String title, final int year,
			final String description, final String mmpaRating,
			final String dustinRating) {
		client.getSession()
				.execute(
						"INSERT INTO mykeyspace.movies (title, year, description, mmpa_rating, dustin_rating) VALUES (?, ?, ?, ?, ?)",
						title, year, description, mmpaRating, dustinRating);
	}

	/**
	 * Returns movie matching provided title and year.
	 *
	 * @param title
	 *            Title of desired movie.
	 * @param year
	 *            Year of desired movie.
	 * @return Desired movie if match is found; Optional.empty() if no match is
	 *         found.
	 */
	public Optional<Movie> queryMovieByTitle(final String title) {
		final ResultSet movieResults = client
				.getSession()
				.execute(
						"SELECT * from mykeyspace.movies WHERE title = ?", title);
		final Row movieRow = movieResults.one();
		Movie m1 = new Movie();
		m1.setDescription(movieRow.getString("description"));
		m1.setTitle(movieRow.getString("title"));
		m1.setYear(movieRow.getInt("year"));
		m1.setDustinRating(movieRow.getString("dustin_rating"));
		m1.setMovieRow(movieRow.getString("mmpa_rating"));
		
		@SuppressWarnings("unchecked")
		final Optional<Movie> movie = (Optional<Movie>) (movieRow != null ? Optional.of(m1) : Optional.empty());
		return movie;
	}

	/**
	 * Deletes the movie with the provided title and release year.
	 *
	 * @param title
	 *            Title of movie to be deleted.
	 * @param year
	 *            Year of release of movie to be deleted.
	 */
	public void deleteMovieWithTitleAndYear(final String title, final int year) {
		final String deleteString = "DELETE FROM movies_keyspace.movies WHERE title = ? and year = ?";
		client.getSession().execute(deleteString, title, year);
	}

	/**
	 * Close my underlying Cassandra connection.
	 */
	public void close() {
		client.close();
	}
}