package movieTheaterPackage;

public class Group {
	
	private String groupName; // name that group is lead by
	private int groupNum; // number of customers in this group
	
	public Group(String name, int num)
	{
		groupName = name;
		groupNum = num;
	}
	
	public String getGroupName()
	{
		return groupName;
	}
	
	public int getGroupNum()
	{
		return groupNum;
	}

} // end Group class
