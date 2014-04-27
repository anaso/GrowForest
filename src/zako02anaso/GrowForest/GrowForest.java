package zako02anaso.GrowForest;

import org.bukkit.plugin.java.JavaPlugin;

public class GrowForest extends JavaPlugin
{
	public void onEnable()
	{
		// コンフィグファイルが無ければファイルの作成
		this.saveDefaultConfig();

		System.out.println("GrowForest In Bukkit");

		new SaplingDespawnListener(this);
	}

	public void onDisable()
	{
	}
}
