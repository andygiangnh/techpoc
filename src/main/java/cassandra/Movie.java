package cassandra;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Movie {
	private String title;
	private int year;
	private String description;
	private String dustinRating;
	/*
	public Movie(String title, int year, String description, String dustinRating,
			String mmpaRating) {
		this.title = title;
		this.year = year;
		this.description = description;
		this.dustinRating = dustinRating;
		this.movieRow = mmpaRating;
	}
	*/
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDustinRating() {
		return dustinRating;
	}
	public void setDustinRating(String dustinRating) {
		this.dustinRating = dustinRating;
	}
	public String getMovieRow() {
		return movieRow;
	}
	public void setMovieRow(String movieRow) {
		this.movieRow = movieRow;
	}
	private String movieRow;
	@Override
	public String toString() {
		return "Movie [title=" + title + ", year=" + year + ", description="
				+ description + ", dustinRating=" + dustinRating
				+ ", movieRow=" + movieRow + "]";
	}
	
	
}
