package zako02anaso.GrowForest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Tree;

import java.util.Random;

public class SaplingDespawnListener implements Listener
{
	// 植える確率用乱数
	Random random = new Random(System.currentTimeMillis());

	boolean outputLog = false;
	int plantingPercent = 100;

	public SaplingDespawnListener(GrowForest growForest)
	{
		growForest.getServer().getPluginManager().registerEvents(this, growForest);

		outputLog = growForest.getConfig().getBoolean("OutputConsoleLog");
		plantingPercent = growForest.getConfig().getInt("PlantingPercent");
	}

	@EventHandler
	public void saplingDespawnEvent(ItemDespawnEvent event)
	{
		MaterialData eventItemData = event.getEntity().getItemStack().getData();

		if (eventItemData != null && eventItemData instanceof Tree)
		{
			// メタデータの取得は結局非推奨です
			byte saplingMetadata = eventItemData.getData();
			
			//Tree saplingData = (Tree)eventItemData;
			//String saplingNameString = saplingData.getSpecies().toString();
			//MaterialData setSapling = new Tree(TreeSpecies.valueOf(saplingNameString));

			// ワールドを取得
			World world = Bukkit.getServer().getWorld(event.getLocation().getWorld().getUID());

			// 苗木の1つ下のブロックを取得
			Material baseBlock = world.getBlockAt(event.getLocation().getBlockX(), event.getLocation().getBlockY()-1, event.getLocation().getBlockZ()).getType();

			// 1つ下が草か土ならば
			if(baseBlock == Material.GRASS || baseBlock == Material.DIRT)
			{
				// 今の場所にブロックが何も無いならば
				if(world.getBlockAt(event.getLocation().getBlockX(), event.getLocation().getBlockY(), event.getLocation().getBlockZ()).getType() == Material.AIR)
				{
					int randomPercent = random.nextInt(100)+1;

					if(randomPercent < plantingPercent * event.getEntity().getItemStack().getAmount()){
						// 苗木に置換する 結局メタデータの設定は非推奨のまま
						world.getBlockAt(event.getLocation()).setType(Material.SAPLING);
						world.getBlockAt(event.getLocation()).setData(saplingMetadata);

						if(outputLog){
							System.out.println("GrowForest SetSapling : " + saplingMetadata + "  StackSize : " + event.getEntity().getItemStack().getAmount() + "   : " + randomPercent + "<" + plantingPercent * event.getEntity().getItemStack().getAmount());
						} else{
						}
					}
				}
			}
			//System.out.println("ItemDespawn!  " + event.getEntity().getItemStack().getType().name() + " : " + saplingNameString);
		}
	}

	@EventHandler
	public void saplingSpawnEvent(ItemSpawnEvent event)
	{

	}
}
