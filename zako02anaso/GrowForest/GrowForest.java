package zako02anaso.GrowForest;

import org.bukkit.plugin.java.JavaPlugin;

public class GrowForest extends JavaPlugin
{
	public void onEnable()
	{
		System.out.println("GrowForest In Bukkit");

		new SaplingDespawnListener(this);
	}

	public void onDisable()
	{
	}
}
