package entity;

public class Cereal {
	
	private String name;
	private int rank;
	
	/*
	 * Default Constructor
	 */
	public Cereal() {
		this.name = "Default";
		this.rank = 0;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getRank()
	{
		return this.rank;
	}
	
	public void setRank(int rank)
	{
		this.rank = rank;
	}
}
