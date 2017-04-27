package movieTheaterPackage;

public class Group {
	
	private String groupName; // name that group is lead by
	private int groupNum; // number of customers in this group
	private String movieToSee; //movie this group is seeing
	
	/**
	 * Constructor: puts the name of the group, the size of the group, and the movie they want to see in instance variables
	 * @param name - Name of the group leader
	 * @param num - Number of people in the group
	 * @param movieToSee - Name of the movie the group wants to see
	 */
	public Group(String name, int num, String movieToSee)
	{
		groupName = name;
		groupNum = num;
		this.movieToSee = movieToSee;
	}
	
	/**
	 * GetMovieToSee: gets the name of the movie
	 * @return movieToSee - the name of the movie the group wants to see
	 */
	public String getMovieToSee()
	{
		return movieToSee;
	}
	
	/**
	 * GetGroupName: gets the name of the group
	 * @return groupName - the name of the group leader
	 */
	public String getGroupName()
	{
		return groupName;
	}
	
	/**
	 * GetGroupNum: gets the size of the group
	 * @return groupNum - the number of people in the group
	 */
	public int getGroupNum()
	{
		return groupNum;
	}
	
	/**
	 * toString: returns group in this format: "Charizard (Party of 3) for Logan movie."
	 */
	public String toString() 
	{
		return groupName + "(Party of " + groupNum + ") for " + movieToSee + " movie." ;
	}

} // end Group class
