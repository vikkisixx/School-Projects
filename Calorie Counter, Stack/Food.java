
public class Food 
{	
	String name, mealType;
	int quantity, calories;
	
	public Food(String name, int quantity, int calories, String mealType)
	{
		this.name = name;
		this.quantity = quantity;
		this.calories = calories;
		this.mealType = mealType;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getCalories()
	{
		return calories;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public String getMealType()
	{
		return mealType;
	}
	
	@Override
	public String toString()
	{
		return name + " " + quantity + " " + calories + " " + mealType + "\n";
	}
}