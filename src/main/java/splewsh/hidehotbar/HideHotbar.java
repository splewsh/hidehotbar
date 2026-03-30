package splewsh.hidehotbar;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HideHotbar implements ModInitializer {
	public static final String MOD_ID = "hidehotbar";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("HideHotbar initialized.");
	}
}