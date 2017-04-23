package movieTheaterPackage;

public class Group {
	
	private String groupName; // name that group is lead by
	private int groupNum; // number of customers in this group
	private String movieToSee; //movie this group is seeing
	
	public Group(String name, int num, String movieToSee)
	{
		groupName = name;
		groupNum = num;
		this.movieToSee = movieToSee;
	}
	
	public String getMovieToSee()
	{
		return movieToSee;
	}
	
	public String getGroupName()
	{
		return groupName;
	}
	
	public int getGroupNum()
	{
		return groupNum;
	}
	
	public String toString() 
	{
		return groupName + "(Party of " + groupNum + ") for " + movieToSee + " movie." ;
	}

} // end Group class